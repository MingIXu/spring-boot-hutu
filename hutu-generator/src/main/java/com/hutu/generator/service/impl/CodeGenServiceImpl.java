package com.hutu.generator.service.impl;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.hutu.generator.form.GenConfig;
import com.hutu.generator.form.TableRequest;
import com.hutu.generator.service.CodeGenService;

import java.util.Arrays;
import java.util.List;

public class CodeGenServiceImpl implements CodeGenService {
    @Override
    public byte[] generatorCode(GenConfig genConfig) {
        return new byte[0];
    }
    private final static String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String LOGIC_DELETE_NAME = "isDeleted";
    private final static String[] TABLE_PREFIX = {"t_"};
    // 需要自动填充的字段
    private final static List<TableFill> TABLE_FILL_LIST = Arrays.asList(
            new TableFill("createTime", FieldFill.INSERT),
            new TableFill("createId", FieldFill.INSERT),
            new TableFill("createName", FieldFill.INSERT),
            new TableFill("orgId", FieldFill.INSERT),
            new TableFill("updateTime", FieldFill.INSERT_UPDATE),
            new TableFill("updateId", FieldFill.INSERT_UPDATE),
            new TableFill("updateName", FieldFill.INSERT_UPDATE));

    @Override
    public List listTables(TableRequest request) {
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(request.getUrl());
        // dsc.setSchemaName("public");
        dsc.setDriverName(DB_DRIVER);
        dsc.setUsername(request.getUsername());
        dsc.setPassword(request.getPassword());

        StrategyConfig sc = new StrategyConfig().setCapitalMode(true)
                .setEntityLombokModel(true)
                .setLogicDeleteFieldName(LOGIC_DELETE_NAME)
                .setTableFillList(TABLE_FILL_LIST)
                .setColumnNaming(NamingStrategy.no_change)
                .setTablePrefix(TABLE_PREFIX)
                .setNaming(NamingStrategy.underline_to_camel);
        ConfigBuilder configBuilder = new ConfigBuilder(null, dsc, sc, null, null);
        List<TableInfo> tableInfoList = configBuilder.getTableInfoList();
        System.out.println();
        return null;
    }
}
