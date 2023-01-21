package GameAssets;

import java.awt.Graphics;
import java.util.HashSet;
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

    protected int shootCooldown;
    protected int shootCooldownCounter = 0;

    public static final int ATTACKING = 1;
    public static final int DEFENDING = 2;
    protected int unitType = -1;
    protected int unit;

    protected int spawnCooldown;
    protected int spawnCooldownCounter = 100;
    protected HashSet<Unit> spawns;
    protected int maxSpawns;
    protected int spawnRange;

    int preset;


    Tower(int x, int y, int preset){
        this.x = x;
        this.y = y;
        this.preset = preset;
        // stats
        maxHp = TowerPreset.STATS[preset][0];
        dmg = TowerPreset.STATS[preset][1];
        hp = maxHp;
        // ranged stuff
        projectile = TowerPreset.STATS[preset][2];
        range = TowerPreset.STATS[preset][3];
        shootCooldown = TowerPreset.STATS[preset][4];
        // melee stuff
        unitType = TowerPreset.STATS[preset][5];
        unit = TowerPreset.STATS[preset][6];
        spawnCooldown = TowerPreset.STATS[preset][7];
        spawns = new HashSet<Unit>();
        maxSpawns = TowerPreset.STATS[preset][8];
        spawnRange = TowerPreset.STATS[preset][9];

        NUM_OF_UPGRADES = TowerPreset.UPGRADES[preset].length;
        UPGRADES = TowerPreset.UPGRADES[preset];
    }
    Tower(int x, int y, int hp, int preset){
        this(x, y, preset);
        this.hp = hp;
    }
    public void update(){
        if (shootCooldownCounter > 0){
            shootCooldownCounter--;
        } 
        if (spawnCooldownCounter > 0){
            spawnCooldownCounter--;
        }
        if (unitType != NONE){
            for (Unit u: spawns){
                if (u.isDead()){
                    spawns.remove(u);
                    break;
                }
            }
        }
    }
    public void draw(int x, int y, int size, Graphics g) {
        g.setColor(TowerPreset.COLOR[preset]);
        g.fillRect(x, y, size, size);
    }

    public void drawRange(int x, int y, boolean towerSelected, Graphics g){
        if (towerSelected){
            if (isSpawningType()){
                g.setColor(Color.ORANGE);
                g.drawOval(x-spawnRange, y-spawnRange, spawnRange*2, spawnRange*2);
            }
            
            if (isShootingType()){
                g.setColor(Color.BLACK);
                g.drawOval(x-range, y-range, range*2, range*2);
            }
        } else {
            if (isSpawningType()){
                for (Unit u: spawns){
                    if (u.isSelected()){
                        g.setColor(Color.ORANGE);
                        g.drawOval(x-spawnRange, y-spawnRange, spawnRange*2, spawnRange*2);
                        return;
                    }
                }

            }
        }
    }

    public AttackFieldBullet shoot(int x, int y){
        if (projectile != -1){
            shootCooldownCounter = shootCooldown;
            Point start = new Point(this.x, this.y);
            double dir = start.dirTo(x, y);
            return new AttackFieldBullet(dmg, start, (int)dir, 15, (int)(range*1.2));
        }
        return null;
    }
    public int shootCooldown(){
        return shootCooldownCounter;
    }
    public Unit spawn(int x, int y, Map m){
        if (unitType == ATTACKING){
            //UnitAttacker = new UnitAttacker() 
            //spawnCooldownCounter = spawnCooldown;
        } else if (unitType == DEFENDING){
            if (spawns.size() < maxSpawns){
                double dir = Math.random()* 6.282 - 3.14;
                double dist = Math.random()*30 + 50;
                x += Math.cos(dir)*(50+dist);
                y += Math.sin(dir)*(50+dist);
                if (m.inBounds(x, y)){
                    spawnCooldownCounter = spawnCooldown;
                    UnitDefender spawnedUnit = null;
                    if(unit == UnitDefender.KNIGHT){
                        spawnedUnit = new UKnight(x, y, m, new Point(this.x, this.y));
                    }
                    
                    if (spawns != null){
                        spawns.add(spawnedUnit);
                    }
                    return spawnedUnit;
                }
            }
        }
        return null;
    }
    public int spawnCooldown(){
        return spawnCooldownCounter;
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
