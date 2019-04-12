package com.code.generate.utils;

public class TypeConvertUtils {
    /**
     * mysql字段的类型转换成java的类型
     *
     * @param mysqlType
     * @return
     */
    public static String mysqlTypeToJavaType(String mysqlType) {
        String javaType = "";
        switch (mysqlType.toUpperCase()) {
            case "TEXT":
                javaType = "String";
                break;
            case "BIT":
                javaType = "Boolean";
                break;
            case "SMALLINT":
                javaType = "Integer";
                break;
            case "MEDIUMINT ":
                javaType = "Integer";
                break;
            case "INTEGER":
                javaType = "Integer";
                break;
            case "INT":
                javaType = "Integer";
                break;
            case "BIGINT UNSIGNED":
                javaType = "Long";
                break;
            case "FLOAT":
                javaType = "Float";
                break;
            case "DOUBLE":
                javaType = "Double";
                break;
            case "DECIMAL":
                javaType = "BigDecimal";
                break;
            case "TIMESTAMP":
                javaType = "Date";
                break;
            case "TIME":
                javaType = "Time";
                break;
            case "DATE":
                javaType = "Date";
                break;
            case "CHAR":
                javaType = "String";
                break;
            case "VARCHAR":
                javaType = "String";
                break;
            case "DATETIME":
                javaType = "Date";
                break;
            case "TINYINT":
                javaType = "Integer";
            default:
                javaType = null;
                break;
        }
        return javaType;
    }
}
