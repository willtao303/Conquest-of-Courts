import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import GameAssets.ScreenConsts;
import GameAssets.Input;
import Components.*;

public class UsernameState extends State{
    TextField inputField = new TextField();
    private int inputFieldW = 505;
    private int inputFieldH = 40;

    private Button back = new ImageButton(100, 100, Button.BACK_BUTTON);
    private Button enter = new BasicButton(ScreenConsts.WINDOWWIDTH/2 + 150 + ((505/2)-202) + 30, ScreenConsts.WINDOWHEIGHT/2 + 7, 300, inputFieldH+14, "ENTER");
    private int blinkCounter = 15;
    private String error = "";

    
    Client client;

    @Override
    public void start() {
        client = new Client();
        handler.addTextbox(inputField);
        inputField.limitChars(15);
        inputField.defaultIncludeChar();
        inputField.includeChar(new char[]{'.', '_','-', ':', '~', '+'});
        inputField.focus();
    }

    @Override
    public void run() {
        
        enter.update(input.mousePosX(), input.mousePosY(), input.mouseIsDown(Input.LMB));
        back.update(input.mousePosX(), input.mousePosY(), input.mouseIsDown(Input.LMB));
        if (!client.connected()){
            client.connect();
        } else {
            
            if (input.keyIsTapped(Input.SEND) || enter.isReleased()){
                if (inputField.content().equals("")){
                    error = "Please enter a name!";
                } else {
                    client.sendMessage(Commands.NAME + " " + inputField.content());
                }
            } else if (!input.keyIsTapped(Input.NO_KEYS)){
                error = "";
            }
            String msg = client.nextMessage();
            if (msg != null){
                if (msg.split(" ")[0].equals(Commands.NAME)){
                    if (msg.split(" ")[1].equals("1")){
                        client.setUserame(msg.split(" ")[2]);
                        handler.setClient(client);
                        handler.changeState(LOBBY);
                    } else if (msg.split(" ")[1].equals("0")){
                        error = "This name has already been taken! :(";
                    }
                }
            }
        }

        if (back.isReleased()){
            client.kill();
            handler.changeState(MAINMENU);
        }
    }

    @Override
    public void draw(Graphics g) {
        back.draw(g);
        if (client.connected()){
            g.setFont(Fonts.USERNAME_INPUT_FONT);
            g.setColor(Color.DARK_GRAY);
            g.fillRect(ScreenConsts.WINDOWWIDTH/2 - inputFieldW/2 - 202, ScreenConsts.WINDOWHEIGHT/2 - inputFieldH/2, inputFieldW, inputFieldH+15);

            g.drawString("Enter an username:", ScreenConsts.WINDOWWIDTH/2 - inputFieldW/2 - 202, ScreenConsts.WINDOWHEIGHT/2 - inputFieldH/2 + 35 - 60);
            g.setColor(Color.WHITE);

            String inputFieldString = inputField.content();
            if (inputField.isFocused()){
                blinkCounter++;
                    if (blinkCounter > 15){
                        inputFieldString = inputFieldString + "|";
                        if (blinkCounter > 30){
                            blinkCounter = 0;
                        }
                    }
                }
            g.drawString(inputFieldString, ScreenConsts.WINDOWWIDTH/2 - inputFieldW/2 + 15 - 202, ScreenConsts.WINDOWHEIGHT/2 - inputFieldH/2 + 35);

            g.setColor(Color.RED);
            g.drawString(error, ScreenConsts.WINDOWWIDTH/2 - inputFieldW/2 - 202, ScreenConsts.WINDOWHEIGHT/2 - inputFieldH/2 + 35 + 60);

            enter.draw(g);
        } else {
            g.setColor(Color.BLACK);
            g.drawString("Connecting...", ScreenConsts.WINDOWWIDTH/2 - 10, ScreenConsts.WINDOWHEIGHT/2 - 5);
        }

    }

    @Override
    public void end() {
        handler.closeTextbox(inputField);
    }
    
}
