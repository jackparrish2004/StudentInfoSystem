public class Professor {
    String name;
    String ID;
    String email;
    String[] coursesTaught = new String[1];
    String department;

    Professor(String name, String ID, String email, String department){
        this.name = name;
        this.ID = ID;
        this.email = email;
        this.department = department;
    }
}
