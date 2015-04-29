package edu.brandeis.cs.json2json;

import org.lappsgrid.api.WebService;

import java.util.logging.Logger;

/**
 * Created by shi on 4/7/14.
 */
public class Json2JsonWS implements WebService, ITransform {
    protected static Logger log = Logger.getLogger(Json2JsonWS.class.getName());

//    @Override
//    public Data execute(Data input) {
//        if (input == null || input.getDiscriminator() != Types.JSON) {
//            throw new UnknownFormatFlagsException("Only JSON format is valid. But your input Discriminator is not Types.JSON");
//        }
//        String payload = input.getPayload();
//        Container container = new Container(payload);
////        System.out.println(">>>>>>>>>>>>>>>" + container);
//        String json = container.getText();
//        String template = container.getMetadata().get(Template).toString();
//        Data output  = new Data(Types.JSON);
//        try{
//            String targetJson = Json2Json.transform(template, json);
//            output.setPayload(targetJson);
//        }catch (Json2JsonException e) {
//            output.setPayload(e.toString());
//            output.setDiscriminator(Types.ERROR);
//        }
//        return output;
//    }

    public String json2json(String sourceJson, String templateDsl) throws Exception{
        System.out.println("Json:\n" + sourceJson);
        System.out.println("Dsl:\n" + templateDsl);
        String res = Json2Json.json2json(sourceJson, templateDsl);
        System.out.println("Json2Json:\n" + res);
        return res;
    }

    public String xml2xml(String sourceXml, String templateXsl)  throws Exception{
        System.out.println("Xml:\n" + sourceXml);
        System.out.println("Xsl:\n" + templateXsl);
        String res = Json2Json.xml2xml(sourceXml, templateXsl);
        System.out.println("Xml2Xml:\n" + res);
        return res;
    }

    public String xml2json(String xml) throws Exception {
        System.out.println("Xml:\n" + xml);
        String res = Json2Json.xml2json(xml);
        System.out.println("Xml2Json:\n" + res);
        return res;
    }

    public String json2xml(String json) throws Exception {
        System.out.println("Json:\n" + json);
        String res = Json2Json.json2xml(json);
        System.out.println("Json2Xml:\n" + res);
        return res;
    }

    @Override
    public String execute(String s) {
        return null;
    }

    @Override
    public String getMetadata() {
        return null;
    }
}
