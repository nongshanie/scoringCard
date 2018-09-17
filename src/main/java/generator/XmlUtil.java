package generator;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;

public class XmlUtil {

    //修改XML文件
    public static void mergeXML(File sourceXml, String appendingXml) throws Exception {
        SAXReader reader = new SAXReader();
        Document doc = reader.read(sourceXml);

        Document flagment = DocumentHelper.parseText(appendingXml);
        Element flagEle = flagment.getRootElement();
        flagEle.setQName(new QName(flagEle.getName(), doc.getRootElement()
                .getNamespace()));
        if (flagEle.elements().size() > 0) {
            for (Object c : flagEle.elements()) {
                Element cel = (Element) c;
                cel.setQName(new QName(cel.getName(), flagEle.getNamespace()));
            }
        }
        doc.getRootElement().add(flagEle);

        XMLWriter writer = new XMLWriter(new FileWriter(sourceXml));
        writer.write(doc.getRootElement());
        writer.close();
    }
}
