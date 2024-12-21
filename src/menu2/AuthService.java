package menu2;

import Users.*;
import utilites.SaveData;
import java.util.Scanner;

public class AuthService {

    private SaveData saveData;

    public AuthService() {
        this.saveData = SaveData.INSTANCE;
    }

    public User authenticateUser(Scanner scanner) {
        System.out.println("Введите ваш ID: ");
        String id = scanner.nextLine();

        System.out.println("Введите ваш пароль: ");
        String password = scanner.nextLine();

        User user = saveData.getUserById(id);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Авторизация успешна!");
            return user;
        }
        System.out.println("Неверный ID или пароль.");
        return null;
    }
}
