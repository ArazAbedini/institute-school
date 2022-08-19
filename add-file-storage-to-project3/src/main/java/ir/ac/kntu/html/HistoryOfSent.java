package ir.ac.kntu.html;

import ir.ac.kntu.model.Competition;
import ir.ac.kntu.model.Question;
import ir.ac.kntu.util.ScannerWrapper;

import java.io.*;

public class HistoryOfSent {

    public HistoryOfSent(Competition competition) {
        for (int i = 0;i < competition.getQuestions().size();i++) {
            System.out.println(competition.getQuestions().get(i).getName());
        }
        System.out.print("Enter question you want : ");
        String name = ScannerWrapper.getInstance().next();
        for (int j = 0;j < competition.getQuestions().size();j++) {
            if (name.equals(competition.getQuestions().get(j).getName())) {
                history(competition.getQuestions().get(j));
            }
        }
    }

    private void history(Question question) {
        File file = new File("history.html");
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write("<html>" + "\n" + "<style>" + "\n" + "table, th, td {" + "\n" +
                    "  border:1px solid black;" + "\n" + "}" + "\n" + "</style>" + "\n" + "\n" + "<body>" + "\n"+
                    "<h2>history</h2>" + "\n" +"\n" + "<table style=\"width:100%\">" + "\n" + "<tr>" + "\n" + "<th>name</th>"
                    + "\n" + "<th>time</th>" + "\n"+ "<th>answer</th>" + "\n" + "</tr>" + "\n");
            for (int i = 0;i < question.getAllAnswer().size();i++) {
                bufferedWriter.write( "<tr>" + "\n" + "<th>" + String.valueOf(question.getAllAnswer().get(i).getName())
                        + "</th>" + "\n" + "<th>" + String.valueOf(question.getAllAnswer().get(i).getDate()) +"</th>" + "\n" + "<th>" +
                        String.valueOf(question.getAllAnswer().get(i).getSolution()) +"</th>" + "\n" + "</tr>" + "\n" );
            }
            bufferedWriter.write("</table>" + "\n" + "</body>" + "\n" + "</html>");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
