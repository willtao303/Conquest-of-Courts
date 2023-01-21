import java.awt.Graphics;

import Components.*;
import GameAssets.ScreenConsts;
import GameAssets.Input;

public class LonelyMenuState extends State{
    Button backButton = new ImageButton(150,150, Button.BACK_BUTTON);
    Button campaign = new BasicButton(ScreenConsts.WINDOWWIDTH/2 - 250 - 10, ScreenConsts.WINDOWHEIGHT/2, 500, 800);
    Button sandbox = new BasicButton(ScreenConsts.WINDOWWIDTH/2 + 250 + 10, ScreenConsts.WINDOWHEIGHT/2 - 400 + 250, 500, 500-10);
    Button tutorial = new BasicButton(ScreenConsts.WINDOWWIDTH/2 + 250 + 10, ScreenConsts.WINDOWHEIGHT/2 + 400 - 150, 500, 300-10);
    @Override
    public void start() {
        
    }

    @Override
    public void run() {
        backButton.update(input.mousePosX(), input.mousePosY(), input.mouseIsDown(Input.LMB));
        campaign.update(input.mousePosX(), input.mousePosY(), input.mouseIsDown(Input.LMB));
        sandbox.update(input.mousePosX(), input.mousePosY(), input.mouseIsDown(Input.LMB));
        tutorial.update(input.mousePosX(), input.mousePosY(), input.mouseIsDown(Input.LMB));

        if (backButton.isReleased()){
            handler.changeState(MAINMENU);
        }
        if (sandbox.isReleased()){
            handler.changeState(SANDBOX);
        }
    }

    @Override
    public void draw(Graphics g) {
        // TODO Auto-generated method stub
        backButton.draw(g);
        campaign.draw(g);
        sandbox.draw(g);
        tutorial.draw(g);
        
    }

    @Override
    public void end() {
    }
    
}
