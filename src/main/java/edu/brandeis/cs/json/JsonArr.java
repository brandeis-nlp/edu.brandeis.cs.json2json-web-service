package edu.brandeis.cs.json;




import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by 310201833 on 2015/10/30.
 */
public class JsonArr extends JsonProxy implements Json.Arr {

    List<Object> list = null;

    public JsonArr(List<Object> list) {
        this.list = list;
    }

    public JsonArr() {
        list = new ArrayList<Object>();
    }

    public JsonArr(String s) {
        this.read(s);
    }


    public Json.Arr read(String s) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            list = (List<Object>) mapper.readValue(s, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }


    public int length() {
        return list.size();
    }


    public Object get(int i) {
        return wrap(list.get(i));
    }


    public Json.Obj getJsonObj(int i) {
        Object val = get(i);
        if (val != null) {
            return (Json.Obj) val;
        }
        return null;
    }

    public Json.Arr getJsonArr(int i) {
        Object val = get(i);
        if (val != null) {
            return (Json.Arr) val;
        }
        return null;
    }

    public String getString(int i) {
        Object val = this.get(i);
        if (val != null) {
            return (String) val;
        }
        return null;
    }

    public Long getLong(int i) {
        Object val = this.get(i);
        if (val != null) {
            return (Long) val;
        }
        return null;
    }

    public Double getDouble(int i) {
        Object val = this.get(i);
        if (val != null) {
            return (Double) val;
        }
        return null;
    }

    public Boolean getBoolean(int i) {
        Object val = this.get(i);
        if (val != null) {
            return (Boolean) val;
        }
        return null;
    }

    public Integer getInt(int i) {
        Object val = this.get(i);
        if (val != null) {
            return (Integer) val;
        }
        return null;
    }

    public Json.Arr add(Object s) {
        list.add(unwrap(s));
        return this;
    }


    public Json.Arr remove(int i) {
        list.remove(i);
        return this;
    }


    public Json.Arr set(int i, Object obj) {
        list.set(i, obj);
        return this;
    }


    public Json.Arr convert(String[] arr) {
        Collections.addAll(list, arr);
        return this;
    }


    public Json.Arr convert(Collection<String> arr) {
        for (String s : arr) {
            list.add(s);
        }
        return this;
    }

//
//        public Json.Arr clone() {
//            return new JacksonJson.Arr(new ArrayList<Object>(list));
//        }


    public Object original() {
        return list;
    }


    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this.list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}