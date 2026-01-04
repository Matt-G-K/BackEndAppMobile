package dk.sdu.mmmi.cbse.restapibackendandroid.repositoy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import dk.sdu.mmmi.cbse.restapibackendandroid.Group;

@Component
public class GroupStore {

    private ArrayList<Integer> emptyArray = new ArrayList<>();
    private ArrayList<String> emptyArrayString = new ArrayList<>();
    private final String ip = "10.0.2.2";

    private List<Group> Groups = new ArrayList<>(Arrays.asList(
            new Group(1, "Event1", emptyArrayString, emptyArray, "01-01-2020", "http://"+ip+":8080/images/group1.png"),
            new Group(2, "Event2", emptyArrayString, emptyArray, "02-01-2020", "http://"+ip+":8080/images/group1.png"),
            new Group(3, "Event3", emptyArrayString, emptyArray, "03-01-2020", "http://"+ip+":8080/images/group1.png")
    ));

    public List<Group> getGroups() {
        return Groups;
    }

    public void addGroup(Group group) {
        Groups.add(group);
    }

    public Group getLast() {
        return Groups.get(Groups.size() - 1);
    }

    public Group getGroupById(int id) {
        for (Group group : Groups) {
            if (group.getId() == id) {
                return group;
            }
        }
        return null;
    }
}
