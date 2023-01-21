import java.awt.Graphics;
import java.awt.Color;
import GameAssets.ScreenConsts;
import GameAssets.Input;
import Components.*;

public class MainMenuState extends State {
    Button[] buttons = {
        new ImageButton(ScreenConsts.WINDOWWIDTH/2, ScreenConsts.WINDOWHEIGHT/2+50, Button.START_BUTTON),
        new ImageButton(ScreenConsts.WINDOWWIDTH/2, ScreenConsts.WINDOWHEIGHT/2+150, Button.MULTIPLAYER_BUTTON),
        new BasicButton(ScreenConsts.WINDOWWIDTH/2-50, ScreenConsts.WINDOWHEIGHT/2+250, 700, 80),
        new ImageButton(ScreenConsts.WINDOWWIDTH/2+360, ScreenConsts.WINDOWHEIGHT/2+250, Button.SETTINGS_BUTTON)
    };

    Button exit = new ImageButton(100, 100, Button.EXIT_BUTTON);

    Button exitConfirmButton    = new BasicButton(ScreenConsts.WINDOWWIDTH/2 + 125,ScreenConsts.WINDOWHEIGHT/2 + 100,160, 50, Color.RED);
    Button exitNotConfirmButton = new BasicButton(ScreenConsts.WINDOWWIDTH/2 - 125,ScreenConsts.WINDOWHEIGHT/2 + 100,160, 50, Color.GRAY);

    int exitButtonCD;

    private boolean exitConfirmation;
    @Override
    public void start() {
        exitButtonCD = 5;
        exitConfirmation = false;
        exit.reset();
    }

    @Override
    public void run() {
        if (exitConfirmation){
            exitConfirmButton.update(input.mousePosX(), input.mousePosY(), input.mouseIsDown(Input.LMB));
            exitNotConfirmButton.update(input.mousePosX(), input.mousePosY(), input.mouseIsDown(Input.LMB));
            if (input.keyIsTapped(Input.ESC) || exitNotConfirmButton.isReleased()){
                exitConfirmation = false;
            }

            if (exitConfirmButton.isReleased()){
                handler.end();
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
            g.fillRect(0,0,ScreenConsts.WINDOWWIDTH, ScreenConsts.WINDOWHEIGHT);
            g.setColor(Color.WHITE);
            g.fillRoundRect(ScreenConsts.WINDOWWIDTH/2 - 250,ScreenConsts.WINDOWHEIGHT/2 - 150,500, 300, 30, 30);
            g.setColor(Color.BLACK);
            g.drawString("Are you sure you", ScreenConsts.WINDOWWIDTH/2 - 150,ScreenConsts.WINDOWHEIGHT/2-40);
            g.drawString("want to leave?", ScreenConsts.WINDOWWIDTH/2 - 130,ScreenConsts.WINDOWHEIGHT/2);

            exitConfirmButton.draw(g);
            exitNotConfirmButton.draw(g);
            
            g.setColor(Color.WHITE);
            g.drawString("NO", ScreenConsts.WINDOWWIDTH/2 - 125 - 20 + 2,ScreenConsts.WINDOWHEIGHT/2+ 100 + 10);
            g.drawString("EXIT", ScreenConsts.WINDOWWIDTH/2 + 125 - 40 + 3,ScreenConsts.WINDOWHEIGHT/2+ 100 + 10) ;
        }
    }

    @Override
    public void end() {
    }
    
}
