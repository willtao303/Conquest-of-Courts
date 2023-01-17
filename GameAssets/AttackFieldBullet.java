package GameAssets;

import java.awt.Graphics;
import java.util.HashSet;
import java.awt.Color;

public class AttackFieldBullet extends AttackFieldPoint{
    private int speed;
    private int dir;
    private int iframes = 5;
    private int piercing;
    private HashSet<Integer> unitsHit = new HashSet<Integer>();
    AttackFieldBullet(int dmg, Point start, int dir, int speed, int range){
        this(dmg, start, dir, speed, range, 3);
    }
    AttackFieldBullet(int dmg, Point start, int dir, int speed, int range, int piercing){
        super(0, dmg, range/speed, start);
        this.speed = speed;
        this.dir = dir;
        this.piercing = piercing;
    }

    public void update(){
        super.update();
        if (unitsHit.size() >= piercing){
            this.kill();
        }
    }
    @Override
    public void move(){
        double xmove = speed*Math.cos(Math.toRadians(dir));
        double ymove = speed*Math.sin(Math.toRadians(dir));
        pos.x += xmove;
        pos.y -= ymove;
    }

    @Override
    protected boolean collidesPoint(Point p) {
        return pos.distTo(p) < 5;
    }

    @Override
    public boolean hitUnit(Unit unit) {
        if (unitsHit.contains(unit.id)){
            return false;
        }
        if (super.hitUnit(unit)){
            unitsHit.add(unit.id);
            return true;
        }
        return false;
    }

    public void collidesWall(Map m){
        if (iframes <= 0){
            if (!m.inBounds(pos.x, pos.y)){
                this.kill();
            }
        } else {
            iframes--;
        }
    }
}
