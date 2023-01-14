import java.awt.Graphics;
import java.awt.Color;
import GameAssets.*;

public class MultiplayerState extends State{
    Game game;
    Client opponent;

    @Override
    public void start() {
        game = new Game();
        game.setup(input);
        opponent = new Client();
        opponent.setEnemyManager(game.getEnemyManager());
        opponent.setGame(game);
    }

    @Override
    public void run() {
        if (!opponent.connected){
            opponent.setup();
        } else {
            game.run();
            opponent.run();
        }
    }

    @Override
    public void draw(Graphics g) {
        if (!opponent.connected){
            g.setColor(Color.BLACK);
            g.drawString("Connecting...", Consts.WINDOWWIDTH/2 - 10, Consts.WINDOWHEIGHT/2 - 5);
        } else {
            game.render(g);
        }
    }

    @Override
    public void end() {
        // TODO Auto-generated method stub
        
    }
    
}
