
import java.awt.Graphics;

public abstract class State {
    public final static int MAIN_MENU = 0;
    public final static int SANDBOX = 4;
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
