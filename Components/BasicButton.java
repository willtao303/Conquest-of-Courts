package Components;

import java.awt.Graphics;
import java.awt.Color;

public class BasicButton extends Button{
    private Color color;
    public BasicButton(int x, int y, int width, int height, Color color){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }
    public BasicButton(int x, int y, int width, int height){
        this(x, y, width, height, Color.WHITE);
    }

    @Override
    public void update(int x, int y, boolean mouseDown) {
        super.defaultUpdate(x, y, mouseDown);
    }

    @Override
    public void draw(Graphics g) {
        if (currentSpriteNum == PLAIN){
            g.setColor(color);
        } else if (currentSpriteNum == HOVER){
            g.setColor(color.darker());
        } else if (currentSpriteNum == CLICK){
            g.setColor(color.darker().darker());
        }
        g.fillRect(x()-(width/2), y()-(height/2), width, height);
        g.setColor(Color.BLACK);
        g.drawRect(x()-(width/2), y()-(height/2), width, height);
    }
}
