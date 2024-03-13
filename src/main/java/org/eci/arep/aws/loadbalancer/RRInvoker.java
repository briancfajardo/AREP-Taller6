package org.eci.arep.aws.loadbalancer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RRInvoker {

    private static final String USER_AGENT = "Mozilla/5.0";
    //private static final String GET_URL = "http://ec2-35-174-11-169.compute-1.amazonaws.com:";
    private static final String GET_URL = "http://localhost:";

    public static String invoke(String msg, String port) throws IOException {

        URL obj = new URL(GET_URL+ port +"/logservice?"+ msg);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        StringBuffer response = new StringBuffer();
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }
        System.out.println("GET DONE");

        return response.toString();
    }
}