import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

import Components.*;
import GameAssets.Consts;
import GameAssets.Game;
import GameAssets.Input;

public class LobbyState extends State{
    Room room;
    Button backButton = new ImageButton(120, 120, Button.BACK_BUTTON);
    Button exitButton = new ImageButton(150, 150, Button.EXIT_BUTTON);
    Button joinButton = new BasicButton(Consts.WINDOWWIDTH/2 + 30 + 100, 50 + 15 + 25, 180, 50, "join");
    Button startButton = new BasicButton(Consts.WINDOWWIDTH/4, Consts.WINDOWHEIGHT/2 - 180 + 30, Consts.WINDOWWIDTH/4, 60, "START GAME");
    Button unstartButton = new BasicButton(Consts.WINDOWWIDTH/4 + Consts.WINDOWWIDTH/8 - 30,Consts.WINDOWHEIGHT/2 - 180 + 30, 20, 20, "x");

    TextField roomCode = new TextField();
    int backButtonCD;
    int startButtonCD = 0;
    Client client;
    @Override
    public void start() {
        client = handler.getClient();
        if (!client.alive()){
            client.start();
        }
        backButtonCD = 5;
        roomCode.defaultIncludeChar();
        roomCode.capslock();
        roomCode.limitChars(4);
        handler.addTextbox(roomCode);
        roomCode.focus();
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

            joinButton.update(input.mousePosX(), input.mousePosY(), input.mouseIsDown(Input.LMB));

            if (joinButton.isReleased() && roomCode.content().length() == 4){
                client.sendMessage(Commands.JOIN_ROOM + " " + roomCode.content());
            }
            String msg = client.nextMessage();
            if (msg != null){
                if (msg.split(" ")[0].equals(Commands.JOIN_ROOM)){
                    String[] cmd = msg.split(" ");
                    room = new Room(cmd[1], Integer.valueOf(cmd[2]));
                }
            }
            
        } else {
            exitButton.update(input.mousePosX(), input.mousePosY(), input.mouseIsDown(Input.LMB));
            startButton.update(input.mousePosX(), input.mousePosY(), input.mouseIsDown(Input.LMB));
            unstartButton.update(input.mousePosX(), input.mousePosY(), input.mouseIsDown(Input.LMB));
            if (exitButton.isReleased()){
                backButtonCD = 5;
                // TODO
                // room.leave();
                // client.send(leave room);
                room = null;
            } else if (startButtonCD == 0){
                if (startButton.isReleased()){
                    startButtonCD = 5;
                    client.sendMessage(Commands.USER_READY);
                    startButton.lock();
                    unstartButton.unlock();
                } 
                if (unstartButton.isReleased()){
                    startButtonCD = 5;
                    startButton.unlock();
                    unstartButton.lock();
                }
            } 
            if (startButtonCD > 0){
                startButtonCD--;
            }


            /*if (input.mouseIsReleased(Input.LMB)){
                int clicked = room.tileClicked(input.mousePosX(), input.mousePosY());
                if (clicked != Room.NONE_CLICKED){
                    if (room.getUserAt(clicked) != null){

                    }
                }
            }*/

            String msg = client.nextMessage();
            if (msg != null){
                if (msg.split(" ")[0].equals(Commands.USER_MOVE)){
                    String[] cmd = msg.split(" ");
                    for (int i = 1; i < cmd.length; i+=2){
                        room.set(Integer.valueOf(cmd[i]), cmd[i+1]);
                    }
                } else if (msg.split(" ")[0].equals(Commands.USER_SIDE)){
                    int side = Integer.valueOf(msg.split(" ")[1]);
                    if (side >= 0){
                        client.setSide(Game.SPECTATOR);
                    } else {
                        client.setSide(side);
                    }
                }
                if (msg.equals(Commands.START)){
                    handler.changeState(MULTIPLAYER);
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
            g.setColor(Color.BLACK);


            // middle section (room selection)
            // width 500
            // section 1: centered horizontally, 50 away from top, full width, 80 tall
            // section 2: centered horizontally, 50 away from section 1, full width, full height - 50
            g.drawRect(Consts.WINDOWWIDTH/2 - 250, 50, 500, 80);
            g.drawRect(Consts.WINDOWWIDTH/2 - 250, 50 + 80 + 50, 500, Consts.WINDOWHEIGHT - (180 + 50));
            
            
            //g.drawRect(Consts.WINDOWWIDTH/2  - 400, 200, 800, 80);
            g.setFont(new Font(Font.MONOSPACED, Font.ROMAN_BASELINE, 40));
            for (int i = 0; i < 4; i++){
                if (i < roomCode.content().length()){
                    g.drawString(roomCode.content().charAt(i)+"", Consts.WINDOWWIDTH/2 - 200 + i*35, 50 + 55);
                } else {
                    g.drawString("_", Consts.WINDOWWIDTH/2 - 200 + i*35, 50 + 55 - 7);
                }

            }  

            joinButton.draw(g);
        } else {
            room.draw(g);
            startButton.draw(g);
            if (startButton.locked()){
                unstartButton.draw(g);
            }
        }
    }

    @Override
    public void end() {
        handler.closeTextbox(roomCode);
    }
    
}
