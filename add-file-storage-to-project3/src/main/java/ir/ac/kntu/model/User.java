package ir.ac.kntu.model;

import ir.ac.kntu.menu.Print;
import ir.ac.kntu.util.ScannerWrapper;
import ir.ac.kntu.*;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

    private String name;

    private String userName;

    private String email;

    private String password;

    private String nationalCode;

    private String phoneNumber;

    private Lecture lecture;

    private Graduate type;

    private ArrayList<Lecture> existedLecture;

    private double mark;

    private ArrayList<Competition> competitions;

    public User() {
        existedLecture = new ArrayList<>();
        competitions = new ArrayList<>();
    }

    public Graduate getType() {
        return type;
    }


    public ArrayList<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(ArrayList<Competition> competitions) {
        this.competitions = competitions;
    }

    public ArrayList<Lecture> getExistedLecture() {
        return existedLecture;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public double getMark() {
        return mark;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public void setEmail(String email) {
        if (email.matches("\\S+@\\w+\\.\\S+")) {
            this.email = email;
        } else {
            System.out.println("your email isnt correct, please correct it");
        }
    }

    public void setUserName(String familyName) {
        this.userName = familyName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.matches("09\\d{9}")) {
            this.phoneNumber = phoneNumber;
        } else {
            System.out.println("your phone number isnt correct, please enter again");
        }
    }

    public void signUp() {
        ScannerWrapper.getInstance().nextLine();
        type();
        System.out.print("Enter your name : ");
        String name = ScannerWrapper.getInstance().next();
        setName(name);
        System.out.print("Enter your username : ");
        String familyName = ScannerWrapper.getInstance().next();
        setUserName(familyName);
        System.out.print("Enter your  national code: ");
        String nationalCode = ScannerWrapper.getInstance().next();
        setNationalCode(nationalCode);
        System.out.print("Enter your email : ");
        String email = ScannerWrapper.getInstance().next();
        setEmail(email);
        System.out.print("Enter your phone number : ");
        String phoneNumber = ScannerWrapper.getInstance().next();
        setPhoneNumber(phoneNumber);
        System.out.print("Enter your password : ");
        String password = ScannerWrapper.getInstance().next();
        setPassword(password);

    }

    public void type() {
        Print print = new Print();
        print.introduce();
        String number = ScannerWrapper.getInstance().next();
        if (number.equals("1")) {
            type = Graduate.LECTURER;
        } else if (number.equals("2")) {
            type = Graduate.TA;
        } else if (number.equals("3")) {
            type = Graduate.STUDENT;
        } else {
            System.out.println("try agin, wrong number");
            type();
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nationalCode='" + nationalCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", mark='" + mark + '\'' +
                '}';
    }

    public User signIn(ArrayList<Customer> users, ArrayList<Admin> admins) {
        System.out.print("Enter your  national code: ");
        String nationalCode = ScannerWrapper.getInstance().next();
        System.out.print("Enter your password : ");
        String password = ScannerWrapper.getInstance().next();
        boolean isExisted = false;
        for (Admin admin : admins) {
            if (admin.getPassword().equals(password)) {
                if (admin.getNationalCode().equals(nationalCode)) {
                    return admin;
                }
            }
        }
        for (int i = 0; i < users.size(); i++) {
            String passwordOfUser = users.get(i).getPassword();
            if ((nationalCode.equals(users.get(i).getNationalCode())) && (password.equals(passwordOfUser))) {
                System.out.println("welcome " + users.get(i).getName() + " !");
                isExisted = true;
                return users.get(i);
            }
        }
        if (!isExisted) {
            System.out.println("nationalCode or password is incorrect");
            User userSecond = new User();
            userSecond = signIn(users, admins);
            return userSecond;
        }
        return null;
    }

    public Lecture createClass(ArrayList<Customer> users) {
        printCreateClass(users);
        String privacy = ScannerWrapper.getInstance().next();
        if (privacy.equals("private")) {
            lecture.setPrivacy(true);
            System.out.print("Enter class password : ");
            String password = ScannerWrapper.getInstance().next();
            lecture.setPassword(password);
        } else {
            lecture.setPrivacy(false);
        }
        System.out.print("Enter yes for being open or no for not being open : ");
        String open = ScannerWrapper.getInstance().next();
        if (open.equals("yes")) {
            lecture.setOpen(true);
        } else {
            lecture.setOpen(false);
        }
        return lecture;
    }

    public void printCreateClass(ArrayList<Customer> users) {
        lecture = new Lecture();
        boolean isExist = false;
        System.out.print("Enter Lecturer username : ");
        String lecturer = ScannerWrapper.getInstance().next();
        for (int i = 0; i < users.size(); i++) {
            if (lecturer.equals(users.get(i).getUserName())) {
                lecture.setLecturer(users.get(i));
                isExist = true;
            }
        }
        if (isExist == false) {
            System.out.println("try again, wrong username");
            printCreateClass(users);
        }
        System.out.print("Enter class name : ");
        String name = ScannerWrapper.getInstance().next();
        lecture.setName(name);
        System.out.print("Enter class institute : ");
        String institute = ScannerWrapper.getInstance().next();
        lecture.setInstitute(institute);
        System.out.print("Enter class info : ");
        String info = ScannerWrapper.getInstance().next();
        lecture.setInfo(info);
        System.out.print("Enter class privacy : ");
        String privacy = ScannerWrapper.getInstance().next();
    }

    public void searching(ArrayList<Lecture> lecture, User user) {
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

    public void elsePrint() {
        System.out.println("password is incorrect");
        System.out.println("1 - try again");
        System.out.println("0 - back");
    }

    public Lecture goToClass(ArrayList<Lecture> lecture, User user) {
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
                        if (!passwordCorrect(password, lecture, i, user).equals(null)) {
                            return passwordCorrect(password, lecture, i, user);
                        }
                    } else {
                        passwordWrong(lecture, user);
                    }
                } else {
                    return addingYourSelf(user, i, lecture);
                }

            }
        }
        if (!existClass) {
            System.out.println("the class isnt exist!");
        }
        return null;
    }

    public Lecture addingYourSelf(User user, int number, ArrayList<Lecture> lecture) {
        System.out.println("for adding yourself to class enter 1");
        int enterClass = ScannerWrapper.getInstance().nextInt();
        if (enterClass == 1) {
            lecture.get(number).addUser(user);
            return lecture.get(number);
        }
        return null;
    }

    public void passwordWrong(ArrayList<Lecture> lecture, User user) {
        System.out.println("password is incorrect");
        System.out.println("1 - try again");
        System.out.println("0 - back");
        int option = ScannerWrapper.getInstance().nextInt();
        if (option == 1) {
            searching(lecture, user);
        }

    }

    public Lecture passwordCorrect(String password, ArrayList<Lecture> lecture, int number, User user) {
        for (int j = 0; j < lecture.get(number).getUsers().size(); j++) {
            if (lecture.get(number).getUsers().get(j).getNationalCode().equals(user.getNationalCode())) {
                return lecture.get(number);
            }
        }
        if (lecture.get(number).getLecturer().getNationalCode().equals(user.getNationalCode())) {
            return lecture.get(number);
        }
        System.out.println("for adding youself to class enter 1");
        int enterClass = ScannerWrapper.getInstance().nextInt();
        if (enterClass == 1) {
            lecture.get(number).addUser(user);
            return lecture.get(number);
        }
        return null;

    }


}
