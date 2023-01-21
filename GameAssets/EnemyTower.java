package GameAssets;

import java.awt.Graphics;

public class EnemyTower {
    int x;
    int y;

    int type = -1;
    int hp;
    int frame;

    EnemyTower(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setTower(String args){
        String[] raw = args.split("/");
        type = Integer.valueOf(raw[0]);
        hp = Integer.valueOf(raw[1]);
        frame = Integer.valueOf(raw[2]);
    }

    public void draw(int xOff, int yOff, Graphics g){

    }
}
