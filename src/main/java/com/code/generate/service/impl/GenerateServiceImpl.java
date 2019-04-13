package com.code.generate.service.impl;

import com.code.generate.entity.Model;
import com.code.generate.service.GenerateService;
import com.code.generate.utils.GenerateUtils;
import org.springframework.stereotype.Service;


@Service
public class GenerateServiceImpl implements GenerateService {




    public void generateController(Model model, String path, String fileName) {
        String templateName = "Controller.ftl";
        GenerateUtils.generateFile(model, path, fileName, templateName);
    }

    public void generateEntity(Model model, String path, String fileName) {
        String templateName = "Entity.ftl";
        GenerateUtils.generateFile(model, path, fileName, templateName);
    }

    public void generateMapper(Model model, String path, String fileName) {
        String templateName = "Mapper.ftl";
        GenerateUtils.generateFile(model, path, fileName, templateName);
    }

    public void generateMapperXml(Model model, String path, String fileName) {
        String templateName = "MapperXml.ftl";
        GenerateUtils.generateFile(model, path, fileName, templateName);
    }

    public void generateService(Model model, String path, String fileName) {
        String templateName = "Service.ftl";
        GenerateUtils.generateFile(model, path, fileName, templateName);
    }

    public void generateServiceImpl(Model model, String path, String fileName) {
        String templateName = "ServiceImpl.ftl";
        GenerateUtils.generateFile(model, path, fileName, templateName);
    }

}
