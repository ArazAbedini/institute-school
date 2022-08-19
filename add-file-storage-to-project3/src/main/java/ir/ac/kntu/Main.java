package ir.ac.kntu;

import ir.ac.kntu.logic.Menu;

import javax.imageio.IIOException;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        Menu menu = null;
        String fileName = "file.txt";
        try {
            FileInputStream file = new FileInputStream("file.txt");
            ObjectInputStream in = new ObjectInputStream(file);
            menu = (Menu)in.readObject();
            in.close();
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //
        menu.sign();
        String fileName1 = "file.txt";
        try {
            FileOutputStream file = new FileOutputStream(fileName1);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(menu);
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        

    }
}