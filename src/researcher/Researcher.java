package researcher;

import java.util.List;


public interface Researcher {
    List<ResearchPaper> printPapers();
    int calculateHIndex();
    int getTotalCitations(); 
    String getFirstName();
}
