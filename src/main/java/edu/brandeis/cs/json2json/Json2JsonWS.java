package edu.brandeis.cs.json2json;

import edu.brandeis.cs.json.JsonProxy;
import edu.brandeis.cs.json.JsonSerialization;
import org.apache.commons.io.IOUtils;
import org.lappsgrid.api.WebService;
import org.lappsgrid.discriminator.Discriminators;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

/**
 * Created by shi on 4/7/14.
 */
public class Json2JsonWS implements WebService, ITransform {
    protected static Logger log = Logger.getLogger(Json2JsonWS.class.getName());

    public String json2jsondsl(String sourceJson, String templateDsl) throws Exception{
        System.out.println("\n--------------------json2jsondsl----------------");
        System.out.println("Json:\n\t\t" + sourceJson);
        System.out.println("Dsl:\n\t\t" + templateDsl);
        String res = Json2Json.json2jsondsl(sourceJson, templateDsl);
        System.out.println("Json2JsonDsl:\n\t\t" + res);
        return res;
    }

    @Override
    public String xml2jsondsl(String sourceXml, String templateDsl) throws Exception {
        System.out.println("\n--------------------xml2jsondsl----------------");
        System.out.println("Xml:\n\t\t" + sourceXml);
        System.out.println("Xsl:\n\t\t" + templateDsl);
        String res = Json2Json.xml2jsondsl(sourceXml, templateDsl);
        System.out.println("Xml2JsonDsl:\n\t\t" + res);
        return res;
    }

    public String xml2xmlxsl(String sourceXml, String templateXsl)  throws Exception{
        System.out.println("\n--------------------xml2xmlxsl----------------");
        System.out.println("Xml:\n\t\t" + sourceXml);
        System.out.println("Xsl:\n\t\t" + templateXsl);
        String res = Json2Json.xml2xmlxsl(sourceXml, templateXsl);
        System.out.println("Xml2XmlXsl:\n\t\t" + res);
        return res;
    }

    public String xml2json(String xml) throws Exception {
        System.out.println("\n--------------------xml2json----------------");
        System.out.println("Xml:\n\t\t" + xml);
        String res = Json2Json.xml2json(xml);
        System.out.println("Xml2Json:\n\t\t" + res);
        return res;
    }

    public String json2xml(String json) throws Exception {
        System.out.println("\n--------------------json2xml----------------");
        System.out.println("Json:\n\t\t" + json);
        String res = Json2Json.json2xml(json);
        System.out.println("Json2Xml:\n\t\t" + res);
        return res;
    }

    @Override
    public String execute(String s) {
        JsonSerialization json = null;
        try{
            s = s.trim();
            if (s.startsWith("{") && s.endsWith("}")) {
                json = new JsonSerialization(s);
                if (json.getDiscriminator().equals(Discriminators.Uri.ERROR)) {
                    return json.toString();
                }
            } else {
                json = new JsonSerialization();
                json.setError("Only JSON imput is allowed!", "Unkown input: " + s);
                return json.toString();
            }
            return execute(json);
        }catch(Throwable th) {
            json = new JsonSerialization();
            StringWriter sw = new StringWriter();
            th.printStackTrace( new PrintWriter(sw));
            json.setError(th.toString(), sw.toString());
            System.err.println(sw.toString());
            return json.toString();
        }
    }

    public String execute(JsonSerialization json) throws Exception {
        for (String source : json.getSources()) {
            String target = null;
            if (json.getOperator() == JsonSerialization.OperatorType.json2jsondsl) {
                String template = json.getTemplate();
                target = json2jsondsl(source, template);
            }else if (json.getOperator() == JsonSerialization.OperatorType.xml2jsondsl) {
                String template = json.getTemplate();
                target = xml2jsondsl(source, template);
            }else if (json.getOperator() == JsonSerialization.OperatorType.xml2xmlxsl) {
                String template = json.getTemplate();
                target = xml2xmlxsl(source, template);
            }else if (json.getOperator() == JsonSerialization.OperatorType.xml2json) {
                target = xml2json(source);
            }else if (json.getOperator() == JsonSerialization.OperatorType.json2xml) {
                target = json2xml(source);
            }
            json.addTarget(target);
        }
        return json.toString();
    }


    @Override
    public String getMetadata() {
        {
            // get caller name using reflection
            String name = this.getClass().getName();
            //
            String resName = "/metadata/"+ name +".json";
            System.out.println("load resources:" + resName);

            try {
                String meta = IOUtils.toString(this.getClass().getResourceAsStream(resName));
                JsonProxy.JsonObject json = JsonProxy.newObject();
                json.put("discriminator", Discriminators.Uri.META);
                json.put("payload", JsonProxy.newObject().read(meta));
                System.out.println("---------------------META:-------------------\n" + json.toString());
                return json.toString();
            }catch (Throwable th) {
                JsonProxy.JsonObject json = JsonProxy.newObject();
                json.put("discriminator", Discriminators.Uri.ERROR);
                JsonProxy.JsonObject error = JsonProxy.newObject();
                error.put("class", name);
                error.put("error", "NOT EXIST: " + resName);
                error.put("message", th.getMessage());
                StringWriter sw = new StringWriter();
                th.printStackTrace( new PrintWriter(sw));
                System.err.println(sw.toString());
                error.put("stacktrace", sw.toString());
                json.put("payload", error);
                return json.toString();
            }
        }
    }
}
