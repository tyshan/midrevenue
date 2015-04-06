0) configure MySQL db

Login credential :
username root
password 

and import /sql/update.sql into Database masterdb

1) install maven from http://maven.apache.org

2) revenu range are defined in /src/main/resources/f17.csv, replace the possible values with your own

3) run in a console

mvn clean compile exec:java

