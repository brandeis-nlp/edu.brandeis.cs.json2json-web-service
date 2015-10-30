package edu.brandeis.cs.json2json;


import org.junit.Test;
import org.lappsgrid.serialization.Data;
import org.lappsgrid.serialization.Serializer;

public class TestTemp extends TestWebService {

       @Test
        public void testBratDslSplitter() throws Exception {
                Json2JsonWS ws = new Json2JsonWS();
                // Test Splitter
                String bratdsl = readResource("brat/brat.dsl");
                String json = readResource("tmp.json");
                String brat = ws.json2jsondsl(json, bratdsl);
                System.out.println(brat);
        }

}
