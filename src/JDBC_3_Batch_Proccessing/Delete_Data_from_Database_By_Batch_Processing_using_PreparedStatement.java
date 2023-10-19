package JDBC_3_Batch_Proccessing;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Delete_Data_from_Database_By_Batch_Processing_using_PreparedStatement {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","CoderRohit","Radhey@725386");

            String query="delete from students where id=?";

            PreparedStatement preparedStatement=connection.prepareStatement(query);

            while (true){

                System.out.println("Enter your id : ");
                int id =scanner.nextInt();

                System.out.println("Do you want to delete more data from database (Y/N)");
                String choice=scanner.next();

                preparedStatement.setInt(1,id);

                preparedStatement.addBatch();

                if (choice.toUpperCase().equals("N")){
                    break;
                }
            }
            int [] delete=preparedStatement.executeBatch();

            for (int i=0;i<delete.length;i++){
                if(delete[i]==0){
                    System.out.println("Query "+i+1+" is not deleted");
                }
            }
            JOptionPane.showMessageDialog(null,"Data Deleted Successfully");
            connection.close();

        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
}
