package GameAssets;

import java.awt.Graphics;
import java.awt.Color;

public class Tower {

    protected Color color;
    public int NUM_OF_UPGRADES = 3;
    public int[] UPGRADES = {-1, -1, -1};
    
    protected int x, y;
    protected int hp, maxHp;

    protected int range;
    protected int blindRange;

    public static final int NONE = -1;

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

    int preset;


    Tower(int x, int y, int preset){
        this.x = x;
        this.y = y;
        this.preset = preset;
        maxHp = TowerPreset.STATS[preset][0];
        hp = maxHp;
        range = TowerPreset.STATS[preset][1];
        projectile = TowerPreset.STATS[preset][2];
        unitType = TowerPreset.STATS[preset][3];
        dmg = TowerPreset.STATS[preset][4];
        cooldown = TowerPreset.STATS[preset][5];
        NUM_OF_UPGRADES = TowerPreset.UPGRADES[preset].length;
    }
    Tower(int x, int y, int hp, int preset){
        this(x, y, preset);
        this.hp = hp;
    }
    public void update(){
        cooldownCounter--;
    }
    public void draw(int x, int y, int size, Graphics g) {
        g.setColor(TowerPreset.COLOR[preset]);
        g.fillRect(x, y, size, size);
    }

    public AttackFieldBullet shoot(int x, int y){
        if (projectile != -1){
            cooldownCounter = cooldown;
            Point start = new Point(this.x, this.y);
            double dir = start.dirTo(x, y);
            return new AttackFieldBullet(dmg, start, (int)dir, 15, (int)(range*1.2));
        }
        return null;
    }
    public int shootCooldown(){
        return cooldownCounter;
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

    public boolean isShootingType(){
        return projectile != -1;
    }

    public boolean isSpawningType(){
        return unitType != -1;
    }
}
