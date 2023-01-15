import java.awt.Graphics;
import java.awt.Color;
import GameAssets.Consts;
import GameAssets.Input;
import Components.*;

public class MainMenuState extends State {
    Button[] buttons = {
        new ImageButton(Consts.WINDOWWIDTH/2, Consts.WINDOWHEIGHT/2+50, Button.START_BUTTON),
        new ImageButton(Consts.WINDOWWIDTH/2, Consts.WINDOWHEIGHT/2+150, Button.MULTIPLAYER_BUTTON),
        new BasicButton(Consts.WINDOWWIDTH/2-50, Consts.WINDOWHEIGHT/2+250, 700, 80),
        new ImageButton(Consts.WINDOWWIDTH/2+360, Consts.WINDOWHEIGHT/2+250, Button.SETTINGS_BUTTON)
    };

    Button exit = new BasicButton(100, 100, 80, 80);

    int exitButtonCD;

    private boolean exitConfirmation = false;
    @Override
    public void start() {
        exitButtonCD = 5;
    }

    @Override
    public void run() {
        if (exitConfirmation){
            if (input.keyIsTapped(Input.ESC)){
                exitConfirmation = false;
            }
        } else {
            if (input.keyIsTapped(Input.ESC)){
                window.toggleFullscreen(false);
            } else if (input.keyIsTapped(Input.FULLSCREEN)){
                window.toggleFullscreen();
            }
            for (Button b : buttons){
                b.update(input.mousePosX(), input.mousePosY(), input.mouseIsDown(Input.LMB));
            }
            if (exitButtonCD <= 0){
                exit.update(input.mousePosX(), input.mousePosY(), input.mouseIsDown(Input.LMB));
            } else {
                exitButtonCD--;
            }

            if (buttons[0].isReleased()){
                handler.changeState(SINGLEMENU);
            } else if (buttons[1].isReleased()){
                if (handler.getClient() == null){
                    handler.changeState(USERNAME);
                } else {
                    handler.changeState(LOBBY);
                }
            } else if (buttons[2].isReleased()){
                // i have no fing idea what this button does lmao
                // im losing my mind ;-;
                //handler.changeState(LOBBY);
            } else if (buttons[3].isReleased()){
                handler.changeState(SETTINGS);
            } else if (exit.isReleased()){
                exitConfirmation = true;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        for (Button b : buttons){
            b.draw(g);
        }
        exit.draw(g);

        if (exitConfirmation){
            g.setColor(new Color(40, 40, 40, 180));
            g.fillRect(0,0,Consts.WINDOWWIDTH, Consts.WINDOWHEIGHT);
            g.setColor(Color.WHITE);
            g.fillRoundRect(Consts.WINDOWWIDTH/2 - 250,Consts.WINDOWHEIGHT/2 - 150,500, 300, 30, 30);
        }
    }

    @Override
    public void end() {
    }
    
}
