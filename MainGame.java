import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Queue;
import GameAssets.*;

public class MainGame {
    Input input;
    Renderer renderer;
    private boolean running = true;

    State[] states = new State[]{
        new MainMenuState(), 
        null, // login
        new MultiplayerState(),// multiplayer
        null,// singleplayer
        new SandboxState()
    };
    int currentState = 0;
    Queue<Integer> stateChanges = new LinkedList<Integer>();

    public void setup(Input i, Renderer r){
        input = i;
        renderer = r;
        states[currentState].start();
        for (State state: states){
            if (state == null){
                continue;
            }
            state.setInput(i);
            state.setWindow(r);
            state.setHandler(this);
        }
    }

    public void run(){
        while (running){
            states[currentState].run();
            renderer.repaint();
            try{Thread.sleep(30);}catch(Exception e){}
            if (!stateChanges.isEmpty()){
                changeState(stateChanges.remove(), true);
            }
        }
    }

    public void resetState(int state){
        if (state==State.MAIN_MENU){
            states[state] = new MainMenuState();
        }
    }


    public void changeState(int newState){
        stateChanges.add(newState);
    }

    private void changeState(int newState, boolean a){
        states[currentState].end();
        currentState = newState;
        states[currentState].start();
    }

    public void draw(Graphics g) {
        states[currentState].draw(g);
    }
}
