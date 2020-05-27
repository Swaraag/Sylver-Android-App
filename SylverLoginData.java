
package sylverlogin;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
import java.util.Random;
import java.util.ArrayList;

public class SylverLoginData {
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String username = "postgres";
    private final String password = "Sylver";
    static ArrayList<Integer> IDs = new ArrayList();
    private String UserUsername;
    private String UserPassword;
    private Integer id;

    
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
    
    public Connection AddData() throws SQLException
    {
        Connection conn = DriverManager.getConnection(url, username, password);;

        String SQLCode = "INSERT INTO UserLoginInfo"
                + "(UserID, username, password)" + " values (?, ?, ?)";
        
        PreparedStatement p = conn.prepareStatement(SQLCode);
        
        p.setInt(1, IDs.get(getID()));
        p.setString(2, getUsername());
        p.setString(3, getPassword());
        
        p.executeUpdate();
        
        return conn;
    }
    
    
}

//sources: https://www.postgresqltutorial.com/postgresql-jdbc/connecting-to-postgresql-database/,
//https://dev.mysql.com/doc/connector-j/5.1/en/connector-j-usagenotes-connect-drivermanager.html, https://www.postgresql.org/message-id/e13c14ec050510103846db6b0e@mail.gmail.com
//https://dba.stackexchange.com/questions/58312/how-to-get-the-name-of-the-current-database-from-within-postgresql, //https://dba.stackexchange.com/questions/58312/how-to-get-the-name-of-the-current-database-from-within-postgresql, 
//https://www.luv2code.com/2015/01/23/java-jdbc-tutorial-inserting-data-with-user-input/, https://alvinalexander.com/java/java-mysql-insert-example-preparedstatement/https://www.w3schools.com/sql/sql_create_table.asp,
//https://www.postgresqltutorial.com/postgresql-jdbc/insert/, https://www.oreilly.com/library/view/practical-postgresql/9781449309770/ch04s03.html, https://netbeans.org/kb/docs/ide/mysql.html
