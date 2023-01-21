package GameAssets;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;

import java.awt.Image;


public abstract class Unit {
    // referance variables
    Map map;

    protected double x, y;
    protected int width, height;
    protected int dmg;
    protected int hp, maxHp;
    protected int def;
    protected double speed;
    protected int target = -1;

    // sprites and visuals
    protected BufferedImage spritesheet;
    protected int frame = 0;
    private int LEFT = 0;
    private int RIGHT = 1;
    protected int dir = LEFT;
    protected int FRAMES_PER_ANIMATION = 5;
    protected static int IDLE = -1;
    protected static int WALK = 0;
    protected static int ATK = 1;
    protected int animation = IDLE;
    protected Color color;

    int id;

    protected boolean selected = false;

    Unit(int x, int y, Map m){
        this.x = x;
        this.y = y;
        this.map = m;
    }

    public void update(){
        if (animation == IDLE && isMoving()){
            animation = WALK;
            frame = FRAMES_PER_ANIMATION;
        }
        if (animation == WALK && !isMoving()){
            animation = IDLE;
        }
        frame++;
        if (frame/FRAMES_PER_ANIMATION >= 8){
            frame = 0;
        }
    };
    protected abstract boolean isMoving();
    public abstract void move();
    public void draw(int xOff, int yOff, Graphics g){
        if (x + width/2 - xOff > 0 && x - width/2 - xOff < ScreenConsts.WINDOWWIDTH) {
            if (y + height/2 - yOff > 0 && y - height/2 - yOff < ScreenConsts.WINDOWHEIGHT) {
                if (selected){
                    g.setColor(Color.WHITE);
                    g.drawOval((int)(x - width/2 - 5 -xOff), (int)(y + height - width/4 - yOff), width+10, width/2);
                }
                if (spritesheet == null){
                    if (color == null) {
                        g.setColor(Color.YELLOW);
                    } else {
                        g.setColor(color);
                    }
                    g.fillRect((int)(x - width/2 -xOff), (int)(y - yOff), width, height);
                } else {
                    Image sprite;
                    if (animation == IDLE){
                        sprite = spritesheet.getSubimage(0, 0, width/ScreenConsts.PIXELRARIO, height/ScreenConsts.PIXELRARIO);
                    } else {
                        sprite = spritesheet.getSubimage((frame/FRAMES_PER_ANIMATION)*(width/ScreenConsts.PIXELRARIO), animation*(height/ScreenConsts.PIXELRARIO), width/ScreenConsts.PIXELRARIO, height/ScreenConsts.PIXELRARIO);
                    }
                    if (dir == LEFT){
                        g.drawImage(sprite, (int)(x - width/2 -xOff), (int)(y - height - yOff), width, height, null);
                    } else {
                        g.drawImage(sprite, (int)(x + width/2 -xOff), (int)(y - height - yOff), -width, height, null);
                    }
                }
                g.setColor(Color.RED);
                g.fillRect((int)(x - width/2 -xOff), (int)(y - yOff)+5, width, 5);
                g.setColor(Color.GREEN);
                g.fillRect((int)(x - width/2 -xOff), (int)(y - yOff)+5, (int)(width*(hp/(maxHp+0.0))), 5);
                
            }
        }
    }
    public void collides(AttackField a){
        if (a.hitUnit(this)){
            this.hp -= a.dmg();
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
        if (xmove > 0){
            this.dir = LEFT;
        } else {
            this.dir = RIGHT;
        }
        if (map.inBounds((int)(this.x+xmove), (int)(this.y))){
            x += xmove;
        } else {
            clearPath();
        }
        if (map.inBounds((int)(this.x), (int)(this.y-ymove))){
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

    public int xCenter(){
        return (int)x;
    }
    public int yCenter(){
        return (int)(y - height/2);
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
    public boolean isDead(){
        return this.hp <= 0;
    }
    protected abstract void clearPath();

    public String toString(){
        return " " + id + "/" + (int)x + "/" + (int)y + "/" + hp + "/" + maxHp + "/" + width+ "/" + height;
        // + "/" + sprite + "/" + frameNum;
    }
}
