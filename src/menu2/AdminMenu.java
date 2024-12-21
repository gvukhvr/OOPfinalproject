package menu2;

import Users.Admin;
import Users.FinanceManager;
import teacher.Teacher;
import Students.Student;
import Students.Schools;
import teacher.Degree;
import Users.Manager;
import Users.ManagerType;
import utilites.SaveData;

import java.io.IOException;
import java.util.Scanner;

public class AdminMenu {
    private Scanner scanner;
    private Admin admin;

    public AdminMenu(Scanner scanner, Admin admin) {
        this.scanner = scanner;
        this.admin = admin;
    }

    public void showMenu() {
        System.out.println("Меню администратора:");
        System.out.println("1. Добавить пользователя");
        System.out.println("2. Удалить пользователя");
        System.out.println("3. Просмотреть всех пользователей");
        System.out.println("4. Выйти");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера
        switch (choice) {
            case 1:
                addUser();
                break;
            case 2:
                removeUser();
                break;
            case 3:
                viewAllUsers();
                break;
            case 4:
                return;
            default:
                System.out.println("Неверный выбор");
        }
    }

    private void addUser() {
        System.out.println("Выберите тип пользователя:");
        System.out.println("1. Учитель");
        System.out.println("2. Менеджер");
        System.out.println("3. Студент");
        System.out.println("4. Финансовый менеджер");
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        System.out.println("Введите фамилию:");
        String lastName = scanner.nextLine();
        System.out.println("Введите имя:");
        String firstName = scanner.nextLine();
        System.out.println("Введите пароль:");
        String password = scanner.nextLine();

        try {
            switch (choice) {
                case 1:
                    System.out.println("Выберите школу (SAM, SITE, BS):");
                    Schools school = Schools.valueOf(scanner.nextLine().toUpperCase());
                    System.out.println("Выберите степень (TUTOR, LECTURER, SENIOR_LECTURER, PROFESSOR):");
                    Degree degree = Degree.valueOf(scanner.nextLine().toUpperCase());
                    Teacher teacher = new Teacher(firstName, lastName, school, degree);
                    teacher.changePassword(password);
                    admin.addUser(teacher);
                    break;

                case 2:
                    System.out.println("Введите тип менеджера (OR, FACULTY, DEPARTMENT):");
                    ManagerType managerType = ManagerType.valueOf(scanner.nextLine().toUpperCase());
                    Manager manager = new Manager(firstName, lastName, managerType);
                    manager.changePassword(password);
                    admin.addUser(manager);
                    break;

                case 3:
                    System.out.println("Выберите школу (SAM, SITE, BS):");
                    Schools schoolStudent = Schools.valueOf(scanner.nextLine().toUpperCase());
                    System.out.println("Введите курс:");
                    int course = scanner.nextInt();
                    Student student = new Student(firstName, lastName, schoolStudent, course);
                    student.changePassword(password);
                    admin.addUser(student);
                    break;

                case 4:
                    FinanceManager financeManager = new FinanceManager(firstName, lastName);
                    financeManager.changePassword(password);
                    admin.addUser(financeManager);
                    break;

                default:
                    System.out.println("Неверный выбор.");
            }
            SaveData.write();
            System.out.println("Пользователь успешно добавлен.");
        } catch (IOException e) {
            System.out.println("Ошибка при добавлении пользователя: " + e.getMessage());
        }
    }

    private void removeUser() {
        System.out.println("Введите ID пользователя для удаления:");
        String email = scanner.nextLine();
        try {
            admin.removeUser(email);
            SaveData.write();
            System.out.println("Пользователь успешно удалён.");
        } catch (Exception e) {
            System.out.println("Ошибка при удалении пользователя: " + e.getMessage());
        }
    }

    private void viewAllUsers() {
        System.out.println("Список пользователей:");
        String allUsers = admin.viewAllUsers();
        System.out.println(allUsers);
    }
}
