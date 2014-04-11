package edu.brandeis.cs.json2json;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lappsgrid.api.Data;

public class WebServiceTest {

    @Before
    public void setup(){
       System.out.println("<------------" + this.getClass().getSimpleName()+"------------");

    }

    @After
    public void tear() {
        System.out.println("=============================================================>");
    }


    public Data getInput(){
        return new Data();
    }

    public Data getOuput() {
        return new Data();
    }
}
