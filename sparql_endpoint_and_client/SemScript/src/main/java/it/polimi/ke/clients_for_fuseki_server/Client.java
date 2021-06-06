package it.polimi.ke.clients_for_fuseki_server;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFactory;

import java.util.function.Consumer;

public class Client {
    public static void main(String[] args) {
        Query query = QueryFactory.create("PREFIX  xsd:    <http://www.w3.org/2001/XMLSchema#>\n" +
                "PREFIX  dc:     <http://purl.org/dc/elements/1.1/>\n" +
                "PREFIX  :       <.>\n" +
                "\n" +
                "SELECT *\n" +
                "{\n" +
                "{ ?s ?p ?o } UNION { GRAPH ?g { ?s ?p ?o } }\n" +
                "}");
        /*
        URLS: (for the fuseki server)

    SPARQL query: http://localhost:3030/name_of_dataset/query
    SPARQL update: http://localhost:3030/name_of_dataset/update
    SPARQL HTTP update: http://localhost:3030/name_of_dataset/data

         */
        
        try (RDFConnection conn = RDFConnectionFactory.connect("http://localhost:3030/ds1_example/query") ) {
            Consumer<QuerySolution> consumer = new Consumer<QuerySolution>() {
                @Override
                public void accept(QuerySolution querySolution) {
                    org.apache.jena.rdf.model.Resource subject = querySolution.getResource("s") ;
                    System.out.println("Subject: "+ subject) ;
                }
            };
            conn.querySelect(query, consumer);
    }
    }
}
