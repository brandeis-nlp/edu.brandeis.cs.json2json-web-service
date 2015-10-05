package edu.brandeis.cs.json2json;


import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class TestWebService {
    public static String readResource(String filename) throws Exception {
        File objFile = new File(TestWebService.class.getResource("/" + filename).toURI());
        return FileUtils.readFileToString(objFile, "UTF-8");
    }


    @Before
    public void setup(){
       System.out.println("<------------" + this.getClass().getSimpleName()+"------------");

    }

    @After
    public void tear() {
        System.out.println("=============================================================>");
    }

}
