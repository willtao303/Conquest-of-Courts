
import java.awt.Color;
import java.awt.Graphics;
import Containters.Point;
public class BaseForge {
    Point pos;
    Resources resources;
    private final int width = 90, height = 120;
    
    public boolean clicked(int mouseX, int mouseY){
        return (pos.x-width/2 < mouseX && mouseX < pos.x+width/2 && pos.y-height/2 < mouseY && mouseY < pos.y+height/2);
    }
    public void draw(Graphics g, Camera c){
        if (c.onScreen(pos.x, pos.y, 100)){
            g.setColor(Color.magenta);
            g.fillRect(pos.x-(width/2)-c.anchorX(), pos.y-(height/2)-c.anchorY(), width, height);
        }
    }
}
