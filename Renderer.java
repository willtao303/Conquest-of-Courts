import javax.swing.*;
import java.awt.*;

public class Renderer {
    JFrame frame;
    GraphicsPanel canvas;
    Boolean fullscreen = true;
    Boolean running;
    Input input;
    MainGame maingame;

    Renderer(){
        frame = new JFrame("Agar.java");
        canvas = new GraphicsPanel();
        input = new Input();
        running = true;
    }

    public void setGame(MainGame g){
        maingame = g;
    }

    public void setup(){
        frame.setSize(Consts.WINDOWWIDTH,Consts.WINDOWHEIGHT);
        frame.setMinimumSize(new Dimension(Consts.WINDOWWIDTH,Consts.WINDOWHEIGHT));
        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas.addKeyListener(input);
        canvas.addMouseListener(input);
        canvas.addMouseMotionListener(input);

        frame.add(canvas); 

        frame.setUndecorated(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        Cursor cursor;

            
        cursor = (Toolkit.getDefaultToolkit().createCustomCursor(
            (new ImageIcon("Sprites/cursor.png")).getImage(),
            new java.awt.Point(5,5),"fancyCursor"));

            cursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
        canvas.setCursor(cursor);
    }

    public void repaint(){
        canvas.repaint();
    }

    public void toggleFullscreen(boolean full){
        if (fullscreen == full){
            return;
        } 
        toggleFullscreen();
    }

    public void toggleFullscreen(){
        fullscreen = !fullscreen;
        if (fullscreen){
            frame.dispose();
            Consts.FULLSCREEN_DIMS();
            frame.setUndecorated(true);
            frame.setVisible(true);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        } else {
            frame.dispose();
            Consts.WINDOWED_DIMS();
            frame.setUndecorated(false);
            frame.setVisible(true);
            frame.setExtendedState(JFrame.NORMAL);
            frame.setSize(Consts.WINDOWWIDTH,Consts.WINDOWHEIGHT);
            frame.setLocationRelativeTo(null);
        }
    }

    public boolean isFullscreen(){
        return fullscreen;
    }

    class GraphicsPanel extends JPanel {
        public GraphicsPanel(){
            setFocusable(true);
            requestFocusInWindow();
        }
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            maingame.draw(g);
        }
    }
}
