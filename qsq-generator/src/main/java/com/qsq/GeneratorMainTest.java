package com.qsq;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;
import com.mysql.cj.jdbc.Driver;

import java.util.HashMap;
import java.util.Map;

/**
 * @author QSQ
 * @create 2019/9/27 10:17
 * No, again
 * 〈〉
 */
public class GeneratorMainTest {

    /**
     * 是否强制带上注解
     */
    private boolean enableTableFieldAnnotation = true;
    /**
     * 生成的注解带上IdType类型
     */
    private IdType tableIdType = null;
    /**
     * 是否去掉生成实体的属性名前缀
     */
    private String[] fieldPrefix = null;
    /**
     * 生成的Service 接口类名是否以I开头
     * <p>默认是以I开头</p>
     * <p>user表 -> IUserService, UserServiceImpl</p>
     */
    private boolean serviceClassNameStartWithI = false;

    @Test
    public void generateCode() {
        String packageName = CodeProperties.PACKAGE_NAME;
        enableTableFieldAnnotation = false;
        tableIdType = null;
        generateByTables(packageName, CodeProperties.TABLE_NAME);

    }

    private void generateByTables(String packageName, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        String dbUrl = CodeProperties.DB_URL;
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername(CodeProperties.DB_USERNAME)
                .setPassword(CodeProperties.DB_PASS_WORD)
                .setDriverName(Driver.class.getName());
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                /*
                设置实体类是否需要 lombok 属性
                 */
                .setEntityLombokModel(true)
                .setRestControllerStyle(true)
                .setLogicDeleteFieldName(CodeProperties.LOGIC_DELETE_FIELD_NAME)
                // .setDbColumnUnderline(true) 改为如下 2 个配置
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                /*
                  test_id -> id, test_type -> type
                 */
                .setEntityTableFieldAnnotationEnable(enableTableFieldAnnotation)
                .setFieldPrefix(fieldPrefix)
                /*
                  修改替换成你需要的表名，多个表名传数组
                 */
                .setInclude(tableNames)
                .setSuperControllerClass(CodeProperties.BASE_CONTROLLER_CLASS_NAME)
                .setSuperEntityClass(CodeProperties.SUPER_ENTITY_CLASS)
                .setSuperEntityColumns(CodeProperties.SUPER_ENTITY_COLUMNS)
//                .setSuperServiceImplClass(CodeProperties.SuperServiceClass)
        ;
        config.setActiveRecord(false)
                .setIdType(tableIdType)
                .setAuthor("QSQ")
                .setOutputDir(CodeProperties.OUTPUT_DIR)
                .setFileOverride(true)
                .setDateType(DateType.ONLY_DATE)


        ;
        if (!serviceClassNameStartWithI) {
            config.setServiceName("%sService");
        }
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        TemplateConfig templateConfig = new TemplateConfig()
                .setController(CodeProperties.TEM_PLATE_CONTROLLER);
        InjectionConfig injectionConfig = new InjectionConfig() {
            //自定义属性注入:abc
            //在.vm/ftl模板中，通过${cfg.abc}获取属性
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>(1);
                this.setMap(map);
            }
        };
        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                //配置自定义模板
                .setTemplate(templateConfig)
                //配置自定义属性注入
                .setCfg(injectionConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("controller")
                                .setService("service")
                                .setServiceImpl("service.impl")
                                .setEntity("po")
                                .setMapper("mapper")
                                .setXml("mapper")
                ).execute();
    }
}