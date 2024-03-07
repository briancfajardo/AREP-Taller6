package org.eci.arep.aws;

import static spark.Spark.*;

public class LogService {

    public static void main(String[] args) {
        port(getPort());
        get("/logservice", (req, res) -> "{\"msg\":\"primer mensajeeee, 24-02-2024 16:45:45\"}");
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000;
    }

}
