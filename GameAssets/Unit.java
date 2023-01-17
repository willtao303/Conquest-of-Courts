package GameAssets;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


public abstract class Unit {
    // referance variables
    Map map;
    EnemyManager enemyManager;

    protected double x, y;
    int width, height;
    int dmg;
    int hp, maxHp;
    int def;
    double speed;
    Unit target = null;
    BufferedImage sprites;
    boolean dead;

    int id;

    protected boolean selected = false;

    Unit(int x, int y, Map m, EnemyManager e){
        this.x = x;
        this.y = y;
        this.map = m;
        this.enemyManager = e;
    }

    public abstract void update();
    public abstract void move();
    public abstract void draw(int xOff, int yOff, Graphics g);
    public void collides(AttackField a){
        if (a.hitUnit(this)){
            this.hp -= a.dmg;
        }
    }

    protected double distTo(Point other){
        return Math.sqrt((x-other.x)*(x-other.x) + (y-other.y)*(y-other.y));
    }
    protected double distTo(double x, double y){
        return Math.sqrt((x-this.x)*(x-this.x) + (y-this.y)*(y-this.y));
    }

    protected void moveTowards(Point p){
        double dir = Math.atan2((p.x-this.x), (p.y-this.y))+4.71225;
        double xmove = (speed*Math.cos(dir));
        double ymove = (speed*Math.sin(dir));
        if (map.inBounds((int)(this.x+xmove), (int)(this.y))){
            x += xmove;
        } else {
            clearPath();
        }
        if (map.inBounds((int)(this.x), (int)(this.y+ymove))){
            y -= ymove;
        } else {
            clearPath();
        }
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public void focus(){
        this.selected = true;
    }
    public void unfocus(){
        this.selected = false;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public boolean isSelected(){
        return this.selected;
    }
    protected abstract void clearPath();

}
