import db.DatabaseManager;
import db.User;
import db.UserRepository;
import db.UserService;

public class Main {
    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();
        UserRepository userRepo = new UserRepository(dbManager);
        UserService userService = new UserService(dbManager);

        try {
            // Добавление пользователя
            //userService.addUser(new User("newUser", "securePass123"));

            // Получение всех пользователей
            System.out.println("All users:");
            userRepo.getAllUsers().forEach(user ->
                    System.out.println(user.getUsername() + ": " + user.getPassword())
            );

            // Обновление пароля
            userService.updateUserPassword("newUser", "evenMoreSecure456");

            // Удаление пользователя
            userService.deleteUser("newUser");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}