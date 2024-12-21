package teacher;
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

import Users.Employee;
import Students.Schools;
import Students.Student;
import Course.Course;
import Course.Lesson;
import utilites.SaveData;
import researcher.Researcher;
import researcher.Researchers;
import researcher.ResearchPaper;
import utilites.Mark;

public class Teacher extends Employee{
	private static final long serialVersionUID = 3831962457385399365L;
	private Schools school;
	private Degree academicDegree;
	private List<Course> courses = new ArrayList<>();  


	public Teacher() {}
	
	
	
	public Teacher(String email, String password) {
		super(email, password);
	}
    
	public Teacher(String firstName, String lastName, Schools school) {
		super(lastName, firstName);
		this.school = school;
	}
    
	public Teacher(String firstName, String lastName,  Schools school, Degree academicDegree) {
		super(lastName, firstName);
		this.setAcademicDegree(academicDegree);
		this.setSchool(school);
	}
	
	
	
	
	
	public Schools getSchool() {
		return school;
	}
  
	public void setSchool(Schools school) {
		this.school = school;
	}
    
	public Degree getAcademicDegree() {
		return academicDegree;
	}
   
	public void setAcademicDegree(Degree academicDegree) {
		this.academicDegree = academicDegree;
	}
	
	
	
	public List<Course> viewCourses() {
	    List<Course> courses = new ArrayList<Course>();

	    for (Course course : SaveData.INSTANCE.getAllCourses()) {
	        if (course.getInstructors().contains(this)) {
	            courses.add(course); 
	        }
	    }

	    return courses;
	}

	

    // Get all students in a course
    public List<Student> getStudentsInCourse(Course course) {
        return course.getEnrolledStudents();
    }
    
	public void putMark(Lesson l, Student s, Double score, int currentWeek) throws IOException {
	    if (currentWeek <= 8) {
	        s.getMarks().get(l.getCourse()).setAtt1(score);
	    } else if (currentWeek >= 14 && currentWeek <= 15) {
	        s.getMarks().get(l.getCourse()).setAtt2(score);
	    } else if (currentWeek >= 16) {
	        s.getMarks().get(l.getCourse()).setFinalExamScore(score);
	    }
	    SaveData.write();
	}

	public void addCourse(Course course) {
	    if (!courses.contains(course)) {
	        courses.add(course);
	        course.assignTeacher(this);
	    }
	}
    
	public List<Student> viewStudentInfo(Lesson l){
		return SaveData.INSTANCE.getLessonsOfStudents().entrySet()
				.stream()
				.filter(n->n.getValue().contains(l))
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());
		
	}
    
	public List<Lesson> getLessons() {
		return SaveData.INSTANCE.getLessonsOfTeachers().get(this);
	}
	
    
	public void sendComplaint(Schools school, String s, UrgencyLevel level) throws IOException {
		String complaint = "\n" + "Urgency Level: " + level + " -> " + s;
		SaveData.INSTANCE.addComplaint(school, complaint); 
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(academicDegree, school);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj))
			return false;
		Teacher other = (Teacher) obj;
		return academicDegree == other.academicDegree && school == other.school;
	}
   
	@Override
	public String toString() {
		return super.toString() + "Teacher' school: " + school + ", academicDegree: " + academicDegree;
	}
	
    
	 public class TeacherResearcher implements Researcher, Serializable {
		 @Override
	        public List<ResearchPaper> printPapers() {
	            return Researchers.printPapers(this);
	        }

	        @Override
	        public int calculateHIndex() {
	            return Researchers.calculateHIndex(this);
	        }

	        @Override
	        public int getTotalCitations() {
	            return 0; 
	        }

	        @Override
	        public String getFirstName() {
	            return Teacher.this.getFirstName(); 
	        }

	        @Override
	        public int hashCode() {
	            return Objects.hash(getEnclosingInstance()); 
	        }

	        @Override
	        public boolean equals(Object obj) {
	            if (this == obj) return true;
	            if (obj == null || getClass() != obj.getClass()) return false;
	            TeacherResearcher that = (TeacherResearcher) obj;
	            return getEnclosingInstance().equals(that.getEnclosingInstance());
	        }

	        private Teacher getEnclosingInstance() {
	            return Teacher.this; 
	        }
	    }
	
	
	
}