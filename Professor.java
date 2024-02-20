/*
 * CS 280
 * Jack Parrish
 * Mario Medeles
 * Project 1
 * 2/27
 */
import java.util.ArrayList;
public class Professor {
    String name;
    String ID;
    String email;
    ArrayList<String> coursesTaught = new ArrayList<String>();
    String department;

    Professor(String name, String ID, String email, String department){
        this.name = name;
        this.ID = ID;
        this.email = email;
        this.department = department;
    }
}
