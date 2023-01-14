package Components;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageButton extends Button{
    private static final String[] IMAGE_PRESETS = {
        "StartButton"
    };
    private static final int[][] DIMS_PRESETS = {
        {800, 80},
    };
    public ImageButton(int x, int y, int preset){
        this.x = x;
        this.y = y;
        this.width = DIMS_PRESETS[preset][0];
        this.height = DIMS_PRESETS[preset][1];
        try {
            buttonSprite = ImageIO.read(new File("Sprites/"+IMAGE_PRESETS[preset]+"Normal.png"));
            hoverSprite = ImageIO.read(new File("Sprites/"+IMAGE_PRESETS[preset]+"Hover.png"));
            clickSprite = ImageIO.read(new File("Sprites/"+IMAGE_PRESETS[preset]+"Click.png"));
        } catch (IOException e) {e.printStackTrace();}
    }
    @Override
    public void update(int x, int y, boolean mouseDown) {
        super.defaultUpdate(x, y, mouseDown); 
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(currentSprite, x()-(width/2), y()-(height/2), width, height, null);
    }
}
