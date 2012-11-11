package superfarmar.xml;

import java.io.*;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class XMLSender { 
	 
	 public static void send(Document tosend, OutputStream channel) throws   IOException, TransformerException { 
	     XMLOutputStream out = new XMLOutputStream(channel); 
	 
	     StreamResult sr = new StreamResult(out); 
	     DOMSource ds = new DOMSource(tosend); 
	     Transformer tf = TransformerFactory.newInstance().newTransformer(); 
	 
	     tf.transform(ds, sr); 
	 
	     out.send(); 
	 } 
} 