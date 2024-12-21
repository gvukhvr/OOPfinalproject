package Users;

import java.util.Date;
import java.util.Objects;
import java.io.*;
import utilites.Message;
import utilites.SaveData;
import utilites.Request;

public class Employee extends User {
    protected double salary;
    protected Date hireDate;

    // Конструкторы
    public Employee(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public Employee() {}

    public Employee(String firstName, String lastName, double salary) {
        super(firstName, lastName);
        this.salary = salary;
        this.hireDate = new Date();
    }

    public Employee(Integer id, String firstName, String lastName, String email, String password, double salary) {
        super(id, firstName, lastName, email, password);
        this.salary = salary;
        this.hireDate = new Date();
    }

    // Геттеры и Сеттеры
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    
//    public void sendMessage(String text, Employee e) throws Exception {
//        
//    }

    
//    public Message getMessage() {
//        
//    }

    
//    public void sendRequest(Request r) throws IOException {
//        
//    }

    @Override
    public String toString() {
        return super.toString() + " Employee{" +
                "salary=" + salary +
                ", hireDate=" + hireDate +
                '}';
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(salary);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        Employee other = (Employee) obj;
        return Double.doubleToLongBits(salary) == Double.doubleToLongBits(other.salary);
    }
}