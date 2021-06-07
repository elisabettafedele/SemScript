package it.polimi.ke.embedded_client_server_multiple_movies;

import it.polimi.ke.embedded_client_server.EmbeddedServer;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdfconnection.RDFConnectionFuseki;
import org.apache.jena.rdfconnection.RDFConnectionRemoteBuilder;

/**
 * It allows to connect to the {@link EmbeddedServerMultipleMovies} through the Java API.
 * It connects to localhost on port number 3031 and executes the query
 * correspondent to the competency questions devised.
 * The url for the connection is:
 * http://localhost:3031/ds/query
 */
public class ClientForEmbeddedServerMultipleMovies {
    public static void main(String[] args) {

        //Create a connection with the embedded server (port number = 3031)
        RDFConnectionRemoteBuilder builder = RDFConnectionFuseki.create()
                .destination("http://127.0.0.1:3031/ds/query");

        //Query 1:
        Query query1 = QueryFactory.create("PREFIX ex:      <http://www.semanticweb.org/elisabetta&amp;filippo/ontologies/2021/4/SemScript#>\n" +
                "PREFIX  :       <>\n" +
                "\n" +
                "SELECT ?title\n" +
                "{\n" +
                "?g ex:title ?title.\n" +
                "}");

        //A connection is built each time; the results are print on the command line
        System.out.println("Query 1: Which are the titles of all the movies in the dataset?");
        try ( RDFConnectionFuseki conn = (RDFConnectionFuseki)builder.build() ) {
            conn.queryResultSet(query1, ResultSetFormatter::out);
        }

        //Query 2:
        Query query2 = QueryFactory.create("PREFIX ex:      <http://www.semanticweb.org/elisabetta&amp;filippo/ontologies/2021/4/SemScript#>\n" +
                "PREFIX  :       <>\n" +
                "\n" +
                "SELECT distinct ?name\n" +
                "{\n" +
                "GRAPH :the_social_network.ttl\n" +
                "{?character ex:hasName ?name.\n" +
                " ?character ex:says ?something.\n" +
                " FILTER regex(?something, \"Did you know\", \"i\")}\n" +
                "}");

        //A connection is built each time; the results are print on the command line
        System.out.println("Query 2: Which characters in \"the social network\" movie say \"Did you know\" ?");
        try ( RDFConnectionFuseki conn = (RDFConnectionFuseki)builder.build() ) {
            conn.queryResultSet(query2, ResultSetFormatter::out);
        }
    }
}
