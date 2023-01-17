package Components;

import java.awt.Image;
import java.awt.Graphics;

public abstract class Button {
    protected int x;
    protected int y;
    protected Image buttonSprite;
    protected Image hoverSprite;
    protected Image clickSprite;
    public static final int START_BUTTON = 0;
    public static final int MULTIPLAYER_BUTTON = 1;
    public static final int SETTINGS_BUTTON = 2;
    public static final int BACK_BUTTON = 3;
    public static final int EXIT_BUTTON = 4;

    protected Image currentSprite;
    protected int currentSpriteNum;
    protected static int PLAIN = 0;
    protected static int HOVER = 1;
    protected static int CLICK = 2;
    
    protected int width;
    protected int height;

    private boolean pressed;
    private boolean released;

    private boolean lock = false;

    public abstract void update(int x, int y, boolean mouseDown);
    public abstract void draw(Graphics g);

    protected Button(){}

    public void defaultUpdate(int mousex, int mousey, boolean mouseDown){
        if (!lock){
            released = false;
            if (pressed && !mouseDown){
                released = true;
                pressed = false;
            }
            if (!pressed){
                currentSprite = buttonSprite;
                currentSpriteNum = 0;
            }
            if ((x() - width/2 < mousex && mousex < x() + width/2 )&&(y() - height/2 < mousey && mousey < y() + height/2 )){
                currentSprite = hoverSprite;
                currentSpriteNum = 1;
                if (mouseDown){
                    pressed = true;
                    currentSprite = clickSprite;
                    currentSpriteNum = 2;
                }
            }
        }
    }

    public void lock(){
        this.lock = true;
        pressed = false;
        released = false;
        currentSprite = hoverSprite;
    }
    public void unlock(){
        this.lock = false;
    }
    public boolean locked(){
        return lock;
    }

    public boolean isDown(){
        return this.pressed;
    }
    public boolean isReleased(){
        return this.released;
    }
    public int y(){
        return this.y;
    }
    public int x(){
        return this.x;
    }
}
