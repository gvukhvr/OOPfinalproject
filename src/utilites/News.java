package utilites;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.List;

import java.util.ArrayList;
import teacher.UrgencyLevel;

public class News implements Serializable {
    private static final long serialVersionUID = 7367991649511446746L;
	private String title;
	private UrgencyLevel priority;
	private String text;
	private Date date;
	private boolean pinned;  
    private List<String> comments;

	/**
	 * Default empty constructor for the News class
	 */
	public News() {
		
	}
	public News(String title, UrgencyLevel priority, String text) {
	    this.title = title;
	    this.priority = priority;
	    this.text = text;
	    this.date = new Date();  
	    this.pinned = title.equalsIgnoreCase("Research");
	    this.comments = new ArrayList<>();
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(date, priority, text, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		News other = (News) obj;
		return Objects.equals(date, other.date) && priority == other.priority && Objects.equals(text, other.text)
				&& Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "News " + '\n' + "title: " + title + '\n' + "priority: " + priority + '\n' + "date: " + date + '\n' + "text: " + text;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public UrgencyLevel getPriority() {
		return priority;
	}

	public void setPriority(UrgencyLevel priority) {
		this.priority = priority;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isPinned() {
        return pinned;
    }

    public List<String> getComments() {
        return comments;
    }

    public void addComment(String comment) {
        comments.add(comment);
    }

    public void setPinned(boolean pinned) {
        this.pinned = pinned;
    }

}

