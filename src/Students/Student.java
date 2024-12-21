package Students;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Vector;

import Course.Course;
import utilites.SaveData;
import utilites.Mark;
import researcher.ResearchPaper;
import researcher.Researcher;
import researcher.Researchers;
import Students.Major;
import Users.User;

public class Student extends User {
	private Schools school;
	private Major major;
	private double gpa;
	private HashMap<Course, Mark> marks = new HashMap<Course, Mark>();
	private int yearOfStudy;

	
	public Student() {
		
	}
	
    
	public Student(String firstName) {
		super(firstName);
	}
	
    
	public Student(String email, String password) {
		super(email, password);
	}
	
    
	public Student(String lastName, String firstName, Schools school) {
		super(lastName, firstName);
		this.school = school;
		this.yearOfStudy = 1;
	}
	
    
	public Student(String lastName, String firstName, Schools school,  int yearOfStudy) {
		super(lastName, firstName);
		this.school = school;
		this.yearOfStudy = yearOfStudy;
	}
	
	
	//for test
	public Student(String lastName, String firstName, double gpa) {
		super(lastName, firstName);
		this.gpa = gpa;
	}
	
	//for test createReport
	public Student(String firstName, double gpa, Schools school) {
		super(firstName);
		this.gpa = gpa;
		this.school = school;
	}
	
    
	public Schools getSchool() {
		return school;
	}
	
	public Major getMajor() {
		return major;
	}
	
	public double getGpa() {
		return gpa;
	}
	
	public int getYearOfStudy() {
		return this.yearOfStudy;
	}
	

	
	public void setSchool(Schools school) {
		this.school = school;
	}
	
	
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	
	public void setYearOfStudy(int yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}
	


    /**
     * Displays the mark of the student for a specific course
     * @param course The course for which to view the mark
     * @return The mark of the student for the given course
     */
	public HashMap<Course, Mark> viewMark(Course course) {
		for(Student s: SaveData.INSTANCE.getAllStudents()) {
			if(s.equals(this)) return s.getMarks();
		}
		return null;
		
	}
	
	// Add this method in Student class to update the mark for a course.
	public void putMark(Course course, Mark mark) {
	    marks.put(course, mark);  // Updates or adds a mark for the student in the specific course
	}

    
	/**
	 * Displays the transcript of the student, including marks for all courses
	 */
	public void viewTranscript() {
		System.out.println("|Course|" +"|First Att|"+"|Second Att|"+"|Final|");
		marks.forEach((key,value)->{
			System.out.println("|"+key.getName()+"\t|"+value.getAtt1()+"\t|"+value.getAtt2()+"\t|"+value.getFinalExamScore()+"\t|\t"+value.getLetterGrade());
		});
	}
	
	//Method to view info about Teacher
	public void viewTeacherInfo(Course course) {
		
	}
	
	/**
	 * Register to some courses
	 * @param course
	 */
	public void registerCourses(Course course) {
		marks.put(course, new Mark());
	}
	
    /**
     * Retrieves the courses for registration
     * @return null
     */
	public Vector<Course> getCoursesForRegistration() {
		return null;
		//return SaveData.getInstance().getCourse().stream().filter(n->n.);
	}
	
    /**
     * Retrieves Marks of the student
     * @return The HashMap with course name and Mark of the student
     */
	public HashMap<Course, Mark> getMarks() {
		return marks;
	}
	
	public String toString() {
		return "Student [" + super.toString() + ", school= " + this.school + ", year of study= " + this.yearOfStudy + "]"; 
	}
	
	public boolean equals(Object o) {
		if(!super.equals(o)) return false;
		Student s = (Student) o;
		return this.gpa == s.gpa && this.yearOfStudy == s.yearOfStudy && this.school.equals(s.school);
	}
	
	public int hashCode() {
		return Objects.hash(getEmail(), getId(), getFirstName(), getPassword(), getLastName(), getSchool(), getMajor(), getGpa(), getYearOfStudy());
	}
	
    /**
     * Inner class representing a student who is also a researcher
     */
	public class ResearcherStudent implements Researcher, Serializable {

        @Override
        public String toString() {
            return "and I am a student researcher";
        }

        @Override
        public List<ResearchPaper> printPapers() {
            // Используем метод printPapers из Researchers
            return Researchers.printPapers(this);
        }

        @Override
        public int calculateHIndex() {
            // Используем метод calculateHIndex из Researchers
            return Researchers.calculateHIndex(this);
        }

        @Override
        public int getTotalCitations() {
            // Возвращаем общее количество цитирований для этого исследователя
            return 0; // Реализуйте логику подсчета цитирований
        }

        @Override
        public String getFirstName() {
            return Student.this.getFirstName();  // Получаем имя из родительского класса Student
        }
    }
	
	
}
