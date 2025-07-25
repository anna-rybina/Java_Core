package ru.example.xmljson;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {
    public static List<ru.example.xmljson.Employee> parseXML(String fileName) throws Exception {
        List<ru.example.xmljson.Employee> employees = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File(fileName));

        NodeList nodeList = doc.getElementsByTagName("employee");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                ru.example.xmljson.Employee employee = new ru.example.xmljson.Employee(
                        Long.parseLong(getTagValue("id", element)),
                        getTagValue("firstName", element),
                        getTagValue("lastName", element),
                        getTagValue("country", element),
                        Integer.parseInt(getTagValue("age", element))
                );
                employees.add(employee);
            }
        }
        return employees;
    }

    private static String getTagValue(String tag, Element element) {
        return element.getElementsByTagName(tag).item(0).getTextContent();
    }
}