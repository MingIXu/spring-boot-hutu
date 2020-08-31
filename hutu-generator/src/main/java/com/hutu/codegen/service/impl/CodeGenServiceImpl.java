package com.hutu.codegen.service.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.hutu.codegen.common.PageResult;
import com.hutu.codegen.entity.GenConfig;
import com.hutu.codegen.entity.TableRequest;
import com.hutu.codegen.utils.CodeGenUtil;
import com.hutu.codegen.utils.DbUtil;
import com.hutu.codegen.service.CodeGenService;
import com.hutu.codegen.vo.TableInfoVO;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.zip.ZipOutputStream;

/**
 * <p>
 * 代码生成器
 * </p>
 *
 * @package: com.xkcoding.codegen.service.impl
 * @description: 代码生成器
 * @author: yangkai.shen
 * @date: Created in 2019-03-22 10:15
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Service
@AllArgsConstructor
public class CodeGenServiceImpl implements CodeGenService {
    private final String TABLE_SQL_TEMPLATE = "select table_name tableName, engine, table_comment tableComment, create_time createTime from information_schema.tables where table_schema = (select database()) %s order by create_time desc";

    private final String COLUMN_SQL_TEMPLATE = "select column_name columnName, data_type dataType, column_comment columnComment, column_key columnKey, extra from information_schema.columns where table_name = ? and table_schema = (select database()) order by ordinal_position";

    private final String COUNT_SQL_TEMPLATE = "select count(1) from (%s)tmp";

    private final String PAGE_SQL_TEMPLATE = " limit ?,?";

    /**
     * 分页查询表信息
     *
     * @param request 请求参数
     * @return 表名分页信息
     */
    @Override
    @SneakyThrows
    public PageResult<TableInfoVO> listTables(TableRequest request) {
        HikariDataSource dataSource = DbUtil.buildFromTableRequest(request);
        Db db = new Db(dataSource);


        int start = request.getCurrentPage()-1;
        int pageSize = request.getPageSize();

        String paramSql = StrUtil.EMPTY;
        if (StrUtil.isNotBlank(request.getTableName())) {
            paramSql = "and table_name like concat('%', ?, '%')";
        }
        String sql = String.format(TABLE_SQL_TEMPLATE, paramSql);
        String countSql = String.format(COUNT_SQL_TEMPLATE, sql);

        List<TableInfoVO> query;
        BigDecimal count;
        if (StrUtil.isNotBlank(request.getTableName())) {
            query = db.query(sql + PAGE_SQL_TEMPLATE,TableInfoVO.class, request.getTableName(), start, pageSize);
            count = (BigDecimal) db.queryNumber(countSql, request.getTableName());
        } else {
            query = db.query(sql + PAGE_SQL_TEMPLATE,TableInfoVO.class, start, pageSize);
            count = (BigDecimal) db.queryNumber(countSql);
        }
        PageResult<TableInfoVO> pageResult = new PageResult<>(count.longValue(), request.getCurrentPage(), request.getPageSize(), query);

        dataSource.close();
        return pageResult;
    }

    /**
     * 生成代码
     *
     * @param genConfig 生成配置
     * @return 代码压缩文件
     */
    @Override
    public byte[] generatorCode(GenConfig genConfig) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        //查询表信息
        Entity table = queryTable(genConfig.getRequest());
        //查询列信息
        List<Entity> columns = queryColumns(genConfig.getRequest());
        //生成代码
        CodeGenUtil.generatorCode(genConfig, table, columns, zip);
        IoUtil.close(zip);
        return outputStream.toByteArray();
    }

    @SneakyThrows
    private Entity queryTable(TableRequest request) {
        HikariDataSource dataSource = DbUtil.buildFromTableRequest(request);
        Db db = new Db(dataSource);

        String paramSql = StrUtil.EMPTY;
        if (StrUtil.isNotBlank(request.getTableName())) {
            paramSql = "and table_name = ?";
        }
        String sql = String.format(TABLE_SQL_TEMPLATE, paramSql);
        Entity entity = db.queryOne(sql, request.getTableName());

        dataSource.close();
        return entity;
    }

    @SneakyThrows
    private List<Entity> queryColumns(TableRequest request) {
        HikariDataSource dataSource = DbUtil.buildFromTableRequest(request);
        Db db = new Db(dataSource);

        List<Entity> query = db.query(COLUMN_SQL_TEMPLATE, request.getTableName());

        dataSource.close();
        return query;
    }

}