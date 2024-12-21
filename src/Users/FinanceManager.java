package Users;
import java.util.ArrayList;
import java.util.List;
import utilites.Message;
import utilites.SaveData;

public class FinanceManager extends Employee {
    protected String dataSalary;

    public FinanceManager(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public FinanceManager(Integer id, String firstName, String lastName, String email, String password, double salary) {
        super(id, firstName, lastName, email, password, salary);
    }

    
    public void sendSalary(Employee e) throws Exception {
        if (e == null) {
            throw new IllegalArgumentException("Employee cannot be null.");
        }
        if (this.getSalary() <= 0) {
            throw new IllegalStateException("Finance Manager's salary is not set or invalid.");
        }
        String salaryMessage = "Your salary: " + this.getSalary();
        Message m = new Message(e, salaryMessage);
        m.setSender(this);
        SaveData.INSTANCE.addMessage(m);
        System.out.println("Salary message sent to Employee ID: " + e.getEmail());
    }


   
    public List<String> getSalaryInfo() {
        List<String> salaryInfo = new ArrayList<>();
        for (Employee e : SaveData.INSTANCE.getAllEmployees()) {
            salaryInfo.add("Employee ID: " + e.getId() + ", Name: " + e.getFirstName() + " " + e.getLastName() + ", Salary: " + e.getSalary());
        }
        return salaryInfo;
    }
}
