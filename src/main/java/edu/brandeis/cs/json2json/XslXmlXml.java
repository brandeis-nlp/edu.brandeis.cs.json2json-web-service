package edu.brandeis.cs.json2json;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by 310201833 on 2015/10/27.
 */
public class XslXmlXml {
    public static String transform(String sourceXml, String templateXsl) throws Exception {
        StreamSource stylesource = new StreamSource(new StringReader(templateXsl.trim()));
        Transformer transformer = TransformerFactory.newInstance().newTransformer(stylesource);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        transformer.transform(new StreamSource(new StringReader(sourceXml.trim())), result);
        return  writer.toString();
    }
}
