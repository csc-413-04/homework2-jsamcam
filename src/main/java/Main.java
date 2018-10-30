package main.java;
import static com.mongodb.client.model.Filters.eq;
import static spark.Spark.*;

import java.util.ArrayList;
import java.util.List;
import java.util.List.*;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import javax.print.Doc;
//import com.google.code.gson.*;

/*

I found a tutorial here https://dzone.com/articles/building-simple-restful-api
https://blog.openshift.com/developing-single-page-web-applications-using-java-8-spark-mongodb-and-angularjs/

Mongodb java documentation  https://mongodb-documentation.readthedocs.io/en/latest/ecosystem/tutorial/getting-started-with-java-driver.html
Added PATH to my .bash_profile in order to be able to launch mongod from the terminal.  Also had to indicate the --dbpath value when trying to launch mongod
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

        //connecting to the MongoDB Server, use Robo 3t to start MongoDB on your computer
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        //connecting to the specific database on the MongoDB database by providing the name of the database
        MongoDatabase database = mongoClient.getDatabase("REST2");
        System.out.println("Database Name: " + database.getName());

        //getting a list of all of the databases that are available on the server to test the connection
        java.util.List<String> databaseNamesOnServer = mongoClient.getDatabaseNames();
        System.out.println("The server contains:" + databaseNamesOnServer);

        //getting a specific Table from the database on the server
        //the generics class here is <Document> but we will use a gson/json object later
        MongoCollection<Document> myDatabaseUsersCollection = database.getCollection("users");

        //getting a Table/Collection for the session tokens that we'll use to manage authentication
        MongoCollection<Document> sessionTokenCollection = database.getCollection("auth");

        //requirement: /newuser?username=<username>&password=<pass>
        path("/newuser", () -> {
            get("",(req,res) ->
                UserService.addUser(req.queryParams("username"), req.queryParams("password"), myDatabaseUsersCollection));
        });

        //requirement: /login?username=<username>&password=<pass>
        path("/login", () -> {
            get("", (req, res) ->
                UserService.checkUser(req.queryParams("username"), req.queryParams("password"), myDatabaseUsersCollection, sessionTokenCollection));
        });

        //requirement: /addfriend?token=<token>&friend=<freindsuserid>
        path("/addfriend", () -> {
                get("", (req, res) ->
                    UserService.addFriend(req.queryParams("token"), req.queryParams("friend"), myDatabaseUsersCollection, sessionTokenCollection));
            });

        //requirement: /friends?token=<token>
        path("/friends", () -> {
            get("", (req, res ) -> UserService.checkFriend(req.queryParams("token"), myDatabaseUsersCollection));
        });

        //add custom 404 handling for any path that is not matched above
        //we might be able to use this to send back an invalid request json
        path("/",() -> {
            notFound("<html><body><h1>CSC412 Assignment 2</h1><img src=\"https://i.kym-cdn.com/photos/images/original/000/232/248/483.png\"</body></html>");

        });

    }//end main method
}//end Main class
