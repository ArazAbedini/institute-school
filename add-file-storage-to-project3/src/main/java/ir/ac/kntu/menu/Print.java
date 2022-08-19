package ir.ac.kntu.menu;

import java.io.Serializable;

public class Print implements Serializable {

    public void sign() {
        System.out.println("for sign-in enter 1 of enter 2 for sign-up and 0 for back");
        System.out.println("---------------------------------------------------------");
        System.out.println("          1 -  sign-in");
        System.out.println("          2 -  sign-up");
        System.out.println("          3 -  enter as a guest");
        System.out.println("          0 -  close");
        System.out.println("---------------------------------------------------------");
    }

    public void user() {
        System.out.println("enter the number before order");
        System.out.println("          1 -  class");
        System.out.println("          2 -  competition");
        System.out.println("          0 -  back");

    }

    public void userCompetition() {
        System.out.println("enter the number before order");
        System.out.println("-----------------------------");
        System.out.println("          1 -  join competition");
        System.out.println("          2 -  go to competition");
        System.out.println("          0 -  back");
        System.out.println("----------------------------- ");
    }

    public void guest() {
        System.out.println("enter the number before order");
        System.out.println("-----------------------------");
        System.out.println("          1 -  question bank");
        System.out.println("          2 -  competition");
        System.out.println("          0 -  back");
        System.out.println("----------------------------- ");
    }

    public void admin() {
        System.out.println("enter the number before order");
        System.out.println("-----------------------------");
        System.out.println("          1 -  competition");
        System.out.println("          2 -  class");
        System.out.println("          3 -  users");
        System.out.println("          4 -  question bank");
        System.out.println("          5 -  make admin");
        System.out.println("          0 -  back");
        System.out.println("----------------------------- ");
    }

    public void competition() {
        System.out.println("enter the number before order");
        System.out.println("-----------------------------");
        System.out.println("          1 -  create competition");
        System.out.println("          2 -  edit competition");
        System.out.println("          3 -  delete competition");
        System.out.println("          4 -  inviting competition");
        System.out.println("          5 -  see result");
        System.out.println("          0 -  back");
        System.out.println("----------------------------- ");
    }

    public void usersClassPrint() {
        System.out.println("enter the number before order");
        System.out.println("-----------------------------");
        System.out.println("          1 -  create class");
        System.out.println("          2 -  join class");
        System.out.println("          3 -  go to class");
        System.out.println("          4 -  question bank");
        System.out.println("          5 -  competition");
        System.out.println("          0 -  back");
        System.out.println("----------------------------- ");
    }

    public void question() {
        System.out.println("     1 - get question");
        System.out.println("     2 - get question and add to class");
        System.out.println("     3 - add question in exercise");
    }

    public void lecturer() {
        System.out.println("Enter number or order you want");
        System.out.println("      1 - add Student");
        System.out.println("      2 - add exercise");
        System.out.println("      3 - give mark");
        System.out.println("      0 - back");
    }

    public void introduce() {
        System.out.println("introduce yourself : ");
        System.out.println("            1 - Lecturer");
        System.out.println("            2 - TA");
        System.out.println("            3 - Student");
    }
}
