package Components;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

public class TextField implements KeyListener{
    public static final int LIMITLESS = -1;
    private String content = "";
    private HashSet<Character> blacklist = new HashSet<Character>();
    private HashSet<Character> whitelist = new HashSet<Character>();
    private int maxLen = -1;
    private boolean focused;
    private boolean capslock = false;

    public void excludeChar(char[] chars){
        for (char c: chars){
            excludeChar(c);
        }
    }
    public void excludeChar(char chr){
        if (whitelist.contains(chr)){
            whitelist.remove(chr);
        }
        blacklist.add(chr);
    }
    public void includeChar(char[] chars){
        for (char c: chars){
            whitelist.add(c);
        }
    }
    public void includeChar(char chr){
        if (blacklist.contains(chr)){
            blacklist.remove(chr);
        }
        whitelist.add(chr);
    }
    public void capslock(){
        this.capslock = true;
    }
    public void defaultIncludeChar(){
        for (char i = 'a'; i < 'z'+1; i++){
            whitelist.add(i);
        }
        for (char i = 'A'; i < 'Z'+1; i++){
            whitelist.add(i);
        }
        for (char i = '0'; i < '9'+1; i++){
            whitelist.add(i);
        }
    }
    public void limitChars(int len){
        maxLen = len;
    }
    public String content(){
        return content;
    }

    public void focus(){
        this.focused = true;
    }
    public void unfocus(){
        this.focused = false;
    }
    public boolean isFocused() {
        return focused;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (focused) {
            if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
                if (content.length() > 0){
                    content = content.substring(0, content.length()-1);
                }
            } else {
                if (content.length() < maxLen || maxLen == -1){
                    if (!blacklist.contains(e.getKeyChar()) && (whitelist.isEmpty() || whitelist.contains(e.getKeyChar()))){
                        if (capslock){
                            if ('a' <= e.getKeyChar() && e.getKeyChar() <= 'z'){
                                content = content+(char)(e.getKeyChar() - 'a' + 'A');
                            } else {
                                content = content+e.getKeyChar();
                            }
                        } else {
                            content = content+e.getKeyChar();
                        }
                    }
                }
            }
        }
        
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
    
}
