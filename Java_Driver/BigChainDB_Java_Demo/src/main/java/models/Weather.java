package models;

import org.bson.Document;

import javax.print.Doc;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Weather {
    String station;
    String date;
    String latitiude;
    String longitude;
    String elevation;
    String name;
    String temp;
    String temp_attributes;
    String dewp;
    String dewp_attributes;
    String slp;
    String slp_attributes;
    String stp;
    String stp_attributes;
    String visib;
    String visib_attributes;
    String wdsp;
    String wdsp_attributes;
    String mxspd;
    String gust;
    String max;
    String max_attributes;
    String min;
    String min_attributes;
    String prcp;
    String prcp_attributes;
    String sdnp;
    String frshtt;

    public Weather() {
    }

    public Weather(String[] weatherList) {
        station = weatherList[0];
        date = weatherList[1];
        latitiude = weatherList[2];
        longitude = weatherList[3];
        elevation = weatherList[4];
        name = weatherList[5];
        temp = weatherList[6];
        temp_attributes = weatherList[7];
        dewp = weatherList[8];
        dewp_attributes = weatherList[9];
        slp = weatherList[10];
        slp_attributes = weatherList[11];
        stp = weatherList[12];
        stp_attributes = weatherList[13];
        visib = weatherList[14];
        visib_attributes = weatherList[15];
        wdsp = weatherList[16];
        wdsp_attributes = weatherList[17];
        mxspd = weatherList[18];
        gust = weatherList[19];
        max = weatherList[20];
        max_attributes = weatherList[21];
        min = weatherList[22];
        min_attributes = weatherList[23];
        prcp = weatherList[24];
        prcp_attributes = weatherList[25];
        sdnp = weatherList[26];
        frshtt = weatherList[27];
    }

    @Override
    public String toString() {
        return String.format("[Station: %s Date: %s]", station, date);
    }

    public Map<String, String> transformIntoAsset() {
        Map<String, String> asset = new TreeMap<String, String>();
        asset.put("station", station);
        asset.put("date", date);
        asset.put("latitiude", latitiude);
        asset.put("longitude", longitude);
        asset.put("elevation", elevation);
        asset.put("name", name);
        asset.put("temp", temp);
        asset.put("temp_attributes", temp_attributes);
        asset.put("dewp", dewp);
        asset.put("dewp_attributes", dewp_attributes);
        asset.put("slp", slp);
        asset.put("slp_attributes", slp_attributes);
        asset.put("stp", stp);
        asset.put("stp_attributes", slp_attributes);
        asset.put("visib", visib);
        asset.put("visib_attributes", visib_attributes);
        asset.put("wdsp", wdsp);
        asset.put("wdsp_attributes", wdsp_attributes);
        asset.put("mxspd", mxspd);
        asset.put("gust", gust);
        asset.put("max", max);
        asset.put("max_attributes", max_attributes);
        asset.put("min", min);
        asset.put("min_attributes", min_attributes);
        asset.put("prcp", prcp);
        asset.put("prcp_attributes", prcp_attributes);
        asset.put("sdnp", sdnp);
        asset.put("frshtt", frshtt);
        return asset;
    }


    public Document transformIntoDocument() {
        Document doc = new Document("station", station)
                .append("station", station)
                .append("date", date)
                .append("latitiude", latitiude)
                .append("longitude", longitude)
                .append("elevation", elevation)
                .append("name", name)
                .append("temp", temp)
                .append("temp_attributes", temp_attributes)
                .append("dewp", dewp)
                .append("dewp_attributes", dewp_attributes)
                .append("slp", slp)
                .append("slp_attributes", slp_attributes)
                .append("stp", stp)
                .append("stp_attributes", slp_attributes)
                .append("visib", visib)
                .append("visib_attributes", visib_attributes)
                .append("wdsp", wdsp)
                .append("wdsp_attributes", wdsp_attributes)
                .append("mxspd", mxspd)
                .append("gust", gust)
                .append("max", max)
                .append("max_attributes", max_attributes)
                .append("min", min)
                .append("min_attributes", min_attributes)
                .append("prcp", prcp)
                .append("prcp_attributes", prcp_attributes)
                .append("sdnp", sdnp)
                .append("frshtt", frshtt);

        return doc;
    }
}