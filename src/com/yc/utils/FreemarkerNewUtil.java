package com.yc.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;
 
import freemarker.template.Configuration;
import freemarker.template.Template;
 
public class FreemarkerNewUtil {
    private static Configuration cfg;
    static{
        cfg = new Configuration();
        try {
            //读取位于./src/ftl/下的模板文件即  ftl文件
            cfg.setDirectoryForTemplateLoading(new File("./src/ftl/"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
    /**
     * 获取模板对象
     * <a href="http://home.cnblogs.com/u/309701/" target="_blank">@param</a> fileName ftl文件名
     * @return
     */
    public static Template getTemplate(String fileName){
        //创建模板对象
        Template temp = null;
        try {
            temp = cfg.getTemplate(fileName);
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
     
    /**
     * 输出到控制台
     * <a href="http://home.cnblogs.com/u/309701/" target="_blank">@param</a> temp 模板对象
     * <a href="http://home.cnblogs.com/u/309701/" target="_blank">@param</a> map 插值的map对象
     */
    public static void print(Template temp, Map<String, Object> map){
        try {
            //在模板上执行插值操作
            temp.process(map, new OutputStreamWriter(System.out));
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
     
    /**
     * 输出html文件到f盘根目录
     * <a href="http://home.cnblogs.com/u/309701/" target="_blank">@param</a> temp 模板对象
     * <a href="http://home.cnblogs.com/u/309701/" target="_blank">@param</a> map 插值的map对象
     * <a href="http://home.cnblogs.com/u/309701/" target="_blank">@param</a> outfileName html文件名
     */
    public static void fprint(Template temp, Map<String, Object> map, String outfileName){
        try {
            //在模板上执行插值操作，输出到f盘根目录
            temp.process(map, new OutputStreamWriter(new FileOutputStream("f:\\" + outfileName)));
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
     
}
