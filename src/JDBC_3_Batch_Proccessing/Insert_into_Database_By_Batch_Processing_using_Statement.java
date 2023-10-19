package JDBC_3_Batch_Proccessing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Insert_into_Database_By_Batch_Processing_using_Statement {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","CoderRohit","Radhey@725386");

            Statement statement;
            statement=connection.createStatement();
            while (true){
                System.out.println("Enter your name ");
                String name=scanner.next();

                System.out.println("Enter your age ");
                int age=scanner.nextInt();

                System.out.println("Enter your marks ");
                double marks=scanner.nextDouble();

                System.out.println("Enter more data (Y/N)");
                String choice=scanner.next();

                String query=String.format("insert into students(name,age,marks) values('%s',%d,%f)",name,age,marks);
                statement.addBatch(query);

                if(choice.toUpperCase().equals("N")){
                    break;
                }
            }
            int [] insert=statement.executeBatch();

             for (int i=0;i<insert.length;i++){
                 if (insert[i]==0){
                     System.out.println("Query : "+i+1+" "+" not executed successfully");
                 }
             }

        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
