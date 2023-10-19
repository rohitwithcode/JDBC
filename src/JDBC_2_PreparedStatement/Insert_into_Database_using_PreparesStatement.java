package JDBC_2_PreparedStatement;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Insert_into_Database_using_PreparesStatement {
    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","CoderRohit","Radhey@725386");

            String query="insert into students(name,age,marks) values(?,?,?)";

            PreparedStatement pstmt=con.prepareStatement(query);

//            Set the values left to right

            pstmt.setString(1,"Rohit Yadav");
            pstmt.setInt(2,18);
            pstmt.setDouble(3,78.8);

            int return_value=pstmt.executeUpdate();

            if (return_value > 0){
                JOptionPane.showMessageDialog(null,"Data insert successfully");
            }
            else {
                JOptionPane.showMessageDialog(null,"Data not insert successfully");
            }
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }catch (NumberFormatException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
