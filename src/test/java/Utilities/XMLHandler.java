package Utilities;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
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
    private static final String EMAIL = "supriya4@deloitte.com";
    private static final String MOBILE = "7349017534";
    private static Object XPathExpression;

    public static void GetData(String Filepath) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder;
        Document doc = null;
        try
        {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(Filepath);

            // Create XPathFactory object
            XPathFactory xpathFactory = XPathFactory.newInstance();

            // Create XPath object
            XPath xpath = xpathFactory.newXPath();

            List<String> nameWithMobile = getUserNameWithMobile(doc, xpath,"7349017534");
            System.out.println("User Name with mobile no:" + Arrays.toString(nameWithMobile.toArray()));

            List<String> nameWithEmail = getUserNameWithEmail(doc, xpath, "supriya4@deloitte.com");
            System.out.println("User Name with Email:" + Arrays.toString(nameWithEmail.toArray()));

            // To Get EMAIL
            List<String> email = getEmail(doc, xpath);
            System.out.println("Email:" + Arrays.toString(email.toArray()));

            // To Get NAME
            List<String> name = getName(doc, xpath);
            System.out.println("Name:" + Arrays.toString(name.toArray()));

            // To Get TC_NAME
            List<String> tc_name = getTC_Name(doc, xpath);
            System.out.println("TC_Name:" + Arrays.toString(tc_name.toArray()));

            // To Get BROWSER
            List<String> browser = getBROWSRE(doc, xpath);
            System.out.println("Browser:" + Arrays.toString(browser.toArray()));

            // To Get USERNAME
            List<String> username = getUSERNAME(doc, xpath);
            System.out.println("Username:" + Arrays.toString(username.toArray()));

            // To Get PASSWORD
            List<String> password = getPASSWORD(doc, xpath);
            System.out.println("Password:" + Arrays.toString(password.toArray()));

            // To Get PRODUCT
            List<String> product = getPRODUCT(doc, xpath);
            System.out.println("Product:" + Arrays.toString(product.toArray()));

            // To Get MOBILE
            List<String> mobile = getMOBILE(doc, xpath);
            System.out.println("Mobile:" + Arrays.toString(mobile.toArray()));

        }
        catch (ParserConfigurationException | SAXException | IOException e)
        {
            e.printStackTrace();
        }
    }

    private static List<String> getMOBILE(Document doc, XPath xpath) {
        List<String> list = new ArrayList<>();
        try {
            //create XPathExpression object
            javax.xml.xpath.XPathExpression expr = xpath.compile("//amazon/data/MOBILE/text()");
            //evaluate expression result on XML document
            NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodes.getLength(); i++)
                list.add(nodes.item(i).getNodeValue());
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static List<String> getPRODUCT(Document doc, XPath xpath) {
        List<String> list = new ArrayList<>();
        try {
            //create XPathExpression object
            XPathExpression expr = xpath.compile("//amazon/data/PRODUCT/text()");
            //evaluate expression result on XML document
            NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodes.getLength(); i++)
                list.add(nodes.item(i).getNodeValue());
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static List<String> getPASSWORD(Document doc, XPath xpath) {
        List<String> list = new ArrayList<>();
        try {
            //create XPathExpression object
            XPathExpression expr = xpath.compile("//amazon/data/PASSWORD/text()");
            //evaluate expression result on XML document
            NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodes.getLength(); i++)
                list.add(nodes.item(i).getNodeValue());
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static List<String> getUSERNAME(Document doc, XPath xpath) {
        List<String> list = new ArrayList<>();
        try {
            //create XPathExpression object
            XPathExpression expr = xpath.compile("//amazon/data/USERNAME/text()");
            //evaluate expression result on XML document
            NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodes.getLength(); i++)
                list.add(nodes.item(i).getNodeValue());
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static List<String> getBROWSRE(Document doc, XPath xpath) {
        List<String> list = new ArrayList<>();
        try {
            //create XPathExpression object
            XPathExpression expr = xpath.compile("//amazon/data/BROWSER/text()");
            //evaluate expression result on XML document
            NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodes.getLength(); i++)
                list.add(nodes.item(i).getNodeValue());
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static List<String> getEmail(Document doc, XPath xpath) {
        List<String> list = new ArrayList<>();
        try {
            //create XPathExpression object
            XPathExpression expr = xpath.compile("//amazon/data/EMAIL/text()");
            //evaluate expression result on XML document
            NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodes.getLength(); i++)
                list.add(nodes.item(i).getNodeValue());
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static List<String> getName(Document doc, XPath xpath) {
        List<String> list = new ArrayList<>();
        try {
            //create XPathExpression object
            XPathExpression expr = xpath.compile("//amazon/data/NAME/text()");
            //evaluate expression result on XML document
            NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodes.getLength(); i++)
                list.add(nodes.item(i).getNodeValue());
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static List<String> getTC_Name(Document doc, XPath xpath) {
        List<String> list = new ArrayList<>();
        try {
            //create XPathExpression object
            XPathExpression expr = xpath.compile("//amazon/data/TC_NAME/text()");
            //evaluate expression result on XML document
            NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodes.getLength(); i++)
                list.add(nodes.item(i).getNodeValue());
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static List<String> getUserNameWithEmail(Document doc, XPath xpath, String s) {
        List<String> list = new ArrayList<>();
        try {
            XPathExpression expr = xpath.compile("/amazon/data[EMAIL='" + EMAIL + "']/NAME/text()");
            NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodes.getLength(); i++)
                list.add(nodes.item(i).getNodeValue());
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static List<String> getUserNameWithMobile(Document doc, XPath xpath, String s) {
        List<String> list = new ArrayList<>();
        try {
            XPathExpression expr = xpath.compile("/amazon/data[MOBILE='" + MOBILE + "']/NAME/text()");
            NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodes.getLength(); i++)
                list.add(nodes.item(i).getNodeValue());
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void GetAllData(String Filepath)
    {
        try {
            File file = new File(Filepath);
            //Create a new object of DocumentBuilderFactory
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //Create an object DocumentBuilder to parse the XML file data
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);

            doc.getDocumentElement().normalize();
            System.out.println("Root element name is: " + doc.getDocumentElement().getNodeName());
            //Creating a list of nodes present in the XML file
            NodeList nodeList = doc.getElementsByTagName("data");

            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/amazon/data";
            nodeList = (NodeList) xPath.compile(expression).evaluate(
                    doc, XPathConstants.NODESET);


            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                System.out.println("\n" + "(" + i + ")" + " Child Node Name :" + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("TC_NAME: " + element.getElementsByTagName("TC_NAME").item(0).getTextContent());
                    System.out.println("BROWSER: " + element.getElementsByTagName("BROWSER").item(0).getTextContent());
                    System.out.println("USERNAME: " + element.getElementsByTagName("USERNAME").item(0).getTextContent());
                    System.out.println("PASSWORD: " + element.getElementsByTagName("PASSWORD").item(0).getTextContent());
                    System.out.println("PRODUCT: " + element.getElementsByTagName("PRODUCT").item(0).getTextContent());
                    System.out.println("EMAIL: " + element.getElementsByTagName("EMAIL").item(0).getTextContent());
                    System.out.println("NAME: " + element.getElementsByTagName("NAME").item(0).getTextContent());
                    System.out.println("MOBILE: " + element.getElementsByTagName("MOBILE").item(0).getTextContent());
                    System.out.println("ADDRESS: " + element.getElementsByTagName("ADDRESS").item(0).getTextContent());
                    System.out.println("SEARCHITEM: " + element.getElementsByTagName("SEARCHITEM").item(0).getTextContent());
                }
            }
        } catch (IOException | ParserConfigurationException | SAXException | XPathExpressionException e)
        {
            e.printStackTrace();
        }
    }

    static public String transformToXML(Map<String, String> pathValueMap, String delimiter, String Filepath)
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

        // to return a XMLstring in response to an API
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);

        StreamResult resultToFile = new StreamResult(new File(Filepath));
        transformer.transform(domSource, resultToFile);
        transformer.transform(domSource, result);

        return writer.toString();
    }

    public static void writexml(String filepath,Document doc,String TC_NAME, String BROWSER, String USERNAME,String PASSWORD, String PRODUCT, String EMAIL, String NAME, String MOBILE, String ADDRESS, String SEARCHITEM)
    {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.newDocument();

            //add elements to document
            Element rootElement = doc.createElement("amazon");

            //append root element to document
            doc.appendChild(rootElement);

            //append first child element to root element
            rootElement.appendChild(createUserElement(doc,TC_NAME, BROWSER, USERNAME, PASSWORD, PRODUCT, EMAIL, NAME, MOBILE, ADDRESS, SEARCHITEM));

            //for output to file, console
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            //for print
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            //write to cosole or file
            StreamResult console = new StreamResult(System.out);
            StreamResult file = new StreamResult(new File(filepath));

            //write data
            transformer.transform(source, console);
            transformer.transform(source, file);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static Node createUserElement(Document doc, String TC_NAME, String BROWSER, String USERNAME,String PASSWORD, String PRODUCT, String EMAIL, String NAME, String MOBILE, String ADDRESS, String SEARCHITEM)
    {

        Element Data = doc.createElement("data");

        //create TC_NAME element
        Data.appendChild(createUserElement(doc, Data, "TC_NAME", TC_NAME));

        //create BROWSER element
        Data.appendChild(createUserElement(doc, Data, "BROWSER", BROWSER));

        //create USERNAME element
        Data.appendChild(createUserElement(doc, Data, "USERNAME", USERNAME));

        //create PASSWORD element
        Data.appendChild(createUserElement(doc, Data, "PASSWORD", PASSWORD));

        //create PRODUCT element
        Data.appendChild(createUserElement(doc, Data, "PRODUCT", PRODUCT));

        //create EMAIL element
        Data.appendChild(createUserElement(doc, Data, "EMAIL", EMAIL));

        //create NAME element
        Data.appendChild(createUserElement(doc, Data, "NAME", NAME));

        //create MOBILE element
        Data.appendChild(createUserElement(doc, Data, "MOBILE", MOBILE));

        //create ADDRESS element
        Data.appendChild(createUserElement(doc, Data, "ADDRESS", ADDRESS));

        //create SEARCHITEM element
        Data.appendChild(createUserElement(doc, Data, "SEARCHITEM", SEARCHITEM));

        return Data;
    }

    //utility method to create text node
    private static Node createUserElement(Document doc, Element element, String name, String value)
    {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

    public static void deletexml(String filepath)
    {
        File Obj = new File(filepath);
        if (Obj.delete()) {
            System.out.println("The deleted file is : " + Obj.getName());
        }
        else {
            System.out.println("Failed in deleting the file.");
        }
    }

}
