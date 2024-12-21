package Course;

import java.io.Serializable;
import java.util.Objects;
import teacher.Teacher;

public class Lesson implements Serializable{
	
	
	private Course course;
	private weekDays day;
	private Time time;
	private Format format;
	private TypeOfLesson type;
	private Room room;
	private Teacher teacher;
	
	public Lesson() {
		
	}
	
	
	public Lesson(Course course, weekDays day,  TypeOfLesson type, Time time) {
		this.course = course;
		this.day = day;
		this.time = time;
		this.type=type;
	}
	
	public Lesson(Course course, weekDays day,  TypeOfLesson type, Time time, Teacher teacher) {
		this.course = course;
		this.day = day;
		this.time = time;
		this.type=type;
	    this.teacher = teacher;
	}

	
	public Lesson(Course course, String name, weekDays day, Time time, TypeOfLesson type,Room room) {
		this.course = course;
		this.day = day;
		this.time = time;
		this.type=type;
		this.room=room;
	}
	
	public String toString() {
		if(format == Format.ONLINE) return "Lesson "+ course.getName()+ ", "+day + ", "+format + ", "+time;
		return "Lesson "+ course.getName()+ ", "+day + ", "+format + ", "+time + ", " + room + ".";
	}
	
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public weekDays getDay() {
		return day;
	}
	public void setDay(weekDays day) {
		this.day = day;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public Format getFormat() {
		return format;
	}
	public void setFormat(Format format) {
		this.format = format;
	}
	public TypeOfLesson getType() {
		return type;
	}
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public void setType(TypeOfLesson type) {
		this.type = type;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(course, day, time, type);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lesson other = (Lesson) obj;
		return Objects.equals(course, other.course) && day == other.day
				&& Objects.equals(time, other.time) && type == other.type;
	}
	
	


}
