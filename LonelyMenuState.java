import java.awt.Graphics;

import Components.*;
import GameAssets.Input;

public class LonelyMenuState extends State{
    Button backButton = new ImageButton(150,150, Button.BACK_BUTTON);
    Button campaign = new BasicButton(600, 120+400, 500, 800);
    @Override
    public void start() {
        
    }

    @Override
    public void run() {
        backButton.update(input.mousePosX(), input.mousePosY(), input.mouseIsDown(Input.LMB));
        campaign.update(input.mousePosX(), input.mousePosY(), input.mouseIsDown(Input.LMB));

        if (backButton.isDown()){
            handler.changeState(MAINMENU);
        }
    }

    @Override
    public void draw(Graphics g) {
        // TODO Auto-generated method stub
        backButton.draw(g);
        campaign.draw(g);
        
    }

    @Override
    public void end() {
    }
    
}
