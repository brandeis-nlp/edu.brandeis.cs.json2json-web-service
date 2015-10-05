package edu.brandeis.cs.json2json;

/**
 * Created by lapps on 4/29/2015.
 */
public interface ITransform {

    public String json2jsondsl(String sourceJson, String templateDsl) throws Exception;

    public String xml2jsondsl(String sourceXml, String templateDsl) throws Exception;

    public String xml2xmlxsl(String sourceXml, String templateXsl) throws Exception;

    public String xml2json(String xml) throws Exception;

    public String json2xml(String json) throws Exception;

}
