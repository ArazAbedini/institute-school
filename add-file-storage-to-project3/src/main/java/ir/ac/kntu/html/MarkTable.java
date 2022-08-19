package ir.ac.kntu.html;

import ir.ac.kntu.model.Competition;
import ir.ac.kntu.model.SpecialCompetition;

import java.io.*;

public class MarkTable {

    public MarkTable(Competition competition) {
        markInTable(competition);
    }

    public void markInTable(Competition competition) {
        File file = new File("markTable.html");
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write("<html>" + "\n" + "<style>" + "\n" + "table, th, td {" + "\n" +
                    "  border:1px solid black;" + "\n" + "}" + "\n" + "</style>" + "\n" + "\n" + "<body>" + "\n" +
                    "<h2> markTable</h2>" + "\n" + "\n" + "<table style=\"width:100%\">" + "\n" + "<tr>" + "\n" + "<th>name</th>"
                    + "\n" + "<th>mark</th>" + "\n" + "</tr>" + "\n");
            if (competition instanceof SpecialCompetition) {
                for (int i = 0; i < ((SpecialCompetition) competition).getGroups().size(); i++) {
                    bufferedWriter.write("<tr>" + "\n" + "<th>" + String.valueOf(((SpecialCompetition) competition).getGroups().get(i).getName())
                            + "</th>"
                            + "\n" + "<th>" + String.valueOf(competition.getMarks().get(i)) + "</th>" + "\n" + "</tr>" + "\n");
                }
            } else {
                for (int i = 0; i < competition.getAttenders().size(); i++) {
                    bufferedWriter.write("<tr>" + "\n" + "<th>" + String.valueOf(competition.getAttenders().get(i).getName()) + "</th>"
                            + "\n" + "<th>" + String.valueOf(competition.getMarks().get(i)) + "</th>" + "\n" + "</tr>" + "\n");
                }
            }
            bufferedWriter.write("</table>" + "\n" + "</body>" + "\n" + "</html>");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
