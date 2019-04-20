package com.code.generate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Model {

    private String name;

    private String packageName;

    private String tableName;

    private String tableComment;

    private List<Column> columnList;

}
