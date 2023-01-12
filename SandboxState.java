import java.awt.Graphics;

public class SandboxState extends State{
    Game game;

    @Override
    public void start() {
        game = new Game();
        game.setup(input);
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
        // TODO Auto-generated method stub
        
    }
    
}
