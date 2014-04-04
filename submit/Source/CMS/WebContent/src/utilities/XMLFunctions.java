package utilities;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

/**
Leslie Ducray - 2014
 */
public class XMLFunctions {
			
			//XML doc is passed through
			public static String xmlToString(Document doc) throws Exception{
			String xmlString =null;
	        
			//Below code taken from http://stackoverflow.com/questions/5456680/xml-document-to-string
			//user: WhiteFang34
			//answered: Mar 28 '11 at 9:11
			
			TransformerFactory transfac = TransformerFactory.newInstance();
	        Transformer trans = transfac.newTransformer();
	        trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
	        trans.setOutputProperty(OutputKeys.INDENT, "yes");
	        StringWriter sw = new StringWriter();
	        StreamResult result = new StreamResult(sw);
	        DOMSource source = new DOMSource(doc);
	        trans.transform(source, result);
	        xmlString = sw.toString();
	        //end extracted code
		
			return xmlString; //string is returned
		}
		
}


