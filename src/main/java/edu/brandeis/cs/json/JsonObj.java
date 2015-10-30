package edu.brandeis.cs.json;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by 310201833 on 2015/10/30.
 */
public class JsonObj extends JsonProxy implements Json.Obj {

    Map<String, Object> map = null;

    public JsonObj() {
        map = new LinkedHashMap<String, Object>();
    }

    public JsonObj(Map<String, Object> map) {
        this.map = map;
    }


    public JsonObj(String s) {
        this.read(s);
    }

    public Json.Obj read(String s) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            map = (Map) mapper.readValue(s, LinkedHashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }


    public boolean has(String key) {
        return map.containsKey(key);
    }


    public Object get(String key) {
        return wrap(map.get(key));
    }


    public Json.Obj put(String key, Object val) {
        map.put(key, unwrap(val));
        return this;
    }


    public Json.Obj remove(String key) {
        map.remove(key);
        return this;
    }


    public int length() {
        return map.size();
    }


    public Collection<String> keys() {
        return map.keySet();
    }


    public Object original() {
        return map;
    }


    public String getString(String key) {
        Object val = this.get(key);
        if (val != null) {
            return (String) val;
        }
        return null;
    }

    public Long getLong(String key) {
        Object val = this.get(key);
        if (val != null) {
            return (Long) val;
        }
        return null;
    }

    public Double getDouble(String key) {
        Object val = this.get(key);
        if (val != null) {
            return (Double) val;
        }
        return null;
    }

    public Boolean getBoolean(String key) {
        Object val = this.get(key);
        if (val != null) {
            return (Boolean) val;
        }
        return null;
    }

    public Integer getInt(String key) {
        Object val = this.get(key);
        if (val != null) {
            return ((Integer) val);
        }
        return null;
    }

    public Arr getJsonArr(String key) {
        Object val = this.get(key);
        if (val != null) {
            return (Arr) val;
        }
        return null;
    }

    public Obj getJsonObj(String key) {
        Object val = this.get(key);
        if (val != null) {
            return (Obj) val;
        }
        return null;
    }

    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this.map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}