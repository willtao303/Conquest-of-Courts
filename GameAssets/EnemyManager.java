package GameAssets;

import java.awt.Graphics;
import java.awt.Color;

public class EnemyManager {
    int side;
    Enemy player;
    Enemy[] defendingEnemies;
    Enemy[] attackingEnemies;
    EnemyTower[] towers;

    private AttackField[] attacks;

    
    public void setupTowers(int[][] towerPresets){
        towers = new EnemyTower[towerPresets.length];
        int j = 0;
        for (int[] towerCoords: towerPresets){
            towers[j] = new EnemyTower(towerCoords[0], towerCoords[1]);
            j++;
        }
    }

    public void setSide(int side){
        this.side = side;
    }
    public void setPlayer(String args){
        player = new Enemy(args, true);
        player.width = 55;
        player.height = 90;
    }
    public void setTowers(String[] args){
        for (int i = 0; i < args.length; i ++){
            String[] argsSplit = args[i].split("/", 2);
            towers[Integer.valueOf(argsSplit[0])].setTower(argsSplit[1]);
        }
    }
    public void setAttackers(String[] args){
        attackingEnemies = new Enemy[args.length];
        for (int i = 0; i < args.length; i ++){
            attackingEnemies[i] = new Enemy(args[i]);
        }
    }
    public void setDefenders(String[] args){
        defendingEnemies = new Enemy[args.length];
        for (int i = 0; i < args.length; i ++){
            defendingEnemies[i] = new Enemy(args[i]);
        }
    }
    public void setAttacks(String[] args){
        attacks = new AttackField[args.length];
        for (int i = 0; i < args.length; i ++){
            String[] params = args[i].split("/");
            if (params[0].equals("1")){
                attacks[i] = new AttackFieldPoint(Integer.valueOf(params[3]), Integer.valueOf(params[1]),Integer.valueOf(params[2]));
            } else if (params[0].equals("3")){
                attacks[i] = new AttackFieldTriangle(
                    Integer.valueOf(params[7]), 
                    new Point(Integer.valueOf(params[1]), Integer.valueOf(params[2])),
                    new Point(Integer.valueOf(params[3]), Integer.valueOf(params[4])),
                    new Point(Integer.valueOf(params[5]),Integer.valueOf(params[6]))
                );
            }
        }
    }


    public void clearAttacks(){
        attacks = null;
    }
    public void clearAttackers(){
        attackingEnemies = null;
    }
    public void clearDefenders(){
        defendingEnemies = null;
    }
    public AttackField[] attacks(){
        if (attacks == null){
            return new AttackField[]{};
        }
        return attacks;
    }

    

    public void draw(Camera c, Graphics g){
        g.setColor(Color.RED);
        if (player != null){
            g.fillRect(player.x - player.width/2 - c.anchorX(), player.y - player.height/2 - c.anchorY(), player.width, player.height);
        }
        if (attackingEnemies != null){
            for (Enemy e: attackingEnemies){
                if (c.onScreen(e.x, e.y, 50)){
                    g.setColor(Color.RED);
                    g.fillRect(e.x - e.width/2 - c.anchorX(), e.y - e.height/2 - c.anchorY(), e.width, e.height);
                    g.fillRect(e.x - e.width/2 - c.anchorX(), e.y + e.height/2 - c.anchorY() + 5, e.width, 5);
                    g.setColor(Color.GREEN);
                    g.fillRect(e.x - e.width/2 - c.anchorX(), e.y + e.height/2 - c.anchorY() + 5, (int)(e.width * (e.hp)/(e.maxhp+0.0)), 5);
                }
            }
        }
        if (defendingEnemies != null){
            for (Enemy e: defendingEnemies){
                if (c.onScreen(e.x, e.y, 50)){
                    g.setColor(Color.RED);
                    g.fillRect(e.x - e.width/2 - c.anchorX(), e.y - e.height/2 - c.anchorY(), e.width, e.height);
                    g.fillRect(e.x - e.width/2 - c.anchorX(), e.y + e.height/2 - c.anchorY() + 5, e.width, 5);
                    g.setColor(Color.GREEN);
                    g.fillRect(e.x - e.width/2 - c.anchorX(), e.y + e.height/2 - c.anchorY() + 5, (int)(e.width * (e.hp)/(e.maxhp+0.0)), 5);
                }
            }
        }

        for (EnemyTower tower : towers){
            if (c.onScreen(tower.x, tower.y, TowerSlot.SIZE/2)){
                if (side == Game.RED){
                    g.setColor(Color.RED);
                } else if (side == Game.BLUE){
                    g.setColor(Color.BLUE);
                }
                g.fillRect(tower.x-TowerSlot.SIZE/2 - c.anchorX()-3, tower.y-TowerSlot.SIZE/2 - c.anchorY()-3, TowerSlot.SIZE+6, TowerSlot.SIZE+6);
                if (tower.type != -1){
                    g.setColor(TowerPreset.COLOR[tower.type]);
                } else {
                    g.setColor(Color.GRAY);
                }
                g.fillRect(tower.x-TowerSlot.SIZE/2 - c.anchorX(), tower.y-TowerSlot.SIZE/2 - c.anchorY(), TowerSlot.SIZE, TowerSlot.SIZE);
            }
        }
    }

    
    
}
