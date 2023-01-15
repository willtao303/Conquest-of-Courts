package Components;

import java.awt.Graphics;
import java.io.File;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageButton extends Button{
    private static final String[] IMAGE_PRESETS = {
        "StartButton",
        "MultiPlayerButton",
        "SettingsButton",
        "BackButton",
        "ExitButton"
    };
    private static final int[][] DIMS_PRESETS = {
        {160, 16},
        {160, 16},
        {16, 16},
        {16, 16},
        {16, 16}
    };
    private static final int PIXEL_RATIO = 5;
    public ImageButton(int x, int y, int preset){
        this.x = x;
        this.y = y;
        this.width = DIMS_PRESETS[preset][0] * PIXEL_RATIO;
        this.height = DIMS_PRESETS[preset][1]* PIXEL_RATIO;
        try {
            BufferedImage tempSprite = ImageIO.read(new File("Sprites/"+IMAGE_PRESETS[preset]+".png"));
            buttonSprite = tempSprite.getSubimage(0, 0, DIMS_PRESETS[preset][0], DIMS_PRESETS[preset][1]);
            hoverSprite = tempSprite.getSubimage(0, DIMS_PRESETS[preset][1], DIMS_PRESETS[preset][0], DIMS_PRESETS[preset][1]);
            clickSprite = tempSprite.getSubimage(0, 2*DIMS_PRESETS[preset][1], DIMS_PRESETS[preset][0], DIMS_PRESETS[preset][1]);
        } catch (IOException e) {System.out.println("Sprite not found: "+IMAGE_PRESETS[preset]);}
        currentSprite = buttonSprite;
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
