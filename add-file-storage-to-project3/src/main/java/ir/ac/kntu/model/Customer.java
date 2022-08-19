package ir.ac.kntu.model;

import ir.ac.kntu.util.ScannerWrapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Customer extends User implements Serializable {
    private int level;

    @Override
    public void setMark(double mark) {
        super.setMark(mark);
        level = (int) (getMark() / 50);
    }

    @Override
    public double getMark() {
        return super.getMark();
    }

    public int getLevel() {
        return level;
    }

    public Customer joinCompetition(ArrayList<Competition> competitions, Customer user) {
        for (int i = 0; i < competitions.size(); i++) {
            if (!(competitions.get(i) instanceof PrivateCompetition)) {
                System.out.println(competitions.get(i).getName());
            }
        }
        System.out.print("Enter competition you want : ");
        String name = ScannerWrapper.getInstance().next();
        if (name.equals("0")) {
            return user;
        }
        boolean isClass = false;
        for (int i = 0; i < competitions.size(); i++) {
            if (competitions.get(i).getName().equals(name)) {
                isClass = true;
                competitionHandler(competitions.get(i), user);
            }
        }
        if (!isClass) {
            System.out.println("for back enter 0");
            joinCompetition(competitions, user);
        }
        return user;
    }

    public void competitionHandler(Competition competition, Customer user) {
        if (competition instanceof OrdinaryCompetition) {
            if (((OrdinaryCompetition) competition).getAttenders().size() < 50) {
                tempCompetition(user, competition);
            }
        } else if (competition instanceof PrivateCompetition) {
            if (((PrivateCompetition) competition).getAttenders().size() < 20) {
                tempCompetition(user, competition);
            }
        } else if (competition instanceof SpecialCompetition) {
            if (((SpecialCompetition) competition).getAttenders().size() < 1000) {
                group((SpecialCompetition) competition, user);
            }
        }
    }

    public void group(SpecialCompetition competition, Customer user) {
        System.out.print("1 for create group, 2 for join group");
        String input = ScannerWrapper.getInstance().next();
        if (input.equals("1")) {
            addGroup(user, competition);
        } else if (input.equals("2")) {
            joinGroup(user, competition);
        } else {
            System.out.println("try again, wrong number");
            group(competition, user);
        }
    }

    public void joinGroup(Customer user, SpecialCompetition competition) {
        for (int i = 0; i < competition.getGroups().size(); i++) {
            System.out.println(competition.getGroups().get(i).getName());
        }
        enterName(user, competition);
    }

    public void enterName(Customer user, SpecialCompetition competition) {
        ScannerWrapper.getInstance().nextLine();
        boolean exist = false;
        System.out.print("enter name : ");
        String name = ScannerWrapper.getInstance().nextLine();
        for (int i = 0; i < competition.getGroups().size(); i++) {
            if (name.equals(competition.getGroups().get(i))) {
                ArrayList<Customer> users = new ArrayList<>();
                users = competition.getGroups().get(i).getUsers();
                users.add(user);
                competition.getGroups().get(i).setUsers(users);
                competition.getAttenders().add(user);
                exist = true;
            }
        }
        if (exist == false) {
            enterName(user, competition);
        }

    }

    public void addGroup(Customer user, SpecialCompetition competition) {
        ScannerWrapper.getInstance().nextLine();
        System.out.print("Enter name : ");
        String name = ScannerWrapper.getInstance().nextLine();
        for (int i = 0; i < competition.getGroups().size(); i++) {
            if (name.equals(competition.getGroups().get(i).getName())) {
                System.out.println("try another name ");
                addGroup(user, competition);
            }
        }
        Group group = new Group(name);
        ArrayList<Customer> users = new ArrayList<>();
        users.add(user);
        group.setUsers(users);
        competition.getAttenders().add(user);
    }

    public void tempCompetition(Customer user, Competition competition) {
        ArrayList<Competition> tempCompetitions = new ArrayList<>();
        tempCompetitions = user.getCompetitions();
        tempCompetitions.add(competition);
        user.setCompetitions(tempCompetitions);
        ArrayList<Double> marks = new ArrayList<>();
        marks = competition.getMarks();
        marks.add(0.0);
    }

    public void giveSolution(ArrayList<Competition> competitions, Customer user) {
        System.out.println("Name of competitions you want : ");
        String name = ScannerWrapper.getInstance().next();
        if (name.equals("0")) {
            return;
        }
        boolean classExist = false;
        Competition competition = new Competition();
        for (int i = 0; i < competitions.size(); i++) {
            if (competitions.get(i).getName().equals(name)) {
                competition = competitions.get(i);
                classExist = true;
            }
        }
        if (!classExist) {
            System.out.println("Competition is not exist!");
            giveSolution(competitions, user);
        }
        if (classExist) {
            int value = competition.getEndTime().compareTo(LocalDateTime.now());
            System.out.println(competition.getEndTime());
            System.out.println(LocalDateTime.now());
            if (value > 0) {
                canAnswer(competition, name, user);
            }
        }
    }

    public void canAnswer(Competition competition, String name, Customer user) {
        System.out.println("All available questions : ");
        for (int i = 0; i < competition.getQuestions().size(); i++) {
            System.out.println("         " + competition.getQuestions().get(i).getName());
        }
        System.out.println("Enter name of question you want");
        name = ScannerWrapper.getInstance().next();
        for (int j = 0; j < competition.getQuestions().size(); j++) {
            if (competition.getQuestions().get(j).getName().equals(name)) {
                System.out.println(competition.getQuestions().get(j).getType());
                System.out.println(competition.getQuestions().get(j).getMark());
                competition.getQuestions().get(j).solutionForCompetition(user, competition, j);

            }
        }
    }

    public boolean isExist(ArrayList<Competition> competitions, String name, Competition competition) {
        boolean classExist = false;
        for (int i = 0; i < competitions.size(); i++) {
            if (competitions.get(i).getName().equals(name)) {
                competition = competitions.get(i);
                classExist = true;
            }
        }
        return classExist;
    }

    public void searching(ArrayList<Lecture> lecture, Customer user) {
        System.out.println("Enter name of class you want : ");
        String name = ScannerWrapper.getInstance().next();
        boolean existClass = false;
        for (int i = 0; i < lecture.size(); i++) {
            if (name.equals(lecture.get(i).getName())) {
                existClass = true;
                if (lecture.get(i).isPrivacy()) {
                    System.out.println("Enter password of class : ");
                    String password = ScannerWrapper.getInstance().next();
                    if (password.equals(lecture.get(i).getPassword())) {
                        lecture.get(i).addUser(user);
                    } else {
                        elsePrint();
                        int option = ScannerWrapper.getInstance().nextInt();
                        if (option == 1) {
                            searching(lecture, user);
                        }
                    }
                } else {
                    lecture.get(i).addUser(user);
                }
            }
        }
        if (!existClass) {
            System.out.println("the class isnt exist!");
        }
    }

    public static void makeHtmlFile(String s) {
        try {
            Files.deleteIfExists(Path.of("PointTable.html"));
            File myObj = new File("PointTable.html");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try {
            FileWriter myWriter = new FileWriter("PointTable.html");
            myWriter.write("<html>\n<head>\n<title>Points table\n</title>\n<p>\n" + s + "</p>");
            myWriter.close();
            //System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
