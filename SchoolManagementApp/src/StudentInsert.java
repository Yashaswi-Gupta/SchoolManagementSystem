import java.sql.*;
import java.util.ArrayList;

public class StudentInsert {
    public static boolean insertSt(Student s){
        boolean flag = false;
        try{
            Connection con = ConnectionProvider.CreateConnection();
            String q = "insert into student (id,sname,mobileNo) values (?,?,?)";

            //prepared statement
            PreparedStatement pstmt = con.prepareStatement(q);

            //set the value of parameter
            pstmt.setInt(1,s.getId());
            pstmt.setString(2, s.getSname());
            pstmt.setString(3,s.getMobileNumber());



            // to execute the pstmt
            pstmt.executeUpdate();
            flag=true;
        }
        catch (SQLIntegrityConstraintViolationException e){
            System.out.println("This id is already exist. !!!");
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return flag;
    }

    public static void display(){
        try{
            Connection con = ConnectionProvider.CreateConnection();
            // String q = "insert into students (sname) values (?)";
            String q = "select * from student;";
            Statement st = con.createStatement();
            ResultSet set = st.executeQuery(q);
            System.out.println("-".repeat(30));
            while (set.next()){
                int id = set.getInt(1);
                String name = set.getString(2);
                String mobileNumber = set.getString(3);
                int sfee = set.getInt(4);
                String courses = set.getString(5);
                System.out.printf("ID: %d, Name: %s, Mobile Number: %s, Fee: %d, Courses: %s%n", id, name, mobileNumber, sfee, courses);
//                System.out.println(id+". "+name+" "+mobileNumber);
                System.out.println("-".repeat(30));
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void displayById(int id){
        try {
            Connection con = ConnectionProvider.CreateConnection();
            String query = "SELECT * FROM student WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            // Check if the result set is empty
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No student found with ID: " + id);
            } else {
                // Process the result set
                while (resultSet.next()) {
                    // Assuming the student table has columns id, sname, and mobileNumber
                    int sid = resultSet.getInt("id");
                    String name = resultSet.getString("sname");
                    String mobileNumber = resultSet.getString("mobileNo");
                    int sfee = resultSet.getInt("fee");
                    String courses = resultSet.getString("courses");
                    System.out.printf("ID: %d, Name: %s, Mobile Number: %s, Fee: %d, Courses: %s%n", sid, name, mobileNumber, sfee, courses);
                }
            }

            System.out.println("-".repeat(30));

            // Close resources
            resultSet.close();
            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // method for delete the student data

    public static int  deleteSt(int a){
        int temp=0;
        try{
            Connection con = ConnectionProvider.CreateConnection();

            String q = "DELETE FROM student WHERE id=?;";

            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setInt(1,a);
            temp=pstmt.executeUpdate();


        }

        catch (Exception e){

            e.printStackTrace();
        }

        return temp;
    }

    public static int updateStudent(Student s){
        int temp = 0;
        try{
            Connection con = ConnectionProvider.CreateConnection();
            String qr = "update student set sname=?,mobileNo=? where id=?";


            //prepared statement
            PreparedStatement pstmt = con.prepareStatement(qr);
            pstmt.setString(1,s.getSname());
            pstmt.setString(2, s.getMobileNumber());
            pstmt.setInt(3,s.getId());

            // to execute the pstmt
            temp= pstmt.executeUpdate();

        }
        catch (SQLIntegrityConstraintViolationException e){
            System.out.println("This id is already exist. !!!");
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return temp;
    }

    public static int feeUpdateStudent(int id, int fee){
        int temp = 0;
        try{
            Connection con = ConnectionProvider.CreateConnection();
            String qr = "update student set fee=? where id=?";


            //prepared statement
            PreparedStatement pstmt = con.prepareStatement(qr);
            pstmt.setInt(1,fee);
            pstmt.setInt(2,id);

            // to execute the pstmt
            temp= pstmt.executeUpdate();

        }
        catch (SQLIntegrityConstraintViolationException e){
            System.out.println("This id is already exist. !!!");
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return temp;
    }

    public static String coursesToString( ArrayList<String> courses) {
        return String.join(",", courses);
    }
    public static int courseUpdateStudent(int id, ArrayList<String> courses){
        int temp = 0;
        try{
            Connection con = ConnectionProvider.CreateConnection();
            String qr = "update student set courses=? where id=?";


            //prepared statement
            PreparedStatement pstmt = con.prepareStatement(qr);
            pstmt.setString(1, coursesToString(courses));
            pstmt.setInt(2,id);

            // to execute the pstmt
            temp= pstmt.executeUpdate();

        }
        catch (SQLIntegrityConstraintViolationException e){
            System.out.println("This id is already exist. !!!");
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return temp;
    }


}
