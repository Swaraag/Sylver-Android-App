
package sylverlogin;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

public class SylverLoginData {
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String username = "postgres";
    private final String password = "Sylver";
    static ArrayList<Integer> IDs = new ArrayList();
    private String UserUsername;
    private String UserPassword;
    private Integer id;
    Scanner s = new Scanner(System.in);

    
    public SylverLoginData()
    {
        
    }
    
    
         // A generator to produce random integers for id
    public Integer GenerateID() {
        Random rand = new Random();
        // Generates a random ID ranging from 1 to 1000000000
        id = 1 + rand.nextInt(1000000000);

        // Keep generating an ID till a unique ID is generated
        while (IDs.contains(id)) {
            id = 1 + rand.nextInt(1000000000);
        }
        return id;
    }
    
    public ArrayList<Integer> getIDs()
    {
        return IDs;
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
    
    public Integer getID()
    {
        return id;
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
    
    public Connection AddData(Integer id, String username2, String password2) throws SQLException
    {
        Connection conn = null;
        
        PreparedStatement p = null;
        
        conn = DriverManager.getConnection(url, username, password);
        
        p = conn.prepareStatement("INSERT INTO UserLoginInfo(userid, username, password) VALUES(?, ?, ?)");
        
       p.setInt(1, id);
       p.setString(2, username2);
       p.setString(3, password2);
       p.executeUpdate();
       
        return conn;
    }
    
    public Connection IsUnique(Integer ID, String username2, String password2) throws SQLException
    {
        Connection conn = null;
        PreparedStatement p = null;
        
        conn = DriverManager.getConnection(url, username, password);
        
       while(true)
       {
        
        String SQLCode = "SELECT 1 FROM userlogininfo WHERE username = ?";
        
        try(PreparedStatement IsUnique = conn.prepareStatement(SQLCode))
        {
            IsUnique.setString(1, username2);
            
            try(ResultSet d = IsUnique.executeQuery())
            {
                if(d.next())
                {
                    System.out.println("Username already exists. Please choose a different username.");
                    username2 = s.next();
                }
                
                else
                {
        
                     AddData(ID, username2, password2);
                   break;
                    
                }
            }
            
        }
        }
        
       return conn; 
    }
    
    public Connection VerifyLogin(Integer ID, String username2, String password2) throws SQLException
    {
        boolean b = true;
        Connection conn = null;
        PreparedStatement p = null;
        
        conn = DriverManager.getConnection(url, username, password);
        
        Integer URow = 0;
        
        while(b = true)
        {
        String SQLCode = "SELECT 1 FROM userlogininfo WHERE username = ?";

        try(PreparedStatement IsUnique = conn.prepareStatement(SQLCode))
        {
            IsUnique.setString(1, username2);
            
            try(ResultSet d = IsUnique.executeQuery())
            {
                if(d.next())
                {
                    URow = d.getRow();
                    System.out.println("Username is correct.");
                    
                }
                
                else
                {
                    
                System.out.println("Incorrect username. Please re-enter.");
                username2 = s.next();
                }
            }
            }
        
                String SQLCode2 = "SELECT 1 FROM userlogininfo WHERE 'password' = ?";
        
        try(PreparedStatement IsUnique = conn.prepareStatement(SQLCode2))
        {
            IsUnique.setString(1, password2);
            
            try(ResultSet d = IsUnique.executeQuery())
            {
                Integer PRow = d.getRow();
                if(d.next())
                {
                   
                   PRow = d.getRow();
                   
                    if(URow == PRow)
                    {
                    System.out.println("Password is correct. Welcome back, " + username2 + "!");
                    break;
                    }
                    
                    else
                    {
                        //System.out.println(URow + " " + PRow);
                        System.out.println("Wrong password. Please re-enter.");
                        password2 = s.next();
                    }
                }
                
                else
                {
                    System.out.println(URow + " " + PRow);
                    System.out.println("Incorrect password. Please re-enter.");
                    password2 = s.next();
                }                   
            }
            }
        }
        return conn;
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
//any other websites used but did not specifically check
