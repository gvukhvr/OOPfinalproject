package utilites;

import java.io.Serializable;
import java.util.Date;

import Users.Employee;

public class Orders implements Serializable {
	private Integer id;
	private Date date;
	private String description;
	private Employee from;
	private AcceptType isAccepted;

	/**
	 * Default empty constructor for the Orders class
	 */
	public Orders() {
		this.setId(SaveData.nextId());
		this.isAccepted=AcceptType.Undone;
		this.date=new Date();
	}
	
	
	public Orders(Employee from,String description) {
		this.setId(SaveData.nextId());
		this.description=description;
		this.date=new Date();
		this.from=from;
		this.isAccepted=AcceptType.Undone;
	}
	private void setId(int nextIdOrder) {
		this.id=nextIdOrder;
		
	}
	public String toString() {
		return "id: "+this.id +", sender: "+from.getFirstName()+", date: "+date+", description: "+description+", Acctive: "+isAccepted;

	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Employee getFrom() {
		return from;
	}
	public void setFrom(Employee from) {
		this.from = from;
	}
	public AcceptType getIsAccepted() {
		return isAccepted;
	}
	public void setIsAccepted(AcceptType isAccepted) {
		this.isAccepted = isAccepted;
	}
	public void setAccept() {
		this.isAccepted=AcceptType.Done;
	}
	public Integer getId() {
		return id;
	}

	

}
