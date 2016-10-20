package brytskyi.week3.xmldomparser;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by alexandr on 13.10.16.
 */
public interface XmlParser<T> {


    void parse(T object, OutputStream os) throws IllegalAccessException, ClassNotFoundException;

    T extract(InputStream is, Class<T> tClass) throws IOException, SAXException, IllegalAccessException, InstantiationException, ClassNotFoundException;
}
