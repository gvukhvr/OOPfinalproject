package teacher;

import researcher.ResearchPaper;
import researcher.Researcher;
import researcher.Researchers;
import Students.Schools;
import java.util.List;

public class Professor extends Teacher implements Researcher {

    private static final long serialVersionUID = 5295963205026648093L;

    public Professor() {}

    public Professor(String lastName, String firstName, Schools school) {
        super(lastName, firstName, school);
        this.setAcademicDegree(Degree.PROFESSOR);
    }

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
        return super.getFirstName();
    }
}