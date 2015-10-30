package edu.brandeis.cs.json;

import java.util.Collection;

/**
 * Created by shi on 7/9/15.
 */
public interface Json {

    /**
     * write Json int string
     **/
    String toString();


    interface Arr extends Json {
        /**
         * read string into Arr
         **/
        Arr read(String s);

        /**
         * length / get / add / insert / remove
         **/
        int length();

        Object get(int i);

        Arr add(Object s);

        Arr remove(int i);

        Arr set(int i, Object s);

        /**
         * directly read string Arr as Json Arr object.
         **/
        Arr convert(String[] arr);

        Arr convert(Collection<String> arr);


        String getString(int i);

        Long getLong(int i);

        Double getDouble(int i);

        Boolean getBoolean(int i);

        Integer getInt(int i);

        Arr getJsonArr(int i);

        Obj getJsonObj(int i);

        /** clone a Json Arr **/
//         Arr clone();

        /**
         * if has original mapping
         **/
        Object original();
    }

    interface Obj extends Json {
        /**
         * read string into Obj
         **/
        Obj read(String s);

        /**
         * has / get / put / remove
         **/
        boolean has(String key);

        Object get(String key);

        Obj put(String key, Object val);

        Obj remove(String key);

        /**
         * length / keys
         **/
        int length();

        Collection<String> keys();

        /**
         * if has original mapping
         **/
        Object original();


        String getString(String key);

        Long getLong(String key);

        Double getDouble(String key);

        Boolean getBoolean(String key);

        Integer getInt(String key);

        Arr getJsonArr(String key);

        Obj getJsonObj(String key);

        /** clone a Json Object **/
//         Obj clone();
    }


    interface Proxy {
        /**
         * object into json string
         */
        String toJsonString(Object obj) throws Exception;

        /**
         * string into Object
         */
        Object fromJsonString(String json) throws Exception;

        Arr newArray();

        Obj newObject();

        Arr convertArray(String[] arr);

        Arr convertArray(Collection<String> arr);

        Arr readArray(String s);

        Obj readObject(String s);

        boolean isArray(Object obj);

        boolean isObject(Object obj);
    }


    interface ObjBind {
        /**
         * type is according to JsonIO.
         */
        String MetaType = "@type";

        Object toObj(String json, Class cls) throws Exception;
    }

}
