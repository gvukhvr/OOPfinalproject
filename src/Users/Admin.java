package Users;

import java.util.Objects;
import utilites.SaveData;

public class Admin extends Employee {

  
    public Admin() {}

    public Admin(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public Admin(Integer id, String firstName, String lastName, String email, String password, double salary) {
        super(id, firstName, lastName, email, password, salary);
    }

    
    public void addUser(User user) {
    }

    public void changeUser(User user) {
    }

    public void removeUser(String email) throws Exception {
        SaveData.INSTANCE.removeUser2(email);
    }


    public String viewAllUsers() {
        return SaveData.INSTANCE.getAllUsers2();
    }

}