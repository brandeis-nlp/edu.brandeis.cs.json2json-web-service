package edu.brandeis.cs.json2json;


import org.junit.Test;

public class TestJson2JsonWS extends TestWebService {

        @Test
        public void test() throws Exception {
                Json2JsonWS ws = new Json2JsonWS();
                System.out.println(ws.getMetadata());
                System.out.println(ws.execute(readResource("input.json")));
        }

}
