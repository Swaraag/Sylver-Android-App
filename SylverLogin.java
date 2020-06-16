package sylverlogin;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.sql.SQLException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.MessagingException;

public class SylverLogin {

    public static void main(String[] args) throws SQLException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, MessagingException
    {
       Scanner sc = new Scanner(System.in);
       System.out.println("Welcome to the Sylver Android companion app! Do you have an account?");
       String question = sc.next();
       SylverLoginData sld = new SylverLoginData();
       String username;
       String password;
       String password2 = "";
       sld.connect();
       
       if(question.equals("yes"))
       {
           System.out.println("Please sign in.");
           username = sc.next();
           password = sc.next();
           sld.VerifyLogin(username, password);
       }
       
       else if(question.equals("no"))
       {
           System.out.println("Thank you for creating an account!\nPlease enter a username and password in that order.");
           username = sc.next();
           password = sc.next();
           
           
           
           while(!password.equals(password2))
           {
           System.out.println("Please re enter your password.");
           password2 = sc.next();
           

           if(password.equals(password2))
           {
            sld.IsUnique(username, password);
            
            System.out.println("Account successfully created!");
            break;
           }
           
           else
           {
               password2 = "";
               continue;
           }

           }
           
       }
       
    }
    

    
}



//sources: https://www.geeksforgeeks.org/generating-random-numbers-in-java/, https://www.homeandlearn.co.uk/java/connect_to_a_database_using_java_code.html, https://dev.mysql.com/doc/connector-j/5.1/en/connector-j-usagenotes-connect-drivermanager.html,
//https://www.w3schools.com/sql/sql_ref_add_constraint.asp
