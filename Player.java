
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Queue;

import Containters.Point;

import java.awt.Color;

public class Player {
    private Point respawnAnchor;
    private Map map;

    public final int HEIGHT = 90;
    public final int WIDTH = 55;
    private double x, y;
    private double direction;
    Weapon weapon;
    
    private int level = 1;
    private int maxHp = 200, hp = maxHp;
    private int dead = -1;
    int maxMana = 100, mana = maxMana;
    double atk, def, intg, spd;
    double speed = 8;

    double critRate = 0.05;
    double critDmg = 1.5;

    String username;
    Queue<Point> path = new LinkedList<Point>();

    Player(){
    }

    public void setup(Point respawn, String side, Map map){
        if (side == Game.RED){

        } else if (side == Game.BLUE){
            
        }
        respawnAnchor = respawn;
        this.map = map;
        weapon = new WBroadsword(this);
        moveTo(respawn);
    }

    public void update(){
        if (this.dead < 0){
            if (this.hp <= 0){
                this.dead = 200; // TODO change this to calculation eventually
                this.moveTo(respawnAnchor);
            }
            if (!path.isEmpty()){
                moveTowards(path.peek());
                if (path.peek().distTo((int)x, (int)y) < WIDTH/3*2){
                    path.remove();
                }
            }
            if (weapon.attackCD > 0){
                weapon.attackCD--;
            }
            if (weapon.skillCD > 0){
                weapon.skillCD--;
            }
        } else {
            if (this.dead == 0){
                this.hp = maxHp;
                this.path.clear();
            }
            this.dead--;
        }
    }

    public void hit(int dmg){
        this.hp -= dmg;
    }

    
    public void equip(Weapon weapon){
        this.weapon = weapon;
    }

    public void moveTowards(int x, int y){
        move(Math.toDegrees(Math.atan2((x-this.x), (y-this.y)))+270, speed);
    }
    public void moveTowards(Point p){
        moveTowards(p.x, p.y);
    }

    public void moveTo(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void moveTo(Point p){
        this.x=p.x; 
        this.y=p.y;
    }

    public void move(boolean up, boolean down, boolean left, boolean right){
        if (!path.isEmpty()){
            path.clear();
        }
        double ymove = 0;
        double xmove = 0;
        if (up){
            ymove--;
        } 
        if (down){
            ymove++;
        }
        if (left){
            xmove--;
        }
        if (right){
            xmove++;
        }
        if (xmove != 0 && ymove != 0){
            xmove /= 1.414;
            ymove /= 1.414;
        }
        moveBy(xmove*speed, ymove*speed);
    }

    private void moveBy(double xmove, double ymove){
        if (map.inBounds((int)(x+xmove), (int)y)){
            this.x += xmove;
        }
        if (map.inBounds((int)x, (int)(y+ymove))){
            this.y += ymove;
        }
    }
    private void move(double dir, double dist){
        double xmove = (dist*Math.cos(Math.toRadians(dir)));
        double ymove = (dist*Math.sin(Math.toRadians(dir)));
        moveBy(xmove, -ymove);
    }

    public boolean collidesPoint(int x, int y){
        return (this.x-WIDTH/2 < x && x < this.x+WIDTH/2 && this.y-HEIGHT/2 < y && y < this.y+HEIGHT/2);
    }
    

    public void generatePath(int x, int y){

    }
    public void shortPath(int x, int y){
        path.clear();
        path.add(new Point(x, y));
    }

    public void draw(int xOffset, int yOffset, Graphics g){
        if (dead < 0) {
            g.setColor(Color.MAGENTA);
            g.fillRect((int)x - xOffset -WIDTH/2, (int)y - yOffset-HEIGHT/2, WIDTH, HEIGHT);
        }
        if (!path.isEmpty()){
            //g.setColor(Color.YELLOW);
            //g.fillRect(path.peek().x - xOffset - 5, path.peek().y - yOffset-5, 10, 10);
        }
    }

    public int hp(){
        return hp;
    }
    public int maxHp(){
        return maxHp;
    }

    public int x(){
        return (int)x;
    }
    public int y(){
        return (int)y;
    }

    public boolean isDead(){
        return dead >= 0;
    }
    public boolean respawned(){
        return dead == 0;
    }
    public int respawningIn(){
        return dead/30;
    }
}
