package org.eci.arep.aws.logservice;

import org.eci.arep.aws.mongo.MessageDAO;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.List;

import static spark.Spark.*;

public class LogService {
    private static final MessageDAO messageDAO = new MessageDAO();

    /**
     * Método principal que gestiona las peticiones
     * @param args argumentos necesarios del método main
     */
    public static void main(String[] args) {
        port(getPort());
        get("/logservice", (req, res) -> {
            return saveLog(req.queryParams("msg"));
        });
    }

    /**
     * Método que guarda y retorna los últimos 10 mensajes almacenados
     * @param rawLog nuevo mesnaje
     * @return lista de mensajes almacenados
     */
    private static List<String> saveLog(String rawLog){
        System.out.println(rawLog);
        messageDAO.addMessage(rawLog, LocalDate.now());
        return messageDAO.listLastMessages();
    }

    /**
     * Método que busca en las variables de entorno el puerto del servidor y en caso de no
     * encontrarlo retorna 35000
     * @return puerto del servidor
     */
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35000;
    }

}
