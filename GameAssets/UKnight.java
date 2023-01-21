package GameAssets;

import java.awt.Graphics;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class UKnight extends UnitDefender{
    public static int RANGE = 400;
    private String sprite;
    UKnight(int x, int y, Map m, Point towerPos) {
        super(x, y, m, towerPos, RANGE);
        hp = maxHp = 100;
        this.x = x;
        width = 15*ScreenConsts.PIXELRARIO;
        height = 17*ScreenConsts.PIXELRARIO;
        this.y = y - width/2;
        speed = 3;
        sprite = "Knight";
        try {
            spritesheet = ImageIO.read(new File("Sprites/Units/"+sprite+".png"));
            
        }catch (IOException e) {System.out.println("Sprite not found: "+sprite);}
    }

    
}
