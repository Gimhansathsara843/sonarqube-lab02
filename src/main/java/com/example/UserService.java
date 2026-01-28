package main.java.com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(UserService.class);

    // SECURITY ISSUE (still): Hardcoded credentials
    private String password = "admin123";

    // Fixed: specific exception + resources closed
    public void findUser(String username) throws SQLException {

        String query =
            "SELECT * FROM users WHERE name = '" + username + "'";

        try (Connection conn =
                 DriverManager.getConnection(
                     "jdbc:mysql://localhost/db",
                     "root",
                     password);
             Statement st = conn.createStatement()) {

            st.executeQuery(query);

            LOGGER.info("User lookup executed for username: {}", username);
        }
    }

    // Fixed: System.out replaced with logger
    public void notUsed() {
        LOGGER.debug("This method is currently not used");
    }

    // Fixed: specific exception + resources closed
    public void deleteUser(String username) throws SQLException {

        String query =
            "DELETE FROM users WHERE name = '" + username + "'";

        try (Connection conn =
                 DriverManager.getConnection(
                     "jdbc:mysql://localhost/db",
                     "root",
                     password);
             Statement st = conn.createStatement()) {

            st.execute(query);

            LOGGER.warn("User deleted: {}", username);
        }
    }
}
