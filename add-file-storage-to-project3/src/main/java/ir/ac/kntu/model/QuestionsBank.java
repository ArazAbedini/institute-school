package ir.ac.kntu.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import ir.ac.kntu.*;
import ir.ac.kntu.util.ScannerWrapper;

public class QuestionsBank implements Serializable {

    private ArrayList<Question> questions;


    public QuestionsBank() {
        questions = new ArrayList<>();
    }

    public Question getQuestion(int number) {
        number -= number;
        if (questions.size() > number) {
            System.out.println("there isnt question");
            return null;
        }
        System.out.println(questions.get(number).getProblem());
        String answer = ScannerWrapper.getInstance().nextLine();
        return questions.get(number);
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public Question getQuestionForClass(int number) {
        number -= number;
        if (questions.size() > number) {
            System.out.println("there isnt question");
            return null;
        }
        System.out.println(questions.get(number).getProblem());
        return questions.get(number);
    }
    //public ArrayList<Question> performance(int type) {
    // return
    // }
}

