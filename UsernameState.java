import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import GameAssets.Consts;
import GameAssets.Input;
import Components.*;

public class UsernameState extends State{
    TextField inputField = new TextField();
    private int inputFieldW = 303;
    private int inputFieldH = 40;

    private Button back = new ImageButton(100, 100, Button.BACK_BUTTON);

    
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
        
        back.update(input.mousePosX(), input.mousePosY(), input.mouseIsDown(Input.LMB));
        if (!client.connected()){
            client.connect();
        } else {
            if (input.keyIsTapped(Input.SEND)){
                client.sendMessage(Commands.NAME + " " + inputField.content());
            }
            String msg = client.nextMessage();
            if (msg != null){
                if (msg.split(" ")[0].equals(Commands.NAME)){
                    if (msg.split(" ")[1].equals("1")){
                        client.setUserame(msg.split(" ")[2]);
                        handler.setClient(client);
                        handler.changeState(LOBBY);
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
            g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));
            g.setColor(Color.DARK_GRAY);
            g.fillRect(Consts.WINDOWWIDTH/2 - inputFieldW/2, Consts.WINDOWHEIGHT/2 - inputFieldH/2, inputFieldW, inputFieldH+15);
            g.setColor(Color.WHITE);
            g.drawString(inputField.content(), Consts.WINDOWWIDTH/2 - inputFieldW/2 + 15, Consts.WINDOWHEIGHT/2 - inputFieldH/2 + 35);
        } else {
            g.setColor(Color.BLACK);
            g.drawString("Connecting...", Consts.WINDOWWIDTH/2 - 10, Consts.WINDOWHEIGHT/2 - 5);
        }

    }

    @Override
    public void end() {
        handler.closeTextbox(inputField);
    }
    
}
