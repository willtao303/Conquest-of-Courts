package GameAssets;

import java.awt.Graphics;
import java.awt.Color;

public class EnemyManager {
    Enemy player;
    Enemy[] defendingEnemies;
    Enemy[] attackingEnemies;

    public void setPlayer(String args){
        player = new Enemy(args);
        player.width = 55;
        player.height = 90;
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
    
}
