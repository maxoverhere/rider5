package com.blogspot.skooltchdev.tabbedapplicationmaster;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class ConnectionTest {

    String myJson;

    public String readStringFromURL(String requestURL) throws IOException
    {
        try (Scanner scanner = new Scanner(new URL(requestURL).openStream(),
                StandardCharsets.UTF_8.toString()))
        {
            scanner.useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        }
    }

    public ConnectionTest(){

    }
    public ConnectionTest(double lat_start, double lon_start,double lat_end,double lon_end){
        String url = "https://api.openrouteservice.org/directions?api_key=5b3ce3597851110001cf6248e16c403c7197425e868a3aacbab4a87f&coordinates=" + lon_start + "%2C%20" + lat_start + "%7C" + lon_end + "%2C%20" + lat_end + "&profile=driving-car&format=geojson&fbclid=IwAR2oxt23nAl_c_k-8fjc412YBCElx7uAt-h-hTVUZCc3hq5nx5TIk9vBWvg";
        myJson = "";
        try {
            myJson = readStringFromURL(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}