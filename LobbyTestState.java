import java.awt.Graphics;

import Components.*;
import GameAssets.Input;

public class LobbyTestState extends State{
    Lobby lobby;
    Button backButton = new BasicButton(120, 120, 80, 80);
    @Override
    public void start(Object[] args) {
        lobby = new Lobby(10);
        lobby.move("Joe", 2);
    }

    @Override
    public void run() {
        if (input.mouseIsReleased(Input.LMB)){
            int clicked = lobby.tileClicked(input.mousePosX(), input.mousePosY());
            if (clicked != Lobby.NONE_CLICKED){
                System.out.println(clicked);
                lobby.move("Joe", clicked);
            }
        }

        if (backButton.isReleased()){
            //handler.changeState();
        }
    }

    @Override
    public void draw(Graphics g) {
        lobby.draw(g);
        backButton.draw(g);
    }

    @Override
    public Object[] end() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
