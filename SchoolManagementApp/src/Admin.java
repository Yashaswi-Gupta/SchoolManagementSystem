import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.Scanner;
import java.util.Scanner;

public class Admin {
    public boolean login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Admin Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Admin Password: ");
        String password = scanner.nextLine();

        return authenticate(username, password);

    }

    private boolean authenticate(String username, String password) {
        boolean isAuthenticated = false;
        String query = "SELECT * FROM admin WHERE username = ? AND password = ?";

        try (Connection connection = ConnectionProvider.CreateConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    isAuthenticated = true;
                    System.out.println("Admin logged in successfully!");
                } else {
                    System.out.println("Invalid credentials. Try again.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }

        return isAuthenticated;
    }
    public void Menu(int choice) {
        if(choice == 1){
            System.out.println("Welcome, Admin!");

        }
        else{
            System.out.println("Welcome, User!");
        }
        // Admin-specific functionalities can be added here.
//        StudentActivities s =  new StudentActivities();
        StudentActivities.activities(choice);
    }
}
