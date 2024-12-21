package utilites;

import java.io.*;
import java.util.*;
import java.util.List;
import Course.Course;
import Course.Lesson;
import researcher.EmployeeResearcher;
import researcher.ResearchPaper;
import researcher.ResearchProject;
import researcher.Researcher;
import Students.Schools;
import Students.Student;
import Students.StudentOrganization;
import teacher.Teacher;
import teacher.Dean;
import teacher.UrgencyLevel;
import Users.Employee;
import Users.Manager;
import Users.User;

public class SaveData implements Serializable {
    private static final long serialVersionUID = 1L;
    public static SaveData INSTANCE;

    private Vector<User> allUsers = new Vector<>();
    private Vector<Student> allStudents = new Vector<>();
    private Vector<Teacher> allTeachers = new Vector<>();
    private Vector<Manager> managers = new Vector<>();
    private Vector<EmployeeResearcher> employeeResearchers = new Vector<>();
    private Vector<Message> messages = new Vector<>();
    private Vector<Request> requests = new Vector<>();
    private Vector<Course> allCourses = new Vector<>();
    private Vector<ResearchPaper> researchPapers = new Vector<>();
    private HashMap<Schools, Vector<String>> facultyComplaints = new HashMap<>();
    private Vector<ResearchProject> researchProjects = new Vector<>();
    private List<Researcher> allResearchers = new ArrayList<>();;
    private Vector<News> news = new Vector<>();
    private Vector<StudentOrganization> studentOrganizations = new Vector<>();
    private HashMap<String, String> loginInfo = new HashMap<>();
    private Vector<String> logs = new Vector<>();
    private HashMap<Teacher, List<Lesson>> lessonsOfTeachers = new HashMap<>();
    private HashMap<Student, List<Lesson>> lessonsOfStudents = new HashMap<>();
    private List<Lesson> allLessons = new ArrayList<>();
    private Vector<Employee> allEmployees = new Vector<>();
    private Vector<Dean> deans = new Vector<>();
    private List<Observer> observers = new ArrayList<>();

    private static final File DATA_FILE = new File("data.ser");

    private SaveData() {
    }

    static {
        if (DATA_FILE.exists()) {
            try {
                INSTANCE = read();
            } catch (Exception e) {
                e.printStackTrace();
                INSTANCE = new SaveData();
            }
        } else {
            INSTANCE = new SaveData();
        }
    }



	public static void write() throws IOException {
	    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
	        oos.writeObject(INSTANCE);
	        System.out.println("Данные успешно сохранены.");
	    } catch (IOException e) {
	        e.printStackTrace();
	        throw e; 
	    }
	}
	
	public static SaveData read() throws IOException, ClassNotFoundException {
	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
	        return (SaveData) ois.readObject();
	    } catch (IOException | ClassNotFoundException e) {
	        e.printStackTrace();
	        throw e;
	    }
	}

    public void addUser(User user) throws Exception {
        if (loginInfo.containsKey(user.getEmail())) {
            System.out.println("Пользователь с таким ID уже существует: " + user.getEmail());
            return; 
        }
        allUsers.add(user);
        loginInfo.put(user.getEmail(), user.getPassword());
        if (user instanceof Student) allStudents.add((Student) user);
        if (user instanceof Teacher) allTeachers.add((Teacher) user);
        if (user instanceof Manager) managers.add((Manager) user);

        write(); 
    }

    
    public Vector<User> getAllUsers() {
        return allUsers; // Вернуть список пользователей
    }

    public String getAllUsers2() {
        StringBuilder usersList = new StringBuilder("Список пользователей:\n");
        for (User user : allUsers) {
            usersList.append(user.toString()).append("\n");
        }
        return usersList.toString();
    }

    public User getUserById(String id) {
        for (User user : allUsers) {
            if (user.getEmail().equalsIgnoreCase(id)) {
                return user; 
            }
        }
        return null; 
    }



    public void removeUser(int id) throws Exception {
        allUsers.removeIf(user -> user.getId() == id);
        allStudents.removeIf(student -> student.getId() == id);
        allTeachers.removeIf(teacher -> teacher.getId() == id);
        write();
    }
    
    public void removeUser2(String email) throws Exception {
        boolean userRemoved = allUsers.removeIf(user -> user.getEmail().equalsIgnoreCase(email));
        allStudents.removeIf(student -> student.getEmail().equalsIgnoreCase(email));
        allTeachers.removeIf(teacher -> teacher.getEmail().equalsIgnoreCase(email));

        if (userRemoved) {
            loginInfo.remove(email); // Удаляем информацию о логине
            write(); // Сохраняем изменения
            System.out.println("Пользователь с email " + email + " успешно удален.");
        } else {
            System.out.println("Пользователь с email " + email + " не найден.");
        }
    }


    public Vector<Student> getAllStudents() {
        return allStudents;
    }

    public Vector<Teacher> getAllTeachers() {
        return allTeachers;
    }

    public static int nextId() {
        return INSTANCE.allUsers.size() + 1;
    }

   

    public Vector<Message> getMessages() {
        return messages;
    }

    public void addMessage(Message message) throws IOException {
        messages.add(message);
        write();
    }

    public Vector<ResearchPaper> getResearchPapers() {
        return researchPapers;
    }

    public void addResearchPaper(ResearchPaper researchPaper) throws IOException {
        researchPapers.add(researchPaper);
       
    }

    public Vector<ResearchProject> getResearchProjects() {
        return researchProjects;
    }

    public void addResearchProject(ResearchProject researchProject) throws IOException {
        researchProjects.add(researchProject);
        write();
    }

    public void addCourse(Course course) throws IOException {
        allCourses.add(course);
        write();
    }

    public Vector<Course> getAllCourses() {
        return allCourses;
    }
    
    public Course getCourseByName(String name) {
        for (Course course : allCourses) {
            if (course.getName().equals(name)) { 
                return course;
            }
        }
        return null; 
    }
    
    public void addLessonToCourse(String courseName, Lesson lesson, Teacher teacher) throws IOException {
        Course course = getCourseByName(courseName);
        if (course != null) {
            lesson.setTeacher(teacher); // Назначить преподавателя
            course.addLesson(lesson);
            allLessons.add(lesson);
            write();
        } else {
            System.out.println("Курс не найден.");
        }
    }

    
    public void enrollStudentToCourse(String courseName, Student student) throws IOException {
        Course course = getCourseByName(courseName); 
        if (course != null) {
            course.enrollStudent(student);
            write(); 
            System.out.println("Студент " + student.getFirstName() + " записан на курс " + courseName);
        } else {
            System.out.println("Курс не найден.");
        }
    }


    public void addComplaint(Schools school, String complaint) throws IOException {
        facultyComplaints.computeIfAbsent(school, k -> new Vector<>()).add(complaint);
        write();
    }

    public Vector<String> getComplaintsBySchool(Schools school) {
        return facultyComplaints.getOrDefault(school, new Vector<>());
    }

    public void addNews(News newsItem) throws IOException {
        news.add(newsItem);
        
    }

    public Vector<News> getAllNews() {
        return news;
    }
    
    public List<Researcher> getAllResearchers() {
        return allResearchers;
    }

    public void addResearcher(Researcher researcher) {
        allResearchers.add(researcher);
    }
    
    public void generateResearcherAnnouncement(Researcher researcher, String paperTitle) {
        String content = "Researcher " + researcher.getFirstName() + " has published a new paper: " + paperTitle + ".";
        News announcement = new News("New Research Paper Published", UrgencyLevel.HIGH, content);
        try {
            addNews(announcement);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateTopCitedResearcherAnnouncement() {
        List<Researcher> researchers = SaveData.INSTANCE.getAllResearchers();
        
        Researcher topCitedResearcher = null;
        int maxCitations = 0;

        for (Researcher researcher : researchers) {
            int citationCount = researcher.getTotalCitations(); 
            if (citationCount > maxCitations) {
                maxCitations = citationCount;
                topCitedResearcher = researcher;
            }
        }

        if (topCitedResearcher != null) {
            String content = "Congratulations to " + topCitedResearcher.getFirstName() + " for becoming the top-cited researcher this month!";
            News announcement = new News("Research", UrgencyLevel.HIGH, content);
            try {
                addNews(announcement);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Researcher getTopCitedResearcher() {
        // Placeholder logic for determining the top-cited researcher
        List<Researcher> researchers = SaveData.INSTANCE.getAllResearchers();
        return researchers.stream()
            .max(Comparator.comparingInt(Researcher::getTotalCitations))
            .orElse(null);
    }



    public void addStudentOrganization(StudentOrganization organization) throws IOException {
        studentOrganizations.add(organization);
        write();
    }

    public Vector<StudentOrganization> getAllStudentOrganizations() {
        return studentOrganizations;
    }

    

    public Vector<Request> getRequests() {
        return requests;
    }

    public void addRequest(Request request) throws IOException {
        requests.add(request);
        write();
    }

//    public Vector<Orders> getAllOrders() {
//        return orders;
//    }
//
//    public void addOrder(Orders order) throws IOException {
//        orders.add(order);
//        write();
//    }

    public Vector<Manager> getManagers() {
        return managers;
    }

    public void addManager(Manager manager) throws IOException {
        managers.add(manager);
        write();
    }

    public Vector<EmployeeResearcher> getEmployeeResearchers() {
        return employeeResearchers;
    }

    public void addEmployeeResearcher(EmployeeResearcher employeeResearcher) throws IOException {
        employeeResearchers.add(employeeResearcher);
        write();
    }

    public HashMap<String, String> getLoginInfo() {
        return loginInfo;
    }

    public Vector<String> getLogs() {
        return logs;
    }

    public void addLog(String log) throws IOException {
        logs.add(log);
        write();
    }

    public List<Lesson> getAllLessons() {
        return allLessons;
    }

    public HashMap<Teacher, List<Lesson>> getLessonsOfTeachers() {
        return lessonsOfTeachers;
    }

    public HashMap<Student, List<Lesson>> getLessonsOfStudents() {
        return lessonsOfStudents;
    }

    public Vector<Employee> getAllEmployees() {
        if (allEmployees.isEmpty()) {
            for (User user : allUsers) {
                if (user instanceof Employee) {
                    allEmployees.add((Employee) user);
                }
            }
        }
        return allEmployees;
    }

    public Vector<Dean> getDeans() {
        if (deans.isEmpty()) {
            for (Teacher teacher : allTeachers) {
                if (teacher instanceof Dean) {
                    deans.add((Dean) teacher);
                }
            }
        }
        return deans;
    }

    public List<Observer> getObservers() {
        return observers;
    }

    public void setObservers(List<Observer> observers) {
        this.observers = observers;
    }
}
