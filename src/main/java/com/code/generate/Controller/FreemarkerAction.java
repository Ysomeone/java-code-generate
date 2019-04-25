package com.code.generate.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/freemarker")
public class FreemarkerAction {


    @RequestMapping(value = "/toDemo")
    public String toDemo(Model mv) throws Exception {
//        Database database = new Database();
//        database.setDatabaseName("mlf");
//        database.setDriver("com.mysql.jdbc.Driver");
//        database.setPort("3306");
//
//        database.setUsername("root");
//        List<Table> tableNameList = DbUtils.getTableNameList(database);
//        mv.addAttribute("tableNameList", tableNameList);
        return "index";
    }
    @RequestMapping(value = "/admin/order-list")
    public String orderlist(Model mv) throws Exception {
//        Database database = new Database();
//        database.setDatabaseName("mlf");
//        database.setDriver("com.mysql.jdbc.Driver");
//        database.setPort("3306");
//
//        database.setUsername("root");
//        List<Table> tableNameList = DbUtils.getTableNameList(database);
//        mv.addAttribute("tableNameList", tableNameList);
        return "admin/order-list";
    }




}
