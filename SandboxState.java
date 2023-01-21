import java.awt.Graphics;
import GameAssets.*;
import java.util.ArrayList;

public class SandboxState extends State{
    Game game;
    EnemyManager enemies;
    ArrayList<String> defenders = new ArrayList<String>();

    @Override
    public void start() {
        game = new Game();
        game.setup(input, Game.BLUE);
        enemies = game.getEnemyManager();
    }

    @Override
    public void run() {
        game.update();
        
        if (game.forfeited()){
            handler.changeState(SINGLEMENU);
        }
        /*
        if (input.mouseIsTapped(Input.RMB)){
            if (input.keyIsDown(Input.MULTISELECT)){
                defenders.add("1/" + (input.mousePosX()+game.getCamera().anchorX()) + "/" + (input.mousePosY()+game.getCamera().anchorY()) + "/" + 100);
            }
        }*/
        //enemies.setAttackers(defenders.toArray(new String[defenders.size()]));
    }

    @Override
    public void draw(Graphics g) {
        game.render(g);
    }

    @Override
    public void end() {
    }
    
}
