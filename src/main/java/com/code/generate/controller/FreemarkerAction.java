package com.code.generate.controller;

import com.code.generate.entity.Database;
import com.code.generate.entity.Table;
import com.code.generate.utils.DbUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping(value = "/freemarker")
public class FreemarkerAction {


    @RequestMapping(value = "/toDemo")
    public String toDemo() {
        return "index";
    }

    @RequestMapping(value = "/admin/order-list")
    public String orderlist(Model mv, String tableName) throws Exception {
        Database database = new Database();
        database.setDatabaseName("mlf");
        database.setDriver("com.mysql.jdbc.Driver");
        database.setPort("33061");
        database.setPassword("");
        database.setUsername("root");
        List<Table> tableNameList = DbUtils.getTableNameList(database, tableName);
        mv.addAttribute("tableNameList", tableNameList);
        mv.addAttribute("size", tableNameList.size());
        mv.addAttribute("tableName", tableName);
        return "admin/order-list";
    }


}
