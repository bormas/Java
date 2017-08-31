package ru.ncedu.java.tasks;

import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SimpleXMLImpl implements SimpleXML {
    @Override
    public String createXML(String tagName, String textNode) {
        //initializing string writer
        StringWriter stringWriter = new StringWriter();
        try {
            //making document
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            //creating root
            Element root = doc.createElement(tagName);
            //filling the root with textNode
            root.appendChild(doc.createTextNode(textNode));
            //append root to the document
            doc.appendChild(root);

            //creating transformer
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            //setting properties
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            //creating file by using DOM
            transformer.transform(new DOMSource(doc), new StreamResult(stringWriter));

            //System.out.println("File saved!");
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (TransformerException ex) {
            ex.printStackTrace();
        }
        System.out.println(stringWriter.toString());

        return stringWriter.toString();
    }

    public String rootElement = "";

    @Override
    public String parseRootElement(InputStream xmlStream) throws SAXException {
        //new exception for stopping the parsing process
        class EarlyStop extends SAXException {}

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {
                public void startElement(String uri, String localName,
                                         String qName, Attributes attributes) throws SAXException {
                    rootElement = qName;
                    throw new EarlyStop();
                }

                public void endElement(String uri, String localName,
                                       String qName) throws SAXException {
                    System.out.println("Ending Element: " + qName);

                }
            };

            saxParser.parse(xmlStream, handler);
        } catch (EarlyStop ex) {
            //System.out.println("Everything is ok ");
        } catch (SAXException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(rootElement);
        return rootElement;
    }
}
