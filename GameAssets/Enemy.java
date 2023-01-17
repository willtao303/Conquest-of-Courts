package GameAssets;

import java.awt.image.BufferedImage;

public class Enemy {
    public int id;
    public int x;
    public int y;
    public int hp;
    public int width = 50, height = 50;
    BufferedImage sprite;
    Enemy(String s){
        String[] raw = s.split("/");
        id = Integer.valueOf(raw[0]);
        x = Integer.valueOf(raw[1]);
        y = Integer.valueOf(raw[2]);
        hp = Integer.valueOf(raw[3]);
    }
}
