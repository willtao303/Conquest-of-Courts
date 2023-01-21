package GameAssets;

import java.awt.Graphics;
import java.util.LinkedList;
import java.awt.Color;

public class TowerSlot {
    EnemyManager enemies;
    Resources resources;
    int index;

    Tower tower;
    final static int SIZE = 70;
    private final static int[][] BUTTON_OFFSETS = {
        {52, -78},
        {60, -30},
        {55, 20},
        {15, 58},
        {-35, 65}
    };
    private final static int NUM_BUTTONS = 5;
    private final static int[] INITIAL_UPGRADES = {
        TowerPreset.ARCHER,
        TowerPreset.BOMBER,
        TowerPreset.MAGE,
        -1,
        TowerPreset.CASTLE
    };
    private final static int BUTTON_DIAM = 40;
    private final static int PD = 3; // padding for the buttons hitbox
    private int x;
    private int y;
    private boolean focused;
    private int hover = -1;
    
    TowerSlot(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void update(LinkedList<AttackField> attacks, UnitManager units, Map map){
        if (tower != null){
            tower.update();
            if (tower.isShootingType()){
                if (enemies.attackingEnemies != null){
                    for (Enemy e: enemies.attackingEnemies){
                        if (tower.blindRange < tower.distTo(e.x, e.y) && tower.distTo(e.x, e.y) < tower.range){
                            if (tower.shootCooldown() <= 0){
                                attacks.add(tower.shoot(e.x, e.y));
                            }
                        }
                    }
                }
            }
            if (tower.isSpawningType()){
                if (tower.spawnCooldown() <= 0){
                    Unit u = tower.spawn(x, y, map);
                    if (u != null){
                        units.spawn(u);
                    }
                }
            }
        }
    }
    
    public void draw(int offsetX, int offsetY, Graphics g){
        if (focused){
            g.setColor(Color.GRAY);
            int [] upgrades;
            if (tower == null){
                upgrades = INITIAL_UPGRADES;
            } else {
                upgrades = tower.UPGRADES;
            }
            for (int i = 0; i < upgrades.length; i++){
                if (upgrades[i] != -1){
                    if (i == hover){
                        g.setColor(new Color(240, 188, 58));
                        g.fillRect(x+BUTTON_OFFSETS[i][0]-offsetX + BUTTON_DIAM + 5, y+BUTTON_OFFSETS[i][1]-offsetY + BUTTON_DIAM/2 - 3, 6, 6);
                        if (resources.coins < TowerPreset.STATS[upgrades[i]][TowerPreset.COST]){
                            g.setColor(Color.RED);
                        } else {
                            g.setColor(Color.WHITE);
                        }
                        g.drawString(TowerPreset.STATS[upgrades[i]][TowerPreset.COST]+"", x+BUTTON_OFFSETS[i][0]-offsetX + BUTTON_DIAM + + 15, y+BUTTON_OFFSETS[i][1]-offsetY + BUTTON_DIAM/2 +5);
                    }
                    g.setColor(TowerPreset.COLOR[upgrades[i]]);
                } else {
                    g.setColor(Color.DARK_GRAY);
                }
                g.fillOval(x+BUTTON_OFFSETS[i][0]-offsetX, y+BUTTON_OFFSETS[i][1]-offsetY, BUTTON_DIAM, BUTTON_DIAM);
            }
        } else {
            g.setColor(Color.GRAY);
        }
        if (tower != null){
            tower.draw(x-SIZE/2-offsetX, y-SIZE/2-offsetY, SIZE, g);
            tower.drawRange(x-offsetX, y-offsetY, focused, g);
        } else {
            g.fillRect(x-SIZE/2-offsetX, y-SIZE/2-offsetY, SIZE, SIZE);
        }
    }

    public void unfocus(){
        focused = false;
    }
    public void focus(){
        focused = true;
    }

    public boolean clicked(int mouseX, int mouseY){
        return x-SIZE/2 < mouseX && mouseX < x+SIZE/2 && y-SIZE/2 < mouseY  && mouseY < y+SIZE/2;
    }
    public void buttonHovered(int mouseX, int mouseY){
        int [] upgrades;
        if (tower == null){
            upgrades = INITIAL_UPGRADES;
        } else {
            upgrades = tower.UPGRADES;
        }
        int buttonClicked = 0;
        for (int i = 0; i < upgrades.length; i++){
            if (x+BUTTON_OFFSETS[i][0]-PD < mouseX && mouseX < x+BUTTON_OFFSETS[i][0]+BUTTON_DIAM+PD && y+BUTTON_OFFSETS[i][1]-PD < mouseY && mouseY < y+BUTTON_OFFSETS[i][1]+BUTTON_DIAM+PD){
                break;
            }
            buttonClicked++;
        }
        if (buttonClicked != upgrades.length){
            hover = buttonClicked;
        } else {
            hover = -1;
        }
    }
    public boolean buttonClicked(){
        if (hover == -1){
            return false;
        }
        int [] upgrades;
        if (tower == null){
            upgrades = INITIAL_UPGRADES;
        } else {
            upgrades = tower.UPGRADES;
        }
        if (upgrades[hover] != -1){
            if (resources.coins > TowerPreset.STATS[upgrades[hover]][TowerPreset.COST]){
                resources.coins -= TowerPreset.STATS[upgrades[hover]][TowerPreset.COST];
                tower = new Tower(x, y, upgrades[hover]);
            }
        }
        return true;

    }

    public int x(){
        return x;
    }
    public int y(){
        return y;
    }

    public void setEnemies(EnemyManager e){
        this.enemies = e;
    }
    public void setResources(Resources r){
        resources = r;
    }

    public String toString(){
        return index + "/" + tower.preset + "/" + tower.hp + "/" + 0;
    }
}
