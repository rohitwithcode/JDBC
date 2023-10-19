package JDBC_2_PreparedStatement_interface;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Delete_Data_from_Database_using_PreparedStatement {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","CoderRohit","Radhey@725386");

            String query="delete from students where id=?";

            PreparedStatement pstmt=con.prepareStatement(query);

            System.out.println("Enter your id  : ");
            int id=scanner.nextInt();

            pstmt.setInt(1,id);

            int delete=pstmt.executeUpdate();

            if (delete>0){
                JOptionPane.showMessageDialog(null,"Delete Successful");
            }
            else {
                JOptionPane.showMessageDialog(null,"Delete not successful because id not found");
            }

            con.close();

        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
