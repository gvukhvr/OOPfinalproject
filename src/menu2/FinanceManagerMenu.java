package menu2;

import Users.Employee;
import Users.FinanceManager;
import Users.User;
import utilites.SaveData;

import java.util.Scanner;
import java.io.IOException;

public class FinanceManagerMenu {
    private Scanner scanner;
    private FinanceManager financeManager;

    public FinanceManagerMenu(Scanner scanner, FinanceManager financeManager) {
        this.scanner = scanner;
        this.financeManager = financeManager;
    }

    public void showMenu() {
        System.out.println("Меню финансового менеджера:");
        System.out.println("1. Отправить зарплату");
        System.out.println("2. Просмотреть информацию о зарплатах");
        System.out.println("3. Выйти");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                sendSalary();
                break;
            case 2:
                viewSalaryInfo();
                break;
            case 3:
                return;
            default:
                System.out.println("Неверный выбор");
        }
    }

    private void sendSalary() {
        System.out.println("Введите email сотрудника для отправки зарплаты:");
        scanner.nextLine(); 
        String employeeEmail = scanner.nextLine();  

        Employee employee = null;
        User user = SaveData.INSTANCE.getUserById(employeeEmail); 

        if (user instanceof Employee) {
            employee = (Employee) user;  
            try {
                financeManager.sendSalary(employee);  
                SaveData.write();  
                System.out.println("Зарплата успешно отправлена сотруднику с email: " + employeeEmail);
            } catch (Exception e) {
                System.out.println("Ошибка при отправке зарплаты: " + e.getMessage());
            }
        } else {
            System.out.println("Сотрудник с таким email не найден.");
        }
    }

    private void viewSalaryInfo() {
        System.out.println("Информация о зарплатах:");
        for (Employee e : SaveData.INSTANCE.getAllEmployees()) {
            System.out.println("ID: " + e.getId() + ", Имя: " + e.getFirstName() + " " + e.getLastName() + ", Зарплата: " + e.getSalary());
        }
    }
}
