package JDBC_1_Statement_interface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Delete_data_from_Database_using_Statement_interface {

    private static  final String url="jdbc:mysql://localhost:3306/mydb";
    private static final String user="CoderRohit";
    private static final String pass="Radhey@725386";

    public static void main(String[] args) {

        try {
            // Load the Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create a Connection
            Connection con= DriverManager.getConnection(url,user,pass);

            // create String
            String query=String.format("delete from students where id=2");

            Statement stmt=con.createStatement();
            int rows=stmt.executeUpdate(query);

            if (rows>0){
                System.out.println("Data Deleted Successfully...");
            }
            else {
                System.out.println("Data not Deleted !");
            }

        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
