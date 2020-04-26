package com.boot.mybatis.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.sun.prism.PixelFormat.DataType;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author zhaoxiaoping
 * @Description: 代码生成器
 * @Date
 **/
public class CodeGenerator {

  public static void main(String[] args) {
    // 代码自动生成器对象
    AutoGenerator generator = new AutoGenerator();
    // 配置策略
    // 1. 全局配置
    GlobalConfig gc = new GlobalConfig();
    String projectPath = System.getProperty("user.dir");
    gc.setOutputDir(projectPath + "/spring-boot-mybatis/spring-boot-mybatis-plus/src/main/java");
    gc.setAuthor("xp-zhao");
    gc.setOpen(false);
    gc.setFileOverride(false);
    gc.setServiceName("%sService");
    gc.setIdType(IdType.AUTO);
    gc.setDateType(DateType.TIME_PACK);
    generator.setGlobalConfig(gc);

    // 2.设置数据源
    DataSourceConfig db = new DataSourceConfig();
    db.setUrl("jdbc:mysql://47.98.49.140:3306/mp");
    db.setDriverName("com.mysql.cj.jdbc.Driver");
    db.setUsername("root");
    db.setPassword("123456");
    db.setDbType(DbType.MYSQL);
    generator.setDataSource(db);

    // 3.配置包
    PackageConfig pc = new PackageConfig();
    pc.setParent("com.boot.mybatis");
    pc.setEntity("entity");
    pc.setMapper("mapper");
    pc.setService("service");
    pc.setController("controller");
    generator.setPackageInfo(pc);

    // 4.策略配置
    StrategyConfig strategy = new StrategyConfig();
    strategy.setInclude("tbl_menu"); // 设置要映射的表名
    strategy.setNaming(NamingStrategy.underline_to_camel);
    strategy.setColumnNaming(NamingStrategy.underline_to_camel);
    strategy.setEntityLombokModel(true); // 自动lombok；
    strategy.setTablePrefix("tbl");
    // 逻辑删除字段
    strategy.setLogicDeleteFieldName("deleted");
    // 自动填充配置
    TableFill create = new TableFill("create_date", FieldFill.INSERT);
    TableFill update = new TableFill("update_date", FieldFill.INSERT_UPDATE);
    strategy.setTableFillList(Arrays.asList(create, update));
    // 乐观锁
    strategy.setVersionFieldName("version");
    strategy.setRestControllerStyle(true);
    strategy.setControllerMappingHyphenStyle(true); // localhost:8080/hello_id_2
    generator.setStrategy(strategy);

    // 执行
    generator.execute();
  }
}
