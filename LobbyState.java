import java.awt.Color;
import java.awt.Graphics;

import Components.*;
import GameAssets.Input;

public class LobbyState extends State{
    Room room;
    Button backButton = new ImageButton(120, 120, Button.BACK_BUTTON);
    Button exitButton = new ImageButton(150, 150, Button.EXIT_BUTTON);
    int backButtonCD;
    Client client;
    @Override
    public void start() {
        client = handler.getClient();
        if (!client.alive()){
            client.start();
        }
        int backButtonCD = 5;
        //room = new Room(10);
        //room.move("Joe", 2);
    }

    @Override
    public void run() {
        if (room == null){
            if (backButtonCD <= 0){
                backButton.update(input.mousePosX(), input.mousePosY(), input.mouseIsDown(Input.LMB));
                if (backButton.isReleased()){
                    handler.getClient().kill();
                    handler.changeState(MAINMENU);
                }
            } else {
                backButtonCD--;
            }
        } else {
            exitButton.update(input.mousePosX(), input.mousePosY(), input.mouseIsDown(Input.LMB));
            if (exitButton.isReleased()){
                backButtonCD = 5;
                // TODO
                // room.leave();
                // client.send(leave room);
                room = null;
            }
            if (input.mouseIsReleased(Input.LMB)){
                int clicked = room.tileClicked(input.mousePosX(), input.mousePosY());
                if (clicked != Room.NONE_CLICKED){
                    System.out.println(clicked);
                    room.move("Joe", clicked);
                }
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.drawString(handler.getClient().getUserame(), 50, 50);
        if (room == null){
            backButton.draw(g);
        } else {
            room.draw(g);
        }
    }

    @Override
    public void end() {
    }
    
}
