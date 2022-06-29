package Utilities;

import Pages.AmazonSearchPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;


public class XMLHandler {
    static Logger log = LogManager.getLogger(XMLHandler.class);
    public static List<String> GetXPathData(String Filepath, String xpathExpression) {
        List<String> values = new ArrayList<>();
        try {
            File file = new File(Filepath);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            XPath xPath = XPathFactory.newInstance().newXPath();
            NodeList nodes;
            nodes = (NodeList) xPath.compile(xpathExpression).evaluate(
                    doc, XPathConstants.NODESET);
            for (int i = 0; i < nodes.getLength(); i++) {
                values.add(nodes.item(i).getNodeValue());
            }
        } catch (IOException | ParserConfigurationException | SAXException | XPathExpressionException e) {
            e.printStackTrace();
        }
        return values;
    }


    public static String updateXML(String Filepath, String xpathExpression, String newValue)
    {
        try {
            File file = new File(Filepath);
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document;
            document = builder.parse(file);
            XPath xpath = XPathFactory.newInstance().newXPath();
            Element element = (Element)xpath.evaluate(xpathExpression, document, XPathConstants.NODE);
            element.setTextContent(newValue);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StringWriter stringWriter = new StringWriter();
            StreamResult result = new StreamResult(stringWriter);
            StreamResult resultToFile = new StreamResult(new File(Filepath));
            transformer.transform(domSource, resultToFile);
            transformer.transform(domSource, result);
            Filepath = stringWriter.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return Filepath;
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
                    log.info("Data not processed for node: " + pair.getValue());
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
            log.info("The deleted file is : " + Obj.getName());
        } else {
            log.info("Failed in deleting the file.");
        }
    }


}







