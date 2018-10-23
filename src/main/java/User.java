package main.java;

public class User {

    //class fields
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private static Integer counter = 0;

    //default constructor
    User(){
        this("Johnny", "Appleseed", ("username" + Integer.toString(counter)), "password");
        counter++;
    }

    //parameterized constructor
    User(String firstName, String lastName, String userName, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }

    //class getters and setters
    protected User setFirstName(String firstName){
        this.firstName = firstName;
        return this;
    }

    protected User setLastName(String lastName){
        this.lastName = lastName;
        return this;
    }

    protected User setPassword(String password){
        this.password = password;
        return this;
    }

    protected User setUserName(String userName){
        this.userName = userName;
        return this;
    }

    protected String getFirstName(){
        return this.firstName;
    }

    protected String getLastName(){
        return this.lastName;
    }

    protected String getUserName(){
        return this.userName;
    }

}
