package Maps;
import java.awt.image.BufferedImage;

public class MapPreset {
    public static int SMALLMAP = 0;
    public int width;
    public int height;
    public String name;
    public BufferedImage image;

    public int spawnRX;
    public int spawnRY;
    public int crystalRX;
    public int crystalRY;
    
    public int [][][] boundries;
    //public int [][][] sectors;
    //public int [][] sectorsAdjacency;
    public int sectorsVertical;
    public int sectorsHorizontal;

    public int [][] towers;

}
