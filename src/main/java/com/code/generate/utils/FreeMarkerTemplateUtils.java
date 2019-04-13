package com.code.generate.utils;


import com.code.generate.entity.Model;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.util.Map;


public class FreeMarkerTemplateUtils {

    private FreeMarkerTemplateUtils() {

    }

    private static final Configuration CONFIGURATION = new Configuration();

    static {
        try {
            //这里比较重要，用来指定加载模板所在的路径
            CONFIGURATION.setClassForTemplateLoading(FreeMarkerTemplateUtils.class, "/templates");
            CONFIGURATION.setDefaultEncoding("UTF-8");
            CONFIGURATION.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            CONFIGURATION.setCacheStorage(new freemarker.cache.MruCacheStorage(20, 250));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得模板
     *
     * @param templateName
     * @return
     * @throws IOException
     */
    public static Template getTemplate(String templateName) throws IOException {
        try {
            return CONFIGURATION.getTemplate(templateName);
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * 清除缓存
     */
    public static void clearCache() {
        CONFIGURATION.clearTemplateCache();
    }

    /**
     * 通过模板生成可以使用的文件
     *
     * @param templateName
     * @param file
     * @param entity
     * @throws Exception
     */
    public static void generateFile(final String templateName, File file, Model entity) throws Exception {
        Template template = getTemplate(templateName);
        FileOutputStream fos = new FileOutputStream(file);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);
        template.process(entity, out);
    }

    /**
     * 通过模板生成可以使用的文件
     *
     * @param templateName
     * @param file
     * @param dataMap
     * @throws Exception
     */
    public static void generateControllerFile(final String templateName, File file, Map<String, Object> dataMap) throws Exception {
        Template template = getTemplate(templateName);
        FileOutputStream fos = new FileOutputStream(file);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);
        template.process(dataMap, out);
    }
}
