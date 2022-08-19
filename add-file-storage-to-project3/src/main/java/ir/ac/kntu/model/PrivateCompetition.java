package ir.ac.kntu.model;


import java.time.LocalDateTime;
import java.util.ArrayList;

public class PrivateCompetition extends Competition {

    @Override
    public void setAttenders(ArrayList<Customer> attenders) {
        super.setAttenders(attenders);
    }

    @Override
    public LocalDateTime getStartTime() {
        return super.getStartTime();
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
    public String getName() {
        return super.getName();
    }

    @Override
    public LocalDateTime getStartDate() {
        return super.getStartDate();
    }

    @Override
    public void setQuestions(ArrayList<Question> questions) {
        super.setQuestions(questions);
    }

    @Override
    public void setEndTime(LocalDateTime endTime) {
        super.setEndTime(endTime);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public void setStartTime(LocalDateTime startTime) {
        super.setStartTime(startTime);
    }
}
