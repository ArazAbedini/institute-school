package ir.ac.kntu.model;


import ir.ac.kntu.html.MarkTable;
import ir.ac.kntu.util.ScannerWrapper;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Admin extends User implements Serializable {


    public void createCompetition(ArrayList<Competition> competitions) {
        Competition competition = findType();
        String name = name();
        for (int i = 0; i < competitions.size(); i++) {
            if (competitions.get(i).getName().equals(name)) {
                System.out.println("there is such a class name !, try again");
                createCompetition(competitions);
            }
        }
        competition.setName(name);
        competition.setStartTime(time("startTime"));
        competition.setEndTime(time("endTime"));
        ArrayList<Question> questions = new ArrayList<>();
        System.out.println("Enter number of question you want to add : ");
        int number = ScannerWrapper.getInstance().nextInt();
        for (int i = 0; i < number; i++) {
            questions.add(addQuestion());
        }
        competition.setQuestions(questions);
        competitions.add(competition);

    }

    public String name() {
        System.out.print("Enter name of competition : ");
        String name1 = ScannerWrapper.getInstance().nextLine();
        String name = ScannerWrapper.getInstance().nextLine();
        return name;
    }

    public Question addQuestion() {
        Question question = new Question();
        System.out.println("Enter number before type of question :");
        System.out.println("             1 - short answer :");
        System.out.println("             2 - fill in blanks :");
        System.out.println("             3 - descriptive :");
        System.out.println("             4 - multiple choice :");
        int option = ScannerWrapper.getInstance().nextInt();
        question = print();
        if (option == 1) {
            question.setType(Type.SHORT_ANSWER);
        }
        if (option == 2) {
            question.setType(Type.FILL_IN_BLANKS);
        }
        if (option == 3) {
            question.setType(Type.DESCRIPTIVE);
        }
        if (option == 1) {
            question.setType(Type.MULTIPLE_CHOICE);
        }
        return question;

    }

    public Question print() {
        Question question = new Question();
        System.out.print("Enter Name : ");
        String name = ScannerWrapper.getInstance().next();
        question.setName(name);
        System.out.print("Enter mark : ");
        double mark = ScannerWrapper.getInstance().nextDouble();
        question.setMark(mark);
        System.out.print("Enter problem : ");
        String problem = ScannerWrapper.getInstance().nextLine();
        question.setProblem(problem);
        ScannerWrapper.getInstance().nextLine();
        System.out.print("Enter correct answer :");
        String answer = ScannerWrapper.getInstance().next();
        question.setCorrectAnswer(answer);
        return question;
    }


    public Competition findType() {
        System.out.println("enter the number of type of competition");
        System.out.println("          1 -  special competition");
        System.out.println("          2 -  ordinary competition");
        System.out.println("          3 -  private competition");
        int type = ScannerWrapper.getInstance().nextInt();
        if (type == 1) {
            SpecialCompetition competition = new SpecialCompetition();
            return competition;
        } else if (type == 2) {
            OrdinaryCompetition competition = new OrdinaryCompetition();
            return competition;
        } else if (type == 3) {
            PrivateCompetition competition = new PrivateCompetition();
            return competition;
        } else {
            System.out.println("There is something wrong, try again!");
            findType();
        }
        return null;
    }

    public LocalDateTime time(String condition) {
        System.out.print("Enter year of " + condition + " : ");
        int year = ScannerWrapper.getInstance().nextInt();
        System.out.print("Enter month of " + condition + " : ");
        int month = ScannerWrapper.getInstance().nextInt();
        System.out.print("Enter day of " + condition + " : ");
        int day = ScannerWrapper.getInstance().nextInt();
        System.out.print("Enter hour of " + condition + " : ");
        int hour = ScannerWrapper.getInstance().nextInt();
        System.out.print("Enter minutes of " + condition + " : ");
        int minutes = ScannerWrapper.getInstance().nextInt();
        System.out.print("Enter second of " + condition + " : ");
        int second = ScannerWrapper.getInstance().nextInt();
        LocalDateTime localDateTime = LocalDateTime.of(year, month, day, hour, minutes, second);
        return localDateTime;

    }

    public void editCompetition(ArrayList<Competition> competitions) {
        System.out.print("Enter name of competition : ");
        ScannerWrapper.getInstance().nextLine();
        String name = ScannerWrapper.getInstance().nextLine();
        for (Competition competition : competitions) {
            if (competition.getName().equals(name)) {
                edit(competition);
            }
        }
    }

    public void firstPrint() {
        System.out.println("enter the number before order");
        System.out.println("-----------------------------");
        System.out.println("          1 -  edit name");
        System.out.println("          2 -  edit start time");
        System.out.println("          3 -  edit end time");
        System.out.println("          4 -  edit question");
        System.out.println("----------------------------- ");
    }

    public void edit(Competition competition) {
        ArrayList<Question> questions = competition.getQuestions();
        firstPrint();
        int number = ScannerWrapper.getInstance().nextInt();
        if (number == 1) {
            String name = ScannerWrapper.getInstance().next();
            competition.setName(name);
        }
        if (number == 2) {
            competition.setStartTime(time("star time"));
        }
        if (number == 3) {
            competition.setEndTime(time("end time"));
        }
        if (number == 4) {
            secondPrint();
            int numberForQuestion = ScannerWrapper.getInstance().nextInt();
            if (numberForQuestion == 1) {
                System.out.println("Enter number of question you want to add : ");
                int numberOfQuestion = ScannerWrapper.getInstance().nextInt();
                for (int i = 0; i < number; i++) {
                    questions.add(addQuestion());
                }
                edit(competition);
            }
            if (numberForQuestion == 2) {
                System.out.println("Enter name of question");
                String nameOfQuestion = ScannerWrapper.getInstance().nextLine();
                for (Question question : questions) {
                    if (question.getName().equals(nameOfQuestion)) {
                        questions.remove(question);
                    }
                }
            }
        }
        System.out.println("Successfully changed");

    }

    public void secondPrint() {
        System.out.println("enter the number before order");
        System.out.println("-----------------------------");
        System.out.println("          1 -  add question");
        System.out.println("          2 -  delete question");
        System.out.println("          0 -  back");
        System.out.println("----------------------------- ");
    }

    public ArrayList<Customer> invite(ArrayList<Customer> users, Competition competition) {
        System.out.println("Enter nationalCode or email");
        String input = ScannerWrapper.getInstance().next();
        try {
            for (int i = 0; i < users.size(); i++) {
                if (input.equals(users.get(i).getNationalCode())) {
                    ArrayList<Competition> competitions = users.get(i).getCompetitions();
                    if (!competitions.contains(competition)) {
                        competitions.add(competition);
                        System.out.println("successfully invited");
                    }
                    users.get(i).setCompetitions(competitions);
                }
                if (input.equals(users.get(i).getEmail())) {
                    ArrayList<Competition> competitions = users.get(i).getCompetitions();
                    if (!competitions.contains(competition)) {
                        competitions.add(competition);
                        System.out.println("successfully invited");
                    }
                    users.get(i).setCompetitions(competitions);
                }

            }
        } catch (Exception e) {
            System.out.println("there isnt such user, try again");
            users = invite(users, competition);
        }
        return users;
    }

    public void deleteCompetition(ArrayList<Competition> competitions) {
        System.out.print("Enter name of competition : ");
        String name = ScannerWrapper.getInstance().next();
        for (Competition competition : competitions) {
            if (competition.getName().equals(name)) {
                competitions.remove(competition);
                System.out.println("Successfully deleted");
            }
        }

    }

    public Customer searchUser(ArrayList<Customer> users) {
        System.out.println("Enter nationalCode or email");
        Customer user = new Customer();
        String input = ScannerWrapper.getInstance().next();
        try {
            for (int i = 0; i < users.size(); i++) {
                if (input.equals(users.get(i).getNationalCode())) {
                    user = users.get(i);
                }
                if (input.equals(users.get(i).getEmail())) {
                    user = users.get(i);
                }
            }
        } catch (Exception e) {
            System.out.println("there isnt such user, try again");

        }
        return user;
    }

    public Admin makeAdmin(Customer user) {
        Admin admin = new Admin();
        admin.setName(user.getName());
        admin.setEmail(user.getEmail());
        admin.setPassword(user.getPassword());
        admin.setNationalCode(user.getNationalCode());
        admin.setPhoneNumber(user.getPhoneNumber());
        admin.setUserName(user.getUserName());
        return admin;
    }

    public void giveMark(Competition competition) {
        for (int i = 0; i < competition.getQuestions().size(); i++) {
            System.out.println(competition.getQuestions().get(i).getName());
        }
        String question = ScannerWrapper.getInstance().next();
        for (int j = 0; j < competition.getQuestions().size(); j++) {
            if ((question.equals(competition.getQuestions().get(j).getName())) &&
                    (competition.getQuestions().get(j).getType().equals(Type.DESCRIPTIVE))) {
                for (int k = 0; k < competition.getQuestions().get(j).getFinalAnswer().size(); k++) {
                    System.out.println(competition.getQuestions().get(j).getFinalAnswer().get(k).getSolution());
                    String mark = ScannerWrapper.getInstance().next();
                    competition.getQuestions().get(j).setMark(Double.valueOf(mark));
                }
            }
        }
    }


    public void competitionResult(ArrayList<Competition> competitions) {
        System.out.println("enter name of competition you want");
        Competition competition = new Competition();
        String name = ScannerWrapper.getInstance().next();
        for (int i = 0; i < competitions.size(); i++) {
            if (competitions.get(i).getName().equals(name)) {
                competition = competitions.get(i);
            }
        }
        competition.sort();
        if (competition instanceof OrdinaryCompetition) {
            competition = isOrdinary(competition);
        }
        if (competition instanceof PrivateCompetition) {
            competition = isPrivate(competition);
        }
        if (competition instanceof SpecialCompetition) {
            isSpecial(competition);
        }
        MarkTable markTable = new MarkTable(competition);
        markTable.markInTable(competition);
    }

    public void isSpecial(Competition competition) {
        for (int i = 0; i < ((SpecialCompetition) competition).getGroups().size(); i++) {
            System.out.println(((SpecialCompetition) competition).getGroups().get(i).getName());
            System.out.println(competition.getMarks().get(i));
        }
        for (int j = 0; j < 10; j++) {
            if (j < competition.getAttenders().size()) {
                for (int k = 0; k < ((SpecialCompetition) competition).getGroups().size(); k++) {
                    ((SpecialCompetition) competition).getGroups().get(k).setMark(20.0);
                    ((SpecialCompetition) competition).getGroups().get(k).giveMark(((SpecialCompetition) competition).getGroups().get(k));
                }
            }
        }
    }

    public Competition isOrdinary(Competition competition) {
        for (int i = 0; i < competition.getAttenders().size(); i++) {
            System.out.println(competition.getAttenders().get(i));
            System.out.println(competition.getMarks().get(i));
        }
        for (int j = 0; j < 5; j++) {
            if (j < competition.getAttenders().size()) {
                competition.getAttenders().get(j).setMark((double) (competition.getAttenders().get(j).getMark() + 20));
            }
        }
        return competition;
    }

    public Competition isPrivate(Competition competition) {
        for (int i = 0; i < competition.getAttenders().size(); i++) {
            System.out.println(competition.getAttenders().get(i));
            System.out.println(competition.getMarks().get(i));
        }
        for (int j = 0; j < 3; j++) {
            if (j < competition.getAttenders().size()) {
                competition.getAttenders().get(j).setMark((double) (competition.getAttenders().get(j).getMark() + 10));
            }
        }
        return competition;
    }


}
