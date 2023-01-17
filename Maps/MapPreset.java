package Maps;
import java.awt.image.BufferedImage;

public class MapPreset {
    public static int SMALLMAP = 0;
    public int width;
    public int height;
    public String name;
    public BufferedImage image;

    public int [][][] boundries;
    public int sectorsVertical;
    public int sectorsHorizontal;

    public int spawnRX, spawnRY;
    public int crystalRX, crystalRY;
    public int [][] towersR;
    public int spawnBX, spawnBY;
    public int crystalBX, crystalBY;
    public int [][] towersB;
}
