package org.eci.arep.aws.mongo;

import java.time.LocalDate;

public class MongoMsg {

    /**
     * Método principal que se usó para realizar pruebas
     * @param args argumentos necesarios para correr el método main
     */
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
