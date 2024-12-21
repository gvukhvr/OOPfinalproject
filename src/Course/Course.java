package Course;

import teacher.Teacher;
import Students.Schools;
import Students.Major;
import Users.Employee;
import Students.Student;
import Course.Course;
import Course.Lesson;
import utilites.SaveData;
import researcher.Researcher;
import researcher.Researchers;
import researcher.ResearchPaper;
import utilites.Mark;
import utilites.SaveData;
import java.util.*;
import java.io.Serializable;

public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String code;
    private Schools school;
    private int credits;
    private HashSet<Course> prerequisites;
    private HashMap<Major, CourseType> obligation = new HashMap<>();
    private List<Teacher> instructors = new ArrayList<>();
    private List<Student> enrolledStudents = new ArrayList<>();
    private List<Lesson> lessons = new ArrayList<>();
    private List<Mark> marks = new ArrayList<>();
    {
        prerequisites = new HashSet<>();
    }
    public Course(String name) {
        this.name = name;
    }

    

    public void setInstructors(List<Teacher> instructors) {
        this.instructors = instructors;
    }
    
    public Course(String name, String code, Schools school, int credits) {
        this.name = name;
        this.code = code;
        this.school = school;
        this.credits = credits;
        this.instructors = new ArrayList<>();
    }
    
    public Course(List<Teacher> instructors) {
        if (instructors != null) {
            this.instructors = instructors;
        }
    }

    public void addPrerequisite(Course c) {
        prerequisites.add(c);
    }

    public void addInstructor(Teacher teacher) {
        instructors.add(teacher);
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public int getCredits() {
        return credits;
    }

    public List<Teacher> getInstructors() {
        return instructors;
    }

    public HashSet<Course> getPrerequisites() {
        return prerequisites;
    }
    
    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    } 
    public List<Lesson> getLessons() {
        return lessons;
    }
    public void enrollStudent(Student student) {
        if (!enrolledStudents.contains(student)) {
            enrolledStudents.add(student);
        }
    }
    public void assignTeacher(Teacher teacher) {
        this.instructors.add(teacher);
    }
    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }
    
    
    @Override
    public String toString() {
        return "Course name: " + name + ", code: " + code + ", school: " + school + ", credits: " + credits + " ";
    }
}
