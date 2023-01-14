package GameAssets;

import java.util.HashMap;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class Input implements KeyListener, MouseMotionListener, MouseListener, MouseWheelListener {
    public static final char UP   = 0;
    public static final char DOWN = 1;
    public static final char LEFT = 2;
    public static final char RIGHT = 3;
    public static final char PAUSE = 4;
    public static final char SWITCH = 5;
    public static final char DASH = 6;
    public static final char ESC = 7;
    public static final char FULLSCREEN = 8;
    public static final char MULTISELECT = 9;
    public static final char SEND = 10;

    public static final int LMB = 0;
    public static final int MIDDLECLICK = 1;
    public static final int RMB = 2;
    public static final int MBFOUR = 3;
    public static final int MBFIVE = 4;


    private HashMap<Integer, Boolean> keyDown = new HashMap<Integer, Boolean>();
    private int keyTapped = -1;
    private HashMap<Character, Integer> keyBind= new HashMap<Character, Integer>();
    private boolean[] mouseDown = new boolean[5]; 
    private int[] mouseTap = new int[5]; 
    private int mouseX = 0;
    private int mouseY = 0;
    private int scroll = 0;
    private char changeKeybind = 42069;

    public Input(){
        this.defualtKeybinds();
    }

    public void resetTapped(){
        for (int i = 0; i < 5; i ++){
            mouseTap[i] = 0;
        }
        scroll = 0;
    }
    public int scroll(){
        return scroll;
    }
    public boolean keyIsDown(int key){
        return keyDown.get(key);
    }
    public boolean keyIsDown(char keybind){
        if (keyDown.containsKey(keyBind.get(keybind))){
            return keyDown.get(keyBind.get(keybind));
        }
        return false;
    }

    public boolean keyIsTapped(int key){
        if (keyTapped == key){
            keyTapped = -1;
            return true;
        }
        return false;
    }
    public boolean keyIsTapped(char keybind){
        return keyIsTapped(keyBind.get(keybind));
    }

    public boolean mouseIsDown(int button){
        if (button < 5){
            return mouseDown[button];
        }
        return false;
    }

    public boolean mouseIsTapped(int button){
        if (button < 5){
            if (mouseTap[button]==1){
                return true;
            }
        }
        return false;
    }
    public boolean mouseIsReleased(int button){
        if (button < 5){
            if (mouseTap[button]==2){
                return true;
            }
        }
        return false;
    }

    public int mousePosX(){
        return mouseX;
    }
    public int mousePosY(){
        return mouseY;
    }

    public void defualtKeybinds(){
        keyBind.put(UP, KeyEvent.VK_W);
        keyBind.put(DOWN, KeyEvent.VK_S);
        keyBind.put(LEFT, KeyEvent.VK_A);
        keyBind.put(RIGHT, KeyEvent.VK_D);
        keyBind.put(PAUSE, KeyEvent.VK_ESCAPE);
        keyBind.put(SWITCH, KeyEvent.VK_Q);
        keyBind.put(DASH, KeyEvent.VK_CONTROL);
        keyBind.put(ESC, KeyEvent.VK_ESCAPE);
        keyBind.put(FULLSCREEN, KeyEvent.VK_F11);
        keyBind.put(MULTISELECT, KeyEvent.VK_SHIFT);
        keyBind.put(SEND, KeyEvent.VK_ENTER);
    }
    public void setKeybind(char keybind){
        if (keyBind.containsKey(keyBind)){
            changeKeybind = keybind;
        }
    }

    @Override
    public void mouseClicked(MouseEvent m) {
    }
    @Override
    public void mousePressed(MouseEvent m) {
        if (m.getButton() <= 5){
            this.mouseDown[m.getButton()-1] = true;
            this.mouseTap[m.getButton()-1] = 1;
        }
    }
    @Override
    public void mouseReleased(MouseEvent m) {
        if (m.getButton() <= 5){
            this.mouseDown[m.getButton()-1] = false;
            this.mouseTap[m.getButton()-1] = 2;
        }
    }
    @Override
    public void mouseEntered(MouseEvent m) {
    }
    @Override
    public void mouseExited(MouseEvent m) {
    }
    @Override
    public void mouseDragged(MouseEvent m) {
        this.mouseX = m.getX();
        this.mouseY = m.getY();
    }
    @Override
    public void mouseMoved(MouseEvent m) {
        this.mouseX = m.getX();
        this.mouseY = m.getY();
    }
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        scroll = e.getWheelRotation();
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        keyTapped = -1;
        int key = e.getKeyCode();
        keyTapped = e.getKeyCode();
        keyDown.put(key, true);

        if (changeKeybind != 42069){
            keyBind.replace(changeKeybind, e.getKeyCode());
            changeKeybind = 42069;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        keyDown.put(key, false);
    }
}
