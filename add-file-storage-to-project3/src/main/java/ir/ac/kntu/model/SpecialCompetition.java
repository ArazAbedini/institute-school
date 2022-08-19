package ir.ac.kntu.model;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;

import ir.ac.kntu.*;

public class SpecialCompetition extends Competition {
    private ArrayList<Group> groups = new ArrayList<>();


    public SpecialCompetition() {
        groups = new ArrayList<>();

    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public ArrayList<Customer> getAttenders() {
        return super.getAttenders();
    }

    @Override
    public ArrayList<Question> getQuestions() {
        return super.getQuestions();
    }

    @Override
    public LocalDateTime getStartDate() {
        return super.getStartDate();
    }

    @Override
    public void setAttenders(ArrayList<Customer> attenders) {
        super.setAttenders(attenders);
    }

    @Override
    public void setEndTime(LocalDateTime endTime) {
        super.setEndTime(endTime);
    }

    @Override
    public void setStartTime(LocalDateTime startTime) {
        super.setStartTime(startTime);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public void setQuestions(ArrayList<Question> questions) {
        super.setQuestions(questions);
    }


    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }
}
