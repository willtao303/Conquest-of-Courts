package GameAssets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class MiniMap {
    public final static int SIZE = 200;

    Map map;
    Player player;
    EnemyManager enemies;
    UnitManager units;
    TowerSlot[] towers;
    Camera cam;

    boolean centerPlayer = false;
    private final int scale = 9;

    public void addResources(Game game){
        map = game.map;
        player = game.player;
        enemies = game.enemyUnits;
        units = game.playerUnits;
        towers = game.towers;
        cam = game.camera;
    }

    public void draw(int xPos, int yPos, Graphics g){
        if (centerPlayer){

        } else {

            // unit conversion (just notes for myself)
            // spriteunit = regular / pixelratio
            // regular = spriteunit * pixelratio
            //  - 1 pixel on a sprite = pixelratio pixels in the game
            // minimapunit = regular / minimap scale
            // regular = minimapunit * minimap scale
            // - 1 on the minimap = minimapscale pixels in game
    
            BufferedImage rawImage = map.getPresetMap().image;
            // these are in regular units
            int subX = (cam.x()-(SIZE*scale/2)); // anchor
            int subY = (cam.y()-(SIZE*scale/2)); // anchor
            int subWidth = (SIZE*SIZE);
            int subHeight = (SIZE*SIZE);
            int xOverflow = 0;
            int yOverflow = 0;

            if (subWidth > rawImage.getWidth()*ScreenConsts.PIXELRARIO){
                xOverflow = (subWidth - (rawImage.getWidth()*ScreenConsts.PIXELRARIO))/2;
                subWidth = rawImage.getWidth()*ScreenConsts.PIXELRARIO;
                subX = 0;
            } else {
                if (subX < 0){
                    subX = 0;
                }
                if (subX + subWidth > rawImage.getWidth()*ScreenConsts.PIXELRARIO){
                    subX = rawImage.getWidth()*ScreenConsts.PIXELRARIO - subWidth;
                }
            }
            if (subHeight > rawImage.getHeight()*ScreenConsts.PIXELRARIO){
                
                yOverflow = (subHeight - (rawImage.getHeight()*ScreenConsts.PIXELRARIO))/2;
                subHeight = rawImage.getHeight()*ScreenConsts.PIXELRARIO;
                subY= 0;
            } else {
                if (subY < 0){
                    subY = 0;
                }
                if (subY + subHeight > rawImage.getHeight()*ScreenConsts.PIXELRARIO){
                    subY = rawImage.getHeight()*ScreenConsts.PIXELRARIO - subHeight;
                }
            }

            // rawImage.getSubImage uses spriteUnits
            Image image = rawImage.getSubimage(subX/ScreenConsts.PIXELRARIO, subY/ScreenConsts.PIXELRARIO, subWidth/ScreenConsts.PIXELRARIO, subHeight/ScreenConsts.PIXELRARIO);
            if (xOverflow != 0 || yOverflow != 0){
                g.setColor(Color.darkGray);
                g.fillRect(xPos, yPos, SIZE, SIZE);
                xPos += xOverflow/scale;
                yPos += yOverflow/scale;
            }
            // draw image is in minimap units
            g.drawImage(image, xPos, yPos, subWidth/scale, subHeight/scale, null);

            //          (make sure all parameter units match up)
            if (inBounds(player.x(), player.y(), subX, subY, subWidth, subHeight)){
                g.setColor(Color.magenta);
                // this should be in minimap units (all draw functions are in minimap units)
                g.fillRect(xPos + (player.x()-subX)/(scale) - 2, yPos + (player.y()-subY)/(scale) - 2, 4, 4);
            }

            if (enemies.attackingEnemies != null){
                g.setColor(Color.RED);
                for (Enemy e: enemies.attackingEnemies){
                    if (inBounds(e.x, e.y, subX, subY, subWidth, subHeight)){
                        g.fillRect(xPos + (e.x-subX)/(scale) - 1, yPos + (e.y-subY)/(scale) - 1, 2, 2);
                    }
                }
            }

            if (enemies.defendingEnemies != null){
                g.setColor(Color.RED.darker().darker());
                for (Enemy e: enemies.defendingEnemies){
                    if (inBounds(e.x, e.y, subX, subY, subWidth, subHeight)){
                        g.fillRect(xPos + (e.x-subX)/(scale) - 1, yPos + (e.y-subY)/(scale) - 1, 2, 2);
                    }
                }
            }
            
            g.setColor(Color.GREEN);
            for (Unit u: units.attackingUnits){
                if (inBounds((int)u.x(), (int)u.y(), subX, subY, subWidth, subHeight)){
                    g.fillRect(xPos + ((int)u.x()-subX)/(scale) - 1, yPos + ((int)u.y()-subY)/(scale) - 1, 2, 2);
                }
            }
            g.setColor(Color.GREEN.darker().darker());
            for (Unit u: units.defendingUnits){
                if (inBounds((int)u.x(), (int)u.y(), subX, subY, subWidth, subHeight)){
                    g.fillRect(xPos + ((int)u.x()-subX)/(scale) - 1, yPos + ((int)u.y()-subY)/(scale) - 1, 2, 2);
                }
            }
        }
    }

    private boolean inBounds(int x, int y, int boundX, int boundY, int boundW, int boundH){
        return (boundX <= x && x <= boundX+boundW && boundY <= y && y <= boundY+boundH);
    }
}
