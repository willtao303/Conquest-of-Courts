
import java.awt.Graphics;
import java.awt.Color;

public class Tower {
    public static final int ARCHER = 1;
    public static final int BOMBER = 2;
    public static final int BARRACK = 3;
    public static final int MAGE = 4;
    public static final int CASTLE = 5;

    protected Color color;
    public int NUM_OF_UPGRADES = 3;
    public int[] UPGRADES = {-1, -1, -1};
    
    protected int x, y;
    protected int hp, maxHp;

    protected int range;

    public static final int PROJECTILE = 1;
    public static final int BALLISTIC = 2;
    public static final int DRAIN = 3;
    protected int projectile = -1;
    protected int dmg;

    public static final int ATTACKING = 1;
    public static final int DEFENDING = 2;
    protected int unitType = -1;

    protected int cooldown;
    protected int cooldownCounter = 0;

    Tower(int x, int y, int hp){
        this.x = x;
        this.y = y;
        this.hp = hp;
    }
    public void update(EnemyManager e, UnitManager u){

    }
    public void draw(int x, int y, int size, Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, size, size);
    }
    public Tower upgrade(int buttonClicked) {
        
        return null;
    }

    public void shoot(){
        if (projectile != -1){

        }
    }
    public void spawn(){
        if (unitType != -1){

        }
    }
    

    public int upgradeByButton(int id){
        if (id < NUM_OF_UPGRADES){
            return UPGRADES[id];
        }
        return -1;
    }
    public int distTo(int x, int y){
        return (int)Math.sqrt((this.x - x)*(this.x - x) + (this.y - y)*(this.y - y));
    }
}
