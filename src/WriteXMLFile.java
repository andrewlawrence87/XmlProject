//package com.mkyong.core;
import java.util.Scanner;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WriteXMLFile {

    public static void main(String argv[]) {

        try {
            Scanner reader = new Scanner(System.in);  // Reading from System.in
            System.out.println("What would you like to save the file as: ");
            String fn = reader.nextLine();
            System.out.println("What is the device IMSI: ");
            String InIMSI = reader.nextLine();
            System.out.println("What is the device IMEI: ");
            String InIMEI = reader.nextLine();
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Device");
            doc.appendChild(rootElement);

            // Flow Gen elements
            Element FG = doc.createElement("FlowGen");
            rootElement.appendChild(FG);

            // set attribute to staff element
            Attr attr = doc.createAttribute("SerialNo");
            attr.setValue("123456");
            FG.setAttributeNode(attr);

            // shorten way
            // staff.setAttribute("id", "1");

            // IMSI elements
            Element IMSI = doc.createElement("IMSI");
            IMSI.appendChild(doc.createTextNode(InIMSI));
            FG.appendChild(IMSI);

            // IMEI elements
            Element IMEI = doc.createElement("IMEI");
            IMEI.appendChild(doc.createTextNode(InIMEI));
            FG.appendChild(IMEI);



            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("C:\\PracticeXML\\"+fn+".xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}