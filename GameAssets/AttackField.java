package GameAssets;
import java.awt.Color;
import java.awt.Graphics;

public abstract class AttackField {
    protected int dmg;
    private int tickdmg;
    private int tickcounter = 0;
    private int ticks;
    protected boolean active = true;
    
    AttackField(int dmg){
        this.dmg = dmg;
        this.tickdmg = 0;
        this.ticks = 1;
    }
    AttackField(int dmg, int tickdmg, int ticks){
        this.dmg = dmg;
        this.tickdmg = tickdmg;
        this.ticks = ticks;
        
    }


    public boolean hitPlayer(Player p){
        if (collidesPoint(p.x()-p.WIDTH/2, p.y()-p.HEIGHT/2)||collidesPoint(p.x()+p.WIDTH/2, p.y()-p.HEIGHT/2)||collidesPoint(p.x()-p.WIDTH/2, p.y()+p.HEIGHT/2)||collidesPoint(p.x()+p.WIDTH/2, p.y()+p.HEIGHT/2)){
            return true;
        } else {
            for (Point point: getPoints()){
                if (p.x()-p.WIDTH < point.x && point.x < p.x()+p.WIDTH && p.y()-p.HEIGHT < point.y && point.y < p.y()+p.HEIGHT){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hitUnit(Unit unit) {
        if (collidesPoint(new Point(unit.x, unit.y))){
            return true;
        }
        for (Point point: getPoints()){
            if (unit.xCenter()-unit.width < point.x && point.x < unit.xCenter()+unit.width && unit.yCenter()-unit.height < point.y && point.y < unit.yCenter()+unit.height){
                return true;
            }
        }
        return false;
    }

    public boolean hitCrystal(BaseCrystal c){
        if (collidesPoint(c.x()-c.width()/2, c.y()-c.height()/2)||
            collidesPoint(c.x()-c.width()/2, c.y()+c.height()/2)||
            collidesPoint(c.x()+c.width()/2, c.y()-c.height()/2)||
            collidesPoint(c.x()+c.width()/2, c.y()+c.height()/2)){
            return true;
        } else {
            for (Point point: getPoints()){
                if (c.x()-c.width() < point.x && point.x < c.x()+c.width() && 
                    c.y()-c.height() < point.y && point.y < c.y()+c.height()){
                    return true;
                }
            }
        }
        return false;
    }

    public void update(){
        this.tickcounter++;
        move();
    }
    public int dmg(){
        if (!active){
            return 0;
        }
        onhit();
        if (tickcounter == 0){
            return dmg;
        } else {
            return tickdmg;
        }
    }

    protected boolean collidesPoint(int x, int y){
        return collidesPoint(new Point(x,y));
    }
    protected abstract boolean collidesPoint(Point p);
    protected abstract Point[] getPoints();
    protected void onhit(){}


    public void move(){}
    public abstract void draw (int xOff, int yOff, Graphics g);

    public boolean dead(){
        return tickcounter >= ticks;
    }
    protected void kill(){
        tickcounter = ticks;
        active = false;
    }

    public abstract String toString();
}
