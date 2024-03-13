package org.eci.arep.aws.mongo;

import java.time.LocalDate;

public class MongoMsg {


    public static void main(String[] args) {
        MessageDAO messageDAO = new MessageDAO();

        messageDAO.addMessage("Mensaje de prueba", LocalDate.now());

        for (String msg:
                messageDAO.listLastMessages()) {
            System.out.println(msg);
        }


        messageDAO.deleteAllMessages();

    }
}
