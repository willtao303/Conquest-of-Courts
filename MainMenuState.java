import java.awt.Graphics;

public class MainMenuState extends State {
    Button[] buttons = {new StartButton(Consts.WINDOWWIDTH/2, Consts.WINDOWHEIGHT/2+100)};
    @Override
    public void start() {}

    @Override
    public void run() {
        if (input.keyIsTapped(Input.ESC)){
            window.toggleFullscreen(false);
        } else if (input.keyIsTapped(Input.FULLSCREEN)){
            window.toggleFullscreen();
        }
        
        for (Button b : buttons){
            b.update(input.mousePosX(), input.mousePosY(), input.mouseIsDown(Input.LMB));
        }

        if (buttons[0].isReleased()){
            handler.changeState(SANDBOX);
        }
    }

    @Override
    public void draw(Graphics g) {
        for (Button b : buttons){
            b.draw(g);
        }
    }

    @Override
    public void end() {
        // TODO Auto-generated method stub
        
    }
    
}
