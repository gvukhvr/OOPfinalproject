package Students;

import java.io.Serializable;
import java.util.Vector;
/**
 * The StudentOrganization class includes information such as the organization name, head (student leader) and members
 * @author Code Symphony
 */
public class StudentOrganization implements Serializable{
	//Attributes specific to the StundentOrganization class
	private String studOrgName;
	private Student head;
	private Vector<Student> members;
	//Default constructor for the StudentOrganization class.
	public StudentOrganization() {
		
	}
    /**
     * Constructor to initialize a student organization with a given name and head (student leader).
     *
     * @param studOrgName The name of the student organization.
     * @param head        The head (student leader) of the student organization.
     */
	public StudentOrganization(String studOrgName, Student head) {
		this.studOrgName = studOrgName;
		this.head = head;
	}
    /**
     * Getter method to retrieve the name of the student organization.
     *
     * @return The name of the student organization.
     */
	public String getStudOrgName() {
		return this.studOrgName;
	}
    /**
     * Getter method to retrieve the head (student leader) of the student organization.
     *
     * @return The head (student leader) of the student organization.
     */
	public Student getHead() {
		return this.head;
	}
    /**
     * Getter method to retrieve the members of the student organization.
     *
     * @return A vector containing the members of the student organization.
     */
	public Vector<Student> getMembers() {
		return this.members;
	}
    /**
     * Setter method to set the name of the student organization.
     *
     * @param studOrgName The name to be set for the student organization.
     */
	public void setStudOrgName(String studOrgName) {
		this.studOrgName = studOrgName;
	}
    /**
     * Setter method to set the head (student leader) of the student organization.
     *
     * @param head The head (student leader) to be set for the student organization.
     */
	public void setHead(Student head) {
		this.head = head;
	}
    /**
     * Method to add a member to the student organization.
     *
     * @param member The student to be added as a member of the student organization.
     */
	public void addMember(Student member) {
		members.add(member);
	}
    /**
     * Method to remove a member from the student organization.
     *
     * @param member The student to be removed as a member of the student organization.
     */
	public void removeMember(Student member) {
		members.remove(member);
	}
    /**
     * Override of the toString method to provide a string representation of the student organization.
     *
     * @return A string representation of the student organization.
     */
	public String toString() {
		return "StudentOrganization [name=" + this.studOrgName + ", head=" + this.head + ", members=" + this.members + "]";
	}
}
