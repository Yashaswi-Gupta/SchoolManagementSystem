import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
public class StudentActivities {
    public static void activities(int choice){
        Scanner br = new Scanner(System.in);
        System.out.println("WELCOME TO STUDENT MANAGEMENT APP");
        while(true){
            if(choice == 1){
                System.out.println("PRESS 1 TO ADD STUDENT.");
                System.out.println("PRESS 2 TO DELETE STUDENT.");
                System.out.println("PRESS 3 TO DISPLAY STUDENT.");
                System.out.println("PRESS 4 TO UPDATE STUDENT.");
                System.out.println("PRESS 5 TO FEE UPDATE FOR STUDENT.");
                System.out.println("PRESS 6 TO COURSES UPDATE FOR STUDENT.");
                System.out.println("PRESS 8 TO REGISTER A STUDENT");
//                System.out.println("PRESS 9 TO COURSES UPDATE FOR STUDENT.");

            }else {System.out.println("PRESS 1 TO ADD YOUR DATA.");
                System.out.println("PRESS 7 TO DISPLAY YOUR DATA.");
            }

            System.out.println("PRESS 9 TO EXIT APP...");
            // int c = Integer.parseInt(br.readLine());
            int c = br.nextInt();
            if(c==1){
                // add student
                System.out.println("Enter the id : ");
                int id = br.nextInt();
                br.nextLine();
                System.out.println("Enter the name : ");
                String sname=br.nextLine();
                System.out.println("Enter the mobile number : ");
                String mobileNumebr = br.nextLine();

                // object of student
                Student ss = new Student(id,sname,mobileNumebr);
                boolean answer = StudentInsert.insertSt(ss);
                if(answer){
                    System.out.println("Successfully student data added...\n");
                }
                else{
                    System.out.println("Something went wrong....\n");
                }
            }

            else if(c==2) {
                //delete student
                System.out.println("Enter the id : ");
                int a = br.nextInt();
                int answer = StudentInsert.deleteSt(a);
                if(answer>0){
                    System.out.println(a + " id successfully deleted...");
                }
                else{
                    System.out.println("This id doesn't exist...\n");
                }
            }
            else if(c==3){
                // to display student
                StudentInsert.display();
            }
            else if(c==4){
                System.out.println("Enter the id : ");
                int id=br.nextInt();
                br.nextLine();
                System.out.println("Enter the name : ");
                String sname = br.nextLine();
                System.out.println("Enter the mobile number :");
                String mobileNo = br.nextLine();
                Student s = new Student(id,sname,mobileNo);
                int update = StudentInsert.updateStudent(s);
                if(update>0){
                    System.out.println(id + " id updated successfully...");
                }
                else{
                    System.out.println("id " + id + " Doesn't exist.!!! \n");
                }
            }
            else if(c==5){
                System.out.println("Enter the id : ");
                int id=br.nextInt();
                br.nextLine();
                System.out.println("Enter the fee : ");
                int fee = br.nextInt();
//                Student s = new Student(id,sname,mobileNo);
                int update = StudentInsert.feeUpdateStudent(id, fee);
                if(update>0){
                    System.out.println(id + " id updated successfully...");
                }
                else{
                    System.out.println("id " + id + " Doesn't exist.!!! \n");
                }
            }
            else if(c==6){
                System.out.println("Enter the id : ");
                int id=br.nextInt();
                br.nextLine();
//                System.out.println("Enter the courses : ");
                ArrayList<String> courses = new ArrayList<>();
                System.out.println("Enter course names. Type 'q' to quit.");
                String input;

                while (true) {
                    System.out.print("Enter course: ");
                    input = br.nextLine();

                    if (input.equalsIgnoreCase("q")) {
                        break;
                    }

                    courses.add(input);
                }

                int update = StudentInsert.courseUpdateStudent(id, courses);
                if(update>0){
                    System.out.println(id + " id updated successfully...");
                }
                else{
                    System.out.println("id " + id + " Doesn't exist.!!! \n");
                }
            }
            else if(c==7){
                // to display student
                System.out.println("Enter the id : ");
                int id=br.nextInt();
                br.nextLine();
                StudentInsert.displayById(id);
            }
            else if(c==8) {
                // register student
                System.out.println("Enter the username: ");
                String username = br.nextLine();
                System.out.println("Enter the password: ");
                String password = br.nextLine();
                String hashedPassword = hashPassword(password);

                boolean success = registerAdmin(username, hashedPassword);
                if (success) {
                    System.out.println("Admin registered successfully.");
                } else {
                    System.out.println("Failed to register admin.");
                }
            }
            else if(c==9) {
                // exit
                break;
            }
            else {
                System.out.println("Invalid Input !!!");
            }
        }
    }
    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing algorithm not found.");
        }
    }

    private static boolean registerAdmin(String username, String hashedPassword) {
        try {
            Connection con = ConnectionProvider.CreateConnection();
            String query = "INSERT INTO admin (username, password) VALUES (?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, hashedPassword);
            int rowsInserted = pstmt.executeUpdate();
            pstmt.close();
            con.close();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
