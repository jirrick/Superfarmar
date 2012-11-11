package superfarmar.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLDocumentFactory {
	
	private Document document;
	private Element root;
	private String root_name;
	
	public XMLDocumentFactory(String rootElementName) throws ParserConfigurationException {
		this.root_name = rootElementName;
		Clear();
	}
	
	public void Clear() throws ParserConfigurationException{
		document = null;
		root = null;
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		document = builder.newDocument();
		
		root = document.createElement(root_name);
		document.appendChild(root);
	}
	
	public void AddSimpleElement(String name, String value){
		Element newElement = document.createElement(name);
		newElement.appendChild(document.createTextNode(value));
		root.appendChild(newElement);	
	}
	
	public Document GetDocument(){
		return document;
	}
	
	public void WriteToXMLFile(String filename) throws TransformerFactoryConfigurationError, TransformerException{
        Source source = new DOMSource(document);

        File file = new File(filename);
        Result result = new StreamResult(file);

        Transformer tf = TransformerFactory.newInstance().newTransformer();
        tf.transform(source, result);
	}
}
