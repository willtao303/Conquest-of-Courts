
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class StartButton extends Button{
    public StartButton(int x, int y){
        this.x = x;
        this.y = y;

        width = 800;
        height = 80;

        try {
            buttonSprite = ImageIO.read(new File("Sprites/StartButtonNormal.png"));
            hoverSprite = ImageIO.read(new File("Sprites/StartButtonHover.png"));
            clickSprite = ImageIO.read(new File("Sprites/StartButtonClick.png"));
        } catch (IOException e) {e.printStackTrace();}

        //buttonSprite.
    }
    @Override
    public void update(int x, int y, boolean mouseDown) {
        super.defaultUpdate(x, y, mouseDown); 
    }

    @Override
    public void draw(Graphics g) {
        super.defaultDraw(g);
    }
    
}
