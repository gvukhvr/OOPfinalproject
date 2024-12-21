package menu2;

import java.io.IOException;
import java.util.List;
import java.util.Vector;
import java.util.Scanner;


import Users.Manager;
import Course.Course;
import Users.User;
import Students.Student;
import teacher.Teacher;
import teacher.UrgencyLevel;
import teacher.Teacher.TeacherResearcher;
import utilites.SaveData;
import researcher.EmployeeResearcher;
import utilites.News;


public class ManagerMenu {
    private Scanner scanner;
    private Manager manager;
    private SaveData saveData;
    private EmployeeResearchMenu employeeResearch; 

    public ManagerMenu(Scanner scanner, Manager manager) {
        this.scanner = scanner;
        this.manager = manager;
        this.saveData = SaveData.INSTANCE;
        this.employeeResearch = new EmployeeResearchMenu(scanner);
    }
    
    

    public void showMenu() {
        System.out.println("\nМеню менеджера:");
        System.out.println("1. Добавить курс");
        System.out.println("2. Добавить урок");
        System.out.println("3. Посмотреть все курсы");
        System.out.println("4. Посмотреть всех студентов");
        System.out.println("5. Посмотреть всех учителей");
        System.out.println("6. Добавить новость");
        System.out.println("7. Управление научными статьями");
        System.out.println("8. Выйти");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                addCourse();
                break;
            case 2:
                addLesson();
                break;
            case 3:
                viewAllCourses();
                break;
            case 4:
                viewAllStudents();
                break;
            case 5:
                viewAllTeachers();
                break;
            case 6:
                addNews();
                break;
            case 7:
                manageResearchPapers(); 
                break;
            case 8:
                return;
            default:
                System.out.println("Неверный выбор");
        }
    }

    private void addCourse() {
        System.out.println("Введите название курса: ");
        scanner.nextLine(); 
        String courseName = scanner.nextLine();

        
        Course course = new Course(courseName);

       
        System.out.println("Введите количество преподавателей для курса: ");
        int numInstructors = scanner.nextInt();

        if (numInstructors > 0) {
            scanner.nextLine();
            for (int i = 1; i <= numInstructors; i++) {
                System.out.println("Введите ID учителя " + i + ": ");
                String teacherId = scanner.nextLine();

                Teacher teacher = (Teacher) SaveData.INSTANCE.getUserById(teacherId);
                if (teacher != null) {
                    course.getInstructors().add(teacher);
                    System.out.println("Преподаватель " + teacher.getFirstName() + " " + teacher.getLastName() + " добавлен.");
                } else {
                    System.out.println("Преподаватель с ID " + teacherId + " не найден.");
                }
            }
        } else {
            System.out.println("Курс не может быть добавлен без преподавателей.");
            return;
        }

        try {
          
            SaveData.INSTANCE.addCourse(course);
            System.out.println("Курс добавлен!");
        } catch (IOException e) {
            System.out.println("Ошибка при добавлении курса: " + e.getMessage());
        }
    }


    private void addLesson() {
        System.out.println("Введите название урока: ");
        scanner.nextLine(); 
        String lessonName = scanner.nextLine();

        System.out.println("Введите день недели: ");
        String day = scanner.nextLine();

        System.out.println("Введите тип урока (лекция, семинар и т.д.): ");
        String type = scanner.nextLine();

        System.out.println("Введите время начала урока: ");
        String time = scanner.nextLine();

        System.out.println("Урок добавлен!");
    }

   

    private void viewAllCourses() {
        Vector<Course> courses = SaveData.INSTANCE.getAllCourses(); 
        System.out.println("Все курсы:");
        for (Course course : courses) {
            System.out.println(course.getName());
        }
    }

    private void viewAllStudents() {
        Vector<Student> students = SaveData.INSTANCE.getAllStudents();
        System.out.println("Все студенты:");
        for (Student student : students) {
            System.out.println(student.getEmail() + " " + student.getLastName());
        }
    }

    private void viewAllTeachers() {
        Vector<Teacher> teachers = SaveData.INSTANCE.getAllTeachers(); 
        System.out.println("Все учителя:");
        for (Teacher teacher : teachers) {
            System.out.println(teacher.getEmail() + " " + teacher.getLastName());
        }
    }
    
    private void manageResearchPapers() {
        Teacher.TeacherResearcher teacherResearcher = new Teacher().new TeacherResearcher();  
        employeeResearch.manageResearchPapers(teacherResearcher); 
    }
    
    private void addNews() {
        scanner.nextLine();  // Считывание лишнего символа после ввода числа
        System.out.println("Введите название новости: ");
        String title = scanner.nextLine();

        System.out.println("Введите текст новости: ");
        String text = scanner.nextLine();

        System.out.println("Выберите уровень важности (1 - HIGH, 2 - MEDIUM, 3 - LOW): ");
        int priorityChoice = scanner.nextInt();

        UrgencyLevel priority = null;
        switch (priorityChoice) {
            case 1:
                priority = UrgencyLevel.HIGH;
                break;
            case 2:
                priority = UrgencyLevel.MEDIUM;
                break;
            case 3:
                priority = UrgencyLevel.LOW;
                break;
            default:
                System.out.println("Некорректный выбор приоритета. Установлен MEDIUM по умолчанию.");
                priority = UrgencyLevel.MEDIUM;
        }

        News news = new News(title, priority, text);        
        try {
            SaveData.INSTANCE.addNews(news);
            SaveData.write();
            System.out.println("Новость добавлена!");
        } catch (IOException e) {
            System.out.println("Ошибка при добавлении новости: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
