package GameAssets;

import java.awt.Graphics;
import java.awt.Color;

public class TowerSlot {
    EnemyManager enemies;

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
        -1
    };
    private final static int BUTTON_DIAM = 40;
    private final static int PD = 3; // padding for the buttons hitbox
    private int x;
    private int y;
    private boolean focused;
    
    TowerSlot(int x, int y){
        this.x = x;
        this.y = y;
    }

    
    public void draw(int offsetX, int offsetY, Graphics g){
        if (focused){
            if (tower == null){
                g.setColor(Color.GRAY);
                for (int i = 0; i < NUM_BUTTONS; i++){ // TODO finish tower class and limit num of buttons to upgrades
                    if (this.tower != null){
                        if (i < tower.NUM_OF_UPGRADES){
                            if (TowerPreset.UPGRADES[tower.preset][i] != -1){
                                g.setColor(TowerPreset.COLOR[TowerPreset.UPGRADES[tower.preset][i]]);
                            } else {
                                g.setColor(Color.GRAY);
                            }
                        }
                    } else {
                        if (i < INITIAL_UPGRADES.length){
                            if (INITIAL_UPGRADES[i] != -1){
                                g.setColor(TowerPreset.COLOR[INITIAL_UPGRADES[i]]);
                            } else {
                                g.setColor(Color.GRAY);
                            }
                        }
                    }
                    g.fillOval(x+BUTTON_OFFSETS[i][0]-offsetX, y+BUTTON_OFFSETS[i][1]-offsetY, BUTTON_DIAM, BUTTON_DIAM);
                }
                
                g.setColor(Color.LIGHT_GRAY);
            } else {
                for (int i = 0; i < tower.NUM_OF_UPGRADES; i++){ // TODO finish tower class and limit num of buttons to upgrades
                    if (i == 0) {
                        g.setColor(Color.DARK_GRAY);
                    }
                    g.fillOval(x+BUTTON_OFFSETS[i][0]-offsetX, y+BUTTON_OFFSETS[i][1]-offsetY, BUTTON_DIAM, BUTTON_DIAM);
                }
                
            }
        } else {
            g.setColor(Color.GRAY);
        }
        if (tower != null){
            tower.draw(x-SIZE/2-offsetX, y-SIZE/2-offsetY, SIZE, g);
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
    public boolean buttonClicked(int mouseX, int mouseY){
        int buttonClicked = 0;
        for (int i = 0; i < BUTTON_OFFSETS.length; i++){
            if (x+BUTTON_OFFSETS[i][0]-PD < mouseX && mouseX < x+BUTTON_OFFSETS[i][0]+BUTTON_DIAM+PD && y+BUTTON_OFFSETS[i][1]-PD < mouseY && mouseY < y+BUTTON_OFFSETS[i][1]+BUTTON_DIAM+PD){
                break;
            }
            buttonClicked++;
        }
        if (buttonClicked != BUTTON_OFFSETS.length){
            if (tower == null){
                tower = new Tower(x, y, INITIAL_UPGRADES[buttonClicked]);
            } else {
                tower = new Tower(x, y, tower.UPGRADES[buttonClicked]);
            }
            return true;
        }
        return false;
    }

    public int x(){
        return x;
    }
    public int y(){
        return y;
    }
}
