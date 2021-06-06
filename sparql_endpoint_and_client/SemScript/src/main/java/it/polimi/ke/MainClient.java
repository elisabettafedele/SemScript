package it.polimi.ke;

import org.apache.jena.fuseki.main.FusekiServer;
import org.apache.jena.fuseki.system.FusekiLogging;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFactory;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.reasoner.ValidityReport;

import java.util.Iterator;
import java.util.function.Consumer;

public class MainClient {

    public static void main(String[] args) {

        //Set the name of the file you want to query
        String fileName = "the_social_network";

        //Create a Jena model from the data:
        Model data = ModelFactory.createDefaultModel();

        //read the data from the files; the serialization format is given by the extension of the file
        data.read("./src/main/resources/movies_in_rdf/" + fileName + ".ttl");

        //set the namespace:
        String ex = "http://www.semanticweb.org/elisabetta&amp;filippo/ontologies/2021/4/SemScript#";
        data.setNsPrefix( "ex", ex );

        //create the dataset
        Dataset dataset = DatasetFactory.create(data);

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
