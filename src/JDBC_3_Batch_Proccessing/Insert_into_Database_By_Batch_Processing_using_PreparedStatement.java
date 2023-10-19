package JDBC_3_Batch_Proccessing;

/*
        STEPS TO ADD MORE ENTRIES IN DATABASE USING BATCH PROCESSING

               1.Load Driver
               2.Create Connection
               3.Create Query
               4.Get Statement
               5.statement.addBatch()
               6.statement.executeBatch()
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Insert_into_Database_By_Batch_Processing_using_PreparedStatement {
    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","CoderRohit","Radhey@725386");

            String query="insert into students(name,age,marks) values(?,?,?)";

            PreparedStatement pstmt=con.prepareStatement(query);

            while (true) {

                System.out.println("Enter your name : ");
                String name=scanner.next();

                System.out.println("Enter your age : ");
                int age=scanner.nextInt();

                System.out.println("Enter your marks : ");
                double marks=scanner.nextDouble();

                System.out.println("Do you want insert more Data(Y/N)");
                String choice=scanner.next();

                pstmt.setString(1,name);
                pstmt.setInt(2,age);
                pstmt.setDouble(3,marks);

                pstmt.addBatch();

                if (choice.toUpperCase().equals("N")){
                    break;
                }
            }

            int [] insert=pstmt.executeBatch();

            for (int i=0;i<insert.length;i++){
                if (insert[i]==0){
                    System.out.println("Query : "+(i+1)+" not executed successfully");
                }
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
