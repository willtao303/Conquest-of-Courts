import java.awt.Graphics;
import java.awt.Color;
import GameAssets.*;

public class MultiplayerState extends State{
    Game game;
    Client client;
    @Override
    public void start() {
        client = handler.getClient();
        game = new Game();
        game.setup(input, client.getSide());
        //client.setEnemyManager(game.getEnemyManager());
        //client.setGame(game);
    }

    @Override
    public void run() {
        game.run();
    }

    @Override
    public void draw(Graphics g) {
        game.render(g);
    }

    @Override
    public void end() {
    }
    
}
