package JDBC_4_Transaction_Handling;

import java.sql.*;
import java.util.Scanner;

public class Transaction {
    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/Transaction","CoderRohit","Radhey@725386");
            connection.setAutoCommit(false);
            String queryDebit="update accounts set balance=balance-? where account_number=?";
            String creditQuery="update accounts set balance=balance+? where account_number=?";

            PreparedStatement debitPreparedStatement=connection.prepareStatement(queryDebit);
            PreparedStatement creditPreparedStatement=connection.prepareStatement(creditQuery);

            Scanner scanner=new Scanner(System.in);
            System.out.println("Enter amount : ");
            int amount=scanner.nextInt();

            debitPreparedStatement.setDouble(1,amount);
            debitPreparedStatement.setInt(2,101);

            creditPreparedStatement.setDouble(1,amount);
            creditPreparedStatement.setInt(2,102);
            int debit=debitPreparedStatement.executeUpdate();
            int credit=creditPreparedStatement.executeUpdate();
            if (balanceSufficient(connection,101,amount)){
               connection.commit();
                System.out.println("Transaction Successful");
            }
            else {
                connection.rollback();
                System.out.println("Transaction failed.Insufficient Balance");
            }
            debitPreparedStatement.close();
            creditPreparedStatement.close();
            connection.close();
            scanner.close();
        } catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public static boolean balanceSufficient(Connection con,int accountNumber,double amount){
        String query="select balance from accounts where account_number=?";
        try {
            PreparedStatement preparedStatement=con.prepareStatement(query);

            preparedStatement.setInt(1,accountNumber);

            ResultSet set=preparedStatement.executeQuery();
            if (set.next()){
                double currentBalance=set.getDouble("balance");
                if (amount>currentBalance){
                    return false;
                }else {
                    return true;
                }
            }
            set.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
