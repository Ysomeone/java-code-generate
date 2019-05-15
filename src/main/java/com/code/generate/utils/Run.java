package com.code.generate.utils;

import com.code.generate.entity.Database;
import com.code.generate.entity.Model;

public class Run {
    public static void main(String[] args) {
        Database database = new Database();
        database.setDatabaseName("test");
        database.setDriver("com.mysql.jdbc.Driver");
        database.setPort("3306");
        database.setIp("127.0.0.1");
        database.setPassword("");
        database.setUsername("root");
        Model model = DbUtils.getModel("database",database);
        model.setPackageName("com.code.generate");
        model.setName("Database");
        model.setTableComment("类解释");
        GenerateUtils.generateFile(model, "D:/gg/",model.getName()+ "Mapper.xml",  "MapperXml.ftl");
        GenerateUtils.generateFile(model, "D:/gg/com/java/Entity/",model.getName()+ ".java", "Entity.ftl");
        GenerateUtils.generateFile(model, "D:/gg/com/java/Mapper/",model.getName()+ "Mapper.java", "Mapper.ftl");
        GenerateUtils.generateFile(model, "D:/gg/com/java/Service/",model.getName()+ "Service.java", "Service.ftl");
        GenerateUtils.generateFile(model, "D:/gg/com/java/ServiceImpl/",model.getName()+ "ServiceImpl.java", "ServiceImpl.ftl");
        GenerateUtils.generateFile(model, "D:/gg/com/java/Controller/",model.getName()+ "Controller.java", "Controller.ftl");
    }
}