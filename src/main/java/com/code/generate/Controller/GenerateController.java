package com.code.generate.Controller;


import com.code.generate.entity.Database;
import com.code.generate.entity.Model;
import com.code.generate.entity.Table;
import com.code.generate.service.GenerateService;
import com.code.generate.utils.DbUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/generate")
@Api("模板生成")
public class GenerateController {

    @Autowired
    private GenerateService generateService;


    /**
     *
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
    @RequestMapping("/getTableList")
    @ResponseBody
    public List<Table> getTableList(@ApiParam("驱动名称") String driver,
                                    @ApiParam("端口ip") String ip,
                                    @ApiParam("端口号") String port,
                                    @ApiParam("用户名") String username,
                                    @ApiParam("密码") String password,
                                    @ApiParam("数据名称") String databaseName) throws Exception {
        Database database = new Database();
        database.setDatabaseName(databaseName);
        database.setIp(ip);
        database.setDriver(driver);
        database.setPort(port);
        database.setPassword(password);
        database.setUsername(username);
        List<Table> tableNameList = DbUtils.getTableNameList(database);
        return tableNameList;
    }


    /**
     * 根据表名生成模板
     *
     * @param tableName
     * @return
     */
    @ApiOperation(value = "根据表名生成模板", notes = "根据表名生成模板")
    @RequestMapping("/generateTemplate")
    @ResponseBody
    public String generateTemplate( @ApiParam("数据名称")String tableName) {
        Model model = DbUtils.getModel(tableName);
        generateService.generateController(model, "path", "fileName");
        generateService.generateEntity(model, "path", "fileName");
        generateService.generateMapper(model, "path", "fileName");
        generateService.generateMapperXml(model, "path", "fileName");
        generateService.generateService(model, "path", "fileName");
        generateService.generateServiceImpl(model, "path", "fileName");
        return null;
    }


}
