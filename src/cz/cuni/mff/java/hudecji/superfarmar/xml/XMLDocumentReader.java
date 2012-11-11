package superfarmar.xml;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLDocumentReader {
	
	private Document document;
	private Element root;
	
	public XMLDocumentReader(Document document){
		this.document = document;
		this.root = document.getDocumentElement();
	}
	
	public XMLDocumentReader(String filename) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder loader = factory.newDocumentBuilder();
		
			this.document = loader.parse("sample.xml");
			this.root = document.getDocumentElement();
		} catch (SAXException | IOException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String GetRootName(){
		return root.getNodeName();
	}
	
	public String GetElementValue(String elementName){
		Element e;
		NodeList nodes = document.getElementsByTagName(elementName);
		  if (nodes.getLength()>0) {
			  e = (Element) nodes.item(0);
			  return e.getNodeValue();
		  } else {
			  return "";
		  }
	}

}
