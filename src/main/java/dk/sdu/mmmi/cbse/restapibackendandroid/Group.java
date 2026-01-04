package dk.sdu.mmmi.cbse.restapibackendandroid;

import java.util.ArrayList;

public class Group {
    private Integer id;
    private String name;
    private ArrayList<String> memberIDs;
    private ArrayList<Integer> transactionIDs;
    private String creationDate;
    private String groupImage;

    public Group(Integer id, String name, ArrayList<String> memberIDs, ArrayList<Integer> transactionIDs, String creationDate, String groupImage){
        this.id = id;
        this.name = name;
        this.memberIDs = memberIDs;
        this.transactionIDs = transactionIDs;
        this.creationDate = creationDate;
        this.groupImage = groupImage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getMemberIDs() {
        return memberIDs;
    }

    public void setMemberIDs(ArrayList<String> memberIDs) {
        this.memberIDs = memberIDs;
    }

    public void addMemberID(String memberID) {
        memberIDs.add(memberID);
    }

    public void removeMemberID(String memberID) {
        memberIDs.removeIf(ID -> ID.equals(memberID));
    }

    public ArrayList<Integer> getTransactionIDs() {
        return transactionIDs;
    }

    public void setTransactionIDs(ArrayList<Integer> transactionIDs) {
        this.transactionIDs = transactionIDs;
    }

    public void addTransactionID(Integer transactionID) {
        transactionIDs.add(transactionID);
    }

    public void removeTransactionID(Integer transactionID) {
        transactionIDs.removeIf(ID -> ID.equals(transactionID));
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getGroupImage() {
        return groupImage;
    }

    public void setGroupImage(String groupImage) {
        this.groupImage = groupImage;
    }
}
