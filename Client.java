import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.LinkedList;
import java.util.Queue;

public class Client {
    private final String LOCAL_HOST = "127.0.0.1";
    private final String IP;
    private final int PORT = 5420;

    private EnemyManager e;
    private Game g;

    Socket socket;
    PrintWriter output;
    private Queue<String> messages = new LinkedList<String>();

    boolean connected = false;
    int retryTimer = 0;
    int retry = 50;

    Client(){
        IP = LOCAL_HOST;
    }
    Client(String ip){
        IP = ip;
    }

    public void setup(){
        if (retryTimer == 0){
            try {
                socket = new Socket(IP, PORT);
                output = new PrintWriter(socket.getOutputStream());
                connected = true;
            } catch (Exception e){
                System.out.println("you failed to connect! retrying...");
                connected = false;
                retryTimer = retry;
                retry += 5;
            }
        } else {
            retryTimer--;
        }
    }

    public void run(){

    }

    public void setEnemyManager(EnemyManager E){
        e = E;
    }
    public void setGame(Game G){
        g = G;
    }





    
}
