package researcher;


import java.util.Collections;

import java.util.List;
import java.util.stream.Collectors;

import utilites.SaveData;

public class Researchers {
	
	/**
    * Calculates the H-index of a given researcher based on their authored research papers
    * @param researcher The researcher for whom the H-index is to be calculated
    * @return The calculated H-index
    */
	public static int calculateHIndex2(Researcher researcher) {
		List<ResearchPaper> papers = printPapers(researcher);
		Collections.sort(papers);
		int hIndex = 0;
        for (int i = 0; i < papers.size(); i++) {
            while(papers.get(i).getCitations()>=i+1) {
            	hIndex = i;
            	i++;
            }
        }
        return hIndex+1;
	}
	
	public static int calculateHIndex(Researcher researcher) {
	    List<ResearchPaper> papers = printPapers(researcher);
	    
	    // Sort papers by citations in descending order
	    Collections.sort(papers, (p1, p2) -> Integer.compare(p2.getCitations(), p1.getCitations()));
	    
	    int hIndex = 0;
	    
	    // Iterate through the papers and calculate the H-index
	    for (int i = 0; i < papers.size(); i++) {
	        // Check if the current paper has at least (i + 1) citations
	        if (papers.get(i).getCitations() >= (i + 1)) {
	            hIndex = i + 1; // Update the h-index to (i + 1)
	        } else {
	            break; // If we encounter a paper with fewer citations than the current index, stop the loop
	        }
	    }
	    
	    return hIndex;
	}

	/**
	 * Takes a researcher as input and returns a list of research papers that the researcher has authored
	 * @param researcher Instance of the Researcher class that represents a researcher who has authored one or more research papers
	 * @return A list of ResearchPaper objects
	 */
	public static List<ResearchPaper> printPapers(Researcher researcher){
		List<ResearchPaper> papers = SaveData.INSTANCE.getResearchPapers().stream().filter(n->n.getAuthors().contains(researcher)).collect(Collectors.toList());
		return papers;
	}
}