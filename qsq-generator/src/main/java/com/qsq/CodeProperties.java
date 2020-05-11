package com.qsq;


/**
 * @author QSQ
 * @create 2019/4/12 10:00
 * No, again
 * 〈自动生成文件的配置文件〉
 */
class CodeProperties {

    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/springboot?allowMultiQueries=true&useSSL=false&useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&zeroDateTimeBehavior=convertToNull&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&nullCatalogMeansCurrent=true";
    static final String DB_USERNAME = "root";
    static final String DB_PASS_WORD = "123456";
    /**
     * 控制层的继承类BaseController
     */
    static final String BASE_CONTROLLER_CLASS_NAME = "com.qsq.common.model.BaseController";
    static final String SUPER_ENTITY_CLASS = "com.qsq.common.model.BaseEntity";
    /**
     * 生成后的包名
     */
    static final String PACKAGE_NAME = "com.qsq.user";
    /**
     * 需要生成的表
     */
    static final String[] TABLE_NAME = {"sys_role", "sys_user_role", "sys_permissions", "sys_role_permissions"};
    /**
     * 控制层的模板名称
     */
    static final String TEM_PLATE_CONTROLLER = "templates/controller.java";
    /**
     * 基本字段
     */
    static final String[] SUPER_ENTITY_COLUMNS = {"create_time", "create_user", "update_time", "update_user", "version", "is_delete"};
    static final String LOGIC_DELETE_FIELD_NAME = "del_flag";
    static final String OUTPUT_DIR = "E:\\java-code\\generator-code";
}