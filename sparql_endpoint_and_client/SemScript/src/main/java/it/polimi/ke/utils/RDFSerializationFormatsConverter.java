package it.polimi.ke.utils;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.riot.RDFDataMgr;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class RDFSerializationFormatsConverter {
    public static void main(String[] args) {
        //path of the rdf file to convert
        String path = "./src/main/resources/movies_in_rdf/the_social_network.rdf";

        //load model
        Model model = RDFDataMgr.loadModel(path) ;

        //write model in new format
        File file = new File("./src/main/resources/movies_in_rdf/the_social_network.ttl");
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //possible formats: "name" -> refers to -> LANGUAGE
        //"TURTLE" ->	TURTLE
        //"TTL" ->	TURTLE
        //"Turtle" ->	TURTLE
        //"N-TRIPLES" ->	NTRIPLES
        //"N-TRIPLE" ->	NTRIPLES
        //"NT" ->	NTRIPLES
        //"JSON-LD" ->	JSONLD
        //"RDF/XML-ABBREV" -> 	RDFXML
        //"RDF/XML" ->	RDFXML_PLAIN
        //"N3" ->	N3
        //"RDF/JSON" -> RDFJSON
        model.write(os, "TURTLE") ;

    }
}
