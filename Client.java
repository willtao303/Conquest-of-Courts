import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.BlockingQueue;
import java.util.Queue;
import GameAssets.*;

public class Client{
    private final static String LOCAL_HOST = "127.0.0.1";
    private final String IP;
    private final int PORT = 5420;

    private String username;

    private Socket socket;
    private PrintWriter output;
    private BufferedReader input;
    private BlockingQueue<String> messages = new LinkedBlockingQueue<String>();

    private int side;
    

    private boolean connected = false;
    private boolean alive = false;
    private int retryTimer = 0;
    private int retry = 50;

    Client(){
        this(LOCAL_HOST);
    }
    Client(String ip){
        IP = ip;
    }

    public void connect(){
        if (retryTimer == 0 ){
            (new Connecter()).start();
        } else if (retryTimer != -1){
            retryTimer--;
        }
    }

    public void start(){
        if (!alive){
            alive = true;
            (new Reciever()).start();
        }
    }
    synchronized public void kill(){
        alive = false;
    }
    synchronized public boolean alive(){
        return alive;
    }

    public String nextMessage(){
        try {
            return messages.poll(10, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int unreadMessages(){
        return messages.size();
    }

    public void sendMessage(String msg){
        output.println(msg);
        output.flush();
    }
    
    

    public void update(){

    }
    
    public void setUserame(String s){
        username = s;
    }
    public String getUserame(){
        return username;
    }


    synchronized public boolean connected(){
        return connected;
    }

    private void startClientThread(){
        this.start();
    }

    private class Connecter extends Thread{
        @Override
        public void run() {
            retryTimer = -1;
            try {
                socket = new Socket(IP, PORT);
                output = new PrintWriter(socket.getOutputStream());
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                connected = true;
            } catch (Exception e){
                System.out.println("you failed to connect! retrying...");
                connected = false;
                retryTimer = retry;
                retry += 5;
            }
            
            if (connected){
                startClientThread();
                alive = true;
            }
        }
    }

    private class Reciever extends Thread{
        @Override
        public void run(){
            try{
                while (true){
                    String msg = input.readLine();
                    //System.out.pri/ntln(msg);
                    if (msg != null){
                        messages.offer(msg);
                    }
                    if (!alive()){
                        break;
                    }
                }
            } catch (IOException e){
                connected = false;
                System.out.println("unexpectedly disconected");
            }
            alive = false;
        }
    }

    public int getSide(){
        return side;
    }
    public void setSide(int side){
        this.side = side;
    }
}
