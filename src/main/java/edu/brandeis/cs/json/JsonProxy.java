package edu.brandeis.cs.json;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by shi on 7/9/15.
 */
public class JsonProxy  implements Json.Proxy {

    public Object fromJsonString(String json) throws Exception {
        if (json == null)
            return null;
        json = json.trim();
        if (json.startsWith("{")) {
            return readObject(json);
        } else if (json.startsWith("[")) {
            return readArray(json);
        } else {
            return json;
        }
    }


    public Json.Arr convertArray(String[] arr) {
        return newArray().convert(arr);
    }


    public Json.Arr convertArray(Collection<String> arr) {
        return newArray().convert(arr);
    }


    public Json.Arr readArray(String s) {
        return newArray().read(s);
    }


    public Json.Obj readObject(String s) {
        return newObject().read(s);
    }


    public boolean isArray(Object obj) {
        return obj instanceof Json.Arr;
    }


    public boolean isObject(Object obj) {
        return obj instanceof Json.Obj;
    }

    public String toJsonString(Object obj) throws Exception {
        ObjectWriter ow = new ObjectMapper().writer();
        String json = null;
        try {
            json = ow.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw e;
        }
        return json;
    }

    public Json.Arr newArray() {
        return new JsonArr();
    }

    public Json.Obj newObject() {
        return new JsonObj();
    }

    public Object unwrap(Object obj) {
        if (obj instanceof JsonObj) {
            return ((JsonObj) obj).original();
        } else if (obj instanceof JsonArr) {
            return ((JsonArr) obj).original();
        }
        return obj;
    }


    public Object wrap(Object obj) {
        if(obj instanceof List) {
            return new JsonArr((List)obj);
        } else if(obj instanceof Map) {
            return new JsonObj((Map)obj);
        }
        return obj;
    }

}
