package com.code.generate.entity;


import lombok.Data;

@Data
public class Database {
    private String databaseType;
    private String ip;
    private String port;
    private String databaseName;
    private String username;
    private String password;
    private String driver;
}
