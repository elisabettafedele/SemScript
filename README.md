# Knowledge Engineering Project 2021: SemScript


- ###  Elisabetta Fedele  ([@elisabettafedele](https://github.com/elisabettafedele)) <br> elisabetta.fedele@mail.polimi.it
- ###  Filippo Lazzati ([@filippolazzati](https://github.com/filippolazzati)) <br> filippo.lazzati@mail.polimi.it

SemScript is an ontology to represent a screenplay.
##1. KNOWLEDGE ENGINEERING

The ontology is written in OWL and its structure is the following:
- Character
- ParticularScene
  - ActionScene
  - CharacterScene
  - DualDialogueScene
  - TransitionScene
- Scene

Where Character, ParticularScene and Scene are the three main classes.
  

Scene represents a main scene of the movie. 
A scene has properties about the location and the time in which
it is played and other information about the pages it occupies on the screenplay.

Character represents a character of the movie, with its name and the sentences he says during the movie.
Furthermore it has associated a hasModifier property which provides information about the visibility of
the character in a scene (see [Screenplay](https://en.wikipedia.org/wiki/Screenplay)) .

Finally, ParticularScene is just a superclass of ActionScene, CharacterScene, DualDialogueScene and TransitionScene.
The suffix -scene should not be mis-interpreted. In our model, a ParticularScene is the basic unit of a Scene, and
it can be of 4 different types according to what in this unit happens (a dialogue, an action or a transition).


##2. DATA LINKING

1) The screenplays in pdf format can be downloaded from the following [website](https://www.scriptslug.com/);
2) In order to obtain a format that can be easy translated to rdf, we used a pdf to json converter for screen play from [SMASH-CUT](https://github.com/SMASH-CUT/screenplay-pdf-to-json);
3) RDF triples can be obtained from Json format through RML rules. There exists a facility language called YARRRML
   (sub-language of YAML) which allows to write RML rules in a less tricky way. The [rules.yaml](rules.yaml) file contains the
   YARRRML rules whereas the yarrrml parser directory contains the [yarrrml parser](https://github.com/RMLio/yarrrml-parser) library
   to convert this file to the RML format. 
   In order to achieve this, open the yarrrml parser folder in the prommpt and write the following command:
   yarrrml-parser -i ../rules.yaml -o ../rules.rml
   in this way you convert the rules.yaml file into the rules.rml one;
4) RMLMapper is 



## Tools

* [Protégé](https://protege.stanford.edu) - Ontology Creation
* [Maven](https://maven.apache.org/) - Dependency Management
* [IntelliJ](https://www.jetbrains.com/idea/) - IDE


1) Write the rules to convert the json in rdf in online editor matey.

2) After parse them into rml rules with the yarrrml parser with the command
(website: https://github.com/RMLio/yarrrml-parser)
(you must be inside the yarrrml parser directory):
yarrrml-parser -i ../rules.yaml -o ../rules.rml

3) I use rml mapper:
(I do not have to specify the input file since it is already specified 
inside the yarrrml file under sources)
The command is:
java -jar rmlmapper-4.9.0.jar -m rules.rml -o sparql_endpoint_and_client/SemScript/src/main/resources/movies_in_rdf/the_social_network.rdf
where output.rdf is the output file and rules.rml is the rules file.
N.B.: change the encoding from ANSI to UTF-8, otherwise the RIO parser won't be able to parse it
N.B.2: change the extension of the file to .ttl

4)