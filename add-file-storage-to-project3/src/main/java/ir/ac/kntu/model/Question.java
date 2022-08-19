package ir.ac.kntu.model;


import ir.ac.kntu.util.ScannerWrapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.Date;

public class Question implements Serializable {

    private String name;

    private double mark;

    private String problem;

    private Level level;

    private Type type;

    private String correctAnswer;

    private ArrayList<Answer> allAnswer;

    private ArrayList<Answer> finalAnswer;

    public Question() {
        allAnswer = new ArrayList<>();
        finalAnswer = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Answer> getAllAnswer() {
        return allAnswer;
    }

    public Type getType() {
        return type;
    }


    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public ArrayList<Answer> getFinalAnswer() {
        return finalAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getProblem() {
        return problem;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setAllAnswer(ArrayList<Answer> allAnswer) {
        this.allAnswer = allAnswer;
    }

    public void setFinalAnswer(ArrayList<Answer> finalAnswer) {
        this.finalAnswer = finalAnswer;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public void giveSolution(User user) {
        Answer answer = new Answer();
        System.out.println(problem);
        answer.setName(user.getName());
        answer.setQuestion(this);
        answer.setDate(LocalDateTime.now());
        System.out.println("Enter your solution");
        String solution = ScannerWrapper.getInstance().next();
        answer.setSolution(solution);
        System.out.println("if its your final answer type yes");
        String finalAnswer = ScannerWrapper.getInstance().next();
        allAnswer.add(answer);
        if (finalAnswer.equals("yes")) {
            answer.setFinalAnswer(true);
        } else {
            handleFinalAnswer(user);
        }
        giveMark(answer);

    }

    public void solutionForCompetition(Customer user, Competition competition, int number) {
        Answer answer = new Answer();
        System.out.println(problem);
        answer.setName(user.getUserName());
        answer.setQuestion(this);
        answer.setDate(LocalDateTime.now());
        System.out.println("Enter your solution");
        String solution = ScannerWrapper.getInstance().next();
        answer.setSolution(solution);
        allAnswer.add(answer);
        if (answer.getSolution().equals(correctAnswer)) {
            changeMark(user,competition);
            finalAnswer.add(answer);
        }
        if (competition instanceof SpecialCompetition) {
            for (int i = 0; i < ((SpecialCompetition) competition).getGroups().size(); i++) {
                if (((SpecialCompetition) competition).getGroups().get(i).getUsers().contains(user)) {
                    for (int j = 0; j < ((SpecialCompetition) competition).getGroups().get(i).getUsers().size(); j++) {
                        answer.setMark(answer.getMark());
                    }
                }
            }
        }


    }

    public void giveMark(Answer answer) {
        Exercise exercise = new Exercise();
        if (!type.equals(Type.DESCRIPTIVE)) {
            if (answer.getSolution().equals(correctAnswer)) {
                if (exercise.getTime() < exercise.getDelayTime()) {
                    if (exercise.getTime() > exercise.getEndTime()) {
                        answer.setMark(getMark());
                    } else {
                        answer.setMark(getMark() * exercise.getDelayRatio());
                    }
                }

            } else {
                answer.setMark(0);
            }
        }
    }

    public void handleFinalAnswer(User user) {
        double mark = 0.0;
        for (int i = 0; i < allAnswer.size(); i++) {
            if (allAnswer.get(i).getName().equals(user.getUserName())) {
                if (allAnswer.get(i).getMark() >= mark) {
                    mark = allAnswer.get(i).getMark();
                    removeFinal(user);
                    finalAnswer.add(allAnswer.get(i));

                }
            }
        }

    }

    public void removeFinal(User user) {
        for (int i = 0; i < finalAnswer.size(); i++) {
            if (user.getUserName().equals(finalAnswer.get(i).getName())) {
                finalAnswer.remove(finalAnswer.get(i));
            }
        }

    }

    public void changeMark(Customer user, Competition competition) {
        boolean isExist = false;
        if (competition instanceof SpecialCompetition) {
            for (int i = 0;i < ((SpecialCompetition) competition).getGroups().size();i++) {
                if (((SpecialCompetition) competition).getGroups().get(i).getUsers().contains(user)) {
                    for (int j = 0;j < finalAnswer.size();j++) {
                        if (finalAnswer.get(j).getName().equals(((SpecialCompetition) competition).getGroups().get(i).getName())) {
                            isExist = true;
                        }
                    }
                    if (isExist == false) {
                        competition.getMarks().set(i,competition.getMarks().get(i) + mark);
                    }
                }
            }
        }
        for (int i = 0;i < competition.getAttenders().size();i++) {
            if (competition.getAttenders().get(i).getUserName().equals(user.getUserName())) {
                for (int j = 0;j < finalAnswer.size();j++) {
                    if (finalAnswer.get(j).getName().equals(user.getUserName())) {
                        isExist = true;
                    }
                }
                if (isExist == false) {
                    competition.getMarks().set(i,competition.getMarks().get(i) + mark);
                }
            }
        }
    }
}
