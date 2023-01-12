
import java.awt.Graphics;

import java.awt.Color;

public class UTestDummy extends Unit{

    UTestDummy(int x, int y, Map m, EnemyManager e){
        super(x,y,m,e);
        hp = maxHp = 99;
        this.x = x;
        this.y = y;
        width = 35;
        height = 45;
        speed = 0;
    }

    @Override
    public void update() {
        if (hp < 0){
            this.dead = true;
        }
    }

    @Override
    public void move() {}

    @Override
    public void draw(int xOff, int yOff, Graphics g) {
        if (x + width/2 - xOff > 0 && x - width/2 - xOff < Consts.WINDOWWIDTH) {
            if (y + height/2 - yOff > 0 && y - height/2 - yOff < Consts.WINDOWHEIGHT) {
                g.setColor(Color.YELLOW);
                g.fillRect((int)(x - width/2 -xOff), (int)(y - height/2-yOff), width, height);
                if (selected){
                    g.setColor(Color.WHITE);
                    g.drawRect((int)(x - width/2 -xOff), (int)(y - height/2-yOff), width, height);
                }
                g.setColor(Color.RED);
                g.fillRect((int)(x - width/2 -xOff), (int)(y + height/2 - yOff)+2, width, 5);
                g.setColor(Color.GREEN);
                g.fillRect((int)(x - width/2 -xOff), (int)(y + height/2 - yOff)+2, (int)(width*(hp/(maxHp+0.0))), 5);

            }
        }
        
    }

    @Override
    protected void clearPath() {
    }
    
}
