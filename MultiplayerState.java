import java.awt.Graphics;
import java.awt.Color;
import GameAssets.*;

public class MultiplayerState extends State{
    Client client;

    @Override
    public void start(Object[]args) {
        client = new Client();
        //client.setEnemyManager(game.getEnemyManager());
        //client.setGame(game);
    }

    @Override
    public void run() {
        if (!client.connected){
            client.setup();
        } else {
            client.run();
        }
    }

    @Override
    public void draw(Graphics g) {
        if (!client.connected){
            g.setColor(Color.BLACK);
            g.drawString("Connecting...", Consts.WINDOWWIDTH/2 - 10, Consts.WINDOWHEIGHT/2 - 5);
        } else {
        }
    }

    @Override
    public Object[] end() {
        return null;
    }
    
}
