package db;

import java.sql.*;

public class UserService {
    private final DatabaseManager dbManager;

    public UserService(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public void addUser(User user) throws SQLException {
        String query = "INSERT INTO USERS (username, password) VALUES (?, ?)";

        try (Connection conn = dbManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
        }
    }

    public void updateUserPassword(String username, String newPassword) throws SQLException {
        String query = "UPDATE USERS SET password = ? WHERE username = ?";

        try (Connection conn = dbManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, newPassword);
            ps.setString(2, username);
            ps.executeUpdate();
        }
    }

    public void deleteUser(String username) throws SQLException {
        String query = "DELETE FROM USERS WHERE username = ?";

        try (Connection conn = dbManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            int rowsAffected = ps.executeUpdate();
            System.out.println("Удалено строк: " + rowsAffected);

            if (!conn.getAutoCommit()) {
                conn.commit();
            }
        }
    }
}