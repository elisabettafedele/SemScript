package it.polimi.ke.clients_for_fuseki_server;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdfconnection.*;

import java.util.function.Consumer;

import org.apache.jena.query.*;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFactory;
import org.apache.jena.system.Txn;

/**
 * TODO
 */
public class Client {
    public static void main(String ...args) {
        //Set the name of the file you want to upload
        String fileName = "the_social_network";

        //Create a connection with the embedded server (port number = 3031)
        RDFConnectionRemoteBuilder builder = RDFConnectionFuseki.create()
                .destination("http://127.0.0.1:3030/SemScript");

        try ( RDFConnectionFuseki conn = (RDFConnectionFuseki)builder.build() ) {
            Txn.executeWrite(conn, () ->{
                System.out.println("Load a file");
                conn.load("./src/main/resources/movies_in_rdf/" + fileName + ".ttl");
                System.out.println("In write transaction");
            });
        }
        // And again - implicit READ transaction.
        System.out.println("After write transaction");

    }
}
