package main.java.com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private String getPassword() {
        return System.getProperty("db.password", "admin123");
    }

    private String getDbUrl() {
        return System.getProperty("db.url", "jdbc:mysql://localhost/db");
    }

    private String getDbUser() {
        return System.getProperty("db.user", "root");
    }

    public void findUser(String username) throws SQLException {
        String query = "SELECT id, name, email FROM users WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(getDbUrl(), getDbUser(), getPassword());
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    logger.info("User found: {}", username);
                } else {
                    logger.info("User not found: {}", username);
                }
            }

        } catch (SQLException e) {
            // OPTION 1: Rethrow with context (no logging here)
            throw new SQLException("Failed to find user with username: " + username, e);
        }
    }

    public void deleteUser(String username) throws SQLException {
        String query = "DELETE FROM users WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(getDbUrl(), getDbUser(), getPassword());
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);
            int rowsAffected = pstmt.executeUpdate();

            logger.warn("Deleted {} user(s) with username: {}", rowsAffected, username);

        } catch (SQLException e) {
            // OPTION 1: Rethrow with context (no logging here)
            throw new SQLException("Failed to delete user with username: " + username, e);
        }
    }

}