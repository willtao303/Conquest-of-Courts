import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import GameAssets.Consts;
import GameAssets.Input;
import Components.TextField;

public class UsernameState extends State{
    TextField inputField = new TextField();
    private int inputFieldW = 303;
    private int inputFieldH = 40;

    @Override
    public void start(Object[] args) {
        handler.addTextbox(inputField);
        inputField.limitChars(15);
        inputField.defaultIncludeChar();
        inputField.includeChar(new char[]{'.', '_','-', ':', '~', '+'});
        inputField.focus();
    }

    @Override
    public void run() {
        if (input.keyIsTapped(Input.SEND)){
            inputField.content();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));
        g.setColor(Color.DARK_GRAY);
        g.fillRect(Consts.WINDOWWIDTH/2 - inputFieldW/2, Consts.WINDOWHEIGHT/2 - inputFieldH/2, inputFieldW, inputFieldH+15);
        g.setColor(Color.WHITE);
        g.drawString(inputField.content(), Consts.WINDOWWIDTH/2 - inputFieldW/2 + 15, Consts.WINDOWHEIGHT/2 - inputFieldH/2 + 35);

    }

    @Override
    public Object[] end() {
        handler.closeTextbox(inputField);
        return null;
    }
    
}
