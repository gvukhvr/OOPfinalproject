package menu2;

import Students.Student;
import Course.Course;
import utilites.News;
import utilites.SaveData;

import java.util.List;
import java.util.Scanner;
import java.io.IOException;

public class StudentMenu {
    private Scanner scanner;
    private Student student;
    private SaveData saveData;

    public StudentMenu(Scanner scanner, Student student) {
        this.scanner = scanner;
        this.student = student;
        this.saveData = SaveData.INSTANCE;
    }

    public void showMenu() {
        System.out.println("Меню студента:");
        System.out.println("1. Посмотреть оценки");
        System.out.println("2. Зарегистрироваться на курсы");
        System.out.println("3. Посмотреть доступные курсы");
        System.out.println("4. Мои курсы");
        System.out.println("5. Новости");
        System.out.println("6. Выйти");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                student.viewTranscript(); 
                break;
            case 2:
                registerForCourses(); 
                break;
            case 3:
                viewAllAvailableCourses(); 
                break;
            case 4:
                viewMyCourses();
                break;
            case 5:
                viewNews();
                break;
            case 6:
                return;
            default:
                System.out.println("Неверный выбор");
        }
    }

    private void registerForCourses() {
        System.out.println("Введите название курса для регистрации: ");
        scanner.nextLine(); 
        String courseName = scanner.nextLine();

        Course course = saveData.getCourseByName(courseName);
        if (course != null) {
            student.registerCourses(course); 
            course.enrollStudent(student);
            System.out.println("Вы успешно зарегистрированы на курс " + course.getName());

            
            try {
                saveData.write(); 
            } catch (IOException e) {
                System.out.println("Ошибка при сохранении данных: " + e.getMessage());
            }
        } else {
            System.out.println("Курс не найден!");
        }
    }


    private void viewAllAvailableCourses() {
        System.out.println("Доступные курсы:");
        for (Course course : saveData.getAllCourses()) {
            System.out.println(course.getName()); 
        }
    }

    private void viewMyCourses() {
        System.out.println("Ваши курсы:");
        if (student.getMarks().isEmpty()) {
            System.out.println("Вы не зарегистрированы ни на одном курсе.");
        } else {
            for (Course course : student.getMarks().keySet()) {
                System.out.println(course.getName()); 
            }
        }
    }
    private void viewNews() {
        List<News> newsList = saveData.getAllNews();
        if (newsList.isEmpty()) {
            System.out.println("Нет новостей.");
        } else {
            System.out.println("Новости:");
            for (News news : newsList) {
                System.out.println(news.getTitle() + " - " + news.getDate());
                System.out.println(news.getText());
                System.out.println();
            }
        }
    }
}
