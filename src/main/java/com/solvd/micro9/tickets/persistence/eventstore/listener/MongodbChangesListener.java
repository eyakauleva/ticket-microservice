package com.solvd.micro9.tickets.persistence.eventstore.listener;

import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.reactivestreams.client.*;
import com.solvd.micro9.tickets.persistence.eventstore.DbsSynchronizer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class MongodbChangesListener { //TODO run in background

    private final DbsSynchronizer synchronizer;
    public void listen() { //TODO (load new library ? )
        String uri = "mongodb://root:system@localhost:27017/tickets?authSource=admin";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("tickets");
            List<Bson> pipeline = Arrays.asList(
                    Aggregates.match(
                            Filters.in("operationType",
                                    Arrays.asList("insert"))));

            ChangeStreamPublisher<Document> changeStream = database.watch(pipeline);

            //TODO
//            changeStream.forEach(event -> {
//                System.out.println("Received a change to the collection: " + event);
//                //synchronizer.sync();
//            });
        }
    }

}
