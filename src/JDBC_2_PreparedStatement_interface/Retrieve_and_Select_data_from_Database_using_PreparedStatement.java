package JDBC_2_PreparedStatement_interface;

import java.sql.*;

public class Retrieve_and_Select_data_from_Database_using_PreparedStatement {
    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","CoderRohit","Radhey@725386");

            String query="select * from students where id=?";

            PreparedStatement pstmt=con.prepareStatement(query);

            pstmt.setInt(1,8);

            ResultSet set=pstmt.executeQuery();

            if (set.next()){
                System.out.println("Your data given below :");
                String name=set.getString(2);
                int age=set.getInt("age");
                double marks= set.getDouble("marks");

                System.out.print("Name is : "+name);
                System.out.print("\nAge is : "+age);
                System.out.print("\nMarks is : "+marks);
            }
            else {
                System.out.println("Data not found");
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
