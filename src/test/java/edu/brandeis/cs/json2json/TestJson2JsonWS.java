package edu.brandeis.cs.json2json;


import org.junit.Test;
import org.lappsgrid.serialization.Data;
import org.lappsgrid.serialization.Serializer;
import org.lappsgrid.serialization.lif.Container;

import java.util.Map;

public class TestJson2JsonWS extends TestWebService {

        @Test
        public void test() throws Exception {
                Json2JsonWS ws = new Json2JsonWS();
                System.out.println(ws.getMetadata());
                String json = ws.execute(readResource("input.json"));
                System.out.println(json);
                Serializer.parse(json, Data.class).getPayload();
        }

}
