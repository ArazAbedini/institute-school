package ir.ac.kntu.model;


import ir.ac.kntu.util.ScannerWrapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Exercise implements Serializable {

    private String name;

    private String info;

    private double startTime;

    private double endTime;

    private double time;

    private boolean test;

    private boolean enableTable;

    private double delayRatio;

    private double delayTime;

    private ArrayList<Question> questions;


    public Exercise() {
        questions = new ArrayList<>();
    }

    public String getName() {
        if ((!test) && (time >= startTime)) {
            return name;
        } else {
            if (test) {
                System.out.println("isnt confirm yet!");
            }
            if (startTime > time) {
                System.out.println("isnt started yet");
            }
        }
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setDelayRatio(double delayRatio) {
        this.delayRatio = delayRatio;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public void setDelayTime(double delayTime) {
        this.delayTime = delayTime;
    }

    public void setEnableTable(boolean enableTable) {
        this.enableTable = enableTable;
    }

    public void setEndTime(double endTime) {
        this.endTime = endTime;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    public String getInfo() {
        if ((!test) && (time >= startTime)) {
            return info;
        } else {
            if (test) {
                System.out.println("isnt confirm yet!");
            }
            if (startTime > time) {
                System.out.println("isnt started yet");
            }
        }
        return null;
    }

    public ArrayList<Question> getQuestions() {
        if ((!test) && (time >= startTime)) {
            ArrayList<Question> copyQuestions = new ArrayList<Question>();
            for (int i = 0; i < questions.size(); i++) {
                copyQuestions.add(questions.get(i));
            }
            return copyQuestions;
        } else {
            if (test) {
                System.out.println("isnt confirm yet!");
            }
            if (startTime > time) {
                System.out.println("isnt started yet");
            }
        }
        return null;
    }

    public void addTo(Question question) {
        questions.add(question);
    }

    public double getTime() {
        return time;
    }

    public double getEndTime() {
        return endTime;
    }

    public double getDelayTime() {
        return delayTime;
    }

    public double getDelayRatio() {
        return delayRatio;
    }

    public void addQuestion() {
        Question question = new Question();
        question = print();
        String correct = ScannerWrapper.getInstance().next();
        System.out.println("Enter number before type of question :");
        System.out.println("             1 - short answer :");
        System.out.println("             2 - fill in blanks :");
        System.out.println("             3 - descriptive :");
        System.out.println("             4 - multiple choice :");
        int option = ScannerWrapper.getInstance().nextInt();
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
        question.setCorrectAnswer(correct);
        questions.add(question);

    }

    public Question print() {
        Question question = new Question();
        System.out.println("Enter Name");
        String name = ScannerWrapper.getInstance().next();
        question.setName(name);
        System.out.println("Enter mark");
        double mark = ScannerWrapper.getInstance().nextDouble();
        question.setMark(mark);
        System.out.println("Enter problem");
        String problem = ScannerWrapper.getInstance().next();
        question.setProblem(problem);
        System.out.println("Enter level");
        String level = ScannerWrapper.getInstance().next();
        if (level.equals("easy")) {
            question.setLevel(Level.EASY);
        }
        if (level.equals("medium")) {
            question.setLevel(Level.MEDIUM);
        }
        if (level.equals("hard")) {
            question.setLevel(Level.HARD);
        }
        if (level.equals("very hard")) {
            question.setLevel(Level.VERY_HARD);
        }
        System.out.println("Enter correct answer");
        return question;
    }

    public ArrayList<Answer> table() {
        ArrayList<Answer> answers = new ArrayList<>();
        answers = questions.get(0).getFinalAnswer();
        for (int i = 1; i < questions.size(); i++) {
            for (int j = 0; j < questions.get(i).getFinalAnswer().size(); j++) {
                int index = finding(questions.get(i).getFinalAnswer().get(j).getName(), answers);
                if (index >= 0) {
                    double before = answers.get(index).getMark();
                    answers.get(index).setMark(questions.get(i).getFinalAnswer().get(j).getMark() + before);
                }
            }

        }
        return answers;
    }

    public ArrayList<Answer> sort(ArrayList<Answer> answer) {
        Answer copyAnswer = new Answer();
        for (int i = 0; i < answer.size(); i++) {
            for (int j = i; j < answer.size(); j++) {
                if (answer.get(j).getMark() < answer.get(i).getMark()) {
                    copyAnswer = answer.get(i);
                    answer.set(i, answer.get(j));
                    answer.set(j, copyAnswer);
                }
                if (answer.get(j).getMark() == answer.get(i).getMark()) {
                    if (answer.get(j).getDate().compareTo(answer.get(i).getDate()) > 1) {
                        copyAnswer = answer.get(i);
                        answer.set(i, answer.get(j));
                        answer.set(j, copyAnswer);
                    }
                }
            }
        }
        return answer;
    }

    public int finding(String name, ArrayList<Answer> answer) {
        for (int i = 0; i < answer.size(); i++) {
            if (answer.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public void showTable() {
        ArrayList<Answer> answer = new ArrayList<>();
        answer = table();
        answer = sort(answer);
        int rate = 1;
        if (enableTable) {
            System.out.print(" ");
            for (int i = 0; i < questions.size(); i++) {
                System.out.print(" " + questions.get(i).getName());
            }
            System.out.println();
            for (int j = answer.size() - 1; j > 0; j--) {
                System.out.print(rate + " " + answer.get(j).getName() + " " + answer.get(j).getMark());

            }

        }
    }


}
