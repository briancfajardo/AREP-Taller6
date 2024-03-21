package org.eci.arep.aws.loadbalancer;


import static spark.Spark.*;

/**
 * Clase que establece la conexión inicial con el cliente y muestra la página de inicio
 * además, se conecta con el back-end
 */
public class Weblogroundrobin
{
    /**
     * Método principal que corre la aplicación
     * @param args argumentos necesarios para correr el método main
     */
    public static void main( String[] args )
    {
        port(getPort());
        staticFiles.location("/public");

        get("/log", (req, res) -> RRInvoker.invoke(req.queryString()));
    }

    /**
     * Método que busca en las variables de entorno el puerto del servidor y en caso de no
     * encontrarlo retorna 4567
     * @return puerto del servidor
     */
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
