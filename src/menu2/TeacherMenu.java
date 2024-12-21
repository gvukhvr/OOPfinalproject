package menu2;

import java.util.*;
import java.io.*;

import teacher.Teacher;
import Course.*;
import utilites.SaveData;
import utilites.Mark;
import utilites.News;
import Students.Student;
import researcher.ResearchPaper;
import researcher.CanNotJoinToProjectException;

public class TeacherMenu {
    private Scanner scanner;
    private Teacher teacher;
    private SaveData saveData;
    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private EmployeeResearchMenu employeeResearch; 

    public TeacherMenu(Scanner scanner, Teacher teacher) {
        this.scanner = scanner;
        this.teacher = teacher;
        this.saveData = SaveData.INSTANCE;
        this.employeeResearch = new EmployeeResearchMenu(scanner);
    }

    public void showMenu() {
        
            System.out.println("\nМеню преподавателя:");
            System.out.println("1. Посмотреть курсы");
            System.out.println("2. Просмотр студентов на курсе");
            System.out.println("3. Просмотр научных статей");
            System.out.println("4. Поставить оценки студентам");
            System.out.println("5. Управление научными статьями");
            System.out.println("6. Новости");
            System.out.println("7. Выйти");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    viewTeacherCourses();
                    break;
                case 2:
                    viewStudentsOnCourse();
                    break;
                case 3:
                    viewResearchPapers();
                    break;
                case 4:
                    assignMarkToStudent();
                    break;
                case 5:
                    manageResearchPapers(); 
                    break;
                case 6:
                    viewNews(); 
                    break;
                case 7:
                	return; 
                default:
                    System.out.println("Неверный выбор");
            }
        }
    

    private void viewTeacherCourses() {
        System.out.println("Ваши курсы:");
        for (Course course : teacher.viewCourses()) {
            System.out.println(course.getName());
        }
    }

    private void viewStudentsOnCourse() {
        System.out.println("Введите название курса: ");
        scanner.nextLine(); 
        String courseName = scanner.nextLine();

        Course course = saveData.getCourseByName(courseName);
        if (course != null) {
            List<Student> students = course.getEnrolledStudents();
            if (students.isEmpty()) {
                System.out.println("Нет студентов, зарегистрированных на этот курс.");
            } else {
                System.out.println("Студенты на курсе " + courseName + ":");
                for (Student student : students) {
                    System.out.println(student.getFirstName() + " " + student.getLastName());
                }
            }
        } else {
            System.out.println("Курс не найден!");
        }
    }


    private void viewResearchPapers() {
        Teacher.TeacherResearcher researcher = teacher.new TeacherResearcher();
        List<ResearchPaper> papers = researcher.printPapers();
        if (papers.isEmpty()) {
            System.out.println("Нет научных статей.");
        } else {
            System.out.println("Ваши научные статьи:");
            for (ResearchPaper paper : papers) {
                System.out.println(paper.getName());
            }
        }
    }

    private void assignMarkToStudent() {
        System.out.println("Введите название курса: ");
        scanner.nextLine(); 
        String courseName = scanner.nextLine();

        Course course = saveData.getCourseByName(courseName); 
        if (course != null) {
            System.out.println("Введите email студента: ");
            String studentEmail = scanner.nextLine();

            for (Student student : saveData.getAllStudents()) {
                if (student.getEmail().equalsIgnoreCase(studentEmail)) {  
                    if (!student.getMarks().containsKey(course)) {
                        System.out.println("Студент не зарегистрирован на этот курс.");
                        return;
                    }

                    System.out.println("Введите оценку за 1 аттестацию: ");
                    double att1 = scanner.nextDouble();
                    System.out.println("Введите оценку за 2 аттестацию: ");
                    double att2 = scanner.nextDouble();
                    System.out.println("Введите оценку за финальный экзамен: ");
                    double finalExam = scanner.nextDouble();

            
                    Mark mark = new Mark(att1, att2, finalExam);

                
                    student.putMark(course, mark);

                 
                    try {
                        saveData.write();
                    } catch (IOException e) {
                        System.out.println("Ошибка при сохранении данных: " + e.getMessage());
                    }

                    System.out.println("Оценки успешно добавлены!");
                    return;
                }
            }
            System.out.println("Студент с таким email не найден!");
        } else {
            System.out.println("Курс не найден!");
        }
    }


    
    private void manageResearchPapers() {
        Teacher.TeacherResearcher teacherResearcher = teacher.new TeacherResearcher();
        employeeResearch.manageResearchPapers(teacherResearcher);
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
