package dk.sdu.mmmi.cbse.restapibackendandroid;

import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

import dk.sdu.mmmi.cbse.restapibackendandroid.repositoy.GroupStore;
import dk.sdu.mmmi.cbse.restapibackendandroid.repositoy.UserStore;
import dk.sdu.mmmi.cbse.restapibackendandroid.service.NotificationService;

@RestController
@CrossOrigin(origins = "*")
public class GroupController {

    private final NotificationService notificationService;
    private final GroupStore Groups;
    private final UserStore users;
    private final String ip = "10.0.2.2";
    

    public GroupController(NotificationService notificationService, GroupStore Groups, UserStore users) {
        this.notificationService = notificationService;;
        this.Groups = Groups;
        this.users = users;
    }

    @GetMapping("/api/groups")
    public List<Group> getGroups() {
        System.out.println("Fetching all groups");
        return Groups.getGroups();
    }

    @GetMapping("/api/groups/member/{id}")
    public List<Group> getGroupsForMember(@PathVariable String id) {
        System.out.println("Fetching groups for member with id: "+id);
        List<Group> memberGroups = new ArrayList<Group>();
        for (Group group : Groups.getGroups()) {
             ArrayList<String> memberIDs = group.getMemberIDs();
            if (memberIDs.contains(id)) {
                memberGroups.add(group);
            }
        }
        return memberGroups;
    }

    @PutMapping("/api/addmember/{username}/{groupID}")
    public String addMember(@PathVariable String username, @PathVariable int groupID) {
        System.out.println("got member add request with username: "+username+" and groupID: "+groupID);
        for(Group group: Groups.getGroups()) {
            if(group.getId().equals(groupID) && !group.getMemberIDs().contains(username)) {
                group.addMemberID(users.getUserByUsername(username).getUserId());
                return "Member "+username+" added to group "+groupID;
            } else if (group.getId().equals(groupID) && group.getMemberIDs().contains(username)) {
                System.out.println("Member "+username+" already in group "+groupID);
                return "Member "+username+" already in group "+groupID;
            } else {
                System.out.println("Not this group: "+group.getId());
            }
        }
        return "Error";
    }

    @PutMapping("/api/addtransaction/{id}/{groupID}")
    public String addTransaction(@PathVariable int id, @PathVariable int groupID) {
        System.out.println("got transaction add request with id: "+id+" and groupID: "+groupID);
        for(Group group: Groups.getGroups()) {
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

    @PostMapping("/api/creategroup/{name}")
    public String createGroup(@PathVariable String name) {
        System.out.println("Trying to create group with name: "+name);
        int ID = Groups.getLast().getId()+1;
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String groupImage = "http://"+ip+":8080/images/group1.png";
        Group newGroup = new Group(ID, name, new ArrayList<>(), new ArrayList<>(), date, groupImage);
        Groups.addGroup(newGroup);
        return "Group created with id: "+ID;
    }

    @GetMapping("/api/group/{id}")
    public String getGroup(@PathVariable int id) {
        System.out.println("Fetching group with name: "+id);
        for(Group group: Groups.getGroups()) {
            if(group.getId().equals(id)) {
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
        for(Group group: Groups.getGroups()) {
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
        for(Group group: Groups.getGroups()) {
            if(group.getId().equals(groupID) && group.getTransactionIDs().contains(id)) {
                group.removeTransactionID(id);
                return "Transaction "+id+" removed from group "+groupID;
            } else if (group.getId().equals(groupID) && !group.getTransactionIDs().contains(id)) {
                System.out.println("Transaction "+id+" not in group "+groupID);
                return "Transaction "+id+" not in group "+groupID;
            } else {
                System.out.println("Not this group: "+group.getId());
            }
        }
        return "Error";
    }

    // Notify group about payment ping
    @GetMapping("/api/group/notify/{id}")
    public String notifyGroupPing(@PathVariable int id) {
        String groupName = getGroupNameById(id);
        if (groupName != null) {
            Groups.getGroups().stream()
                    .filter(group -> group.getId().equals(id))
                    .findFirst()
                    .ifPresent(group -> {
                        for (String memberID : group.getMemberIDs()) {
                            notificationService.sendGroupPing(memberID, groupName);
                            System.out.println("Notification sent to member: " + memberID + " of group: " + groupName);
                        }
                    });
            return "Notification sent to group: " + groupName;
        } else {
            return "Group not found.";
        }
    }


    // Helper method to get group name by ID
    public String getGroupNameById(int id) {
        for (Group group : Groups.getGroups()) {
            if (group.getId().equals(id)) {
                return group.getName();
            }
        }
        return null;
    }

    @GetMapping("/api/group/getimage/{id}")
    public String getGroupImage(@PathVariable int id) {
        System.out.println("Fetching group image for group: "+id);
        for(Group group: Groups.getGroups()) {
            if(group.getId().equals(id)) {
                return group.getGroupImage();
            } else {
                System.out.println("Not this group: "+group.getId());
            }
        }
        return "Error";
    }

    @PutMapping("api/group/setimage/{id}/{image}")
    public String setGroupImage(@PathVariable int id, @PathVariable int image) {
        System.out.println("Changing image for group with id: "+id+" to:"+image);
        for(Group group: Groups.getGroups()) {
            if(group.getId().equals(id)) {
                group.setGroupImage("http://"+ip+":8080/images/group"+image+".png");
                return "Succesfully changed image to: "+image+" for group: "+id;
            } else {
                System.out.println("Not this group: "+group.getId());
            }
        }
        return "Error";
    }
}
