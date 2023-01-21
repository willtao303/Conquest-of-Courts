package GameAssets;
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;


public class BaseCrystal {
    private int maxHp = 1000, hp = maxHp;
    private final int width = 80, height = 150;
    private Point pos;
    BaseCrystal(Point home){
        pos = home;
    }
    public boolean lost(){
        return this.hp < 0;
    }
    public void hit(LinkedList<AttackField> attacks){
        for (AttackField a: attacks){
            if (a.hitCrystal(this)){
                this.hp -= a.dmg();
            }
        }
    }
    public void draw(Graphics g, Camera c){
        if (c.onScreen(pos.x, pos.y, 100)){
            g.setColor(Color.magenta);
            g.fillRect(pos.x-(width/2)-c.anchorX(), pos.y-(height/2)-c.anchorY(), width, height);

            g.setColor(Color.red);
            g.fillRect(pos.x, pos.y+height+7, width, 10);
            if (hp > 0){
                g.setColor(Color.green);
                g.fillRect(pos.x, pos.y+height+7, (int)(width*((hp+0.0)/maxHp)), 10);
            }
        }
    }
    
    public int x(){
        return pos.x;
    }
    public int y(){
        return pos.y;
    }
    public int width(){
        return width;
    }
    public int height(){
        return height;
    }
}
