import java.util.LinkedList;
import java.awt.Graphics;

public class ChatRoom {
    private LinkedList<String> messages = new LinkedList<String>();
    private int messageCap = 60;
    private int shownMessages = 8;
    
    public void addMessage(String msg){
        messages.addFirst(msg);
        if (messages.size() > messageCap + 10){
            for (int i = 0; i < 10; i++){
                messages.removeLast();
            }
        }
    }

    public String[] getMessages(int start, int lim){
        String[] output = new String[10];
        int i = 0;
        for (String msg: messages){
            if (i >= start){
                output[i-start] = msg;
            }
            i++;
            if (i > start+lim){
                break;
            }
        }

        return output;
    }

    public String[] getMessages(){
        return getMessages(0, shownMessages);
    }
}
