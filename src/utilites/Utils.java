package utilites;

import java.security.SecureRandom;
import java.util.List;

import Students.Student;
import Users.User;

public class Utils {
	public static String generatePassword() {
		String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
      
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();
        
        for(int i = 0; i < 10; i++) {
        	int index = random.nextInt(combinedChars.length());
        	password.append(combinedChars.charAt(index));
        }
        return password.toString();
	}
	
	public static String generateCorparateEmail(User u) {
		String email = "@kbtu.kz";
		if(u instanceof Student) return u.getFirstName().toLowerCase().charAt(0)+"_"+u.getLastName().toLowerCase()+email;
		
		return u.getFirstName().toLowerCase().charAt(0)+"."+u.getLastName().toLowerCase()+email;
	}
	
	public static Boolean printList(List list) {
		if(!list.isEmpty()) {
		for(int i=0; i<list.size(); i++)
			System.out.println(i+1+ ")" +list.get(i));
		return true;
	}
	return false;
}
}
