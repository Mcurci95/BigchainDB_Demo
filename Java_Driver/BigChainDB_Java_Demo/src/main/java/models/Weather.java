package models;

import java.util.List;

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


    public Weather(String[] weatherList) {
        station = weatherList[0];
        date    = weatherList[1];
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
}
