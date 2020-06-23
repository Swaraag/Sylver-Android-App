package sylverlogin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Chat implements Runnable
{

private BufferedReader br;
private PrintWriter pw;
private BufferedReader br2;
static ArrayList<Chat> ClientList = new ArrayList<>();
static Socket socket;
static ServerSocket socket2;

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
