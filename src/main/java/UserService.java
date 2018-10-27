package main.java;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class UserService {

    //add user to collection
    public static void addUser (String username, String password, MongoCollection collection){

        Document userToBeAdded = new Document("username", username)
                .append("password", password);
        collection.insertOne(userToBeAdded);
    }

    //add friend to collection
    public static void addFriend (String token, String username, MongoCollection collection){


        Document friendToBeAdded = new Document("username", username)
                    .append("token", token);
                collection.insertOne(friendToBeAdded);
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
