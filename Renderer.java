import javax.swing.*;
import java.awt.*;
import GameAssets.*;
import Components.TextField;

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
        frame.setSize(ScreenConsts.WINDOWWIDTH,ScreenConsts.WINDOWHEIGHT);
        frame.setMinimumSize(new Dimension(ScreenConsts.WINDOWWIDTH,ScreenConsts.WINDOWHEIGHT));
        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas.addKeyListener(input);
        canvas.addMouseListener(input);
        canvas.addMouseMotionListener(input);
        canvas.addMouseWheelListener(input);

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
            ScreenConsts.FULLSCREEN_DIMS();
            frame.setUndecorated(true);
            frame.setVisible(true);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        } else {
            frame.dispose();
            ScreenConsts.WINDOWED_DIMS();
            frame.setUndecorated(false);
            frame.setVisible(true);
            frame.setExtendedState(JFrame.NORMAL);
            frame.setSize(ScreenConsts.WINDOWWIDTH,ScreenConsts.WINDOWHEIGHT);
            frame.setLocationRelativeTo(null);
        }
    }

    public boolean isFullscreen(){
        return fullscreen;
    }
    public void addTextField(TextField t){
        canvas.addKeyListener(t);
    }
    public void closeTextField(TextField t){
        canvas.removeKeyListener(t);
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
