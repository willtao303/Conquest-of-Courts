package GameAssets;

import java.awt.Graphics;
import java.awt.Color;

public class AttackFieldPoint extends AttackField{
    protected Point pos;
    AttackFieldPoint(int dmg, int x, int y){
        super(dmg);
        pos = new Point(x, y);
    }
    AttackFieldPoint(int dmg, Point p){
        super(dmg);
        pos = p;
    }
    AttackFieldPoint(int dmg, int tickDmg, int ticks, int x, int y){
        super(dmg, tickDmg, ticks);
        pos = new Point(x, y);
    }
    AttackFieldPoint(int dmg, int tickDmg, int ticks, Point p){
        super(dmg, tickDmg, ticks);
        pos = p;
    }

    @Override
    protected boolean collidesPoint(Point p) {
        return pos.distTo(p) < 5;
    }

    @Override
    public void draw(int xOff, int yOff, Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(pos.x - 5 - xOff, pos.y - 5 - yOff, 10, 10);
        
    }

    @Override
    protected Point[] getPoints() {
        return new Point[]{pos};
    }

    @Override
    public String toString() {
        return "1/"+pos.x+"/"+pos.y+"/"+dmg();
    }
}
