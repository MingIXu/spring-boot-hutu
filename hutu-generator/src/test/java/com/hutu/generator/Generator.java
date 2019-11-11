package com.hutu.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author hutu
 * @Desc 代码生成
 * @Date 2018/2/3 10:59
 */
public class Generator {
    private final static DbType DB_TYPE = DbType.MYSQL;
    private final static String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String DB_URL = "jdbc:mysql://101.132.129.130:3306/spring-boot-hutu?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false";
    private final static String DB_USER_NAME = "root";
    private final static String DB_PASSWORD = "mysql806001926";

    private final static String PACKAGE_NAME = "com.hutu.tools.mail";
    // 需要生成的表名
    private final static String[] TABLE_NAMES = {
            "t_config_mail"
    };
    // 生成java文件名去掉表前缀 sys_user -> user
    private final static String[] TABLE_PREFIX = {"t_config_"};
    //代码生成目录
    private final static String OUT_PUT_DIR = "codeGen";
    // user -> UserService, 设置成true: user -> IUserService
    private final static boolean SERVICE_NAME_START_WITH_I = false;
    private final static String LOGIC_DELETE_NAME = "isDeleted";
    // 需要自动填充的字段
    private final static List<TableFill> TABLE_FILL_LIST = Arrays.asList(
            new TableFill("createTime", FieldFill.INSERT),
            new TableFill("createId", FieldFill.INSERT),
            new TableFill("createName", FieldFill.INSERT),
            new TableFill("orgId", FieldFill.INSERT),
            new TableFill("updateTime", FieldFill.INSERT_UPDATE),
            new TableFill("updateId", FieldFill.INSERT_UPDATE),
            new TableFill("updateName", FieldFill.INSERT_UPDATE));

    @Test
    public void generateCode() {
        generateByTables(SERVICE_NAME_START_WITH_I, PACKAGE_NAME, TABLE_NAMES);
    }

    private void generateByTables(boolean serviceNameStartWithI, String packageName, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        String dbUrl = DB_URL;
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DB_TYPE)
                .setUrl(dbUrl)
                .setUsername(DB_USER_NAME)
                .setPassword(DB_PASSWORD)
                .setDriverName(DB_DRIVER);
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(true)
                .setLogicDeleteFieldName(LOGIC_DELETE_NAME)
                .setTableFillList(TABLE_FILL_LIST)
                .setColumnNaming(NamingStrategy.no_change)
                .setTablePrefix(TABLE_PREFIX)
                .setNaming(NamingStrategy.underline_to_camel)
                .setInclude(tableNames);// 修改替换成你需要的表名，多个表名传数组
        TemplateConfig tc = new TemplateConfig();
        tc.setController("template/controller.java.vm");
        tc.setEntity("template/entity.java.vm");
        tc.setMapper("template/mapper.java.vm");
        tc.setXml("template/mapper.xml.vm");
        tc.setService("template/service.java.vm");
        tc.setServiceImpl("template/serviceImpl.java.vm");
        config.setActiveRecord(false)
                .setAuthor("generator")
                .setOutputDir(OUT_PUT_DIR)
                .setFileOverride(true);
        if (!serviceNameStartWithI) {
            config.setServiceName("%sService");
        }
//        InjectionConfig icj = new InjectionConfig() {
//            //自定义属性注入:abc
//            //在.ftl(或者是.vm)模板中，通过${cfg.abc}获取属性
//            @Override
//            public void initMap() {
//                Map<String, Object> map = new HashMap<>();
//                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
//                this.setMap(map);
//            }
//        };
//        icj.setFileOutConfigList(Arrays.asList(
//                new FileOutConfig("template/index.vue.vm") {
//                    @Override
//                    public String outputFile(TableInfo tableInfo) {
//                        return config.getOutputDir() + File.separator + tableInfo.getEntityName().toLowerCase() + File.separator + "index.vue";
//                    }
//                },
//                new FileOutConfig("template/api.js.vm") {
//                    @Override
//                    public String outputFile(TableInfo tableInfo) {
//                        return config.getOutputDir() + File.separator + tableInfo.getEntityName().toLowerCase() + File.separator + tableInfo.getEntityName().toLowerCase() + ".js";
//                    }
//                }
//        ));
        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setTemplate(tc)
//                .setCfg(icj)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("controller")
                                .setEntity("entity")
                ).execute();
    }
}
