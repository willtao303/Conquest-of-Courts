
import java.awt.Color;
import java.awt.Graphics;
public class Overlay {
    public static final int DEAD = -2;
    public static final int NONE = -1;
    public static final int PAUSE = 0;
    public static final int SHOP = 1;

    private int current;
    private Game game;

    Overlay(){
        current = NONE;
    }
    public void addResoures(Game g) {
        game = g;
    }

    public void draw(Graphics g){
        g.setColor(Color.GRAY);
        g.drawRect(20, 20, (int)(game.player.maxHp()), 30);
        g.drawRect(20, 50, (int)(game.player.maxMana), 20);
        g.setColor(Color.RED);
        g.fillRect(20, 20, (int)(game.player.hp()), 30);
        g.setColor(Color.BLUE);
        g.fillRect(20, 50, (int)(game.player.mana), 20);

        g.setColor(new Color(240, 188, 58));
        g.fillRect(240, 25, 10, 10);

        g.setColor(new Color(66, 203, 245));
        g.fillRect(240, 40, 10, 10);
    }

    public void drawOverlay(Graphics g){
        switch(current){
            case DEAD:
                g.setColor(new Color(40,40,40,100));
                g.fillRect(0, 0, Consts.WINDOWWIDTH, Consts.WINDOWHEIGHT);
                g.setColor(Color.WHITE);
                g.drawString("YOU DIED",Consts.WINDOWWIDTH/2-30, Consts.WINDOWHEIGHT/2-20);
                g.drawString("RESPAWNING IN", Consts.WINDOWWIDTH/2-50, Consts.WINDOWHEIGHT/2);
                System.out.println(game.player.respawningIn());
                g.drawString(""+(game.player.respawningIn()+1), Consts.WINDOWWIDTH/2-5, Consts.WINDOWHEIGHT/2 + 20);
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
