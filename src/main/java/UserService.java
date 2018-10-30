package main.java;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.lang.reflect.Array;

import static com.mongodb.client.model.Filters.eq;

public class UserService {

    //add user to collection
    public static String addUser (String username, String password, MongoCollection collection){

        Document userToBeAdded = new Document("username", username)
                .append("password", password);
        collection.insertOne(userToBeAdded);
        return "okay";
    }

    public static String checkUser (String username, String password, MongoCollection collection, MongoCollection tokencollection){
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

                        Document sessiontoken = (Document) tokencollection.find(eq("username", username)).first();
                        Object tokenObject = sessiontoken.get("timestamp");
                        String tokenString = tokenObject.toString();
                        return tokenString;

                    } else {
                        return "authentication_failed";
                    }
                }
                catch (NullPointerException e){
                    return "authentication_faied";
                }


    }

    //add friend to collection
    public static String addFriend ( String token, String username, MongoCollection collection, MongoCollection tokencollection){

        /*
        Will add friend into document with token provided, still need to check if it's a valid token
        Will need to pass auth collection and find function, if failed or return null, Authentication_failed
         */

            Document myDoc = (Document) tokencollection.find(eq("timestamp", token)).first();

            if (myDoc != null) {
                Document friendToBeAdded = new Document("friend", username)
                        .append("timestamp", token);
                collection.insertOne(friendToBeAdded);
                return "okay";
            }

            else {
                return "authentication_failed";
            }

    }

    public static String checkFriend (String token, MongoCollection collection){
        /*grabs the Friends document linked to the token provided
        Will need to return the token as a response in the main class
         */
        try{
            Document myDoc = (Document) collection.find(eq("timestamp", token)).first();
            Object friendName = myDoc.get("friend");
            String friendNameString = friendName.toString();

            return friendNameString;
        }
        catch (NullPointerException e){
            return "No friends added";
        }
    }

}//end UserService class
