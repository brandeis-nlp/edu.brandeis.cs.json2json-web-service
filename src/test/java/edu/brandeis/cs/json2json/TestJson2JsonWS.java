package edu.brandeis.cs.json2json;


import org.junit.Test;
import org.lappsgrid.serialization.Data;
import org.lappsgrid.serialization.Serializer;
import org.lappsgrid.serialization.lif.Container;

import java.util.Map;

public class TestJson2JsonWS extends TestWebService {
//
//        @Test
//        public void test() throws Exception {
//                Json2JsonWS ws = new Json2JsonWS();
//                System.out.println(ws.getMetadata());
//                String json = ws.execute(readResource("input.json"));
//                System.out.println(json);
//                Serializer.parse(json, Data.class).getPayload();
//        }
//
//
//        @Test
//        public void testBratDslSplitter() throws Exception {
//                Json2JsonWS ws = new Json2JsonWS();
//                // Test Splitter
//                String bratdsl = readResource("brat/brat.dsl");
//                String json = readResource("stanford/splitter.json");
//                String brat = ws.json2jsondsl(json, bratdsl);
//                System.out.println(brat);
//
//                json = readResource("opennlp/tokenizer.json");
//                brat = ws.json2jsondsl(json, bratdsl);
//                System.out.println(brat);
//        }
//
//        @Test
//        public void testBratDslTokenizer() throws Exception {
//                Json2JsonWS ws = new Json2JsonWS();
//
//                String bratdsl = readResource("brat/brat.dsl");
//                String json = readResource("stanford/tokenizer.json");
//                String brat = ws.json2jsondsl(json, bratdsl);
//                System.out.println(brat);
//
//                json = readResource("opennlp/tokenizer.json");
//                brat = ws.json2jsondsl(json, bratdsl);
//                System.out.println(brat);
//        }
//
//
//        @Test
//        public void testBratDslTagger() throws Exception {
//                Json2JsonWS ws = new Json2JsonWS();
//
//                String bratdsl = readResource("brat/brat.dsl");
//                String json = readResource("stanford/tagger.json");
//                String brat = ws.json2jsondsl(json, bratdsl);
//                System.out.println(brat);
//
//                json = readResource("opennlp/tagger.json");
//                brat = ws.json2jsondsl(json, bratdsl);
//                System.out.println(brat);
//        }
//
//        @Test
//        public void testBratDslNer() throws Exception {
//                Json2JsonWS ws = new Json2JsonWS();
//
//                String bratdsl = readResource("brat/brat.dsl");
//                String json = readResource("stanford/ner.json");
//                String brat = ws.json2jsondsl(json, bratdsl);
//                System.out.println(brat);
//
//                json = readResource("opennlp/ner.json");
//                brat = ws.json2jsondsl(json, bratdsl);
//                System.out.println(brat);
//        }
//
//        @Test
//        public void testBratDslParser() throws Exception {
//            Json2JsonWS ws = new Json2JsonWS();
//
//            String bratdsl = readResource("brat/brat.dsl");
//            String json = readResource("stanford/parser.json");
//            String brat = ws.json2jsondsl(json, bratdsl);
//            System.out.println(brat);
//
//            json = readResource("opennlp/parser.json");
//            brat = ws.json2jsondsl(json, bratdsl);
//            System.out.println(brat);
//        }
//
//        @Test
//        public void testBratDslDepParser() throws Exception {
//            Json2JsonWS ws = new Json2JsonWS();
//
//            String bratdsl = readResource("brat/brat.dsl");
//            String json = readResource("stanford/dependecy.json");
//            String brat = ws.json2jsondsl(json, bratdsl);
//            System.out.println(brat);
//
////            json = readResource("opennlp/dependecy.json");
////            brat = ws.json2jsondsl(json, bratdsl);
////            System.out.println(brat);
//        }

        @Test
        public void testBratDslCoref() throws Exception {
            Json2JsonWS ws = new Json2JsonWS();

            String bratdsl = readResource("brat/brat.dsl");
            String json = readResource("stanford/coreference.json");
            String brat = ws.json2jsondsl(json, bratdsl);
            System.out.println(brat);

            json = readResource("opennlp/coreference.json");
            brat = ws.json2jsondsl(json, bratdsl);
            System.out.println(brat);
        }
}
