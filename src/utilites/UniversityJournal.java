package utilites;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import researcher.ResearchPaper;

public class UniversityJournal implements Observable{
	
private List<Observer> observers = new ArrayList<Observer>(); 
	
	
    public UniversityJournal() {

    }
    

    @Override
	public String toString() {
		return "UniversityJournal " + "\nJournal observers:" + SaveData.INSTANCE.getObservers() + "\nAll Papers: " + SaveData.INSTANCE.getResearchPapers() + "\nLatest Paper: " + getLatest();
	}
  

   
	@Override
    public void subscribe(Observer observer) {
        observers.add(observer);
    }

	
    @Override
    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

   
    @Override
	public void notifyObservers() throws FileNotFoundException {
		for(Observer o: observers) {
			o.update();
		}
		
	}
   

    
    public void setNewPapers(ResearchPaper newPaper) throws IOException {
    	SaveData.INSTANCE.addResearchPaper(newPaper);
        notifyObservers();
    }
	
   
    public ResearchPaper getLatest() {
        if (SaveData.INSTANCE.getResearchPapers().isEmpty()) {
            return null; // No papers available
        }

        ResearchPaper latest = SaveData.INSTANCE.getResearchPapers().get(SaveData.INSTANCE.getResearchPapers().size()-1);

        for (ResearchPaper paper : SaveData.INSTANCE.getResearchPapers()) {
            if (paper.getDate().compareTo(latest.getDate()) > 0) {
                latest = paper;
            }
        }
        
        return latest;
    }


	
}
