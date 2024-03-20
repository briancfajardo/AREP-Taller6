package org.eci.arep.aws.loadbalancer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RRInvoker {

    private static final String[] servers = new String[]{"10.5.0.5", "10.5.0.6", "10.5.0.7"};
    private static final String USER_AGENT = "Mozilla/5.0";
    private static int currentServer = 0;
    //private static final String GET_URL = "http://localhost:";
    public static int getNextServer(){
        int nextServer = currentServer % 3;
        currentServer++;
        return nextServer;

    }

    public static String invoke(String msg) throws IOException {
        URL obj = new URL("http://"+servers[getNextServer()]+ ":46000/logservice?"+ msg);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        System.out.println(obj.getHost());
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