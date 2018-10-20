package main.java;
import static spark.Spark.*;
import com.mongodb.*;
import com.google.gson.*;

/*

I found a tutorial here https://dzone.com/articles/building-simple-restful-api
https://blog.openshift.com/developing-single-page-web-applications-using-java-8-spark-mongodb-and-angularjs/

Mongodb java documentation  https://mongodb-documentation.readthedocs.io/en/latest/ecosystem/tutorial/getting-started-with-java-driver.html

*/


public class Main {

    //collections for storing all users and all authorization tokens
    java.util.Map<String, Object> users;
    java.util.Map<String, Object> auth;
    private final DB mongoDatabase = null;
    private final DBCollection collection = mongoDatabase.getCollection("users");


    public static void main(String[] args) {
      staticFiles.externalLocation("public");
      // http://sparkjava.com/documentation
      port(1234);
      // calling get will make your app start listening for the GET path with the /hello endpoint


      User newUser = new User();

      System.out.println(newUser.getUserName());

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
