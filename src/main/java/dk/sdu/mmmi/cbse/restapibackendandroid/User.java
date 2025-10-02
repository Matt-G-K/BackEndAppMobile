package dk.sdu.mmmi.cbse.restapibackendandroid;

import java.util.ArrayList;

public class User {
    private String username;
    private String email;
    private String password;
    private ArrayList<String> carsRented;

    public User(String username, String email, String password, ArrayList<String> carsRented){
        this.username = username;
        this.email = email;
        this.password = password;
        this.carsRented = carsRented;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getCarsRented() {
        return carsRented;
    }

    public void addCarsRented(String id) {
        carsRented.add(id);
    }
}
