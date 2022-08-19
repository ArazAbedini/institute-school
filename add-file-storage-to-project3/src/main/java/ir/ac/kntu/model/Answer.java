package ir.ac.kntu.model;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class Answer implements Serializable {

    private String name;

    private Question question;

    private LocalDateTime date;

    private double delayRatio;

    private double mark;

    private boolean finalAnswer;

    private String solution;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDelayRatio(double delayRatio) {
        this.delayRatio = delayRatio;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setFinalAnswer(boolean finalAnswer) {
        this.finalAnswer = finalAnswer;
    }

    public double getMark() {
        return mark;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getSolution() {
        return solution;
    }
}
