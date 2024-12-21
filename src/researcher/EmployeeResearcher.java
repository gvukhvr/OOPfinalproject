package researcher;

import java.util.List;
import Users.Employee;


public class EmployeeResearcher extends Employee implements Researcher {
    private static final long serialVersionUID = -3599532175820123036L;

    public EmployeeResearcher() {
    }

   
    public EmployeeResearcher(String firstName, String LastName) {
        super(firstName, LastName);
    }

    
    public List<ResearchPaper> printPapers() {
        return Researchers.printPapers(this);
    }

    public String getFirstName() {
        return super.getFirstName();  
    }
     
    public int calculateHIndex() {
        return Researchers.calculateHIndex(this);
    }
    
    public int getTotalCitations() {
        int totalCitations = 0;
        List<ResearchPaper> papers = printPapers();
        for (ResearchPaper paper : papers) {
            totalCitations += paper.getCitations();
        }
        return totalCitations;
    }

}