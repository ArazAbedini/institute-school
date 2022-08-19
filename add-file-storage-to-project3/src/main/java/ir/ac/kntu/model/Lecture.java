package ir.ac.kntu.model;


import ir.ac.kntu.util.ScannerWrapper;

import java.io.Serializable;
import java.util.ArrayList;

public class Lecture implements Serializable {

    private String name;

    private String institute;

    private User lecturer;

    private int year;

    private boolean open;

    private String password;

    private String info;

    private ArrayList<User> users;

    private boolean privacy;

    private ArrayList<Exercise> exercises;

    public Lecture() {

        users = new ArrayList<>();
        exercises = new ArrayList<>();

    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public boolean isOpen() {
        return open;
    }

    public String getInfo() {
        return info;
    }

    public String getInstitute() {
        return institute;
    }

    public boolean isPrivacy() {
        return privacy;
    }

    public User getLecturer() {
        return lecturer;
    }

    public String getPassword() {
        return password;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public void setLecturer(User lecturer) {
        this.lecturer = lecturer;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void setPrivacy(boolean privacy) {
        this.privacy = privacy;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public void addUser(User user) {
        boolean isJoin = false;
        if (isOpen()) {
            for (int i = 0; i < users.size(); i++) {
                if (user.getNationalCode().equals(users.get(i).getNationalCode())) {
                    System.out.println("the student is already exists!");
                    isJoin = true;
                }
            }
            if (!isJoin) {
                users.add(user);
            }
        } else {
            System.out.println("sorry! the class isnt open to join");
        }
    }

    public void addByLecturer(User student) {
        users.add(student);
    }

    public void joiningStudent(User student) {
        boolean isJoin = true;
        if (isOpen()) {
            for (int i = 0; i < users.size(); i++) {
                if (student.getEmail() == users.get(i).getEmail()) {
                    System.out.println("the student is already exists!");
                    isJoin = false;
                }
            }
        }
        if (isJoin) {
            if (privacy) {
                String password = ScannerWrapper.getInstance().next();
                if (password.equals(this.password)) {
                    users.add(student);
                } else {
                    System.out.println("the password was incorret!");
                }
            } else {
                users.add(student);
            }
        }
    }

    public void addExercise() {
        Exercise exercise = new Exercise();
        exercise = print();
        String test = ScannerWrapper.getInstance().next();
        if (test.equals("yes")) {
            exercise.setTest(true);
        } else {
            exercise.setTest(false);
        }
        System.out.println("Enter table  of exercise");
        String table = ScannerWrapper.getInstance().next();
        if (test.equals("yes")) {
            exercise.setEnableTable(true);
        } else {
            exercise.setEnableTable(false);
        }
        System.out.println("number of question you want to add");
        int number = ScannerWrapper.getInstance().nextInt();
        for (int i = 0; i < number; i++) {
            exercise.addQuestion();
        }
        exercises.add(exercise);

    }

    public Exercise print() {
        Exercise exercise = new Exercise();
        System.out.println("Enter name of exercise");
        String name = ScannerWrapper.getInstance().next();
        exercise.setName(name);
        System.out.println("Enter description of exercise");
        String info = ScannerWrapper.getInstance().next();
        exercise.setInfo(info);
        System.out.println("Enter start time of exercise");
        double start = ScannerWrapper.getInstance().nextDouble();
        exercise.setStartTime(start);
        System.out.println("Enter end time of exercise");
        double end = ScannerWrapper.getInstance().nextDouble();
        exercise.setEndTime(end);
        System.out.println("Enter delay time of exercise");
        double delay = ScannerWrapper.getInstance().nextDouble();
        exercise.setDelayTime(end);
        System.out.println("Enter delay rate of exercise");
        double rate = ScannerWrapper.getInstance().nextDouble();
        exercise.setDelayRatio(rate);
        System.out.println("Enter test of exercise");
        return exercise;
    }


}
