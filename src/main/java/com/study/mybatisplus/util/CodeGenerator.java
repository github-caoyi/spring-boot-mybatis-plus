package com.study.mybatisplus.util;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CodeGenerator {

    //项目在磁盘中的绝对地址
    public static final String DISK_PATH = "H:/project-study/";
    //项目名
    public static final String PROJECT_NAME = "mybatisplus";
    //包名
    public static final String PACKAGE_NAME = "com.study.mybatisplus";
    //java代码路径
    public static final String JAVA_PATH = "/src/main/java/";
    //资源文件路径
    public static final String RESOURCE_PATH = "/src/main/resources/mapper/";
    //作者
    public static final String AUTH_NAME = "cy";
    //JDBC配置
    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/ry-vue?useUnicode=true&characterEncoding=utf8&useSSL=true&serverTimezone=GMT%2B8";
    public static final String JDBC_DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    public static final String JDBC_USERNAME = "root";
    public static final String JDBC_PASSWORD = "admin123";
    //公共类配置
    public static final String SUPPER_MAPPER = "com.baomidou.mybatisplus.core.mapper.BaseMapper";
    public static final String SUPPER_SERVICE = "com.baomidou.mybatisplus.extension.service.IService";
    public static final String[] TABLE_NAMES = {"user"};

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(CodeGenerator.DISK_PATH + CodeGenerator.PROJECT_NAME + CodeGenerator.JAVA_PATH );
        gc.setAuthor(CodeGenerator.AUTH_NAME);
        gc.setOpen(false);
        //是否覆盖文件
        gc.setFileOverride(false);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(CodeGenerator.JDBC_URL);
        dsc.setDriverName(CodeGenerator.JDBC_DRIVER_NAME);
        dsc.setUsername(CodeGenerator.JDBC_USERNAME);
        dsc.setPassword(CodeGenerator.JDBC_PASSWORD);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName(scanner("模块名"));
        pc.setParent(CodeGenerator.PACKAGE_NAME);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return CodeGenerator.DISK_PATH + CodeGenerator.PROJECT_NAME + CodeGenerator.RESOURCE_PATH + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
//        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
//        strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        strategy.setSuperMapperClass(CodeGenerator.SUPPER_MAPPER);
        strategy.setSuperServiceClass(CodeGenerator.SUPPER_SERVICE);
        // 写于父类中的公共字段
        strategy.setSuperEntityColumns("id");
        strategy.setInclude(CodeGenerator.TABLE_NAMES);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}