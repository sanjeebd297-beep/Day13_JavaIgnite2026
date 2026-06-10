/*
Create a Java program connected to database library_db.

Table:

book_id
title
available_copies
Task:
Allow user to input a book ID
Check availability
If available:
Reduce copy count by 1
Print “Book Issued”
Else:
Print “Not Available”
Hint:

Think:

SELECT → check condition
UPDATE → modify data
Use if-else inside Java after ResultSet
*/
package JavaIgniteDay13;
import java.sql.*;
import java.util.Scanner;

public class Library_Borrow_System {
	public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String url = "jdbc:mysql://localhost:3306/library_db";
        String username = "root";
        String password = "root123";

        try {

            // Load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish Connection
            Connection con = DriverManager.getConnection(url, username, password);

            System.out.print("Enter Book ID: ");
            int bookId = sc.nextInt();

            // SELECT Query
            String selectQuery ="SELECT available_copies FROM books WHERE book_id = ?";

            PreparedStatement psSelect = con.prepareStatement(selectQuery);

            psSelect.setInt(1, bookId);

            ResultSet rs = psSelect.executeQuery();

            if (rs.next()) {

                int copies = rs.getInt("available_copies");

                if (copies > 0) {

                    // UPDATE Query
                    String updateQuery ="UPDATE books SET available_copies = available_copies - 1 WHERE book_id = ?";

                    PreparedStatement psUpdate = con.prepareStatement(updateQuery);

                    psUpdate.setInt(1, bookId);

                    int rows = psUpdate.executeUpdate();

                    if (rows > 0) {
                        System.out.println("Book Issued");
                    }

                } 
                else {
                    System.out.println("Not Available");
                }

            }
            else {
                System.out.println("Book ID not found");
            }

            con.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }

        sc.close();
    }
}
