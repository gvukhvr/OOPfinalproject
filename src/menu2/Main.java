package menu2;

import Students.Student;
import teacher.Teacher;
import Users.Admin;
import Users.FinanceManager;
import Users.Manager;
import Users.User;
import utilites.SaveData;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SaveData saveData = SaveData.INSTANCE;

        try {
           
            User student = new Student("Student", "password");
            User teacher = new Teacher("Teacher", "password");
            User manager = new Manager("Manager", "password");
            User admin = new Admin("Admin", "adminPassword");
            User financeManager = new FinanceManager("FinanceManager", "financePassword");

            saveData.addUser(student);
            saveData.addUser(teacher);
            saveData.addUser(manager);
            saveData.addUser(admin);
            saveData.addUser(financeManager);

            System.out.println("Пользователи добавлены в базу данных.");

            System.out.println("Проверка пользователей:");
            for (User u : saveData.getAllUsers()) {
                System.out.println("ID: " + u.getEmail() + ", Password: " + u.getPassword());
            }

        } catch (Exception e) {
            System.out.println("Ошибка при добавлении пользователей: " + e.getMessage());
        }

        AuthService authService = new AuthService();

        boolean running = true;
        while (running) {
            System.out.println("\nДобро пожаловать в систему!");
            System.out.println("Введите 'exit' для выхода или авторизуйтесь:");

            User user = authService.authenticateUser(scanner);

            if (user != null) {
                boolean userSession = true;

                while (userSession) {
                    if (user instanceof Student) {
                        new StudentMenu(scanner, (Student) user).showMenu();
                    } else if (user instanceof Teacher) {
                        new TeacherMenu(scanner, (Teacher) user).showMenu();
                    } else if (user instanceof Manager) {
                        new ManagerMenu(scanner, (Manager) user).showMenu();
                    } else if (user instanceof Admin) {
                        new AdminMenu(scanner, (Admin) user).showMenu();                        
                    } else if (user instanceof FinanceManager) {
                        new FinanceManagerMenu(scanner, (FinanceManager) user).showMenu();
                    }

                    boolean validChoice = false;
                    while (!validChoice) {
                        System.out.println("\nЧто вы хотите сделать?");
                        System.out.println("1. Вернуться в меню.");
                        System.out.println("2. Выйти из аккаунта.");
                        System.out.print("Выберите вариант: ");

                        int choice = scanner.nextInt();  
                        switch (choice) {
                            case 1:
                                validChoice = true; 
                                break;
                            case 2:
                                userSession = false; 
                                System.out.println("Вы вышли из аккаунта.\n");
                                validChoice = true;
                                break;
                            default:
                                System.out.println("Неверный выбор. Попробуйте снова.");
                        }
                    }
                }
            } else {
                System.out.println("Неверный ID или пароль. Попробуйте снова.");
            }
        }

        scanner.close();
        System.out.println("Система завершила работу.");
    }
}
