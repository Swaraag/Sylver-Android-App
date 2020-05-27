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
       Integer ID;
       
       if(question.equals("yes"))
       {
           System.out.println("Please sign in.");
           ID = sc.nextInt();
           
           
               if(sl.getIDs().isEmpty())
               {
                   System.out.println("ID does not exist.");
               }
           
           for(int i = 0; i < sl.getIDs().size(); i++)
           {
               if(sl.getIDs().get(i) == ID)
               {
                   System.out.println("You have been successfully logged in!");
               }
               
               else if(sl.getIDs().get(i) != ID)
               {
                   System.out.println("Incorrect id. Please re enter your id.");
               }
           }
       }
       
       else if(question.equals("no"))
       {
           ID = sl.GenerateID();
           sl.getIDs().add(ID);
           System.out.println("Thank you for creating an account! Your unique id is " + ID + ". Please keep this id in a safe place, since you will need it to login.");
           sld.connect();
           sld.AddData();
       }
       
    }
    

    
}



//sources: https://www.geeksforgeeks.org/generating-random-numbers-in-java/, https://www.homeandlearn.co.uk/java/connect_to_a_database_using_java_code.html, https://dev.mysql.com/doc/connector-j/5.1/en/connector-j-usagenotes-connect-drivermanager.html
