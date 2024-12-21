package menu2;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import researcher.ResearchPaper;
import utilites.SaveData;
import teacher.Teacher;

public class EmployeeResearchMenu {

    private Scanner scanner;

  
    public EmployeeResearchMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void manageResearchPapers(Teacher.TeacherResearcher teacherResearcher) {
        
            System.out.println("\n1) Добавить научную статью");
            System.out.println("2) Напечатать научные статьи");
            System.out.println("3) Рассчитать H-индекс");
            System.out.println("4) Вернуться в меню");
            System.out.println("5) Выйти");

            try {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addResearchPaper(teacherResearcher);
                        break;
                    case 2:
                        printResearchPapers(teacherResearcher);
                        break;
                    case 3:
                        calculateHIndex(teacherResearcher);
                        break;
                    case 4:
                        return; 
                    case 5:
                        System.exit(0);
                    default:
                        System.out.println("Неверный выбор");
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода. Попробуйте снова.");
                scanner.nextLine(); 
            }
        }
    

    
    private void addResearchPaper(Teacher.TeacherResearcher teacherResearcher) {
        try {
            System.out.println("Название статьи: ");
            scanner.nextLine();  // Consume the newline left over from nextInt()
            String name = scanner.nextLine();
            
            System.out.println("Количество страниц: ");
            int pages = scanner.nextInt();
            
            System.out.println("Цитирования: ");
            int citations = scanner.nextInt();

            // Create the new research paper
            ResearchPaper paper = new ResearchPaper(name, pages, citations);
            paper.getAuthors().add(teacherResearcher);  // Add the researcher to the paper

            
            SaveData.INSTANCE.addResearchPaper(paper);
            
            SaveData.INSTANCE.generateResearcherAnnouncement(teacherResearcher, paper.getName());
            
            SaveData.write(); 
            System.out.println("Научная статья успешно добавлена и новость о публикации создана.");
        } catch (Exception e) {
            System.out.println("Ошибка ввода данных. Попробуйте снова.");
            scanner.nextLine(); // Clear the input buffer
        }
    }



    private void printResearchPapers(Teacher.TeacherResearcher teacherResearcher) {
        List<ResearchPaper> papers = SaveData.INSTANCE.getResearchPapers();
        boolean found = false;

        for (ResearchPaper paper : papers) {
            if (paper.getAuthors().contains(teacherResearcher)) {
                System.out.println(paper.getName());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Нет научных статей для этого преподавателя.");
        }
    }

    private void calculateHIndex(Teacher.TeacherResearcher teacherResearcher) {
        System.out.println("H-индекс: " + teacherResearcher.calculateHIndex());
    }
}
