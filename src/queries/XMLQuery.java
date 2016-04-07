package queries;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public abstract class XMLQuery {

	protected static String APP_ID = "ba29a72f";
	protected static String APP_KEY = "37cdebd4fefea99bfe4cf57d74229601";
	protected static String WEATHER_KEY = "d47d5bad42042d02";
	public XMLQuery(){}

	// Opens connecting to XML
	public static URLConnection getConnection(String u) throws IOException{
		URL url = new URL(u);
		return url.openConnection();
	}

	// Use this to open XML and parse
	public static Document openXML(String url) throws SAXException, IOException, ParserConfigurationException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
	
	    return builder.parse(getConnection(url).getInputStream());
	}

	protected static String xmlToString(Element e) {
		if(e != null){
			Node child = e.getFirstChild();
			if (child instanceof CharacterData) {
				CharacterData cd = (CharacterData) child;
				return cd.getData();
			}
		}
		return null;
	}

	protected Map<String, String> getElements(ArrayList<String> tagNames, String compare, Document doc){
		Map<String, String> elements = new HashMap<String,String>();

		NodeList nodes = doc.getElementsByTagName(tagNames.get(0));
		for (int i = 0; i < nodes.getLength(); i++) {
			Element element = (Element) nodes.item(i);
			Element comparedElement = (Element) element.getElementsByTagName(tagNames.get(1)).item(0);
			if(compare.equals(xmlToString(comparedElement))){
				elements.put(tagNames.get(1),xmlToString(comparedElement));
				for (int index = 2; index < tagNames.size(); index++) {
					Element next = (Element) element.getElementsByTagName(tagNames.get(index)).item(0);
					elements.put(tagNames.get(index),xmlToString(next));
				}
				return elements;
			}	
		}
		return null;
	}


	protected ArrayList<Map<String, String>> getElements(ArrayList<String> tagNames, Document doc){
		ArrayList<Map<String,String>> resultList = new ArrayList<>();

		NodeList nodes = doc.getElementsByTagName(tagNames.get(0));
		if(nodes.getLength() == 0) return null;
		for (int i = 0; i < nodes.getLength(); i++) {
			Element element = (Element) nodes.item(i);
			Map<String, String> elements = new HashMap<String,String>();

			for (int index = 1; index < tagNames.size(); index++) {
				Element next = (Element) element.getElementsByTagName(tagNames.get(index)).item(0);
				elements.put(tagNames.get(index),xmlToString(next));
			}

			resultList.add(elements);
		}	
		return resultList;
	}

	protected ArrayList<Map<String, String>> getElements(ArrayList<String> tagNames, Document doc, String w){
		System.out.println("Exception here");
		ArrayList<Map<String,String>> resultList = new ArrayList<>();

		NodeList nodes1 = doc.getElementsByTagName(tagNames.get(0));
		NodeList nodes = nodes1.item(1).getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			// Checks if node is an element otherwise you get an exception
			if(nodes.item(i).getNodeType() == Node.ELEMENT_NODE){
				Element element = (Element) nodes.item(i);
				Map<String, String> elements = new HashMap<String,String>();

				for (int index = 1; index < tagNames.size(); index++) {
					Element next = (Element) element.getElementsByTagName(tagNames.get(index)).item(0);
					elements.put(tagNames.get(index),xmlToString(next));
				}

				resultList.add(elements);
			}
		}	
		return resultList;
	}
}
