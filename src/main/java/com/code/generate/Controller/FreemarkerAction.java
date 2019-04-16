package com.code.generate.Controller;

import com.code.generate.entity.Database;
import com.code.generate.entity.Table;
import com.code.generate.utils.DbUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping(value = "/freemarker")
public class FreemarkerAction {


    @RequestMapping(value = "/toDemo")
    public String toDemo(Model mv) throws Exception {
        Database database = new Database();
        database.setDatabaseName("mlf");
        database.setDriver("com.mysql.jdbc.Driver");
        database.setPort("3306");

        database.setUsername("root");
        List<Table> tableNameList = DbUtils.getTableNameList(database);
        mv.addAttribute("tableNameList", tableNameList);
        return "freemarker";
    }

}
