package sylverlogin;

import java.util.Scanner;
import java.sql.SQLException;

public class SylverLogin {

    public static void main(String[] args) throws SQLException
    {
       Scanner sc = new Scanner(System.in);
       System.out.println("Welcome to the Sylver Android companion app! Do you have an account?");
       String question = sc.next();
       SylverLoginData sl = new SylverLoginData();
       SylverLoginData sld = new SylverLoginData();
       Integer ID = 0;
       String username;
       String password;
       String password2 = "";
       sld.connect();
       
       if(question.equals("yes"))
       {
           System.out.println("Please sign in.");
           username = sc.next();
           password = sc.next();
           sld.VerifyLogin(ID, username, password);
       }
       
       else if(question.equals("no"))
       {
           ID = sl.GenerateID();
           sl.getIDs().add(ID);
           System.out.println("Thank you for creating an account! Your unique id is " + ID + ". Please keep this id in a safe place, since you will need it to login.\nPlease enter a username and password in that order.");
           username = sc.next();
           password = sc.next();
           
           sld.IsUnique(ID, username, password);
           
           while(!password.equals(password2))
           {
           System.out.println("Please re enter your password.");
           password2 = sc.next();
           

           if(password.equals(password2))
           {
            sld.AddData(ID, username, password);
            
            System.out.println("Account successfully created!");
            break;
           }
           
           else
           {
               continue;
           }

           }
           
       }
       
    }
    

    
}



//sources: https://www.geeksforgeeks.org/generating-random-numbers-in-java/, https://www.homeandlearn.co.uk/java/connect_to_a_database_using_java_code.html, https://dev.mysql.com/doc/connector-j/5.1/en/connector-j-usagenotes-connect-drivermanager.html,
//https://www.w3schools.com/sql/sql_ref_add_constraint.asp
