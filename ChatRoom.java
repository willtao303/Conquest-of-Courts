import java.util.LinkedList;
import java.awt.Graphics;
import java.util.Deque;

public class ChatRoom {
    private LinkedList<String> messages = new LinkedList<String>();
    private int messageCap = 60;
    private int shownMessages = 8;
    private int newMessagePersistance = 300;
    private int newMessageReadTimer = newMessagePersistance;
    private int newMessages = 0;
    
    public void update(){
        if (newMessages != 0){
            if (newMessageReadTimer <= 0){
                newMessages--;
                newMessageReadTimer = newMessagePersistance;
            }
            newMessageReadTimer--;
        }
    }
    public void readAll(){
        newMessages = 0;
    }
    public void addMessage(String msg){
        messages.addFirst(msg);
        if (messages.size() > messageCap + 10){
            for (int i = 0; i < 10; i++){
                messages.removeLast();
            }
        }
        newMessages++;
    }

    public String[] getMessages(int start, int lim){
        String[] output = new String[lim];
        int i = 0;
        for (String msg: messages){
            if (i > start+lim){
                break;
            }
            if (i >= start){
                output[i-start] = msg;
            }
            i++;
        }

        return output;
    }

    public String[] getNewMessages(){
        return getMessages(messages.size()-newMessages, newMessages);
    }

    public int newMessages(){
        return newMessages;
    }
    public String[] getMessages(){
        return getMessages(0, shownMessages);
    }
}
