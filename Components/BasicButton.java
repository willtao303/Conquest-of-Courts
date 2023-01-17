package Components;

import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;

public class BasicButton extends Button{
    private Color color;
    private String text;
    public BasicButton(int x, int y, int width, int height, Color color, String text){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.text = text;
    }
    public BasicButton(int x, int y, int width, int height, String text){
        this(x, y, width, height, Color.WHITE, text);
    }
    public BasicButton(int x, int y, int width, int height, Color c){
        this(x, y, width, height, c, "");
        
    }
    public BasicButton(int x, int y, int width, int height){
        this(x, y, width, height, Color.WHITE, "");
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
        g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));
        g.drawString(text, x()-(width/2) + 10, y()+(height/2) - 10);
    }
}
