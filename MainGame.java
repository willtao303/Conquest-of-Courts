import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Queue;

import Components.TextField;
import GameAssets.*;

public class MainGame {
    Input input;
    Renderer renderer;
    String username;
    private boolean running = true;

    State[] states = new State[]{
        new MainMenuState(), 
        new UsernameState(),
        new SandboxState(),
        null,// singleplayer
        new MultiplayerState(),
        new LobbyTestState(),
        new SettingsState()
    };
    int currentState = 0;
    Queue<Integer> stateChanges = new LinkedList<Integer>();

    public void setup(Input i, Renderer r){
        input = i;
        renderer = r;
        states[currentState].start(null);
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
            input.resetTapped();
            try{Thread.sleep(30);}catch(Exception e){}
            if (!stateChanges.isEmpty()){
                changeState(stateChanges.remove(), true);
            }
        }
    }

    public void resetState(int state){
        if (state==State.MAINMENU){
            states[state] = new MainMenuState();
        }
    }

    public void addTextbox(TextField textfield){
        renderer.addTextField(textfield);
    }
    public void closeTextbox(TextField textfield){
        renderer.closeTextField(textfield);
    }

    public void changeState(int newState){
        stateChanges.add(newState);
    }

    private void changeState(int newState, boolean a){
        Object[] args = states[currentState].end();
        currentState = newState;
        states[currentState].start(args);
    }

    public void draw(Graphics g) {
        states[currentState].draw(g);
    }
}
