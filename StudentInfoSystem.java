import java.util.ArrayList;
import java.util.Scanner;

public class StudentInfoSystem{
    static int getUserInput(Scanner keyboard){
        System.out.print("Enter a number to execute the command: ");
        int userInput = keyboard.nextInt();
        return userInput;
    }
    static void printOptions(){
        /*This functions prints option categories and prompts the user to enter a number */
        System.out.println("1. View Student Options\n2. View Course Options\n3. View Instructor Options");
    }

    static void printStudentOptions(){
        /*This function prints all options associated with the student class */
        System.out.println("1. Add Student");
        System.out.println("2. Remove Student");
        System.out.println("3. Update Student Info");
        System.out.println("4. Search for Student");
        System.out.println("5. Update Course Grade");
        System.out.println("6. View Course History");
        System.out.println("7. View Cumulative GPA");
        System.out.println("8. Return to previous menu");
    }

    static void printCourseOptions(){
        /*This function prints all options associated with the course class */
        System.out.println("1. Add Course");
        System.out.println("2. Remove Course");
        System.out.println("3. Update Course Info");
        System.out.println("4. Search for Course");
        System.out.println("5. Return to previous menu");
    }

    static void printProfessorOptions(){
        /*This function prints all options associated with the professor class */
        System.out.println("1. Add Professor");
        System.out.println("2. Remove Professor");
        System.out.println("3. Update Professor Info");
        System.out.println("4. Return to previous menu");
    }
    public static void main(String[] args){

        //I just have a no-args admin for now
        //We can add some sort of login process later
        //But this will let us test stuff for now
        Admin admin = new Admin();

        Scanner keyboard = new Scanner(System.in);
        
        printOptions();
        System.out.print("Enter a number to execute the command, enter -1 to quit: ");
        int userInput = keyboard.nextInt();

        while (userInput != -1){
            if (userInput == 1){
                printStudentOptions();
                getUserInput(keyboard);

            } else if (userInput == 2){
                printCourseOptions();
                getUserInput(keyboard);

            } else if (userInput == 3){
                printProfessorOptions();
                getUserInput(keyboard);
                
            }

            printOptions();
            getUserInput(keyboard);
        }

        keyboard.close();
        System.out.println("Exiting the program");

    }
}