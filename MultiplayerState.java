import java.awt.Graphics;

import Components.*;

import java.awt.Color;
import GameAssets.*;

public class MultiplayerState extends State{
    Game game;
    EnemyManager enemies;
    Client client;
    ChatRoom chat;
    TextField chatField;

    private static int CHATWIDTH = 400;
    BasicButton chatInputBox = new BasicButton(20 + CHATWIDTH/2, ScreenConsts.WINDOWHEIGHT-25 - 20, CHATWIDTH, 50, Color.LIGHT_GRAY);

    

    @Override
    public void start() {
        client = handler.getClient();
        chat = handler.getChat();
        game = new Game();
        game.setup(input, client.getSide());
        enemies = game.getEnemyManager();
        chatField = new TextField();
        handler.addTextbox(chatField);
    }

    @Override
    public void run() {

        chatInputBox.defaultUpdate(input.mousePosX(), input.mousePosY(), input.mouseIsDown(Input.LMB));
        client.sendMessage(Commands.GAME_UNITS + game.getPlayer().toString()+ game.getUnits().toString());
        String output = game.attacksToString();
        if (!output.equals("")){
            client.sendMessage(Commands.GAME_ATKS + " #" + output);
        }
        output = game.towersToString();
        if (!output.equals("")){
            client.sendMessage(Commands.GAME_TOWERS + " #" + output);
        }

        // if message sent, send Commands.CHAT + message;

        int msgs = client.unreadMessages();
        for (int i = 0; i < msgs; i++){
            String raw = client.nextMessage();
            String[] cmd = raw.split(" ");
            if (cmd[0].equals(Commands.GAME_UNITS)){
                String[] units = raw.split(" # ");
                enemies.setPlayer(units[1]);
                if (!units[2].equals("none")){
                    enemies.setAttackers(units[2].split(" "));
                } else {
                    enemies.clearAttackers();
                }
                if (!units[3].equals("none")){
                    enemies.setDefenders(units[3].split(" "));
                } else {
                    enemies.clearDefenders();
                }
            } else if (cmd[0].equals(Commands.GAME_ATKS)){
                String[] atks = raw.split(" # ");
                enemies.setAttacks(atks[1].split(" "));
            } else if (cmd[0].equals(Commands.GAME_TOWERS)){
                String[] towers = raw.split(" # ");
                System.out.println(raw);
                enemies.setTowers(towers[1].split(" "));
            } else if (cmd[0].equals(Commands.CHAT_MSG)){
                chat.addMessage(raw.substring(5));
            } 
        }

        game.run();

        
        
        if (chatField.isFocused()){
            if (input.mouseIsTapped(Input.LMB)){
                if (!(20 < input.mousePosX() && input.mousePosX() < 20+CHATWIDTH)){
                    if (!(ScreenConsts.WINDOWHEIGHT - 20 < input.mousePosY() && input.mousePosY() < ScreenConsts.WINDOWHEIGHT - 520)){
                        chatField.unfocus();
                        game.getOverlay().setCurrent(Overlay.NONE);
                    }
                }
            }
        }
        if (chatInputBox.isReleased()){
            chatField.focus();
            game.getOverlay().setCurrent(Overlay.CHAT);
        }
        

    }

    @Override
    public void draw(Graphics g) {
        game.render(g);
        chatInputBox.draw(g);
        if (chatField.isFocused()) {
            g.setColor(new Color(80, 80, 80, 150));
            g.fillRect(20, ScreenConsts.WINDOWHEIGHT-20 - 50 - 500, CHATWIDTH, 500);
            //for (String message: chat.getMessages()){

            //}
        }
    }

    @Override
    public void end() {
    }
    
}
