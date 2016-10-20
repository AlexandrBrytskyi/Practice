package brytskyi.week3.xmldomparser;

import org.xml.sax.SAXException;

import java.io.*;

/**
 * Created by alexandr on 14.10.16.
 */
public class TestParser {


    public static void main(String[] args) throws IOException, IllegalAccessException, SAXException, ClassNotFoundException, InstantiationException {
        XmlParser<ClassTOwrite> parser = new SimpleXmlParser<>();

        String PATH = "temp/Test.xml";
        ClassTOwrite vasa = new ClassTOwrite(1, "Vasa", 45);
        parser.parse(vasa, new FileOutputStream(PATH));

        ClassTOwrite cl = parser.extract(new FileInputStream(PATH), ClassTOwrite.class);
        System.out.println(cl);
    }
}
