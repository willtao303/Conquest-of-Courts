package GameAssets;

import java.awt.image.BufferedImage;

public class Enemy {
    public int id;
    public int x;
    public int y;
    public int hp;
    public int maxhp;
    public int width = 50, height = 50;
    BufferedImage sprite;
    Enemy(String s){
        String[] raw = s.split("/");
        id = Integer.valueOf(raw[0]);
        x = Integer.valueOf(raw[1]);
        y = Integer.valueOf(raw[2]);
        hp = Integer.valueOf(raw[3]);
        maxhp = Integer.valueOf(raw[4]);
        width = Integer.valueOf(raw[5]);
        height = Integer.valueOf(raw[6]);
    }
    Enemy(String s, boolean isPlayer){
        String[] raw = s.split("/");
        id = Integer.valueOf(raw[0]);
        x = Integer.valueOf(raw[1]);
        y = Integer.valueOf(raw[2]);
        hp = Integer.valueOf(raw[3]);
        maxhp = hp;
    }
}
