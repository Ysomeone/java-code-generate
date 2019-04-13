package com.code.generate.utils;

import com.code.generate.entity.Column;
import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DbUtils {

    /**
     * 数据库连接
     */
    private static Connection con;
    /**
     *
     */
    private static PreparedStatement pstmt;
    /**
     * 返回的结果集
     */
    private static ResultSet rs;
    /**
     * 连接的url
     */
    @Value("${spring.datasource.url}")
    private static String url;
    /**
     * 数据库账户
     */
    @Value("${spring.datasource.username}")
    private static String username;
    /**
     * 数据库密码
     */
    @Value("${spring.datasource.password}")
    private static String password  ;
    /**
     * Mysql驱动类
     */
    @Value("${spring.datasource.password}")
    private static String driver = "com.mysql.jdbc.Driver";

    /**
     * 获取连接的方法
     *
     * @return Connection
     */
    public static Connection getConnection() {
        try {
            if (con == null) {
                Class.forName(driver);
                Properties info = new Properties();
                info.put("user", username);
                info.put("password", password);
                con = DriverManager.getConnection(url, info);
            }
        } catch (Exception e) {
        }
        return con;
    }

    /**
     * 获取rs
     *
     * @param sql
     * @return
     */
    public static ResultSet getResultSet(String sql) {
        con = getConnection();
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // closeCon();
        }
        return rs;
    }

    /**
     * 根据表名获取所有的字段信息
     *
     * @param tableName 表名称
     * @return 字段的集合
     */
    public static List<Column> getColumns(String tableName) {
        List<Column> columnList = new ArrayList<>();
        String PK = "";
        Connection connection = DbUtils.getConnection();
        DatabaseMetaData databaseMetaData;
        try {
            databaseMetaData = connection.getMetaData();
            ResultSet pkRSet = databaseMetaData.getPrimaryKeys(null, null, tableName);
            ResultSet resultSet = databaseMetaData.getColumns(null, "%", tableName, "%");
            //获取主键
            while (pkRSet.next()) {
                PK = (String) pkRSet.getObject(4);
            }
            while (resultSet.next()) {
                Column column = new Column();
                //id字段略过
                if (resultSet.getString("COLUMN_NAME").equalsIgnoreCase(PK)) {
                    column.setIsPrimaryKey(true);
                } else {
                    column.setIsPrimaryKey(false);
                }
                //获取数据库原始字段名称
                column.setOriginalName(resultSet.getString("COLUMN_NAME"));
                //获取字段名称
                column.setName(StrUtils.replaceUnderLine(resultSet.getString("COLUMN_NAME")));
                //转换字段名称，如 sys_name 变成 SysName
                column.setNameUp(StrUtils.toUpperCase(resultSet.getString("COLUMN_NAME")));
                //字段在数据库的注释
                column.setComment(resultSet.getString("REMARKS"));
                //获取字段类型
                column.setType(TypeConvertUtils.mysqlTypeToJavaType(resultSet.getString("TYPE_NAME")));
                columnList.add(column);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("获取表信息失败！");
        }
        return columnList;
    }
}