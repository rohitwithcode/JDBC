package JDBC_3_Batch_Proccessing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Update_Data_in_Database_By_Batch_Processing_using_Prepared_Statement {
    private static final String  url="jdbc:mysql://localhost:3306/mydb";
    private static final String user="CoderRohit";
    private static final String password="Radhey@725386";
    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection= DriverManager.getConnection(url,user,password);

            String query="update students set name=?,age=?,marks=? where id=?";

            PreparedStatement preparedStatement=connection.prepareStatement(query);

            while (true){
                System.out.println("Enter your new name : ");
                String name=scanner.next();

                System.out.println("Enter your new age : ");
                int age=scanner.nextInt();

                System.out.println("Enter your new marks : ");
                double marks=scanner.nextDouble();

                System.out.println("Enter your id : ");
                int id=scanner.nextInt();

                System.out.println("Do you want update more data : (Y/N) ");
                String choice=scanner.next();

                preparedStatement.setString(1,name);
                preparedStatement.setInt(2,age);
                preparedStatement.setDouble(3,marks);
                preparedStatement.setInt(4,id);

                preparedStatement.addBatch();

                if (choice.toUpperCase().equals("N")){
                    break;
                }
            }
            int [] update=preparedStatement.executeBatch();

            for (int i=0;i<update.length;i++){
                if (update[i]==0){
                    System.out.println("Query "+(i+1)+" is not updated");
                }
            }

            connection.close();

        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
