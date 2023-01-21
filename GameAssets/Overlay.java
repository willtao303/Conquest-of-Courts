package GameAssets;

import java.awt.Color;
import java.awt.Graphics;

import Components.*;

public class Overlay {
    public static final int DEAD = -2;
    public static final int NONE = -1;
    public static final int PAUSE = 0;
    public static final int SHOP = 1;
    public static final int CHAT = 4;

    private static final int MINIMAP_POS_X = 40;
    private static final int MINIMAP_POS_Y = 40;

    private static final int BARS_POS_X = MINIMAP_POS_X + MiniMap.SIZE + 20;
    private static final int BARS_POS_Y = MINIMAP_POS_Y;



    private Button pauseButton = new BasicButton(ScreenConsts.WINDOWWIDTH - 25 - 10, 25 + 10, 50, 50);

    private int current;
    private Game game;
    private Input input;

    private MiniMap minimap;

    Overlay(){
        current = NONE;
        minimap = new MiniMap();
    }
    public void addResoures(Game g) {
        game = g;
        input = game.input;
        minimap.addResources(g);
    }

    public void update(){
        switch(current){
            case DEAD:
            break;
            case NONE:
                pauseButton.defaultUpdate(input.mousePosX(), input.mousePosY(), input.mouseIsDown(Input.LMB));
                if (pauseButton.isReleased()){
                    game.ff = true;
                }
            break;
        }
    }

    public void draw(Graphics g){
        g.setColor(Color.GRAY);
        g.drawRect(BARS_POS_X, BARS_POS_Y, (int)(game.player.maxHp()), 30);
        g.drawRect(BARS_POS_X, BARS_POS_Y+30, (int)(game.player.maxMana), 20);
        g.setColor(Color.RED);
        g.fillRect(BARS_POS_X, BARS_POS_Y, (int)(game.player.hp()), 30);
        g.setColor(Color.BLUE);
        g.fillRect(BARS_POS_X, BARS_POS_Y+30, (int)(game.player.mana), 20);

        g.setColor(new Color(240, 188, 58));
        g.fillRect(BARS_POS_X + 240, BARS_POS_Y + 5, 10, 10);

        g.setColor(new Color(66, 203, 245));
        g.fillRect(BARS_POS_X + 240, BARS_POS_Y + 20, 10, 10);

        g.setColor(Color.WHITE);
        g.drawString(game.resources.coins+"", BARS_POS_X + 240 + 15, BARS_POS_Y + 5 + 10);
        g.drawString(game.resources.crystals+"", BARS_POS_X + 240 + 15, BARS_POS_Y + 20 + 10);

        minimap.draw(MINIMAP_POS_X, MINIMAP_POS_Y, g);
        pauseButton.draw(g);
    }

    public void drawOverlay(Graphics g){
        switch(current){
            case DEAD:
                g.setColor(new Color(40,40,40,100));
                g.fillRect(0, 0, ScreenConsts.WINDOWWIDTH, ScreenConsts.WINDOWHEIGHT);
                g.setColor(Color.WHITE);
                g.drawString("YOU DIED",ScreenConsts.WINDOWWIDTH/2-30, ScreenConsts.WINDOWHEIGHT/2-20);
                g.drawString("RESPAWNING IN", ScreenConsts.WINDOWWIDTH/2-50, ScreenConsts.WINDOWHEIGHT/2);
                g.drawString(""+(game.player.respawningIn()+1), ScreenConsts.WINDOWWIDTH/2-5, ScreenConsts.WINDOWHEIGHT/2 + 20);
            break;
            case PAUSE:
                g.setColor(new Color(40,40,40,100));
                g.fillRect(0, 0, ScreenConsts.WINDOWWIDTH, ScreenConsts.WINDOWHEIGHT);
            break;
        }
    }

    public void setCurrent(int newState){
        current = newState;
    }

    public int getCurrent() {
        return current;
    }
}
