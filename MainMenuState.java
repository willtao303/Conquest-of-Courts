import java.awt.Graphics;
import java.awt.Color;
import GameAssets.Consts;
import GameAssets.Input;
import Components.*;

public class MainMenuState extends State {
    Button[] buttons = {
        new ImageButton(Consts.WINDOWWIDTH/2, Consts.WINDOWHEIGHT/2+50, Button.START_BUTTON),
        new BasicButton(Consts.WINDOWWIDTH/2, Consts.WINDOWHEIGHT/2+150, 800, 80),
        new BasicButton(Consts.WINDOWWIDTH/2-50, Consts.WINDOWHEIGHT/2+250, 700, 80),
        new BasicButton(Consts.WINDOWWIDTH/2+360, Consts.WINDOWHEIGHT/2+250, 80, 80)

    };
    @Override
    public void start(Object [] args) {}

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
            handler.changeState(MULTIPLAYER);
        } else if (buttons[1].isReleased()){
            handler.changeState(USERNAME);
        }else if (buttons[2].isReleased()){
            handler.changeState(LOBBY);
        }else if (buttons[3].isReleased()){
            handler.changeState(SETTINGS);
        }
    }

    @Override
    public void draw(Graphics g) {
        for (Button b : buttons){
            b.draw(g);
        }
    }

    @Override
    public Object[] end() {
        return null;
    }
    
}
