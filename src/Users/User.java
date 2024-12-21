package Users;

import java.io.Serializable;
import java.util.Objects;
import utilites.SaveData;

public class User implements Serializable, Comparable<User> {
    protected Integer id;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String password;

    // Конструкторы
    public User() {}
    
    public User(String email) {
        this.email = email;
    }

    
    
    
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
   

    public User(Integer id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Метод для изменения пароля
    public boolean changePassword(String newPassword) {
        if (newPassword != null && !newPassword.isEmpty()) {
            this.password = newPassword;
            SaveData.INSTANCE.getLoginInfo().replace(getEmail(), newPassword);
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof User)) return false;
        User other = (User) obj;
        return Objects.equals(getEmail(), other.getEmail()) &&
                Objects.equals(getFirstName(), other.getFirstName()) &&
                Objects.equals(getPassword(), other.getPassword()) &&
                Objects.equals(getLastName(), other.getLastName());
    }

    
    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getFirstName(), getLastName());
    }

   
    @Override
    public int compareTo(User other) {
        if (this.id == null && other.id == null) return 0;
        if (this.id == null) return -1;
        if (other.id == null) return 1;
        return this.id.compareTo(other.id);
    }
}