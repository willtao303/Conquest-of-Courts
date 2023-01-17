import java.awt.Graphics;
import java.awt.Color;
import GameAssets.*;

public class MultiplayerState extends State{
    Game game;
    EnemyManager enemies;
    Client client;
    @Override
    public void start() {
        client = handler.getClient();
        game = new Game();
        game.setup(input, client.getSide());
        enemies = game.getEnemyManager();
        //client.setEnemyManager(game.getEnemyManager());
        //client.setGame(game);
    }

    @Override
    public void run() {
        game.run();

        client.sendMessage(Commands.GAME_UNITS + game.getPlayer().toString()+ game.getUnits().toString());

        String msg = client.nextMessage();
        if (msg != null){
            String[] cmd = msg.split(" ");
            if (cmd[0].equals(Commands.GAME_UNITS)){
                String[] units = msg.split(" # ");
                enemies.setPlayer(units[1]);
                if (!units[2].equals("none")){
                    enemies.setAttackers(units[2].split(" "));
                }
                if (!units[3].equals("none")){
                    enemies.setDefenders(units[3].split(" "));
                }
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        game.render(g);
    }

    @Override
    public void end() {
    }
    
}
