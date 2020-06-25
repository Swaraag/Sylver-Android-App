package sylverlogin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class Chat implements Runnable
{

private BufferedReader br;
private PrintWriter pw;
private BufferedReader br2;
static ArrayList<Chat> ClientList = new ArrayList<>();
static Socket socket;
static ServerSocket socket2;
Scanner sc = new Scanner(System.in);
static ArrayList<String> UserInvites = new ArrayList();
static ArrayList<String> PasswordInvites = new ArrayList(); //corresponding password to each chat invite that the user receives
String password = "";
SylverLoginData sld = new SylverLoginData();


public Chat()
{
    
}

public Chat(ServerSocket s) throws IOException
{
    this.socket2 = s;
}
   
    
    public static void AcceptClients() throws IOException
    {
        ClientList = new ArrayList<>();
        while(true)
        {
            try
            {
                Socket LocalSocket = socket2.accept();
                Chat c = new Chat(socket2);
                Thread thread = new Thread((Runnable) c);
                thread.start();
                ClientList.add(c);
            }
            
            catch(Exception e)
            {
                System.out.println("There was an error. Please try again.");
            }
        }
    }
    
    public void CreateChat()
    {
        System.out.println("Please enter a password, which will be used for other users to join your chat.");
        password = sc.next();
        
        System.out.println("Chat room password successfully created. The password for this chat room is " + password + ".");
        PasswordInvites.add(password);
    }
    
    public boolean JoinChat()
    {
        boolean b = false;
            
        System.out.println("Please enter a password, so you can join a chat.");
        String password2 = sc.next();

        for(String s: PasswordInvites)
        {
            if(password2.equals(s))
            {
                b = true;
            }
            
            else if (!password2.equals(s))
            {
                b = false;
            }
        }
        return b;
    }
    
    public void ViewChatInvites()
    {
        System.out.println("Users creating their own chat room: " + UserInvites);
        System.out.println("Passwords for these chat rooms: " + PasswordInvites);
    }

    /*
    public void CreateChat() throws IOException
    {
        int port = 139;
        ServerSocket socket = new ServerSocket(port);
        Socket socket2 = new Socket("192.168.1.94", port);
        
        try
        {
            socket = new ServerSocket(port);
            
            try
            {
                socket2 = new Socket("192.168.1.94", port);
                AcceptClients();
                Thread.sleep(1000);
                Thread server = new Thread(new Chat(socket));
            }
            
            catch(IOException e)
            {
                System.err.println("There was a problem. Please try again.");
                e.printStackTrace();
            }
            
            catch(InterruptedException i)
            {
                i.printStackTrace();
            }
        }



        
        catch(Exception e)
        {
            System.err.println("Port " + port + " is unavailable.");
            System.exit(1);
        }
    }

*/
    
        @Override
    public void run()
    {
        try
        {
            socket = new Socket("0.0.0.0", 135);
            //socket2 = new ServerSocket(135);
            pw = new PrintWriter(socket.getOutputStream(), true);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            br2 = new BufferedReader(new InputStreamReader(System.in));
            
            while(!socket.isClosed())
            {
                String input = br.readLine();
                if(input != null && br2.ready())
                {
                    pw.println(SylverLogin.username + ": " + br2.readLine());
                    for(Chat cc: ClientList)
                    {
                        cc.getWriter().write(input);
                        if(input.equals("finished chatting"))
                        {
                            socket.close();
                        }
                    }
                }
            }
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public PrintWriter getWriter()
    {
        return pw;
    }
}

//source: https://www.instructables.com/id/Creating-a-Chat-Server-Using-Java/
