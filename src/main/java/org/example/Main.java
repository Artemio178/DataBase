import model.User;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserRepository repository = new UserRepository();

        try {
            // Добавляем нового пользователя
            repository.addUser("testUser", "testPassword");

            // Получаем всех пользователей
            List<User> users = repository.getAllUsers();

            // Выводим информацию о пользователях
            System.out.println("Список пользователей:");
            for (User user : users) {
                System.out.printf("ID: %d, Username: %s, Password: %s%n",
                        user.getId(), user.getUsername(), user.getPassword());
            }
        } finally {
            repository.close();
        }
    }
}

