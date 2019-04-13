package com.code.generate.utils;

import com.code.generate.entity.Model;

import java.io.File;

import static com.code.generate.utils.DbUtils.getColumns;

public class GenerateUtils {




    public static void generateFile(Model model, String path, String fileName, String templateName) {
        try {
            File pat = new File(path);
            if (!pat.exists()) {
                pat.mkdirs();
            }
            String filePath = path + fileName;
            File file = new File(filePath);
            FreeMarkerTemplateUtils.generateFile(templateName, file, model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
