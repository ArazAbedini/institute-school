package ir.ac.kntu.logic;


import ir.ac.kntu.model.*;
import ir.ac.kntu.util.Data;
import ir.ac.kntu.util.ScannerWrapper;
import ir.ac.kntu.menu.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.io.*;

public class Menu implements java.io.Serializable {

    private User user;

    private Customer customer;

    private ArrayList<Lecture> exitedLectures;

    private Lecture currentLecture;

    private ArrayList<Customer> existedUsers;

    private ArrayList<Competition> competitions;

    private ArrayList<Admin> admins;

    private Admin admin;

    int index = 0;

    private Print print;

    private Data data;


    public Menu() {
        user = new User();
        customer = new Customer();
        currentLecture = new Lecture();
        exitedLectures = new ArrayList<>();
        existedUsers = new ArrayList<>();
        competitions = new ArrayList<>();
        admins = new ArrayList<>();
        print = new Print();
        admin = new Admin();
        data = Data.getInstance(admins, existedUsers, competitions);
        Admin admin = new Admin();


    }

    public void sign() {
        print.sign();
        int signChoosing = ScannerWrapper.getInstance().nextInt();
        if (signChoosing == 2) {
            Customer newUser = new Customer();
            newUser.signUp();
            existedUsers.add(newUser);
            user = newUser;
            usersHandler();
        } else if (signChoosing == 1) {
            System.out.println(existedUsers.size());
            user = user.signIn(existedUsers, admins);
            if (user instanceof Admin) {
                System.out.println("ITS ADMIN");
                adminMenu();
            } else {
                customer = (Customer) user;
                usersHandler();
            }
        } else if (signChoosing == 3) {
            guestMode();
        } else if (signChoosing == 0) {
            //System.exit(0);
            index++;

        } else {
            System.out.println("Wrong Number Entered, Please Try Again");
            sign();
        }

    }

    public void usersHandler() {
        System.out.println(existedUsers.size());
        for (int k = 0; k < existedUsers.size(); k++) {
            System.out.println(existedUsers.get(k).getEmail());
        }
        print.user();
        String number = ScannerWrapper.getInstance().next();
        if (number.equals("1")) {
            usersClass();
        } else if (number.equals("2")) {
            competitionAsUser();
        } else if (number.equals("0")) {
            sign();
        } else {
            System.out.println("try again");
            usersHandler();
        }
    }

    public void competitionAsUser() {
        print.userCompetition();
        String number = ScannerWrapper.getInstance().next();
        if (number.equals("1")) {
            customer = customer.joinCompetition(competitions, customer);
        } else if (number.equals("2")) {
            customer.giveSolution(competitions, customer);
        } else if (number.equals("0")) {
            usersHandler();
        } else {
            competitionAsUser();
        }
        competitionAsUser();
    }

    public void guestMode() {
        print.guest();
        int option = ScannerWrapper.getInstance().nextInt();
        if (option == 1) {
            questionBank();
        } else if (option == 2) {
            Guest guest = new Guest();
            guest.comparing(competitions);
        } else if (option == 0) {
            sign();
        } else {
            System.out.println("Wrong Number Entered, Please Try Again");
            guestMode();
        }

    }

    public void adminMenu() {
        print.admin();
        int option = ScannerWrapper.getInstance().nextInt();
        if (option == 1) {
            competitionPrint();
        } else if (option == 2) {
            customer.searching(exitedLectures, customer);
            if (admins.contains(user)) {
                adminMenu();
            } else {
                usersClass();
            }
        } else if (option == 3) {
            usersList();
        } else if (option == 4) {
            questionBank();
        } else if (option == 5) {
            makeAdmin();
        } else if (option == 0) {
            sign();
        } else {
            System.out.println("Wrong Number Entered, Please Try Again");
            adminMenu();
        }
    }

    public void usersList() {
        Customer tempUser = new Customer();
        tempUser = admin.searchUser(existedUsers);
        System.out.println(tempUser);
    }

    public void makeAdmin() {
        Customer tempUser = admin.searchUser(existedUsers);
        Admin newAdmin = admin.makeAdmin(tempUser);
        existedUsers.remove(tempUser);
        admins.add(newAdmin);
        adminMenu();
    }

    public void competitionPrint() {
        print.competition();
        competitionHandler();
    }

    public void competitionHandler() {
        int order = ScannerWrapper.getInstance().nextInt();
        if (order == 1) {
            admin.createCompetition(competitions);
        } else if (order == 2) {
            admin.editCompetition(competitions);
        } else if (order == 3) {
            admin.deleteCompetition(competitions);
        } else if (order == 4) {
            invite(admin);
        } else if (order == 5) {
            admin.competitionResult(competitions);
        } else if (order == 0) {
            adminMenu();
        } else {
            System.out.println("Wrong Number Entered, Please Try Again");
        }
        competitionPrint();
    }

    public void invite(Admin admin) {
        boolean isExist = false;
        ScannerWrapper.getInstance().nextLine();
        System.out.print("competition you want : ");
        String name = ScannerWrapper.getInstance().nextLine();
        for (int i = 0; i < competitions.size(); i++) {
            if (competitions.get(i).getName().equals(name)) {
                existedUsers = admin.invite(existedUsers, competitions.get(i));
                isExist = true;
            }
        }
        if (isExist == false) {
            System.out.println("There isnt competition, try again");
            invite(admin);
        }
        System.out.println("for again enter 0");
        String again = ScannerWrapper.getInstance().next();
        if (again.equals("0")) {
            invite(admin);
        }
    }


    public void usersClass() {
        print.usersClassPrint();
        int option = ScannerWrapper.getInstance().nextInt();
        if (option == 1) {
            firstChoose();
        }
        if (option == 2) {
            customer.searching(exitedLectures, customer);
            usersClass();
        }
        if (option == 3) {
            thirdChoose();
        }
        if (option == 4) {
            questionBank();
        }
        if (option == 5) {
            competition();
        }
        if (option == 0) {
            sign();
        }

    }

    public void competition() {
        if (customer.getCompetitions().size() == 0) {
            System.out.println("you arent in any competition");
        } else {
            for (int i = 0; i < customer.getCompetitions().size(); i++) {
                System.out.println("            " + i + 1 + " - " + customer.getCompetitions().get(i));
            }
        }
        customer.giveSolution(competitions, customer);
    }

    public void questionBank() {
        QuestionsBank bank = new QuestionsBank();
        print.question();
        int choosenNumber = ScannerWrapper.getInstance().nextInt();
        System.out.println("Enter number of question which you want");
        int number = ScannerWrapper.getInstance().nextInt();
        if ((number - 1) <= bank.getQuestions().size()) {
            if (choosenNumber == 1) {
                bank.getQuestion(number);
            }
            Question question = new Question();
            if (choosenNumber == 2) {
                secondChoose(bank, number, question);
            }
            if (choosenNumber == 3) {
                addFromBankToExercise(number, bank);
            }
        } else {
            System.out.println("there isnt exist");
            usersClass();
        }
    }

    public void addFromBankToExercise(int number, QuestionsBank bank) {
        Question question = new Question();
        Lecture lecture = new Lecture();
        question = bank.getQuestion(number);
        System.out.println("Class You Want : ");
        int index = 0;
        String name = ScannerWrapper.getInstance().next();
        for (int i = 0; i < exitedLectures.size(); i++) {
            if (exitedLectures.get(i).equals(name)) {
                lecture = exitedLectures.get(i);
                index = i;
            }
        }
        System.out.println("Exercise You Want : ");
        name = ScannerWrapper.getInstance().next();
        for (int j = 0; j < exitedLectures.get(index).getExercises().size(); j++) {
            if (name.equals(exitedLectures.get(index).getExercises().get(j))) {
                exitedLectures.get(index).getExercises().get(j).getQuestions().add(question);
            }
        }
    }


    public void firstChoose() {
        Lecture lecture = new Lecture();
        currentLecture = user.createClass(existedUsers);
        exitedLectures.add(currentLecture);
        usersClass();
    }

    public void thirdChoose() {
        currentLecture = user.goToClass(exitedLectures, user);
        if (currentLecture.equals(null)) {
            usersClass();
        } else {
            System.out.println("Hello");
            inClass();
        }
    }

    public void secondChoose(QuestionsBank bank, int number, Question question) {
        question = bank.getQuestionForClass(number);
        System.out.println("Enter name of class you want");
        String className = ScannerWrapper.getInstance().next();
        System.out.println("Enter name of exercise you want");
        String exercise = ScannerWrapper.getInstance().next();
        for (int i = 0; i < exitedLectures.size(); i++) {
            if (exitedLectures.get(i).getName().equals(className)) {
                if (exitedLectures.get(i).getLecturer().equals(user)) {
                    for (int j = 0; j < exitedLectures.get(i).getExercises().size(); j++) {
                        if (exitedLectures.get(i).getExercises().get(j).getName().equals(exercise)) {
                            exitedLectures.get(i).getExercises().get(j).addTo(question);
                        }
                    }
                }
            }
        }
    }

    public void inClass() {
        if (user.getType().equals(Graduate.LECTURER)) {
            userAsLecturer();
        } else if (user.getType().equals(Graduate.STUDENT)) {
            userAsStudent();
        } else {
            userAsTA();
        }

    }

    public void userAsTA() {
        Lecture lecture = new Lecture();
        currentLecture = user.createClass(existedUsers);
        exitedLectures.add(currentLecture);
        usersClass();
    }

    public void userAsStudent() {
        System.out.println("Name of class you want : ");
        String name = ScannerWrapper.getInstance().next();
        boolean classExist = false;
        for (int i = 0; i < exitedLectures.size(); i++) {
            if (exitedLectures.get(i).getName().equals(name)) {
                currentLecture = exitedLectures.get(i);
                classExist = true;
            }
        }
        if (!classExist) {
            System.out.println("Class is not exist!");
        }
        System.out.println("All available exercise : ");
        for (int i = 0; i < currentLecture.getExercises().size(); i++) {
            double time = currentTime();
            currentLecture.getExercises().get(i).setTime(time);
            System.out.println("         " + currentLecture.getExercises().get(i).getName());
        }
        System.out.println("Enter name of Exercise you want");
        name = ScannerWrapper.getInstance().next();
        for (int j = 0; j < currentLecture.getExercises().size(); j++) {
            if (currentLecture.getExercises().get(j).getName().equals(name)) {
                for (int k = 0; k < currentLecture.getExercises().get(j).getQuestions().size(); k++) {
                    currentLecture.getExercises().get(j).getQuestions().get(k).giveSolution(user);
                }
            }
        }
    }

    public double currentTime() {
        System.out.println("time you want it : ");
        double time = ScannerWrapper.getInstance().nextDouble();
        Exercise exercise = new Exercise();
        exercise.setTime(time);
        return time;
    }

    public void userAsLecturer() {
        print.lecturer();
        int option = ScannerWrapper.getInstance().nextInt();
        if (option == 0) {
            usersClass();
        }
        if (option == 1) {
            addStudent();
        }
        if (option == 2) {
            currentLecture.addExercise();
            userAsLecturer();
        }
        if (option == 3) {
            ArrayList<Answer> finalAnswer = new ArrayList<>();
            finalAnswer = findFinalAnswer();
            giveMark(finalAnswer);
        }
    }

    public void giveMark(ArrayList<Answer> answer) {
        for (int i = 0; i < answer.size(); i++) {
            System.out.println(answer.get(i).getSolution());
            double mark = ScannerWrapper.getInstance().nextDouble();
            answer.get(i).setMark(mark);
        }
    }

    public ArrayList<Answer> findFinalAnswer() {
        System.out.println("Exercise you want");
        String name = ScannerWrapper.getInstance().next();
        System.out.println("question you want");
        String nameOfQuestion = ScannerWrapper.getInstance().next();
        for (int i = 0; i < currentLecture.getExercises().size(); i++) {
            if (currentLecture.getExercises().get(i).getName().equals(name)) {
                ArrayList<Question> question = new ArrayList<>();
                question = currentLecture.getExercises().get(i).getQuestions();
                for (int j = 0; j < question.size(); j++) {
                    if (question.get(j).getName().equals(nameOfQuestion)) {
                        return question.get(j).getFinalAnswer();
                    }
                }
            }
        }
        System.out.println("YOU MAKE BIG MISTAKE");
        return null;
    }


    public void addStudent() {
        System.out.println("Enter Email of student who want to add :");
        String email = ScannerWrapper.getInstance().next();
        boolean added = false;
        for (int i = 0; i < existedUsers.size(); i++) {
            if (email.equals(existedUsers.get(i).getEmail())) {
                currentLecture.addByLecturer(existedUsers.get(i));
                added = true;
            }
        }
        if (added == false) {
            System.out.println("The email was wrong");
            userAsLecturer();
        }
    }

    public int getIndex() {
        return index;
    }

    public ArrayList<Admin> getAdmins() {
        return admins;
    }


}

