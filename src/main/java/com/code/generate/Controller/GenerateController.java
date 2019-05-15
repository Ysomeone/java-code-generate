package com.code.generate.Controller;


import com.code.generate.entity.Database;
import com.code.generate.entity.Model;
import com.code.generate.entity.Table;
import com.code.generate.service.GenerateService;
import com.code.generate.utils.DbUtils;
import com.code.generate.utils.GenerateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/generate")
@Api(tags = "模板生成")
public class GenerateController {

    /**
     * @param driver
     * @param ip
     * @param port
     * @param username
     * @param password
     * @param databaseName
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "展示所有表", notes = "根据ip、端口等配置展示所有表")
    @PostMapping("/getTableList")
    @ResponseBody
    public List<Table> getTableList(@ApiParam("驱动名称") @RequestParam(value = "driver") String driver,
                                    @ApiParam("端口ip") @RequestParam(value = "ip") String ip,
                                    @ApiParam("端口号") @RequestParam(value = "port") String port,
                                    @ApiParam("用户名") @RequestParam(value = "username") String username,
                                    @ApiParam("密码") @RequestParam(value = "password") String password,
                                    @ApiParam("数据名称") @RequestParam(value = "databaseName") String databaseName) throws Exception {
//        Database database = new Database();
//        database.setDatabaseName(databaseName);
//        database.setIp(ip);
//        database.setDriver(driver);
//        database.setPort(port);
//        database.setPassword(password);
//        database.setUsername(username);
        Database database = new Database();
        database.setDatabaseName("mlf");
        database.setDriver("com.mysql.jdbc.Driver");
        database.setPort("3306");
        database.setPassword("");
        database.setUsername("root");
        List<Table> tableNameList = DbUtils.getTableNameList(database,"");
        return tableNameList;
    }

    /**
     * 根据表名生成模板
     *
     * @param tableName
     * @return
     */
    @ApiOperation(value = "根据表名生成模板", notes = "根据表名生成模板")
    @RequestMapping ("/generateTemplate")
    @ResponseBody
    public String generateTemplate(@ApiParam("数据名称") String tableName, Database database) {
        Model model = DbUtils.getModel(tableName,database);
        GenerateUtils.generateFile(model, "D:/gg/",model.getName()+ "Mapper.xml",  "MapperXml.ftl");
        GenerateUtils.generateFile(model, "D:/gg/",model.getName()+ ".java", "Entity.ftl");
        GenerateUtils.generateFile(model, "D:/gg/",model.getName()+ "Mapper.java", "Mapper.ftl");
        GenerateUtils.generateFile(model, "D:/gg/",model.getName()+ "Service.java", "Service.ftl");
        GenerateUtils.generateFile(model, "D:/gg/",model.getName()+ "ServiceImpl.java", "ServiceImpl.ftl");
        GenerateUtils.generateFile(model, "D:/gg/",model.getName()+ "Controller.java", "Controller.ftl");
        return "D:/gg/";
    }
}
