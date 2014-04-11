package edu.brandeis.cs.json2json;

import org.anc.lapps.serialization.Container;
import org.lappsgrid.api.Data;
import org.lappsgrid.api.WebService;
import org.lappsgrid.discriminator.Types;

import java.util.UnknownFormatFlagsException;

/**
 * Created by shi on 4/7/14.
 */
public class Json2JsonWS implements WebService {

    public static final String Template = "template";

    @Override
    public long[] requires() {
        return new long[] { Types.JSON };
    }

    @Override
    public long[] produces() {
        return new long[] { Types.JSON };
    }

    @Override
    public Data execute(Data input) {
        if (input == null || input.getDiscriminator() != Types.JSON) {
            throw new UnknownFormatFlagsException("Only JSON format is valid. But your input Discriminator is not Types.JSON");
        }
        String payload = input.getPayload();
        Container container = new Container(payload);
//        System.out.println(">>>>>>>>>>>>>>>" + container);
        String json = container.getText();
        String template = container.getMetadata().get(Template).toString();
        Data output  = new Data(Types.JSON);
        try{
            String targetJson = Json2Json.transform(template, json);
            output.setPayload(targetJson);
        }catch (Json2JsonException e) {
            output.setPayload(e.toString());
            output.setDiscriminator(Types.ERROR);
        }
        return output;
    }

    @Override
    public Data configure(Data config) {
        return null;
    }
}
