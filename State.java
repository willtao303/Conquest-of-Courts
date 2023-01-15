import java.awt.Graphics;
import GameAssets.*;

public abstract class State {
    public final static int MAINMENU = 0;
    public final static int USERNAME = 1;
    public final static int SINGLEMENU = 2;
    public final static int CAMPAIGN = 3;
    public final static int SANDBOX = 4;
    public final static int SINGLEPLAYER = 5;
    public final static int MULTIPLAYER = 6;
    public final static int LOBBY = 7;
    public final static int SETTINGS = 8;
    protected Input input;
    protected Renderer window;
    protected MainGame handler;


    public abstract void start();
    public abstract void run();
    public abstract void draw(Graphics g);
    public abstract void end();
    public void setInput(Input i) {
        input = i;
    }
    public void setWindow(Renderer r){
        window = r;
    }
    public void setHandler(MainGame m){
        handler = m;
    }
}
