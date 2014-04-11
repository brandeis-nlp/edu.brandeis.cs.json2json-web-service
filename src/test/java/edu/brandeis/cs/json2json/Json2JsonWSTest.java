package edu.brandeis.cs.json2json;


import junit.framework.Assert;
import org.anc.lapps.serialization.Container;
import org.junit.Test;
import org.lappsgrid.api.Data;
import org.lappsgrid.discriminator.Types;

public class Json2JsonWSTest extends  WebServiceTest {
    static final String OriginJson = "{\n" +
            "        \"catalog\": {\n" +
            "        \"-xmlns:foo\": \"http://www.foo.org/\",\n" +
            "        \"-xmlns:bar\": \"http://www.bar.org\",\n" +
            "        \"foo:cd\": [\n" +
            "        {\n" +
            "        \"title\": \"Empire Burlesque\",\n" +
            "        \"artist\": \"Bob Dylan\",\n" +
            "        \"country\": \"USA\",\n" +
            "        \"company\": \"Columbia\",\n" +
            "        \"price\": \"10.90\",\n" +
            "        \"bar:year\": \"1985\"\n" +
            "        },\n" +
            "        {\n" +
            "        \"title\": \"Hide your heart\",\n" +
            "        \"artist\": \"Bonnie Tyler\",\n" +
            "        \"country\": \"UK\",\n" +
            "        \"company\": \"CBS Records\",\n" +
            "        \"price\": \"9.90\",\n" +
            "        \"bar:year\": \"1988\"\n" +
            "        },\n" +
            "        {\n" +
            "        \"title\": \"Greatest Hits\",\n" +
            "        \"artist\": \"Dolly Parton\",\n" +
            "        \"country\": \"USA\",\n" +
            "        \"company\": \"RCA\",\n" +
            "        \"price\": \"9.90\",\n" +
            "        \"bar:year\": \"1982\"\n" +
            "        }\n" +
            "        ]\n" +
            "        }\n" +
            "        }";
    static final String Template = "{\n" +
            "  \"html\": {\n" +
            "    \"-xmlns:bar\": \"http://www.bar.org\",\n" +
            "    \"-xmlns:foo\": \"http://www.foo.org/\",\n" +
            "    \"body\": {\n" +
            "      \"h2\": \"My CD Collection\",\n" +
            "      \"table\": {\n" +
            "        \"-border\": \"1\",\n" +
            "        \"tr\": {\"%!for\": {\n" +
            "               \"%$\"    : {\"%!s\":[ { \"-bgcolor\": \"#9acd32\",\n" +
            "                                    \"th\": [\n" +
            "                                      \"Title\",\n" +
            "                                      \"Artist\",\n" +
            "                                      \"Country\",\n" +
            "                                      \"Company\",\n" +
            "                                      \"Price\",\n" +
            "                                      \"Year\" ]\n" +
            "                                  } ]\n" +
            "                         },\n" +
            "               \"%[]\"   : [\"&$.catalog.foo:cd\", \"%i\", \"%e\"],\n" +
            "               \"%each\" : {\"%s\": {\"%]+\": [\"%s\", {\"td\": [\n" +
            "                                                        {\"%&\":[\"%e\", \"$.title\"]} ,\n" +
            "                                                        {\"%&\":[\"%e\", \"$.artist\"]},\n" +
            "                                                        {\"%&\":[\"%e\", \"$.country\"]},\n" +
            "                                                        {\"%&\":[\"%e\", \"$.company\"]},\n" +
            "                                                        {\"%&\":[\"%e\", \"$.price\"]},\n" +
            "                                                        {\"%&\":[\"%e\", \"$.bar:year\"]}\n" +
            "                                                       ]}]}},\n" +
            "\n" +
            "               \"%#\"    : \"%s\"\n" +
            "              }\n" +
            "        }\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";
    static final String TargetJson = "{\n" +
            "  \"html\": {\n" +
            "    \"-xmlns:bar\": \"http://www.bar.org\",\n" +
            "    \"-xmlns:foo\": \"http://www.foo.org/\",\n" +
            "    \"body\": {\n" +
            "      \"h2\": \"My CD Collection\",\n" +
            "      \"table\": {\n" +
            "        \"-border\": \"1\",\n" +
            "        \"tr\": [\n" +
            "          {\n" +
            "            \"-bgcolor\": \"#9acd32\",\n" +
            "            \"th\": [\n" +
            "              \"Title\",\n" +
            "              \"Artist\",\n" +
            "              \"Country\",\n" +
            "              \"Company\",\n" +
            "              \"Price\",\n" +
            "              \"Year\"\n" +
            "            ]\n" +
            "          },\n" +
            "          {\n" +
            "            \"td\": [\n" +
            "              \"Empire Burlesque\",\n" +
            "              \"Bob Dylan\",\n" +
            "              \"USA\",\n" +
            "              \"Columbia\",\n" +
            "              \"10.90\",\n" +
            "              \"1985\"\n" +
            "            ]\n" +
            "          },\n" +
            "          {\n" +
            "            \"td\": [\n" +
            "              \"Hide your heart\",\n" +
            "              \"Bonnie Tyler\",\n" +
            "              \"UK\",\n" +
            "              \"CBS Records\",\n" +
            "              \"9.90\",\n" +
            "              \"1988\"\n" +
            "            ]\n" +
            "          },\n" +
            "          {\n" +
            "            \"td\": [\n" +
            "              \"Greatest Hits\",\n" +
            "              \"Dolly Parton\",\n" +
            "              \"USA\",\n" +
            "              \"RCA\",\n" +
            "              \"9.90\",\n" +
            "              \"1982\"\n" +
            "            ]\n" +
            "          }\n" +
            "        ]\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";



    public Data getInput(){
        Container container = new Container();
        container.setText(OriginJson);
        container.getMetadata().put(Json2JsonWS.Template, Template);
        return new Data(Types.JSON, container.toJson());
    }


    public Data getOuput() {
        Data res = new Data(Types.JSON);
        res.setPayload(TargetJson);
        return res;
    }

    @Test
    public void test() throws  Exception{
        Json2JsonWS ws = new Json2JsonWS();
        Data output = ws.execute(this.getInput());
//        System.out.println(output.getPayload());
        Assert.assertEquals(this.getOuput().getPayload().replaceAll("\\s", "").length(),
                output.getPayload().replaceAll("\\s", "").length());
    }

}
