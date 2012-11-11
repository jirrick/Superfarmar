package superfarmar.xml;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XMLReceiver { 
	 
	 public static Document receive(InputStream channel) { 
		 Document request = null; 
		 try {
		     DocumentBuilderFactory docBuilderFact = DocumentBuilderFactory.newInstance(); 
		     DocumentBuilder docBuilder = docBuilderFact.newDocumentBuilder(); 	 
		 
		     XMLInputStream xmlin = new XMLInputStream(channel); 
		 
		     xmlin.recive(); 
		 
		     request = docBuilder.parse(xmlin);
		} catch (SAXException | IOException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	 
	     return request; 
	 } 
} 