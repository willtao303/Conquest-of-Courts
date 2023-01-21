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

    private int CHAT_WIDTH = 400;
    private Color CHAT_BG = new Color(80, 80, 80, 150);
    private Color CHAT_INPUT_BG = new Color(90,90,90);
    private Color CHAT_FOCUSED_BG = new Color(70,70,70);
    private Color CHAT_BORDER = new Color(50,50,50);
    BasicButton chatInputBox = new BasicButton(20 + CHAT_WIDTH/2, ScreenConsts.WINDOWHEIGHT-25 - 20, CHAT_WIDTH, 50, Color.LIGHT_GRAY);

    

    @Override
    public void start() {
        client = handler.getClient();
        chat = handler.getChat();
        game = new Game();
        game.setup(input, client.getSide());
        enemies = game.getEnemyManager();
        chatField = new TextField();
        chatField.limitChars(100);
        handler.addTextbox(chatField);
    }

    @Override
    public void run() {

        // update components
        chat.update();
        chatInputBox.defaultUpdate(input.mousePosX(), input.mousePosY(), input.mouseIsDown(Input.LMB));
        client.sendMessage(Commands.GAME_UNITS + game.getPlayer().toString()+ game.getUnits().toString());

        // send game data to server
        String output = game.attacksToString();
        if (!output.equals("")){
            client.sendMessage(Commands.GAME_ATKS + " #" + output);
        }
        output = game.towersToString();
        if (!output.equals("")){
            client.sendMessage(Commands.GAME_TOWERS + " #" + output);
        }

        // check messages recieved from server
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

        // update game
        game.update();

        // update chat 
        if (chatField.isFocused()){
            if (input.mouseIsTapped(Input.LMB)){
                if (!(20 < input.mousePosX() && input.mousePosX() < 20+CHAT_WIDTH)){
                    if (!(ScreenConsts.WINDOWHEIGHT - 20 < input.mousePosY() && input.mousePosY() < ScreenConsts.WINDOWHEIGHT - 520)){
                        chatField.unfocus();
                        game.getOverlay().setCurrent(Overlay.NONE);
                    }
                }
            }
            if (input.keyIsTapped(Input.SEND) && chatField.content().trim().length() != 0){
                chatField.unfocus();
                chatField.clear();
                client.sendMessage(Commands.CHAT_MSG + " " + chatField.content());
                game.getOverlay().setCurrent(Overlay.NONE);
            }
        }
        // if chatbox is pressed, focus chatfield
        if (chatInputBox.isReleased()){
            chatField.focus();
            game.getOverlay().setCurrent(Overlay.CHAT);
        }
        

    }

    @Override
    public void draw(Graphics g) {
        game.render(g);
        //chatInputBox.draw(g);
        g.setFont(Fonts.CHAT_FONT);
        if (chatField.isFocused()) {
            g.setColor(CHAT_BG);
            g.fillRect(20, ScreenConsts.WINDOWHEIGHT-20 - 40 - 500, CHAT_WIDTH, 500);

            g.setColor(CHAT_FOCUSED_BG);
            g.fillRect(20, ScreenConsts.WINDOWHEIGHT-20 - 40, CHAT_WIDTH, 40);
            g.setColor(Color.WHITE);
            g.drawString(chatField.content().substring(Math.max(0, chatField.content().length()-20), chatField.content().length()), 20+ 15, ScreenConsts.WINDOWHEIGHT-20 - 10);
            int i = 1;
            for (String message: chat.getMessages()){
                if (message != null){
                    g.drawString(message, 20, 20 + (ScreenConsts.WINDOWHEIGHT-20 - 40) - i*55);
                    i++;
                }
            }
        } else {
            g.setColor(CHAT_INPUT_BG);
            g.fillRect(20, ScreenConsts.WINDOWHEIGHT-20 - 40, CHAT_WIDTH, 40);
            g.setColor(Color.LIGHT_GRAY);
            g.drawString("Chat", 20+ 15, ScreenConsts.WINDOWHEIGHT-20 - 10);
            if (chat.newMessages() != 0){
                g.setColor(CHAT_BG);
                g.fillRect(20, ScreenConsts.WINDOWHEIGHT-20 - 40 - (chat.newMessages()*55), CHAT_WIDTH, (chat.newMessages()*55));
                int i = 1;
                for (String message: chat.getNewMessages()){
                    if (message != null){
                        g.drawString(message, 20, 20 + (ScreenConsts.WINDOWHEIGHT-20 - 40) - i*55);
                        i++;
                    }
                }
            }
        }
    }

    @Override
    public void end() {
    }
    
}
