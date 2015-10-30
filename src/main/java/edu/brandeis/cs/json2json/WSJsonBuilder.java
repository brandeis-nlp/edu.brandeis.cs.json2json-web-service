package edu.brandeis.cs.json2json;


import edu.brandeis.cs.json.JsonArr;
import edu.brandeis.cs.json.JsonObj;
import org.lappsgrid.discriminator.Discriminators;
/**
 * Created by lapps on 10/22/2014.
 * REF:  http://lapps.github.io/interchange/index.html
 *
 */
public class WSJsonBuilder {
    String discriminator = null;
    JsonObj payload = null;
//    JsonObj text = null;
    JsonObj error = null;
    String context =  "http://vocab.lappsgrid.org/context-1.0.0.jsonld";
    JsonObj metadata = null;
    JsonObj json = null;
    JsonArr targets = null;

    public static enum OperatorType {
        json2jsondsl,
        xml2jsondsl,
        xml2xmlxsl,
        xml2json,
        json2xml
    }
    OperatorType operator = null;
    String [] sources = null;
    String template = null;

    public String[] getSources() {
        return sources;
    }

    public OperatorType getOperator(){
        return operator;
    }

    public WSJsonBuilder() {
        json = new JsonObj();
        discriminator = Discriminators.Uri.JSON_LD;
        payload= new JsonObj();
        metadata = new JsonObj();
        operator = OperatorType.json2jsondsl;
        error = new JsonObj();
        targets = new JsonArr();
    }
//
    public void setDiscriminator(String s) {
        this.discriminator = s;
    }
//
    public String getTemplate() {
        return template;
    }

    public String getDiscriminator() {
        return discriminator;
    }
//
    public WSJsonBuilder(String textjson) {
        json = new JsonObj(textjson);
        discriminator = json.getString("discriminator").trim();
        if(discriminator.equals(Discriminators.Uri.JSON_LD)) {
            payload = (JsonObj)json.getJsonObj("payload");
            metadata = (JsonObj)payload.getJsonObj("metadata");
            if (metadata == null) {
                metadata = new JsonObj();
                operator = OperatorType.json2jsondsl;
            } else {
                if(metadata.getString("op") == null) {
                    operator = OperatorType.json2jsondsl;
                } else {
                    operator =  OperatorType.valueOf(metadata.getString("op").trim().toLowerCase());
                }
                if(operator == OperatorType.json2jsondsl || operator == OperatorType.xml2json) {
                    template = metadata.getString("template");
                }
            }
            JsonArr sourceArr =  (JsonArr)payload.getJsonArr("sources");
            sources = new String[sourceArr.length()];
            for(int i = 0; i < sourceArr.length(); i++) {
                sources[i] = sourceArr.getString(i);
            }
            targets = new JsonArr();
        }
    }

    public void addTarget(String target) {
        targets.add(target);
    }
//
    public JsonObj getJsonObj() {
        return json;
    }

    public String toString(){
        json.put("discriminator" ,discriminator);
        if (discriminator.equals(Discriminators.Uri.JSON_LD)) {
            payload.put("targets", targets);
            payload.put("@context",context);
            payload.put("metadata", metadata);
            json.put("payload" ,payload);
        } else if(discriminator.equals(Discriminators.Uri.ERROR)) {
            json.put("payload" ,error);
        }
        return json.toString();
    }

    public void setError(String msg, String stacktrace) {
        this.setDiscriminator(Discriminators.Uri.ERROR);
        JsonObj val = new JsonObj();
        val.put("@value", msg);
        val.put("stacktrace", stacktrace);
        error.put("text",  val);
    }
}
