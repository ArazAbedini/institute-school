package ir.ac.kntu.model;


import java.io.Serializable;
import java.time.*;
import java.util.ArrayList;

public class Competition implements Serializable {

    private String name;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private ArrayList<Question> questions;

    private ArrayList<Customer> attenders;

    private ArrayList<Double> marks = new ArrayList<>();


    public Competition() {
        questions = new ArrayList<>();
        attenders = new ArrayList<>();
    }

    public ArrayList<Double> getMarks() {
        return marks;
    }

    public void setMarks(ArrayList<Double> marks) {
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public ArrayList<Customer> getAttenders() {
        return attenders;
    }

    public LocalDateTime getStartDate() {
        return startTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setAttenders(ArrayList<Customer> attenders) {
        this.attenders = attenders;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void sort() {
        double temp = 0;
        Customer tempUser = new Customer();
        for (int i = 0; i < marks.size(); i++) {
            for (int j = i; j < marks.size(); j++) {
                if (marks.get(i) < marks.get(j)) {
                    temp = marks.get(i);
                    marks.set(i, marks.get(j));
                    marks.set(j, temp);
                    tempUser = attenders.get(i);
                    attenders.set(i, attenders.get(j));
                    attenders.set(j, tempUser);
                }
            }
        }
    }
}
