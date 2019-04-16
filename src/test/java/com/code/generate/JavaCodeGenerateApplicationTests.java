package com.code.generate;

import com.code.generate.entity.Database;
import com.code.generate.utils.DbUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.Statement;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaCodeGenerateApplicationTests {

    @Test
    public void contextLoads() {

        Database database = new Database();
        database.setDatabaseName("mlf");
        database.setIp("192.168.105.16");
        database.setDriver("com.mysql.jdbc.Driver");
        database.setPort("3306");
        database.setPassword("");
        database.setUsername("root");
        Connection connection = DbUtils.getConnection(database);
//        Statement statement = connection.createStatement();

    }

}
