package Users;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Course.Course;
import Course.Lesson;
import Students.Schools;
import Students.Student;
import teacher.Teacher;
import utilites.SaveData;
import utilites.Mark;
import utilites.News;


public class Manager extends Employee{
	
	private ManagerType type;	
	
	
	public Manager() {
		
	}
	
	public Manager(String email, String password) {
		super(email, password);
	}
	
	public Manager(String lastName, String firstName, ManagerType type) {
		super(lastName, firstName);
		this.type = type;
	}

	public ManagerType getType() {
		return type;
	}

	public void setType(ManagerType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(type);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj)) return false;
		Manager other = (Manager) obj;
		return type == other.type;
	}

	@Override
	public String toString() {
		return super.toString() + "Manager " + type;
	}
	
	
	public void addCoursesForRegis(Course crs) throws IOException {
		SaveData.INSTANCE.addCourse(crs);
		SaveData.write();
	}
	
	public void addCourseWithTeacher(Course course, Teacher teacher) throws IOException {
        if (course.getInstructors() == null) {
            course.setInstructors(new ArrayList<>());
        }

        course.getInstructors().add(teacher);

        SaveData.INSTANCE.addCourse(course);
        SaveData.write();
    }
	
	
	public void addLessonToSystem(Lesson l) throws IOException {
		SaveData.INSTANCE.getAllLessons().add(l);
		SaveData.write();
	}
	
	
	public void deleteLessonFromSystem(Lesson l) throws IOException {
		SaveData.INSTANCE.getAllLessons().remove(l);
		SaveData.write();
	}
	
	
	public void attachLessonToTeacher(Teacher t, Lesson l) throws Exception {
		for(Teacher tt: SaveData.INSTANCE.getAllTeachers()) {
			if(tt.equals(t)) {
				if(SaveData.INSTANCE.getLessonsOfTeachers().get(tt) == null) {
					SaveData.INSTANCE.getLessonsOfTeachers().put(tt, new ArrayList<Lesson>());
				} 
				SaveData.INSTANCE.getLessonsOfTeachers().get(tt).add(l);
				SaveData.write();
			}
		}
	}
	

	public void detachLessonToTeacher(Teacher t, Lesson l) throws IOException {
		for(Teacher tt: SaveData.INSTANCE.getAllTeachers()) {
			if(tt.equals(t)) {
				SaveData.INSTANCE.getLessonsOfTeachers().get(tt).remove(l);
				SaveData.write();
			}
		}
	}
	

	public void attachLessonToStudent(Student s, Lesson l) throws Exception {
		for(Student st: SaveData.INSTANCE.getAllStudents()) {
			if(st.equals(s)) {
				if(SaveData.INSTANCE.getLessonsOfStudents().get(st) == null) {
					SaveData.INSTANCE.getLessonsOfStudents().put(st, new ArrayList<Lesson>());
				} 
				SaveData.INSTANCE.getLessonsOfStudents().get(st).add(l);
				SaveData.write();
			}
		}
	}
	

	public void detachLessonToStudent(Student s, Lesson l) throws IOException {
		for(Student st: SaveData.INSTANCE.getAllStudents()) {
			if(st.equals(s)) {
				SaveData.INSTANCE.getLessonsOfStudents().get(st).remove(l);
				SaveData.write();
			}
		}
	}
	

	public void approveRegistration(Student s, Course c) throws Exception {
		for(User u: SaveData.INSTANCE.getAllUsers()) {
			if(u instanceof Student) {
				Student st = (Student)u;
				if(st.equals(s)) {
					if(!st.getMarks().containsKey(c)) 
						st.getMarks().put(c, new Mark());
				}
			}
			
		}
		SaveData.write();
	}
	

	public void addNews(News n) throws IOException {
		SaveData.INSTANCE.addNews(n);
		SaveData.write();
	}
	

	public List<User> viewInfoTeacher() throws Exception {
		List<User> teachers = SaveData.INSTANCE.getAllUsers().stream().filter(n->n instanceof Teacher).collect(Collectors.toList());
		return teachers;
	}


    public String createReportBySchool(Schools school) {
        List<Student> students = SaveData.INSTANCE.getAllStudents();

        double averageGPA = students.stream()
                .filter(student -> student.getSchool().equals(school))
                .mapToDouble(Student::getGpa)
                .average()
                .orElse(0.0);

        return "Average GPA for " + school + ": " + averageGPA;
    }
	

	public List<User> viewStudentsSortedByGpa() throws Exception{
		return SaveData.INSTANCE.getAllStudents().stream()
				.sorted(Comparator.comparingDouble(Student::getGpa).reversed())
				.collect(Collectors.toCollection(Vector::new));
	}
	

	public List<Student> viewStudentsSortedAlphabetically() {
	    return SaveData.INSTANCE.getAllStudents().stream()
	            .sorted(Comparator.comparing(Student::getLastName))
	            .collect(Collectors.toCollection(Vector::new));
	}

	
}
