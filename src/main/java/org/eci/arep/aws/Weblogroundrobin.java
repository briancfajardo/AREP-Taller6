package org.eci.arep.aws;

import static spark.Spark.*;


public class Weblogroundrobin
{
    public static void main( String[] args )
    {
        port(getPort());
        staticFiles.location("/public");
        get("/log", (req, res) -> RRInvoker.invoke());
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
