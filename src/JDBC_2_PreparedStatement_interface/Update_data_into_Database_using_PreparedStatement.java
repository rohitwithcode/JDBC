package JDBC_2_PreparedStatement_interface;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Update_data_into_Database_using_PreparedStatement {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","CoderRohit","Radhey@725386");

            String query="update students set name=?,age=?,marks=? where id=?";

            PreparedStatement pstmt=con.prepareStatement(query);

            System.out.println("Enter your name : ");
            String name=scanner.nextLine();

            System.out.println("Enter your age : ");
            int age=scanner.nextInt();

            System.out.println("Enter your marks : ");
            double marks=scanner.nextDouble();

            System.out.println("Enter your id : ");
            int id=scanner.nextInt();

            pstmt.setString(1,name);
            pstmt.setInt(2,age);
            pstmt.setDouble(3,marks);
            pstmt.setInt(4,id);

            int update=pstmt.executeUpdate();

            if (update>0){
                JOptionPane.showMessageDialog(null,"Update Successful");
            }
            else {
                JOptionPane.showMessageDialog(null,"Update not Successful because id not found");
            }

            con.close();
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
