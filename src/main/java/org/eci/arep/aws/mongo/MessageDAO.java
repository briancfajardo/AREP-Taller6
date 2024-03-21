package org.eci.arep.aws.mongo;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Updates.set;
import static com.mongodb.client.model.Filters.eq;

public class MessageDAO {

    private final MongoCollection<Document> messagesCollection;

    /**
     * Método que obtiene la instancia dela base de datos y una colección
     */
    public MessageDAO() {
        MongoDatabase database = MongoUtil.getDB();
        this.messagesCollection = database.getCollection("messages");
    }

    /**
     * Método que añade un nuevo mensaje a la base de datos
     * @param msg nuevo mensaje
     * @param date fecha del nuevo mensaje
     */
    public void addMessage(String msg, LocalDate date){
        Document newMessage = new Document("date", date.toString()).append("message", URLDecoder.decode(msg, StandardCharsets.UTF_8));
        messagesCollection.insertOne(newMessage);
    }

    /**
     * Método que retorna una lista de los últimos 10 mensajes almacenados en la base de datos
     * @return lista de mensajes
     */
    public List<String> listLastMessages() {
        FindIterable<Document> messages;
        messages = messagesCollection.find().sort(Sorts.descending("_id")).limit(10);
        List<String> messagesReturn = new ArrayList<>();
        for (Document message : messages) {
            messagesReturn.add(message.toJson());
        }
        return messagesReturn;
    }

    /**
     * Método para actualizar un mensaje
     * @param msg mensaje actualizado
     * @param date fecha del mensaje original
     */
    public void updateMessage(String msg, String date){
        messagesCollection.updateOne(eq("date", date), set("message", msg));
    }

    /**
     * Metodo para eliminar un mensaje
     * @param date fecha del mensaje
     */
    public void deleteMessage(String date){
        messagesCollection.deleteOne(eq("date", date));
    }

    /**
     * Método que elimina todos los mensajes
     */
    public void deleteAllMessages(){
        FindIterable<Document> messages;
        messages = messagesCollection.find();
        for (Document message:
             messages) {
            deleteMessage((String) message.get("date"));
        }
    }
}
