package researcher;


import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import java.util.Date;
import java.util.Vector;



public class ResearchPaper implements Comparable<ResearchPaper>, Serializable{

		private String name;
		private Vector<Researcher> authors = new Vector<Researcher>();
		private int pages;
		private Date date;
		private int citations;
	
		
	
		public ResearchPaper() {
			
		}
		
	
		public ResearchPaper(String name, int pages, int citations) {
			this.name = name;
			this.pages = pages;
			this.date = new Date();
			this.citations = citations;
		
		}
		
		public String getName() {
			return this.name;
		}
		
		public Vector<Researcher> getAuthors() {
			return authors;
		}
		public void addAuthor(Researcher r) {
			authors.add(r);
		}
		
		public int getPages() {
			return this.pages;
		}
		
		public Date getDate() {
			return this.date;
		}
		
		public int getCitations() {
			return this.citations;
		}
		
	
		
		public void setName(String name) {
			this.name = name;
		}
		
		public void setPages(int pages) {
			this.pages = pages;
		}
		
		public void setDate(Date date) {
			this.date = date;
		}
		
		public void setCitations(int citations) {
			this.citations = citations;
		}
	
		

		public String getCitation(Format format) {
			if(format == Format.PlainTeX) {
				return String.format("%s et al., \"%s\", %tY, pages: %d, doi: %s", this.authors, this.name, this.date, this.pages);
			}
			if (format == Format.BibTeX) {
			    return String.format("@article{author={%s}, title={%s}, year={%tY}, pages={%d}, authors={%s}, name={%s}, date={%s}}",
			                         this.authors,  this.name, this.date, this.pages, this.authors, this.date);
			}

			return "Unsupported format";
		}
		
		public String toString() {	
			return "ResearchPaper's name: " + this.name + ", authors: " + this.authors + ", pages: " + this.pages + ", date: " + this.date + ", citations: " + this.citations ;
		}
	
		@Override
	    public int compareTo(ResearchPaper r) {
	        // Sort by citations by default
	        return Integer.compare(r.getCitations(), this.getCitations()); // Descending order
	    }

	    public static Comparator<ResearchPaper> sortByDate = new Comparator<ResearchPaper>() {
	        @Override
	        public int compare(ResearchPaper p1, ResearchPaper p2) {
	            return p2.getDate().compareTo(p1.getDate());  // Sort by descending date
	        }
	    };

	    public static Comparator<ResearchPaper> sortByPages = new Comparator<ResearchPaper>() {
	        @Override
	        public int compare(ResearchPaper p1, ResearchPaper p2) {
	            return Integer.compare(p2.getPages(), p1.getPages());  // Sort by descending pages
	        }
	    };

	    // Method to print papers sorted by different criteria
	    public static void printPapers(List<ResearchPaper> papers, Comparator<ResearchPaper> comparator) {
	        Collections.sort(papers, comparator);
	        for (ResearchPaper paper : papers) {
	            System.out.println(paper);
	        }
	    }
	}
