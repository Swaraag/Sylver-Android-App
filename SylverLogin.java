package sylverlogin;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.sql.SQLException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class SylverLogin {
static String username;

    public static void main(String[] args) throws SQLException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, MessagingException, AddressException, IOException
    {
       Scanner sc = new Scanner(System.in);
       System.out.println("Welcome to the Sylver Android companion app! Do you have an account?");
       String question = sc.next();
       SylverLoginData sld = new SylverLoginData();
       String password;
       String password2 = "";
       sld.connect();
       Chat c = new Chat();
       
       if(question.equals("yes"))
       {
           System.out.println("Please sign in.");
           username = sc.next();
           password = sc.next();
           //sld.VerifyLogin(username, password);
           boolean b = sld.VerifyLogin(username, password);
           
           if(b = true)
           {
               while(true)
               {
               System.out.println("What would you like to do?");
               String question2 = sc.next();

               if(question2.equals("chat"))
               {
                   System.out.println("How would you like to chat?");
                   String question3 = sc.next();
                   
                   if(question3.equals("create"))
                   {
                       c.CreateChat();
                       
                       Chat.UserInvites.add(username);
                       
                       String input = "";

                       while (true)
                       {
                           if(!input.equals("leave"))
                           {
                           input = sc.next();
                           input = input.replaceAll("\\s", "");
                           System.out.println(username + ": " + input);
                           }

                           else if (input.equals("leave"))
                           {
                               break;
                           }
                       }
                   }
                   
                   else if(question3.equals("join"))
                   {
                       boolean d = c.JoinChat();
                       
                       if (d = false)
                       {
                           System.out.println("Password is incorrect. Please try again.");
                       }
                       
                       else if (d = true)
                        {
                           System.out.println("Password accepted! Welcome to " + username + "'s chat room! You can begin chatting here.");
                           String input = "";

                            while (true)
                            {
                                if (!input.equals("leave"))
                                {
                                    input = sc.next();
                                    input = input.replaceAll("\\s", "");
                                    System.out.println(username + ": " + input);
                                }
                                
                                else if (input.equals("leave"))
                                {
                                    break;
                                }
                            }
                       }
                    }
                }
               
                   else if(question2.equals("invites"))
                   {
                       c.ViewChatInvites();
                   }
               
               else if(question2.equals("exit"))
               {
                   System.out.println("Thank you for using the Sylver Android App! Have a nice day!");
                   System.exit(0);
               }
           }
           }
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
//https://www.w3schools.com/sql/sql_ref_add_constraint.asp, https://javarevisited.blogspot.com/2014/04/how-to-replace-line-breaks-new-lines-windows-mac-linux.html, https://stackoverflow.com/questions/43027078/prevent-going-to-next-line-after-input-in-console
