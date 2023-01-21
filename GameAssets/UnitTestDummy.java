package GameAssets;

import java.awt.Graphics;

import java.awt.Color;

public class UnitTestDummy extends Unit{

    UnitTestDummy(int x, int y, Map m){
        super(x,y,m);
        hp = maxHp = 99;
        this.x = x;
        this.y = y;
        width = 35;
        height = 45;
        speed = 0;
    }

    @Override
    public void move() {}

    @Override
    public void draw(int xOff, int yOff, Graphics g) {
        if (x + width/2 - xOff > 0 && x - width/2 - xOff < ScreenConsts.WINDOWWIDTH) {
            if (y + height/2 - yOff > 0 && y - height/2 - yOff < ScreenConsts.WINDOWHEIGHT) {
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

    @Override
    public void update() {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected boolean isMoving() {
        // Test dummy does not move B)
        return false;
    }
    
}
