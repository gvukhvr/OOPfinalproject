package Course;

import java.io.Serializable;
import java.util.Objects;


public class Time implements Serializable{
	private int hour;
	private int min;
	
	public Time() {
		
	}
	
	
	public Time(int hour, int min) {
		this.min=min;
		this.hour = hour;
	}

	public String toString() {
		return this.hour+ ":"+this.min;
	}
	@Override
	public int hashCode() {
		return Objects.hash(hour, min);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Time other = (Time) obj;
		return hour == other.hour && min == other.min;
	}
	
	
	public static Time parse(String timeString) {
        String[] parts = timeString.split(":");
        if (parts.length == 2) {
            try {
                int hour = Integer.parseInt(parts[0]);
                int min = Integer.parseInt(parts[1]);
                return new Time(hour, min);
            } catch (NumberFormatException e) {
                System.out.println("Invalid time format. Please enter time in HH:mm format.");
            }
        }
        return null;
    }
}
