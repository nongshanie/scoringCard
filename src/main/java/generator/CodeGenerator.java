package generator;


import freemarker.template.Configuration;
import freemarker.template.Template;
import generator.bean.ClassInfo;

import java.io.File;
import java.io.FileWriter;
import java.sql.SQLException;


//代码生成器类
public class CodeGenerator {
    private static Configuration cfg;

    static {
        try {
            cfg = new Configuration();
            cfg.setDirectoryForTemplateLoading(new File("templates"));
            //cfg.setDirectoryForTemplateLoading(new File("D:\\opensource\\scoringCard\\src\\main\\java\\generator\\templates"));
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
        ClassInfo classInfo = getResultClassInfo();
        createFile(classInfo, "JsonResult.ftl", "../scoringCard_jar/JsonResult.sql");
    }

    /**
     * 获取表对象集合
     *
     * @return 表对象集合
     */
    private static ClassInfo getResultClassInfo() throws SQLException {
        return new ClassInfo(JDBCExample.getTableInfoList());
    }


    //生成文件
    private static void createFile(ClassInfo classInfo, String templateFile, String targetFile) throws Exception {
        Template template = cfg.getTemplate(templateFile);
        File f = new File(targetFile);
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        template.process(classInfo, new FileWriter(targetFile));
    }


}
