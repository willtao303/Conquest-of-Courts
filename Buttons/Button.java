import java.awt.Image;
import java.awt.Graphics;

public abstract class Button {
    protected int x;
    protected int y;
    protected Image buttonSprite;
    protected Image hoverSprite;
    protected Image clickSprite;

    protected Image currentSprite;
    
    protected int width;
    protected int height;

    protected boolean pressed;
    protected boolean released;


    public abstract void update(int x, int y, boolean mouseDown);
    public abstract void draw(Graphics g);

    public void defaultDraw(Graphics g){
        g.drawImage(currentSprite, x-(width/2), y-(height/2), width, height, null);
    }
    public void defaultUpdate(int mousex, int mousey, boolean mouseDown){
        released = false;
        if (pressed && !mouseDown){
            released = true;
            pressed = false;
        }
        if (!pressed){
            currentSprite = buttonSprite;
        }
        if ((x - width/2 < mousex && mousex < x + width/2 )&&(y - height/2 < mousey && mousey < y + height/2 )){
            currentSprite = hoverSprite;
            if (mouseDown){
                pressed = true;
                currentSprite = clickSprite;
            }
        }
    }

    public boolean isDown(){
        return this.pressed;
    }
    public boolean isReleased(){
        return this.released;
    }
}
