package dk.sdu.mmmi.cbse.restapibackendandroid;

import java.util.ArrayList;

public class User {
    private String userId;
    private String username;
    private String email;
    private String password;
    private ArrayList<Integer> groupsMember;
    private ArrayList<Integer> transactionsMember;

    public User(String username, String email, String password, ArrayList<Integer> groupsMember, ArrayList<Integer> transactionsMember){
        this.userId = null;
        this.username = username;
        this.email = email;
        this.password = password;
        this.groupsMember = groupsMember;
        this.transactionsMember = transactionsMember;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public ArrayList<Integer> getGroupsMember() {
        return groupsMember;
    }

    public void addGroupMember(int id) {
        groupsMember.add(id);
    }

    public void removeGroupMember(int id) {
        groupsMember.remove(id);
    }

    public ArrayList<Integer> getTransactionsMember() {
        return transactionsMember;
    }

    public void addTransactionMember(int id) {
        transactionsMember.add(id);
    }

    public void removeTransactionMember(int id) {
        transactionsMember.remove(id);
    }
}
