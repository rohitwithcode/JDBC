import java.sql.*;

public class Retrieve_and_Select_data_from_database {

//    private static final String url="jdbc:mysql://localhost:3306/coder";
//    private static final String user="CoderRohit";
//    private static final String password="Radhey@725386";

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","CoderRohit","Radhey@725386");
            Statement statement=con.createStatement();

            String query="select * from students";

            ResultSet set=statement.executeQuery(query);

            while (set.next()){
                System.out.println("Your data is below : ");

                int id=set.getInt(1);
                String name=set.getString("name");
                int age=set.getInt("age");
                double marks=set.getDouble(4);

                System.out.print("Your id is : "+id);
                System.out.print("\nYour name is : "+name);
                System.out.print("\nYour age is : "+age);
                System.out.print("\nYour marks is : "+marks);
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Driver not Found");
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}