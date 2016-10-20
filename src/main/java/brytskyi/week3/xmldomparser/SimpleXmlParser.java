package brytskyi.week3.xmldomparser;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

/*
Class to parse simple pojo objects into xml
* */
public class SimpleXmlParser<T> implements XmlParser<T> {

    private DocumentBuilder documentBuilder;

    public SimpleXmlParser() {
        DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    /*parse object into xml and returns string*/
    @Override
    public void parse(T object, OutputStream os) throws IllegalAccessException, ClassNotFoundException {
        Document document = documentBuilder.newDocument();
        Element root = document.createElement(object.getClass().getSimpleName());
        writeChildren(root, object, document);
        document.appendChild(root);
        try {
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(os);
            TransformerFactory.newInstance().newTransformer().transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private void writeChildren(Element root, Object object, Document doc) throws IllegalAccessException, ClassNotFoundException {
        Field[] fields = object.getClass().getDeclaredFields();
        if (fields.length < 0) return;
        for (Field field : fields) {
            field.setAccessible(true);
            Element el = doc.createElement(field.getName());
            Class<?> type = field.getType();
            if (type.isPrimitive() || type.getTypeName().equals(String.class.getTypeName())) {
                el.appendChild(doc.createTextNode(String.valueOf(field.get(object))));
            } else if (Iterable.class.isAssignableFrom(type)) {
                String name = field.getType().getName();
                Class colClass = Class.forName(name);
                el.setAttribute("type", name);
                for (Object o : (Iterable) field.get(object)) {
                    Class<?> oClass = o.getClass();
                    if (oClass.getPackage().toString().contains("java.lang")) {
                        Element inner = doc.createElement(o.getClass().getName());
                        inner.appendChild(doc.createTextNode(String.valueOf(o)));
                        el.appendChild(inner);
                    } else {
                        Element inner = doc.createElement(o.getClass().getName());
                        writeChildren(inner, o, doc);
                        el.appendChild(inner);
                    }
                }
            } else if (type.isArray()) {
                Object arr = field.get(object);
                for (int i = 0; i < Array.getLength(arr); i++) {
                    Object o = Array.get(arr, i);
                    Class<?> oClass = o.getClass();
                    if (oClass.getPackage().toString().contains("java.lang")) {
                        Element inner = doc.createElement(o.getClass().getName());
                        inner.appendChild(doc.createTextNode(String.valueOf(o)));
                        el.appendChild(inner);
                    } else {
                        Element inner = doc.createElement(o.getClass().getName());
                        writeChildren(inner, o, doc);
                        el.appendChild(inner);
                    }
                }
            } else {
                el.setAttribute("type", field.getType().getName());
                writeChildren(el, object, doc);
            }
            root.appendChild(el);
        }

    }


    @Override
    public T extract(InputStream is, Class<T> tClass) throws IOException, SAXException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        Document document = documentBuilder.parse(is);
        Element root = document.getDocumentElement();
        return (T) deParseObject(tClass, root, tClass.newInstance());
    }

    private Object deParseObject(Class<?> tClass, Node root, Object res) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Field[] fields = tClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            setFieldValue(field, res, root.getChildNodes());
        }
        return res;
    }

    private void setFieldValue(Field field, Object fieldOwner, NodeList nodes) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Object res = null;
        Class fType = field.getType();
        Node el = null;
        for (int i = 0; i < nodes.getLength(); i++) {
            if (nodes.item(i).getNodeName().equals(field.getName())) {
                el = nodes.item(i);
                break;
            }
        }
        if (el == null) return;
        if (fType.isPrimitive() || fType.getTypeName().equals(String.class.getTypeName())) {
            String fieldVal = el.getChildNodes().item(0).getNodeValue();
            res = parsePrimitive(fieldVal, fType);
        } else if (fType.isArray()) {
            Class type = field.getType().getComponentType();
            res = Array.newInstance(type, el.getChildNodes().getLength());
            for (int i = 0; i < Array.getLength(res); i++) {
                Class<?> oClass = type;
                if (oClass.isPrimitive() || oClass.getPackage().toString().contains("java.lang")) {
                    Array.set(res, i, parsePrimitive(el.getChildNodes().item(i).getChildNodes().item(0).getNodeValue(), oClass));
                } else {
                    Object oObject = oClass.newInstance();
                    Array.set(res, i, deParseObject(oClass, el.getChildNodes().item(i).getChildNodes().item(0), oObject));
                }
            }
        } else {
            Class<?> oClass = Class.forName(el.getAttributes().getNamedItem("type").getNodeValue());
            res = oClass.newInstance();
            deParseObject(oClass, el, res);
        }
        System.out.println(res.getClass().getName());
        field.set(fieldOwner, res);
    }

    private Object parsePrimitive(String fieldVal, Class fType) {

        if (fType.equals(String.class)) {
            return fieldVal;
        } else if (fType.equals(int.class)) {
            return Integer.parseInt(fieldVal);
        } else if (fType.equals(double.class)) {
            return Double.parseDouble(fieldVal);
        } else if (fType.equals(float.class)) {
            return Float.parseFloat(fieldVal);
        } else if (fType.equals(boolean.class)) {
            return Boolean.parseBoolean(fieldVal);
        } else if (fType.equals(byte.class)) {
            return Byte.parseByte(fieldVal);
        } else if (fType.equals(long.class)) {
            return Long.parseLong(fieldVal);
        } else if (fType.equals(char.class)) {
            return fieldVal.charAt(0);
        } else if (fType.equals(short.class))
            return Short.parseShort(fieldVal);
        return null;
    }

}
