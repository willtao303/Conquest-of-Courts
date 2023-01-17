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
        if (collidesPoint(p.x()-p.WIDTH, p.y()-p.HEIGHT)||collidesPoint(p.x()+p.WIDTH, p.y()-p.HEIGHT)||collidesPoint(p.x()-p.WIDTH, p.y()+p.HEIGHT)||collidesPoint(p.x()+p.WIDTH, p.y()+p.HEIGHT)){
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
            if (unit.x()-unit.width < point.x && point.x < unit.x()+unit.width && unit.y()-unit.height < point.y && point.y < unit.y()+unit.height){
                return true;
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
    public abstract void draw (int xOffset, int yOffset, Graphics g);

    public boolean dead(){
        return tickcounter >= ticks;
    }
    protected void kill(){
        tickcounter = ticks;
    }

    public abstract String toString();
}
