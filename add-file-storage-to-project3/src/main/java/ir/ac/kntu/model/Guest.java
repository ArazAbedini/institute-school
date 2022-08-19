package ir.ac.kntu.model;

import ir.ac.kntu.util.ScannerWrapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Guest implements Serializable {


    public void comparing(ArrayList<Competition> competitions) {
        LocalDateTime time = LocalDateTime.now();
        for (int i = 0; i < competitions.size(); i++) {
            int returnComp = time.compareTo(competitions.get(i).getEndTime());
            if (returnComp > 0) {
                System.out.println(competitions.get(i).getName());
            }
        }
        System.out.println("choose one of them");
        String name = ScannerWrapper.getInstance().next();
        for (int i = 0; i < competitions.size(); i++) {
            int returnComp = time.compareTo(competitions.get(i).getEndTime());
            if (returnComp > 0) {
                goCompetition(competitions.get(i));
            }
        }
    }

    public void goCompetition(Competition competition) {
        for (int i = 0; i < competition.getQuestions().size(); i++) {
            System.out.println("            " + competition.getQuestions().get(i).getName());
        }
        System.out.println("Question you want : ");
        String name = ScannerWrapper.getInstance().next();
        for (int i = 0; i < competition.getQuestions().size(); i++) {
            if (name.equals(competition.getQuestions().get(i).getName().equals(name))) {
                System.out.println(competition.getQuestions().get(i).getProblem());
                System.out.println(competition.getQuestions().get(i).getCorrectAnswer());
            }
        }
    }
}
