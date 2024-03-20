package org.eci.arep.aws.logservice;

import org.eci.arep.aws.mongo.MessageDAO;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.List;

import static spark.Spark.*;

public class LogService {
    private static final MessageDAO messageDAO = new MessageDAO();

    public static void main(String[] args) {
        port(getPort());
        get("/logservice", (req, res) -> {
            return saveLog(req.queryParams("msg"));
        });
    }
    private static List<String> saveLog(String rawLog){
        System.out.println(rawLog);
        messageDAO.addMessage(rawLog, LocalDate.now());
        return messageDAO.listLastMessages();
    }


    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35000;
    }

}
