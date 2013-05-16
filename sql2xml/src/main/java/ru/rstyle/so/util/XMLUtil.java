package ru.rstyle.so.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public final class XMLUtil {

    private XMLUtil() {

    }

    private static final Logger LOG = LoggerFactory.getLogger(XMLUtil.class
            .getName());

    public static Document toLowerCase(Document doc) {

        final InputStream xslPath = XMLUtil.class
                .getResourceAsStream("/toLowerCase.xslt");

        TransformerFactory factory = TransformerFactory.newInstance();
        DOMResult r = new DOMResult();

        try {
            Templates template = factory
                    .newTemplates(new StreamSource(xslPath));
            Transformer transformer = template.newTransformer();

            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,
                    "yes");

            transformer.transform(new DOMSource(doc), r);

        } catch (TransformerConfigurationException e) {
            LOG.error("{}", e);
        } catch (TransformerException e) {
            LOG.error("{}", e);
        }

        return (Document) r.getNode();

    }

    public static Document loadFromFile(String xmlFile) {

        File fXmlFile = new File(xmlFile);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            return doc;
        } catch (ParserConfigurationException e) {
            LOG.error("{}", e);
        } catch (SAXException e) {
            LOG.error("{}", e);
        } catch (IOException e) {
            LOG.error("{}", e);
        }

        return null;
    }

    public static void print(Document doc, OutputStream out) {

        try {
            Transformer transformer = getTransformer();

            transformer.setOutputProperty(
                    "{http://xml.apache.org/xslt}indent-amount", "4");

            transformer.transform(new DOMSource(doc), new StreamResult(
                    new OutputStreamWriter(out)));
        } catch (TransformerConfigurationException e) {
            LOG.error("{}", e);
        } catch (TransformerException e) {
            LOG.error("{}", e);
        }
    }

    public static String toString(Document doc) {

        StringWriter sw = new StringWriter();
        try {
            Transformer transformer = getTransformer();
            transformer.transform(new DOMSource(doc), new StreamResult(sw));
        } catch (TransformerException e) {
            LOG.error("{}", e);
        }

        return sw.toString();

    }

    public static Document getDOM(String strData) {

        Document d = null;

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(strData));

            d = builder.parse(is);

        } catch (ParserConfigurationException e) {
            LOG.error("{}", e);
        } catch (SAXException e) {
            LOG.error("{}", e);
        } catch (IOException e) {
            LOG.error("{}", e);
        }

        return d;

    }

    private static Transformer getTransformer() {
        TransformerFactory tf = TransformerFactory.newInstance();

        Transformer transformer = null;
        try {
            transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,
                    "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        } catch (TransformerConfigurationException e) {
            LOG.error("{}", e);
        }

        return transformer;
    }

}
