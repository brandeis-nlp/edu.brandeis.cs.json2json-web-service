package edu.brandeis.cs.json2json;

import junit.framework.Assert;
import org.apache.commons.io.FileUtils;
import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Test;
import org.lappsgrid.json2json.Json2Json;

import java.io.File;
import java.util.List;

/**
 * Created by lapps on 4/29/2015.
 */
public class TestJson2Json {
    public static String readResource(String filename) throws Exception {
        File objFile = new File(TestWebService.class.getResource("/" + filename).toURI());
        return FileUtils.readFileToString(objFile, "UTF-8");
    }
    public static void assertXMLEquals(String expectedXML, String actualXML) throws Exception {
        XMLUnit.setIgnoreWhitespace(true);
        XMLUnit.setIgnoreAttributeOrder(true);

        DetailedDiff diff = new DetailedDiff(XMLUnit.compareXML(expectedXML, actualXML));

        List<?> allDifferences = diff.getAllDifferences();
        Assert.assertEquals("Differences found: " + diff.toString(), 0, allDifferences.size());
    }

    @Test
    public void testXml2XmlXsl()throws Exception{
        String xml = readResource("cdcatalog.xml");
        String xsl = readResource("cdcatalog.xsl");
        String target = readResource("cdcatalog_with_xsl.html");
        String result = Json2Json.xml2xmlxsl(xml, xsl);
        Assert.assertEquals(target.replaceAll("\\s", ""), result.replaceAll("\\s", ""));
    }

    @Test
    public void testXml2JSon()throws Exception{
        System.out.println(Json2Json.xml2json("<good attr=\"y\">Hel<!--This is Comment.--><!--This is Comment.-->lo<x>OK</x><x>OK1</x><x>OK2</x>World</good>"));
        System.out.println(Json2Json.xml2json("<!--This is Comment.--><good attr=\"y\">Hello<x>OK</x><x>OK1</x><x>OK2</x>World</good>"));
        String xml = readResource("GATEANNIE.xml");
        String target = readResource("GATEANNIE.json");
        String result = Json2Json.xml2json(xml);
        Assert.assertEquals(target.replaceAll("\\s", ""), result.replaceAll("\\s", ""));
    }

    @Test
    public void testJSon2Xml()throws Exception{
        System.out.println(Json2Json.json2xml("{\"good\":{\"@attr\":\"y\",\"#comment\":[\"This is Comment.\",\"This is Comment.\"],\"__text__\":[\"Hel\",\"lo\"],\"x\":[\"OK\",\"OK1\",{\"__tail__\":\"World\",\"__text__\":\"OK2\"}]}}"));
        System.out.println(Json2Json.json2xml("{\"good\":{\"@attr\":\"y\",\"#comment\":\"This is Comment.\",\"__text__\":[\"Hel\",\"lo\"],\"x\":[\"OK\",\"OK1\",{\"__tail__\":\"World\",\"__text__\":\"OK2\"}]}}"));
        String json = readResource("GATEANNIE.json");
        String target = readResource("GATEANNIE.xml");
        String result = Json2Json.json2xml(json);
//        System.out.println(target);
//        System.out.println(result);
        assertXMLEquals(target, result);
    }

    @Test
    public void testJson2JsonDsl()throws Exception{
        String json = readResource("jsondsl.source.json");
        String dsl = readResource("jsondsl.template.dsl");
        String target = readResource("jsondsl.target.json");
        String result = Json2Json.json2jsondsl(json, dsl);
        Assert.assertEquals(target.replaceAll("\\s", ""), result.replaceAll("\\s", ""));
    }

    @Test
    public void testXml2JsonDsl()throws Exception{
        String xml = readResource("cdcatalog.xml");
        String dsl = readResource("cdcatalog.dsl");
        String target = readResource("jsondsl.target.json");
        String result = Json2Json.xml2jsondsl(xml, dsl);
        System.out.println(result);
        Assert.assertEquals(target.replaceAll("\\s", ""), result.replaceAll("\\s", ""));
    }

}
