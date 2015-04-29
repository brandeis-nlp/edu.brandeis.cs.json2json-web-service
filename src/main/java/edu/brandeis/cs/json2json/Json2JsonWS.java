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
        return Json2Json.json2json(sourceJson, templateDsl);
    }

    public String xml2xml(String sourceXml, String templateXsl)  throws Exception{
        return Json2Json.xml2xml(sourceXml, templateXsl);
    }

    public String xml2json(String xml) throws Exception {
        return Json2Json.xml2json(xml);
    }

    public String json2xml(String json) throws Exception {
        return Json2Json.json2xml(json);
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
