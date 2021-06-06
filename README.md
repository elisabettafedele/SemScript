# KeProjectSemScript

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