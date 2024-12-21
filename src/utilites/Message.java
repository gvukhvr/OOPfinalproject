package utilites;

import java.io.Serializable;
import java.util.Date;
import Users.Employee;

public class Message implements Serializable {

    private Employee sender;
    private Employee receiver;
    private String title;
    private Date date;

    /**
     * Default empty constructor for the Message class
     */
    public Message() {}

    public Message(Employee receiver, String title) {
        this.setReceiver(receiver);
        this.date = new Date();
        this.title = title;
    }

    @Override
    public String toString() {
        return "From: " + sender.getFirstName() + " " + sender.getLastName() +
                " To: " + receiver.getFirstName() + " " + receiver.getLastName() +
                "\n" + title + "\n" + date;
    }

    public Employee getSender() {
        return sender;
    }

    public void setSender(Employee sender) {
        this.sender = sender;
    }

    public Employee getReceiver() {
        return receiver;
    }

    public void setReceiver(Employee receiver) {
        this.receiver = receiver;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
