package teacher;

import java.io.IOException;

import java.util.Objects;

import Students.Schools;


public class Dean extends Teacher {
    private Schools faculty;


    public Dean() {
    }


    public Dean(String lastName, String firstName, Schools faculty) {
        super(lastName, firstName);
        this.faculty = faculty;
    }

//    public void readComplaints() throws IOException {
//
//    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Dean dean = (Dean) o;
        return Objects.equals(faculty, dean.faculty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), faculty);
    }

    @Override
    public String toString() {
        return "Dean{" + super.toString() + "faculty=" + faculty + "} ";
    }


}

