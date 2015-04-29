package edu.brandeis.cs.json;

import edu.brandeis.cs.json.JsonProxy.JsonArray;
import edu.brandeis.cs.json.JsonProxy.JsonObject;
import org.lappsgrid.discriminator.Discriminators;

/**
 * Created by lapps on 10/22/2014.
 * REF:  http://lapps.github.io/interchange/index.html
 *
 */
public class JsonSerialization {
    String discriminator = null;
    JsonObject payload = null;
//    JsonObject text = null;
    JsonObject error = null;
    String context =  "http://vocab.lappsgrid.org/context-1.0.0.jsonld";
    JsonObject metadata = null;
    JsonObject json = null;
    JsonArray targets = null;

    public static enum OperatorType {
        json2json,
        xml2json,
        json2xml,
        xml2xml
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

    //    JsonArray views = null;

//
//    String idHeader = "";
//    int id = 0;
//
//    public void setIdHeader(String idh) {
//        idHeader = idh;
//        id = 0; // reset.
//    }
//
//    public String getText() {
//        return text.getString("@value");
//    }
//
//    public void setText (String text) {
//        this.text.put("@value", text);
//    }
//
    public JsonSerialization() {
        discriminator = Discriminators.Uri.JSON_LD;
        payload= JsonProxy.newObject();
        metadata = JsonProxy.newObject();
        operator = OperatorType.json2json;
        error = JsonProxy.newObject();
        targets = JsonProxy.newArray();
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
    public JsonSerialization(String textjson) {
        json = JsonProxy.newObject().read(textjson);
        discriminator = json.get("discriminator").toString().trim();
        if(discriminator.equals(Discriminators.Uri.JSON_LD)) {
            payload = (JsonObject)json.get("payload");
            metadata = (JsonObject)json.get("metadata");
            if (metadata == null) {
                metadata = JsonProxy.newObject();
                operator = OperatorType.json2json;
            } else {
                if(metadata.get("op") == null) {
                    operator = OperatorType.json2json;
                } else {
                    operator =  OperatorType.valueOf(metadata.get("op").toString().trim().toLowerCase());
                }
                if(operator == OperatorType.json2json || operator == OperatorType.xml2json) {
                    template = metadata.get("template").toString();
                }
            }
            JsonArray sourceArr =  (JsonArray)payload.get("sources");
            sources = new String[sourceArr.length()];
            for(int i = 0; i < sourceArr.length(); i++) {
                sources[i] = sourceArr.get(i).toString();
            }
            targets = JsonProxy.newArray();
        }
    }

    public void addTarget(String target) {
        targets.add(target);
    }
//
    public JsonObject getJSONObject() {
        return json;
    }
//
//    public JsonObject newViewsMetadata(JsonObject view){
//        JsonObject metadata = view.getJsonObject("metadata");
//        if (metadata == null) {
//            metadata = JsonProxy.newObject();
//            view.put("metadata", metadata);
//        }
//        return metadata;
//    }
//
//
//    public JsonObject newViewswMetadata(JsonObject view, String key, Object val){
//        JsonObject meta = this.newViewsMetadata(view);
//        meta.put(key, val);
//        return meta;
//    }
//
//    public JsonObject newContains(JsonObject view,String containName, String type, String producer){
//        JsonObject meta = this.newViewsMetadata(view);
//        JsonObject contains = meta.getJsonObject("contains");
//        if (contains == null) {
//            contains = JsonProxy.newObject();
//            meta.put("contains", contains);
//        }
//        JsonObject contain = JsonProxy.newObject();
//        contain.put("producer", producer);
//        contain.put("type",type);
//        contains.put(containName,contain);
//        return contains;
//    }
//
//    public JsonObject newAnnotation(JsonObject view){
//        JsonObject annotation = JsonProxy.newObject();
//        JsonArray annotations = view.getJsonArray("annotations");
//        if (annotations == null) {
//            annotations = JsonProxy.newArray();
//            view.put("annotations", annotations);
//        }
//        annotations.add(annotation);
//        return annotation;
//    }
//
//    public JsonObject newAnnotation(JsonObject view, JsonObject copyfrom) {
//        JsonObject annotation = new JsonObject(copyfrom.toString());
//        JsonArray annotations = view.getJsonArray("annotations");
//        if (annotations == null) {
//            annotations = JsonProxy.newArray();
//            view.put("annotations", annotations);
//        }
//        annotations.add(annotation);
//        return annotation;
//    }
//
//    public JsonObject newAnnotation(JsonObject view, String label, String id) {
//        JsonObject ann = this.newAnnotation(view);
//        ann.put("label", label);
//        ann.put("id", id);
//        return ann;
//    }
//
//    public JsonObject newAnnotation(JsonObject view, String label) {
//        JsonObject ann = this.newAnnotation(view);
//        ann.put("label", label);
//        ann.put("id", idHeader+id++);
//        return ann;
//    }
//
//    public JsonObject newAnnotation(JsonObject view, String label, String id, int start, int end) {
//        JsonObject ann = this.newAnnotation(view);
//        ann.put("label", label);
//        ann.put("id", id);
//        ann.put("start", start);
//        ann.put("end", end);
//        return ann;
//    }
//
//
//
//    public JsonObject newAnnotation(JsonObject view, String label,  int start, int end) {
//        JsonObject ann = this.newAnnotation(view);
//        ann.put("label", label);
//        ann.put("id", idHeader+id++);
//        ann.put("start", start);
//        ann.put("end", end);
//        return ann;
//    }
//
//
//    public JsonObject newView() {
//        JsonObject view = JsonProxy.newObject();
//        JsonArray annotations = JsonProxy.newArray();
//        view.put("metadata", JsonProxy.newObject());
//        view.put("annotations", annotations);
//        views.add(view);
//        return view;
//    }
//
//    public void setStart(JsonObject annotation, int start) {
//        annotation.put("start", start);
//    }
//
//    public void setEnd(JsonObject annotation, int end) {
//        annotation.put("end", end);
//    }
//
//    public void setWord(JsonObject annotation, String word) {
//        setFeature(annotation, "word", word);
//    }
//
//    public void setCategory(JsonObject annotation, String word) {
//        setFeature(annotation, "category", word);
//    }
//
//    public List<JsonObject> getLastViewAnnotations() {
//        ArrayList<JsonObject> lastAnnotations = null;
//        if(views.length() > 0) {
//            for(int i = views.length() - 1; i >= 0; i--) {
//                JsonObject lastView =  (JsonObject)views.get(i);
//                JsonObject lastViewMeta = (JsonObject) lastView.get("metadata");
//                JsonArray lastViewAnnotations = (JsonArray)lastView.get("annotations");
//                JsonObject lastViewContains = (JsonObject)lastViewMeta.get("contains");
//                if (lastViewContains.has(Discriminators.Uri.TOKEN)) {
//                    // contains sentence
//                    lastAnnotations = new ArrayList<JsonObject>(lastViewAnnotations.length());
//                    for(int j = 0; j < lastViewAnnotations.length(); j++) {
//                        JsonObject lastStepAnnotation = lastViewAnnotations.get(j);
//                        lastAnnotations.add(lastStepAnnotation);
//                    }
//                    break;
//                }
//            }
//        }
//        return lastAnnotations;
//    }
//
//
//    public String getAnnotationText(JsonObject annotation) {
//        int start = getStart(annotation);
//        int end = getEnd(annotation);
//        return getText().substring(start, end);
//    }
//
//    public void setSentence(JsonObject annotation, String sent) {
//        setFeature(annotation, "sentence", sent);
//    }
//
//
//
//    public void setLabel(JsonObject annotation, String label) {
//        annotation.put("label", label);
//    }
//
//    public void setId(JsonObject annotation, String id) {
//        annotation.put("id", id);
//    }
//
//    public void setPOSTag(JsonObject annotation, String posTag) {
//        setFeature(annotation, "pos", posTag);
//    }
//    public void setNamedEntity(JsonObject annotation, String ne) {
//        setFeature(annotation, "ne", ne);
//    }
//
    public void setError(String msg, String stacktrace) {
        this.setDiscriminator(Discriminators.Uri.ERROR);
        JsonObject val = JsonProxy.newObject();
        val.put("@value", msg);
        val.put("stacktrace", stacktrace);
        error.put("text",  val);
    }
//
//
//
//    public void setFeature(JsonObject annotation, String name,  Object value) {
//        JsonObject features = annotation.getJsonObject("features");
//        if (features == null) {
//            features = newFeatures(annotation);
//        }
//        features.put(name, value);
//    }
//
//    public JsonObject newFeatures(JsonObject annotation) {
//        JsonObject features = JsonProxy.newObject();
//        annotation.put("features", features);
//        return features;
//    }
//
    public String toString(){
        json.put("discriminator" ,discriminator);
        if (discriminator.equals(Discriminators.Uri.JSON_LD)) {
            payload.put("targets", targets);
            json.put("payload" ,payload);
            payload.put("@context",context);
            payload.put("metadata", metadata);
        } else if(discriminator.equals(Discriminators.Uri.ERROR)) {
            json.put("payload" ,error);
        }
        return json.toString();
    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (o == null)
//            return false;
//        JsonSerialization obj = (JsonSerialization)o;
//        this.toString();
//        obj.toString();
//        return this.json.equals(obj.json);
//    }
}
