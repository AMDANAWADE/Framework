package Utilities;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;


public class XMLHandler {
    public static String GetTagValue(String Filepath, String node_xpath, Integer node_index,String TagName) {
        String tagValue = null;
        try {
            File file = new File(Filepath);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            System.out.println("Root element name is: " + doc.getDocumentElement().getNodeName());
            NodeList nodeList;
            XPath xPath = XPathFactory.newInstance().newXPath();
            nodeList = (NodeList) xPath.compile(node_xpath).evaluate(
                    doc, XPathConstants.NODESET);
            Node node = nodeList.item(node_index);
            Element element = (Element) node;
            tagValue = element.getElementsByTagName(TagName).item(0).getTextContent();
        } catch (IOException | ParserConfigurationException | SAXException | XPathExpressionException e) {
            e.printStackTrace();
        }
        return tagValue;
    }
    public static Map<String, String> GetData(String Filepath, String node_xpath, Integer node_index) {
        Map<String, String> data = new HashMap<>();
        try {
            File file = new File(Filepath);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList;

            XPath xPath = XPathFactory.newInstance().newXPath();
            nodeList = (NodeList) xPath.compile(node_xpath).evaluate(
                    doc, XPathConstants.NODESET);
            Node node = nodeList.item(node_index);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                data.put("TC_NAME", element.getElementsByTagName("TC_NAME").item(0).getTextContent());
                data.put("BROWSER", element.getElementsByTagName("BROWSER").item(0).getTextContent());
                data.put("USERNAME", element.getElementsByTagName("USERNAME").item(0).getTextContent());
                data.put("PASSWORD", element.getElementsByTagName("PASSWORD").item(0).getTextContent());
                data.put("PRODUCT", element.getElementsByTagName("PRODUCT").item(0).getTextContent());
                data.put("EMAIL", element.getElementsByTagName("EMAIL").item(0).getTextContent());
                data.put("NAME", element.getElementsByTagName("NAME").item(0).getTextContent());
                data.put("MOBILE", element.getElementsByTagName("MOBILE").item(0).getTextContent());
                data.put("ADDRESS", element.getElementsByTagName("ADDRESS").item(0).getTextContent());
                data.put("SEARCHITEM", element.getElementsByTagName("SEARCHITEM").item(0).getTextContent());
            }
        } catch (IOException | ParserConfigurationException | SAXException | XPathExpressionException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static List<Map<String, String>> GetAllData(String Filepath, String node_xpath) {
        List<Map<String, String>> xmlData = new ArrayList<>();
        try {
            File file = new File(Filepath);
            //Create a new object of DocumentBuilderFactory
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //Create an object DocumentBuilder to parse the XML file data
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList;
            XPath xPath = XPathFactory.newInstance().newXPath();
            nodeList = (NodeList) xPath.compile(node_xpath).evaluate(
                    doc, XPathConstants.NODESET);
            Map<String, String> data = new HashMap<>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                System.out.println("\n" + "(" + i + ")" + " Child Node Name :" + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    data.put("TC_NAME", element.getElementsByTagName("TC_NAME").item(0).getTextContent());
                    data.put("BROWSER", element.getElementsByTagName("BROWSER").item(0).getTextContent());
                    data.put("USERNAME", element.getElementsByTagName("USERNAME").item(0).getTextContent());
                    data.put("PASSWORD", element.getElementsByTagName("PASSWORD").item(0).getTextContent());
                    data.put("PRODUCT", element.getElementsByTagName("PRODUCT").item(0).getTextContent());
                    data.put("EMAIL", element.getElementsByTagName("EMAIL").item(0).getTextContent());
                    data.put("NAME", element.getElementsByTagName("NAME").item(0).getTextContent());
                    data.put("MOBILE", element.getElementsByTagName("MOBILE").item(0).getTextContent());
                    data.put("ADDRESS", element.getElementsByTagName("ADDRESS").item(0).getTextContent());
                    data.put("SEARCHITEM", element.getElementsByTagName("SEARCHITEM").item(0).getTextContent());
                }
                xmlData.add(data);
            }
        } catch (IOException | ParserConfigurationException | SAXException | XPathExpressionException e) {
            e.printStackTrace();
        }
        return xmlData;
    }

    public static String transformToXML(Map<String, String> pathValueMap, String delimiter, String Filepath)
            throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        Element rootElement = null;
        Iterator<Map.Entry<String, String>> it = pathValueMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> pair = it.next();
            if (pair.getKey() != null && pair.getKey() != "" && rootElement == null) {
                String[] pathValuesplit = pair.getKey().split(delimiter);
                rootElement = document.createElement(pathValuesplit[0]);
                break;
            }
        }
        document.appendChild(rootElement);
        Element rootNode = rootElement;
        Iterator<Map.Entry<String, String>> iterator = pathValueMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> pair = iterator.next();
            if (pair.getKey() != null && pair.getKey() != "" && rootElement != null) {
                String[] pathValuesplit = pair.getKey().split(delimiter);
                if (pathValuesplit[0].equals(rootElement.getNodeName())) {
                    int i = pathValuesplit.length;
                    Element parentNode = rootNode;
                    int j = 1;
                    while (j < i) {
                        Element child = null;
                        NodeList childNodes = parentNode.getChildNodes();
                        for (int k = 0; k < childNodes.getLength(); k++) {
                            if (childNodes.item(k).getNodeName().equals(pathValuesplit[j])
                                    && childNodes.item(k) instanceof Element) {
                                child = (Element) childNodes.item(k);
                                break;
                            }
                        }
                        if (child == null) {
                            child = document.createElement(pathValuesplit[j]);
                            if (j == (i - 1)) {
                                child.appendChild(
                                        document.createTextNode(pair.getValue() == null ? "" : pair.getValue()));
                            }
                        }
                        parentNode.appendChild(child);
                        parentNode = child;
                        j++;
                    }
                } else {
                    // ignore any other root - add logger
                    System.out.println("Data not processed for node: " + pair.getValue());
                }
            }
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        StreamResult resultToFile = new StreamResult(new File(Filepath));
        transformer.transform(domSource, resultToFile);
        transformer.transform(domSource, result);
        return writer.toString();
    }

    public static void deletexml(String filepath) {
        File Obj = new File(filepath);
        if (Obj.delete()) {
            System.out.println("The deleted file is : " + Obj.getName());
        } else {
            System.out.println("Failed in deleting the file.");
        }
    }

}







