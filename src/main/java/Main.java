package main.java;
import static spark.Spark.*;
import java.util.List.*;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
//import com.google.code.gson.*;

/*

I found a tutorial here https://dzone.com/articles/building-simple-restful-api
https://blog.openshift.com/developing-single-page-web-applications-using-java-8-spark-mongodb-and-angularjs/

Mongodb java documentation  https://mongodb-documentation.readthedocs.io/en/latest/ecosystem/tutorial/getting-started-with-java-driver.html
Added PATH to my .bash_profile in order to be able to launch mongod from the terminal.  Also had to indicate the --dbpath value
https://docs.mongodb.com/v3.2/tutorial/install-mongodb-on-os-x/
http://mongodb.github.io/mongo-java-driver/3.4/driver/getting-started/quick-start/

*/


public class Main {

    //collections for storing all users and all authorization tokens
    java.util.Map<String, Object> users;
    java.util.Map<String, Object> auth;

    public static void main(String[] args) {
        staticFiles.externalLocation("public");
        // http://sparkjava.com/documentation
        port(1234);
        // calling get will make your app start listening for the GET path with the /hello endpoint

        //connecting to the MongoDB Server, use Robo 3t to start MongoDB on your computer
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        //connecting to the specific database on the MongoDB database by providing the name of the database
        MongoDatabase database = mongoClient.getDatabase("myDatabase");
        System.out.println("Database Name: " + database.getName());

        //getting a list of all of the databases that are available on the server to test the connection
        java.util.List<String> databaseNamesOnServer = mongoClient.getDatabaseNames();
        System.out.println("The server contains:" + databaseNamesOnServer);


        //getting a specific Table from the database on the server
        //the generics class here is <Document> but we will use a gson/json object later
        MongoCollection<Document> myDatabaseUsersCollection = database.getCollection("users");

        //creating an object to add to the Table/Collection in the database on the server
        Document userToBeAdded = new Document("username", "jsamcam")
                .append("firstName","Joel")
                .append("lastName","Samaniego Campos")
                .append("password","password");


        //test comment to see if I can push




      get("/hello", (req, res) -> "Hello Worlds");

      path("/newuser", () -> {

          get("", (req, res) -> "Getting user...");
      });

      path("/addfriend", () -> {
          //post("/", null);
          get("", (req, res) -> "Adding friend...");
      });

      path("/friends", () -> {
          get("", (req, res ) -> "Getting friends...");

      });


    }//end main method
}//end Main class
