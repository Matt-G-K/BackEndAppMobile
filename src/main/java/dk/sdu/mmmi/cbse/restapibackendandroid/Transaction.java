package dk.sdu.mmmi.cbse.restapibackendandroid;

import java.util.ArrayList;

public class Transaction {
    private Integer id;
    private Integer amount;
    private ArrayList<String> users;
    private Integer group;
    private String creationDate;

    public Transaction(Integer id, Integer amount, ArrayList<String> users, Integer group, String creationDate) {
        this.id = id;
        this.amount = amount;
        this.users = users;
        this.group = group;
        this.creationDate = creationDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }

    public void addUser(String username) {
        users.add(username);
    }

    public void removeUser(String username) {
        users.removeIf(ID -> ID.equals(username));
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}
