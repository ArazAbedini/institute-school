package ir.ac.kntu.util;

import ir.ac.kntu.model.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Data implements Serializable {

    private static Data instance = null;

    private Data(ArrayList<Admin> admins, ArrayList<Customer> existedUsers, ArrayList<Competition> competitions) {
        Admin admin = new Admin();
        admin = setAdmin(admin);
        admins.add(admin);
        Customer user1 = new Customer();
        user1 = setUser1(user1);
        existedUsers.add(user1);
        Customer user2 = new Customer();
        user2 = setUser2(user2);
        existedUsers.add(user2);
        Customer user3 = new Customer();
        user3 = setUser3(user3);
        existedUsers.add(user3);
        ArrayList<Question> questions = new ArrayList<>();
        Question question = new Question();
        question = setQuestion1(question);
        questions.add(question);
        Question question2 = new Question();
        question2 = setQuestion2(question2);
        questions.add(question2);
        Competition competition = new OrdinaryCompetition();
        competition = setCompetition(competition, questions);
        competition.setAttenders(existedUsers);
        ArrayList<Double> marks = new ArrayList<>();
        marks.add(0.0);
        marks.add(0.0);
        marks.add(0.0);
        competition.setMarks(marks);
        System.out.println(competition.getName());
        competitions.add(competition);
    }

    public static Data getInstance(ArrayList<Admin> admins, ArrayList<Customer> existedUsers, ArrayList<Competition> competitions) {
        if (instance == null) {
            instance = new Data(admins, existedUsers, competitions);
        }
        return instance;
    }

    private static Admin setAdmin(Admin admin) {
        admin.setName("ARAZ");
        admin.setEmail("araz.abedini@gmail.com");
        admin.setNationalCode("0150151128");
        admin.setPhoneNumber("09920711192");
        admin.setUserName("araz");
        admin.setPassword("12345");
        return admin;
    }

    private static Customer setUser1(Customer user1) {
        user1.setName("ali");
        user1.setEmail("ali@gmail.com");
        user1.setPhoneNumber("09109915790");
        user1.setNationalCode("1111111111");
        user1.setUserName("ali");
        user1.setPassword("1");
        return user1;
    }

    private static Customer setUser2(Customer user2) {
        user2.setName("reza");
        user2.setEmail("reza@gmail.com");
        user2.setPhoneNumber("09129915790");
        user2.setNationalCode("2222222222");
        user2.setUserName("reza");
        user2.setPassword("1");
        return user2;
    }

    private static Customer setUser3(Customer user3) {
        user3.setName("ahmad");
        user3.setEmail("ahmad@gmail.com");
        user3.setPhoneNumber("09139915790");
        user3.setNationalCode("3333333333");
        user3.setUserName("ahmad");
        user3.setPassword("1");
        return user3;
    }

    private static Question setQuestion1(Question question) {
        question.setCorrectAnswer("1");
        question.setType(Type.FILL_IN_BLANKS);
        question.setMark(7.0);
        question.setProblem("soal1");
        question.setName("q1");
        question.setLevel(Level.EASY);
        return question;
    }

    private static Question setQuestion2(Question question2) {
        question2.setCorrectAnswer("2");
        question2.setType(Type.MULTIPLE_CHOICE);
        question2.setMark(7.0);
        question2.setProblem("soal2");
        question2.setName("q2");
        question2.setLevel(Level.EASY);
        return question2;
    }

    private static Competition setCompetition(Competition competition, ArrayList<Question> questions) {
        competition.setStartTime(LocalDateTime.of(2022, 6, 16, 15, 30, 0));
        competition.setEndTime(LocalDateTime.of(2022, 10, 16, 17, 0, 0));
        competition.setQuestions(questions);
        competition.setName("java");
        return competition;
    }
}
