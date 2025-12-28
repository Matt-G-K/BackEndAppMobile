package dk.sdu.mmmi.cbse.restapibackendandroid;

import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
public class GroupController {

    private ArrayList<Integer> emptyArray = new ArrayList<>();
    private ArrayList<String> emptyArrayString = new ArrayList<>();

    private List<Group> Groups = new ArrayList<>(Arrays.asList(
            new Group(1, "Event1", emptyArrayString, emptyArray, "01-01-2020"),
            new Group(2, "Event2", emptyArrayString, emptyArray, "02-01-2020"),
            new Group(3, "Event3", emptyArrayString, emptyArray, "03-01-2020")
    ));

    @GetMapping("/api/groups")
    public List<Group> getGroups() {
        return Groups;
    }

    @GetMapping("/api/groups/member/{id}")
    public List<Group> getGroupsForMember(@PathVariable String id) {
        List<Group> memberGroups = new ArrayList<Group>();
        for (Group group : Groups) {
             ArrayList<String> memberIDs = group.getMemberIDs();
            if (memberIDs.contains(id)) {
                memberGroups.add(group);
            }
        }
        return memberGroups;
    }

    @PutMapping("/api/addmember/{id}/{groupID}")
    public String addMember(@PathVariable String id, @PathVariable int groupID) {
        System.out.println("got member add request with id: "+id+" and groupID: "+groupID);
        for(Group group: Groups) {
            if(group.getId().equals(groupID) && !group.getMemberIDs().contains(id)) {
                group.addMemberID(id);
                return "Member "+id+" added to group "+groupID;
            } else if (group.getId().equals(groupID) && group.getMemberIDs().contains(id)) {
                System.out.println("Member "+id+" already in group "+groupID);
                return "Member "+id+" already in group "+groupID;
            } else {
                System.out.println("Not this group: "+group.getId());
            }
        }
        return "Error";
    }

    @PutMapping("/api/addtransaction/{id}/{groupID}")
    public String addTransaction(@PathVariable int id, @PathVariable int groupID) {
        System.out.println("got transaction add request with id: "+id+" and groupID: "+groupID);
        for(Group group: Groups) {
            if(group.getId().equals(groupID) && !group.getTransactionIDs().contains(id)) {
                group.addTransactionID(id);
                return "Transaction "+id+" added to group "+groupID;
            } else if (group.getId().equals(groupID) && group.getTransactionIDs().contains(id)) {
                System.out.println("Transaction "+id+" already in group "+groupID);
                return "Transaction "+id+" already in group "+groupID;
            } else {
                System.out.println("Not this group: "+group.getId());
            }
        }
        return "Error";
    }

    @PostMapping("api/creategroup/{name}")
    public String createGroup(@PathVariable String name) {
        System.out.println("Trying to create group with name: "+name);
        int ID = Groups.getLast().getId()+1;
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        Group newGroup = new Group(ID, name, emptyArrayString, emptyArray, date);
        Groups.add(newGroup);
        return "Group created with id: "+ID;
    }

    @GetMapping("/api/group/{id}")
    public String getGroup(@PathVariable String id) {
        System.out.println("Fetching group with name: "+id);
        for(Group group: Groups) {
            if(group.getId().equals(Integer.parseInt(id))) {
                return group.toString();
            } else {
                System.out.println("Not this group: "+group.getId());
            }
        }
        return "Error";

    }

    @PutMapping("api/removemember/{id}/{groupID}")
    public String removeMember(@PathVariable String id, @PathVariable int groupID) {
        System.out.println("Trying to remove member: "+id+" from group: "+groupID);
        for(Group group: Groups) {
            if(group.getId().equals(groupID) && group.getMemberIDs().contains(id)) {
                group.removeMemberID(id);
                return "Member "+id+" removed to group "+groupID;
            } else if (group.getId().equals(groupID) && !group.getMemberIDs().contains(id)) {
                System.out.println("Member "+id+" not in group "+groupID);
                return "Member "+id+" not in group "+groupID;
            } else {
                System.out.println("Not this group: "+group.getId());
            }
        }
        return "Error";
    }

    @PutMapping("/api/removetransaction/{id}/{groupID}")
    public String removeTransaction(@PathVariable int id, @PathVariable int groupID) {
        System.out.println("got transaction remove request with id: "+id+" and groupID: "+groupID);
        for(Group group: Groups) {
            if(group.getId().equals(groupID) && group.getTransactionIDs().contains(id)) {
                group.removeTransactionID(id);
                return "Transaction "+id+" added to group "+groupID;
            } else if (group.getId().equals(groupID) && !group.getTransactionIDs().contains(id)) {
                System.out.println("Transaction "+id+" not in group "+groupID);
                return "Transaction "+id+" not in group "+groupID;
            } else {
                System.out.println("Not this group: "+group.getId());
            }
        }
        return "Error";
    }


    // Helper method to get group name by ID
    public String getGroupNameById(int id) {
        for (Group group : Groups) {
            if (group.getId().equals(id)) {
                return group.getName();
            }
        }
        return null;
    }
}
