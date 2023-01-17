package GameAssets;

import java.awt.Graphics;
import java.awt.Color;

public class EnemyManager {
    Enemy player;
    Enemy[] defendingEnemies;
    Enemy[] attackingEnemies;
    private AttackField[] attacks;

    public void setPlayer(String args){
        player = new Enemy(args);
        player.width = 55;
        player.height = 90;
    }
    public void updateAttacks(){
        attacks = null;
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

    public void draw(Camera c, Graphics g){
        g.setColor(Color.RED);
        if (player != null){
            g.fillRect(player.x - player.width/2 - c.anchorX(), player.y - player.height/2 - c.anchorY(), player.width, player.height);
        }
        if (attackingEnemies != null){
            for (Enemy e: attackingEnemies){
                if (c.onScreen(e.x, e.y, 50)){
                    g.fillRect(e.x - e.width/2 - c.anchorX(), e.y - e.height/2 - c.anchorY(), e.width, e.height);
                }
            }
        }
        if (defendingEnemies != null){
            for (Enemy e: defendingEnemies){
                if (c.onScreen(e.x, e.y, 50)){
                    g.fillRect(e.x - e.width/2 - c.anchorX(), e.y - e.height/2 - c.anchorY(), e.width, e.height);
                }
            }
        }
    }

    public AttackField[] attacks(){
        if (attacks == null){
            return new AttackField[]{};
        }
        return attacks;
    }
    
}
