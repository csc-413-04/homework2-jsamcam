package main.java;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.lang.reflect.Array;

import static com.mongodb.client.model.Filters.eq;

public class UserService {

    //add user to collection
    public static void addUser (String username, String password, MongoCollection collection){

        Document userToBeAdded = new Document("username", username)
                .append("password", password);
        collection.insertOne(userToBeAdded);
    }

    public static Boolean checkUser (String username, String password, MongoCollection collection, MongoCollection tokencollection){
        /*Grabs the username's document with password
        Will need to create a check to extract password and check with provided password.
        If either fails (to find username or match password) return Authentication_Failed.
         */
                try {
                    Document myDoc = (Document) collection.find(eq("username", username)).first();

                    //finding and stringifying the username in the database
                    Object actualUsername = myDoc.get("username");
                    String actualUsernameString = actualUsername.toString();

                    //finding and stringifying the password in the database
                    Object actualPassword = myDoc.get("password");
                    String actualPasswordString = actualPassword.toString();


                    if (actualUsernameString.equals(username) && actualPasswordString.equals(password)) {
                        Document userLoginToken = new Document("timestamp", java.time.Instant.now().toString())
                                .append ("username",username);
                        tokencollection.insertOne(userLoginToken);
                        System.out.println(userLoginToken);
                        return true;
                    } else {
                        return false;
                    }
                }
                catch (NullPointerException e){
                    return false;
                }


    }

    //add friend to collection
    public static void addFriend ( String token, String username, MongoCollection collection){

        /*
        Will add friend into document with token provided, still need to check if it's a valid token
        Will need to pass auth collection and find function, if failed or return null, Authentication_failed

         */
        Document friendToBeAdded = new Document("username", username)
                    .append("token", token);
                collection.insertOne(friendToBeAdded);
    }

    public UserService checkFriend (String token, MongoCollection collection){
        /*grabs the Friends document linked to the token provided
        Will need to return the token as a response in the main class
         */
        Document myDoc = (Document) collection.find(eq("token", token)).first();
        return null;
    }

    public User getUser (String userId, String collection){
        //get user by ID
        return null;
    }

    public void editUser (String userId, String collection){
        //update user by ID
    }

    public void deleteUser (String userId, String collection){
        //delete user by ID
    }


}//end UserService class
