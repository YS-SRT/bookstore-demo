package com.yfh.register;

import java.util.Collections;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

public class MybatisPlusGenerator {

    public static void main(String[] args) {

        FastAutoGenerator.create("jdbc:mysql://172.28.16.240:3306/bookstore", "root", "admin")
                .globalConfig(builder -> {
                    builder.author("yfh") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                            .dateType(DateType.TIME_PACK)
                            .commentDate("yyyy-MM-dd HH:mm:ss")
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("E:/projects/microservice/spring-fm/register/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.yfh.register.ddd") // 设置父包名
                            //.moduleName("infrastructure") // 设置父包模块名
                            .entity("infrastructure.model")
                            .service("domain.gateway")
                            .serviceImpl("infrastructure.gatewayimpl")
                            .mapper("infrastructure.mapper")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "E:/projects/microservice/spring-fm/register/src/main/resources/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("user_info","user_paid_info") // 设置需要生成的表名
                            //.addTablePrefix("t_", "c_"); // 设置过滤表前缀
                            .enableSkipView() //跳过视图
                
                            //entity 策略
                            .entityBuilder().enableLombok()
                            .enableActiveRecord()
                            .naming(NamingStrategy.underline_to_camel)
                            .columnNaming(NamingStrategy.underline_to_camel)
                            .formatFileName("%sDO")
                            .addTableFills(
                                new Column("create_time",FieldFill.INSERT),
                                new Column("update_time",FieldFill.INSERT_UPDATE)
                            )
                            //乐观锁
                            .versionColumnName("version")
                            .versionPropertyName("version")
                            //逻辑删除
                            .logicDeleteColumnName("del_flag")
                            .logicDeletePropertyName("delFlag")
                            .enableTableFieldAnnotation()
                            .enableChainModel()
                            

                            //mapper 策略
                            .mapperBuilder().superClass(BaseMapper.class)
                            .formatMapperFileName("%sMapper")
                            .formatXmlFileName("%sMapper")
                            .enableMapperAnnotation()
                            
                            //service 策略
                            .serviceBuilder()
                            .formatServiceFileName("%sGateway")
                            .formatServiceImplFileName("%sGatewayImpl");

                            //controller 策略
                            // .controllerBuilder()
                            // .formatFileName("%sController")
                            // .enableRestStyle();


                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();    
    }
    
}
