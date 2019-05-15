package com.code.generate.utils;

import com.code.generate.entity.Column;
import com.code.generate.entity.Database;
import com.code.generate.entity.Model;
import com.code.generate.entity.Table;
import org.springframework.util.StringUtils;

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
     * 返回的结果集
     */
    private static ResultSet rs;


    public static Model getModel(String tableName, Database database) {
        Model model = new Model();
        model.setTableName(tableName);
        model.setPackageName("com.java");
        model.setName("k");
        model.setColumnList(getColumns(tableName, database));
        return model;
    }


    /**
     * 获取连接的方法
     *
     * @return Connection
     */
    public static Connection getConnection(Database database) {
        try {
            if (con == null) {
                Class.forName(database.getDriver());
                String url = "jdbc:mysql://" + database.getIp() + ":" + database.getPort() + "/" + database.getDatabaseName() + "?seUnicode=true&amp;characterEncoding=utf-8";
                System.out.println("url:" + url);
                Properties info = new Properties();
                info.put("user", database.getUsername());
                info.put("password", database.getPassword());
                con = DriverManager.getConnection(url, info);
            }
        } catch (Exception e) {
        }
        return con;
    }
    /**
     * @param database
     * @return
     * @throws Exception
     */
    public static List<Table> getTableNameList(Database database, String tableName) throws Exception {
        List<Table> tableList = new ArrayList<>();
        String sql;
        if (!StringUtils.isEmpty(tableName)) {
            sql = "select table_name,TABLE_COMMENT  from information_schema.tables where table_schema='" + database.getDatabaseName() + "' and table_type='base table' and table_name like concat('%','" + tableName + "','%') ;";
        } else {
            sql = "select table_name,TABLE_COMMENT  from information_schema.tables where table_schema='" + database.getDatabaseName() + "' and table_type='base table'";
        }
        rs = getRs(database, sql);
        while (rs.next()) {
            Table table = new Table();
            String tableName1 = rs.getString("table_name");
            String tableComment = rs.getString("table_comment");
            if(StringUtils.isEmpty(tableComment)){
                table.setTableComment("");
            }else{
                table.setTableComment(tableComment);
            }
            table.setTableName(tableName1);
            tableList.add(table);
        }
        return tableList;
    }

    public static ResultSet getRs(Database database, String SQL) throws Exception {
        Connection connection = DbUtils.getConnection(database);
        Statement statement = connection.createStatement();
        return statement.executeQuery(SQL);
    }

    /**
     * 根据表名获取所有的字段信息
     *
     * @param tableName 表名称
     * @return 字段的集合
     */
    public static List<Column> getColumns(String tableName, Database database) {
        List<Column> columnList = new ArrayList<>();
        String PK = "";
        Connection connection = DbUtils.getConnection(database);
        DatabaseMetaData databaseMetaData;
        try {
            databaseMetaData = connection.getMetaData();
            ResultSet pkRSet = databaseMetaData.getPrimaryKeys(null, null, tableName);
            ResultSet resultSet = databaseMetaData.getColumns(null, "%", tableName, "%");
            while (pkRSet.next()) {
                PK = (String) pkRSet.getObject(4);
            }
            while (resultSet.next()) {
                Column column = new Column();
                if (resultSet.getString("COLUMN_NAME").equalsIgnoreCase(PK)) {
                    column.setIsPrimaryKey(true);
                } else {
                    column.setIsPrimaryKey(false);
                }
                column.setOriginalName(resultSet.getString("COLUMN_NAME"));
                column.setName(StrUtils.replaceUnderLine(resultSet.getString("COLUMN_NAME")));
                column.setNameUp(StrUtils.toUpperCase(resultSet.getString("COLUMN_NAME")));
                column.setComment(resultSet.getString("REMARKS"));
                column.setType(TypeConvertUtils.mysqlTypeToJavaType(resultSet.getString("TYPE_NAME")));
                column.setOriginalType(resultSet.getString("TYPE_NAME"));
                columnList.add(column);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("获取表信息失败！");
        }
        return columnList;
    }
}
