/*
Create a Java program that connects to a MySQL database college_db.

The table students contains:

id
name
marks
Task:
Fetch all student records
Display only students who scored above average marks
Also print total number of students processed
Hint:

Think in steps:

Fetch all data using ResultSet
First calculate average marks
Then filter while reading result
*/
package JavaIgniteDay13;
import java.sql.*;

public class Student_Database {
	 public static void main(String[] args) {
	    	// TODO Auto-generated method stub
	        String url = "jdbc:mysql://localhost:3306/college_db";
	        String username = "root";
	        String password = "root123";

	        try {

	            // Load Driver
	            Class.forName("com.mysql.cj.jdbc.Driver");

	            // Establish Connection
	            Connection con = DriverManager.getConnection(url, username, password);

	            
	            String query = "SELECT * FROM students";

	            Statement st = con.createStatement();
	            ResultSet rs = st.executeQuery(query);

	            double totalMarks = 0;
	            int count = 0;

	            
	            while (rs.next()) {totalMarks += rs.getDouble("marks");
	                count++;
	            }

	            double average = totalMarks / count;

	            System.out.println("Average Marks = " + average);
	            System.out.println();

	            // Execute query again for filtering
	            rs = st.executeQuery(query);

	            System.out.println("Students Scoring Above Average:");
	            System.out.println("--------------------------------");

	            while (rs.next()) {

	                int id = rs.getInt("id");
	                String name = rs.getString("name");
	                double marks = rs.getDouble("marks");

	                if (marks > average) {
	                    System.out.println("ID: " + id +", Name: " + name +", Marks: " + marks);
	                }
	            }

	            System.out.println("\nTotal Students Processed: " + count);

	            rs.close();
	            st.close();
	            con.close();

	        } catch (ClassNotFoundException e) {
	            System.out.println("MySQL Driver Not Found");
	        } catch (SQLException e) {
	            System.out.println("Database Error: " + e.getMessage());
	        }
	    }

}
