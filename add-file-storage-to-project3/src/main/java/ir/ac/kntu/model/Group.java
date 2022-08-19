package ir.ac.kntu.model;


import java.io.Serializable;
import java.util.ArrayList;

public class Group implements Serializable {

    private String name;

    private ArrayList<Customer> users;

    private double mark;

    public Group(String name) {
        users = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public ArrayList<Customer> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<Customer> users) {
        this.users = users;
    }

    public void giveMark(Group group) {
        for (int i = 0;i < group.getUsers().size();i++) {
            group.getUsers().get(i).setMark(mark);
        }
    }
}
