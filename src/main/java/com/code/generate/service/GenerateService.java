package com.code.generate.service;

import com.code.generate.entity.Model;

public interface GenerateService {


    void generateController(Model model, String path, String fileName);

    void generateEntity(Model model, String path, String fileName);

    void generateMapper(Model model, String path, String fileName);

    void generateMapperXml(Model model, String path, String fileName);

    void generateService(Model model, String path, String fileName);

    void generateServiceImpl(Model model, String path, String fileName);

}
