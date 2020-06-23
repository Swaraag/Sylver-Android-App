
package sylverlogin;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import com.sun.net.httpserver.HttpServer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher; 
import java.util.regex.Pattern; 
import java.security.*;
import java.security.interfaces.*;
import javax.crypto.*;
import java.security.spec.MGF1ParameterSpec;
import java.util.Properties;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import javax.mail.*;
import javax.mail.internet.*; 
import javax.activation.*; 
import javax.mail.Session; 
import javax.mail.Transport;
import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;
import java.net.URLConnection;

public class SylverLoginData extends HttpServlet {
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String username = "postgres";
    private final String password = "Sylver";
    private String UserUsername;
    private String UserPassword;
    private Integer id;
    Scanner s = new Scanner(System.in);
    HttpServletResponse response;
    
    public SylverLoginData()
    {
        //super();
    }
    
    
    public String getUsername()
    {
        return username;
    }
    
    public void setUsername(String u)
    {
        UserUsername = u;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String p)
    {
        UserPassword = p;
    }
    
    public Connection connect()
    {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the SylverLoginData server successfully!");
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
        return conn;
    }
    
    public static boolean ValidEmailCheck(String email) 
    { 
        String EmailCheck = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(EmailCheck);
        if (email == null)
            return false; 
        return pat.matcher(email).matches(); 
    }
    
    public boolean VerifyAccount(String username2) throws AddressException, MessagingException, IOException, NullPointerException
    {
        /*
        String recipient = username2;
        String sender = "sylverappteam@gmail.com";
        String host = "192.168.1.88";
        String link = ""; //link coming soon, probably associated with the Python code written on the Anvil website
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        ServerSocket socket = new ServerSocket();
        socket.bind(socket.getLocalSocketAddress(), 139);
        properties.put((String) "mail.smtp.port", "139");
        Session session = Session.getInstance(properties);
        */
        
        boolean b = false;
       /* HttpServletResponse response2 = this.response;
        response2.setContentType("text/html");
        response2.sendRedirect("https://sylverapp.anvil.app");
      */
       
       
        b = true;
        
        /*
        
        try
        {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(sender));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Please verify your new Sylver account.");
            message.setText("Hi, " + username2 + ", you have created an accounter with Sylver. Please click this link to verify your acccount.\nBest,\nSylverAppTeam\nlink: " + link);
            socket.accept();
            Transport.send(message);
            socket.close();
            System.out.println("Mail successfully sent to " + username2 + ".");
            b = true;
        }
      
      catch(MessagingException e)
      {
          e.printStackTrace();
          b = false;
      }
      */
        
      //redirection code goes here
        
      return b;
    }
    
    public Connection AddData(String username2, String password2) throws SQLException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, MessagingException, AddressException, IOException
    {
        Connection conn = null;
        
        PreparedStatement p = null;
        
        conn = DriverManager.getConnection(url, username, password);
        
       /* KeyPairGenerator kID = KeyPairGenerator.getInstance("RSA");
        KeyPairGenerator kUsername = KeyPairGenerator.getInstance("RSA");
        KeyPairGenerator kPassword = KeyPairGenerator.getInstance("RSA");
        KeyPair ku = kUsername.generateKeyPair();
        KeyPair kp = kPassword.generateKeyPair();
        KeyPair ki = kID.generateKeyPair();
        
        PublicKey pUsername = ku.getPublic();
        PublicKey pPassword = kp.getPublic();
        PublicKey pID = ki.getPublic();
        
        kUsername.initialize(900);
        kPassword.initialize(900);
        kID.initialize(900);
        
        Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        c.init(Cipher.ENCRYPT_MODE, pUsername);
        c.init(Cipher.ENCRYPT_MODE, pPassword);
        c.init(Cipher.ENCRYPT_MODE, pID);
        
        
        byte UsernameConversion[] = username2.getBytes();
        byte PasswordConversion[] = password2.getBytes();
        byte IDConversion[] = new byte[1];
        IDConversion[0] = id.byteValue();
        c.update(UsernameConversion);
        c.update(PasswordConversion);
        c.update(IDConversion);
        
        byte UsernameConversionFinal[] = c.doFinal();
        byte PasswordConversionFinal[] = c.doFinal();
        byte IDConversionFinal[] = c.doFinal();

        */
        
        //p = conn.prepareStatement("INSERT INTO UserLogin2(userid, username, password) VALUES(?, ?, ?)");
        boolean b = VerifyAccount(username2);
        
        if(b = true)
        {
        p = conn.prepareStatement("INSERT INTO userlogininfo(username, password) VALUES(?, ?)");
        //p.setBytes(1, IDConversionFinal);
       // p.setBytes(2, UsernameConversionFinal);
       // p.setBytes(3, PasswordConversionFinal);
        
        //p.setInt(1, id);
        p.setString(1, username2);
        p.setString(2, password2);
        
        p.executeUpdate();
        }
        
        else
        {
            System.out.println("Please verify your account.");
        }
       
        return conn;
    }
    
    public Connection IsUnique(String username2, String password2) throws SQLException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, MessagingException, AddressException, IOException
    {
        Connection conn = null;
        PreparedStatement p = null;
        
        conn = DriverManager.getConnection(url, username, password);
        
       /* KeyPairGenerator kUsername = KeyPairGenerator.getInstance("RSA");
        KeyPair ku = kUsername.generateKeyPair();
                
        RSAPrivateKey pUsername = (RSAPrivateKey) ku.getPrivate();
        
        kUsername.initialize(512);
        
        Cipher x = Cipher.getInstance("RSA/ECB/PKCS1PADDING");
        x.init(Cipher.DECRYPT_MODE, pUsername);
        
         byte[] UsernameConversion = new byte[100];
        
         x.update(UsernameConversion);
        
         byte UsernameConversionFinal[] = x.doFinal();

*/
       
       
        
       while(true)
       {
        //String SQLCode = "SELECT 1 FROM userlogin2 WHERE username = ?";
        String SQLCode = "SELECT 1 FROM userlogininfo WHERE username = ?";
        
        boolean verify = ValidEmailCheck(username2);
        
        try(PreparedStatement IsUnique = conn.prepareStatement(SQLCode))
        {
            //IsUnique.setBytes(1, UsernameConversionFinal);
            IsUnique.setString(1, username2);
            
            try(ResultSet d = IsUnique.executeQuery())
            {
                if(d.next())
                {
                    System.out.println("An email is already associated with an account. Please choose a different email.");
                    username2 = s.next();
                }
                
                else if(!d.next() && !verify)
                {
                    System.out.println("The email you entered is not in the correct format. Please try again.");
                    username2 = s.next();
                }
                
                else if(!d.next() && verify)
                {
                   AddData(username2, password2);
                   break;
                    
                }
            }
            
        }
        }
        
       return conn; 
    }
    
    public boolean VerifyLogin(String username2, String password2) throws SQLException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException
    {
        Connection conn = null;
        PreparedStatement p = null;
        boolean b = false;
        
        conn = DriverManager.getConnection(url, username, password);

        /*KeyPairGenerator kUsername = KeyPairGenerator.getInstance("RSA");
        KeyPairGenerator kPassword = KeyPairGenerator.getInstance("RSA");
        KeyPair ku = kUsername.generateKeyPair();
        KeyPair kp = kPassword.generateKeyPair();
                
        RSAPrivateKey pUsername = (RSAPrivateKey) ku.getPrivate();
        RSAPrivateKey pPassword = (RSAPrivateKey) kp.getPrivate();
        
        //kUsername.initialize(512);
        //kPassword.initialize(512);
        
        
        
        Cipher x = Cipher.getInstance("RSA/ECB/OAEPWithSHA-1AndMGF1Padding");
        //x.init(Cipher.DECRYPT_MODE, pUsername);
        //x.init(Cipher.DECRYPT_MODE, pPassword);
        
        OAEPParameterSpec oaepParamsUsername = new OAEPParameterSpec("SHA-256", "MGF1", new MGF1ParameterSpec("SHA-256"), PSource.PSpecified.DEFAULT);
        x.init(Cipher.DECRYPT_MODE, pUsername, oaepParamsUsername);
        
        OAEPParameterSpec oaepParamsPassword = new OAEPParameterSpec("SHA-256", "MGF1", new MGF1ParameterSpec("SHA-256"), PSource.PSpecified.DEFAULT);
        x.init(Cipher.DECRYPT_MODE, pPassword, oaepParamsPassword);
        
        byte UsernameConversion[] = new byte[100];
        byte PasswordConversion[] = new byte[100];
        
        x.update(UsernameConversion);
        x.update(PasswordConversion);
        */
        
        while(true)
        {
        //String SQLCode = "SELECT 1 FROM userlogin2 WHERE username = ?";
        String SQLCode = "SELECT 1 FROM userlogininfo WHERE username = ?";
        
        boolean verify = ValidEmailCheck(username2);

        try(PreparedStatement IsUnique = conn.prepareStatement(SQLCode))
        {
            //IsUnique.setBytes(1, UsernameConversion);
            IsUnique.setString(1, username2);
            
            try(ResultSet d = IsUnique.executeQuery())
            {
                if(d.next() && verify)
                {
                    System.out.println("Username is correct.");
                }
                
                else
                {    
                System.out.println("Incorrect username. Please re-enter.");
                username2 = s.next();
                }
            }
            }
        
                //String SQLCode2 = "SELECT password FROM userlogin2 WHERE username = '" + username2 + "' AND password = ?";
                String SQLCode2 = "SELECT password FROM userlogininfo WHERE username = '" + username2 + "' AND password = ?";
        
       try (PreparedStatement IsUnique = conn.prepareStatement(SQLCode2))
       {
            //IsUnique.setBytes(1, UsernameConversion);
            //IsUnique.setBytes(1, PasswordConversion);
            
            IsUnique.setString(1, username2);
            IsUnique.setString(1, password2);
            
           try(ResultSet d = IsUnique.executeQuery())
           {
                if(d.next())
                {
                    String password3 = d.getString(1);
                    if(password3.equals(password2))
                    {
                    System.out.println("Password is correct. Welcome back, " + username2 + "!");
                    b = true;
                    break;
                    }
                    
                    else
                    {
                        System.out.println("Incorrect password. Please re enter.");
                        password2 = s.next();
                        b = false;
                    }
                    
                    
                }
                
                else
                {
                   System.out.println("Wrong password. Please re-enter.");
                   password2 = s.next();
                   b = false;
                }
            }
            }
        }
        return b;
    }
    }
     


  

//sources: https://www.postgresqltutorial.com/postgresql-jdbc/connecting-to-postgresql-database/,
//https://dev.mysql.com/doc/connector-j/5.1/en/connector-j-usagenotes-connect-drivermanager.html, https://www.postgresql.org/message-id/e13c14ec050510103846db6b0e@mail.gmail.com
//https://dba.stackexchange.com/questions/58312/how-to-get-the-name-of-the-current-database-from-within-postgresql, //https://dba.stackexchange.com/questions/58312/how-to-get-the-name-of-the-current-database-from-within-postgresql, 
//https://www.luv2code.com/2015/01/23/java-jdbc-tutorial-inserting-data-with-user-input/, https://alvinalexander.com/java/java-mysql-insert-example-preparedstatement/https://www.w3schools.com/sql/sql_create_table.asp,
//https://www.postgresqltutorial.com/postgresql-jdbc/insert/, https://www.oreilly.com/library/view/practical-postgresql/9781449309770/ch04s03.html, https://netbeans.org/kb/docs/ide/mysql.html, https://www.youtube.com/watch?v=Q4T7jg0Lv4E,
//https://www.java-forums.org/new-java/97052-adding-user-input-database.html, https://stackoverflow.com/questions/17686844/nullpointer-exception-when-executing-prepared-statement/17686923, https://bukkit.org/threads/mysql-preparedstatement-nullpointerexception.271500/,
//https://www.mainjava.com/jdbc/java-jdbc-how-to-use-preparedstatement-in-programming/, https://mariadb.com/kb/en/inserting-and-updating-with-views/, https://stackoverflow.com/questions/42454582/check-if-value-accountnumber-exist-in-a-java-database, https://coderanch.com/t/301469/databases/Check-exists-ina-database, https://coderanch.com/t/677510/java/Java-Mysql-check-username-exist,
//https://stackoverflow.com/questions/27455114/how-to-compare-user-input-with-my-database-in-java, https://stackoverflow.com/questions/21268415/the-column-name-was-not-found-in-this-resultset, https://stackoverflow.com/questions/16099382/java-mysql-check-if-value-exists-in-database,
//https://support.sas.com/kb/61/126.html, https://stackoverflow.com/questions/244243/how-to-reset-postgres-primary-key-sequence-when-it-falls-out-of-sync/23390399#23390399, https://dba.stackexchange.com/questions/193568/how-to-fix-all-duplicate-key-value-violates-unique-constraint,
//https://stackoverflow.com/questions/12385763/suppress-duplicate-key-value-violates-unique-constraint-errors, https://stackoverflow.com/questions/4448340/postgresql-duplicate-key-violates-unique-constraint,
//https://hcmc.uvic.ca/blogs/index.php/how_to_fix_postgresql_error_duplicate_ke?blog=22, https://www.w3schools.com/sql/sql_ref_add_constraint.asp, https://docs.microsoft.com/en-us/sql/relational-databases/tables/modify-columns-database-engine?view=sql-server-ver15,
//https://www.youtube.com/watch?v=-MAf4fb4cAQ, https://confluence.atlassian.com/bitbucketserverkb/duplicate-key-value-errors-in-logs-in-bitbucket-server-using-postgresql-979408650.html, https://dba.stackexchange.com/questions/60802/fixing-table-structure-to-avoid-error-duplicate-key-value-violates-unique-cons,
//https://github.com/nextcloud/server/issues/6343, https://www.youtube.com/watch?v=eQu_kRlvinE, https://www.w3schools.com/sql/sql_datatypes.asp, https://www.geeksforgeeks.org/difference-between-primary-key-and-unique-key/, https://tshf.sas.com/techsup/download/hotfix/HF2/A4G.html#61126,
//any other websites used but did not specifically check, https://stackoverflow.com/questions/20074897/check-username-and-password-in-java-database-and-give-wrong-password-message-if, https://ramsis-code.blogspot.com/2013/09/how-to-validate-username-and-password.html,
//https://stackoverflow.com/questions/11015023/user-login-by-comparing-with-user-details-in-database, https://stackoverflow.com/questions/22536960/how-to-compare-login-credentials-against-a-database-using-jdbc-postgresql/22554450,
//https://stackoverflow.com/questions/36439305/error-the-column-index-is-out-of-range-1-number-of-columns-0, https://www.w3schools.com/sql/sql_and_or.asp, https://www.tutorialspoint.com/how-to-get-the-current-value-of-a-particular-row-from-a-database-using-jdbc,
//https://stackoverflow.com/questions/22536960/how-to-compare-login-credentials-against-a-database-using-jdbc-postgresql/22554450, https://www.tutorialspoint.com/java_cryptography/java_cryptography_encrypting_data.htm,
//https://www.postgresql.org/docs/7.3/jdbc-binary-data.html, https://stackoverflow.com/questions/12803298/encrypt-a-string-save-it-to-db-load-it-and-decrypt-it, https://www.quickprogrammingtips.com/java/how-to-encrypt-and-decrypt-data-in-java-using-aes-algorithm.html,
//https://stackoverflow.com/questions/14085333/rsa-encryption-decryption-badpaddingexception-data-must-start-with-zero, https://stackoverflow.com/questions/31944374/badpaddingexception-decryption-error, https://stackoverflow.com/questions/24658939/decrypt-pbe-encrypted-passwords-with-postgres-sql,
//https://www.codeproject.com/Questions/828838/Rsa-Encryption-And-Decryption-Getting-Error-When-I, https://github.com/metabase/metabase/issues/5632, https://stackoverflow.com/questions/21285165/how-to-store-byte-from-java-into-a-bytea-in-postgresql,
//https://www.devglan.com/java8/rsa-encryption-decryption-java, https://stackoverflow.com/questions/27918511/error-operator-does-not-exist-character-varying-bytea?rq=1, https://medium.com/@PrakhashS/javax-crypto-badpaddingexception-analyzing-related-root-causes-9b81ebda21b1,
//https://github.com/randombit/botan/issues/1225, https://groups.google.com/forum/#!topic/activate-persistence/jtSKUtJvgIU, https://www.geeksforgeeks.org/check-email-address-valid-not-java/, https://www.geeksforgeeks.org/send-email-using-java-program/,
//https://netbeans.org/kb/74/java/project-setup.html#projects-configuring, https://www.sparkpost.com/blog/what-smtp-port/#:~:text=Ports%2025%2C%20465%2C%20587%2C%20or%202525%20for%20SMTP%20have,be%20considered%20for%20modern%20use.,
//https://stackoverflow.com/questions/12901475/javamail-api-to-imail-java-net-socketexception-permission-denied-connect, https://stackoverflow.com/questions/7477712/sending-email-using-jsp/7478027#7478027, https://stackoverflow.com/questions/5190730/mail-sending-problem,
//https://stackoverflow.com/questions/585599/whats-causing-my-java-net-socketexception-connection-reset, https://www.tutorialspoint.com/javamail_api/javamail_api_smtp_servers.htm, https://kb.netgear.com/20878/Finding-your-IP-address-without-using-the-command-prompt,
//https://stackoverflow.com/questions/8771167/how-to-change-javamail-port, https://stackoverflow.com/questions/6484275/java-net-unknownhostexception-invalid-hostname-for-server-local, https://java.databasedevelop.com/article/11851490/JMS,
//https://stackoverflow.com/questions/5659325/getting-javax-mail-messagingexception-and-java-net-socketexception, https://docs.microsoft.com/en-us/office365/enterprise/urls-and-ip-address-ranges, https://stackoverflow.com/questions/8051863/how-can-i-close-the-socket-in-a-proper-way,
//https://www.edureka.co/community/7308/how-does-java-net-socketexception-connection-reset-happen, http://hc.apache.org/httpcomponents-client-ga/tutorial/html/fundamentals.htmlhttps://stackoverflow.com/questions/3913502/restful-call-in-java,
//https://support.tibco.com/s/article/How-to-resolve-java-net-SocketException-Connection-reset-exception-with-Invoke-REST-API-activity, https://download.oracle.com/otndocs/jcp/servlet-3.0-fr-eval-oth-JSpec/, https://stackoverflow.com/questions/13951127/servletexception-httpservletresponse-and-httpservletrequest-cannot-be-resolved,
//https://docs.oracle.com/javaee/6/api/javax/servlet/http/package-tree.html, https://stackoverflow.com/questions/8276897/java-nullpointerexception-on-httpservletresponse-line, https://docs.oracle.com/javaee/7/api/javax/faces/context/FacesContext.html,
//https://stackoverflow.com/questions/25443247/how-to-check-link-is-already-clicked-in-java, https://www.codejava.net/java-ee/servlet/how-to-send-redirect-from-java-servlet, https://stackoverflow.com/questions/11721622/how-do-i-pass-the-httpservletrequest-object-to-the-test-case, https://www.codota.com/code/java/classes/javax.servlet.http.HttpServlet
//https://stackoverflow.com/questions/8557490/redirect-to-a-different-url, https://examples.javacodegeeks.com/enterprise-java/servlet/java-servlet-sendredirect-example/#:~:text=The%20sendRedirect()%20method%20of%20HttpServletResponse%20interface%20can%20be%20used,inside%20and%20outside%20the%20server.,
//https://smallbusiness.chron.com/redirect-new-url-servlet-50862.html, https://stackoverflow.com/questions/30027912/how-can-i-get-httpservletrequest-and-httpservletresponse-object-in-spring-aop, https://www.javatpoint.com/sendRedirect()-method, https://docs.oracle.com/javase/8/docs/api/java/net/URLConnection.html#getURL--,
//https://docs.oracle.com/javase/8/docs/api/java/net/URLConnection.html, https://stackoverflow.com/questions/2236413/how-to-redirect-to-particular-url-while-clicking-on-button-in-android, https://www.instructables.com/id/Creating-a-Chat-Server-Using-Java/
