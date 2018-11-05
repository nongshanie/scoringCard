package generator;


import freemarker.template.Configuration;
import freemarker.template.Template;
import mybatis.simple.model.XrSysDictionary;

import java.io.File;
import java.io.FileWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//代码生成器类
public class CodeGenerator {
    private static Configuration cfg;

    static {
        try {
            cfg = new Configuration();
            //cfg.setDirectoryForTemplateLoading(new File("templates"));
            cfg.setDirectoryForTemplateLoading(new File("D:\\opensource\\scoringCard\\src\\main\\java\\generator\\templates"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //生成之后马上注释
    public static void main(String[] args) throws Exception {
        System.out.println("生成开始");
        generated();
        System.out.println("生成结束");
    }

    private static void generated() throws Exception {
        Object classInfo = getResultClassInfo();
        //createFile(classInfo, "JsonResult.ftl", "../scoringCard_jar/JsonResult.sql");
        createFile(classInfo, "EnumResult.ftl", "../scoringCard_jar/XrSysDictionaryEnum.java");
    }

    /**
     * 获取表对象集合
     *
     * @return 表对象集合
     */
    private static Object getResultClassInfo() throws SQLException {
        Map<String, Object> map = new HashMap<>(1);
        List<XrSysDictionary> tableInfoList = JDBCExample.getTableInfo();
        for (XrSysDictionary tableInfo : tableInfoList) {
            System.out.println("tableInfo = " + tableInfo);
        }
        map.put("tablesList", tableInfoList);
        return map;
    }


    //生成文件
    private static void createFile(Object object, String templateFile, String targetFile) throws Exception {
        Template template = cfg.getTemplate(templateFile);
        File f = new File(targetFile);
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        template.process(object, new FileWriter(targetFile));
    }


}
