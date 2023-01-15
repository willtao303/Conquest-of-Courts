import java.util.ArrayList;

import Components.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;


public class Room {
    private SlotTile red = new SlotTile(RED);
    private SlotTile blue = new SlotTile(BLUE);
    public static final int RED = -1;
    public static final int BLUE = -2;
    public static final int NONE_CLICKED = -69;
    private ArrayList<SlotTile> spectators = new ArrayList<SlotTile>();
    private int size;

    Room (int roomSize){
        size = roomSize;
        for (int i = 0; i < roomSize-2; i++){
            spectators.add(new SlotTile(i));
        }
    }

    //private Button startButton = new BasicButton(0, 0, 0, 0)
    
    public void move(String name, int slot){
        if (slot >= size-2){
            return;
        }
        if (red.name == name){
            red.name = null;
        } else if (blue.name == name){
            blue.name = null;
        } else {
            for (int i = 0; i < spectators.size(); i++){
                if (spectators.get(i).name != null){
                    if (spectators.get(i).name.equals(name)){
                        spectators.get(i).name = null;
                    }
                }
            }
        }

        if (slot == RED){
            red.name = name;
        } else if (slot == BLUE){
            blue.name = name;
        } else {
            spectators.get(slot).name = name;
        }
    }

    public int tileClicked(int mouseX, int mouseY){
        if (red.clicked(mouseX, mouseY)){
            return red.slot;
        }
        if (blue.clicked(mouseX, mouseY)){
            return blue.slot;
        }
        for (int i = 0; i < size-2; i++){
            if (spectators.get(i).clicked(mouseX, mouseY)){
                return spectators.get(i).slot;
            }
        }
        return -69;
    }

    public void draw(Graphics g){
        g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 55));
        // players
        blue.draw(g);
        red.draw(g);

        // spectators
        for (int i = 0; i < size-2; i++){
            spectators.get(i).draw(g);
        }
    }

    class SlotTile{
        int slot;
        String name = null;
        private int x, y; // top right, not center
        private int width = 400, height = 80;

        SlotTile(int slotIndex){
            slot = slotIndex;
            if (slotIndex >= 0){
                x = 80 + 420*(slotIndex%2);
                y = (slotIndex/2)*100 + 500;
            } else {
                x = 80;
                y = 100+150;
                if (slotIndex == RED){
                    x += 420;
                } 
            }
        }

        public boolean clicked(int mouseX, int mouseY){
            return (x < mouseX && mouseX < x+width) && (y < mouseY && mouseY < y+height);
        }
        public void draw(Graphics g){
            if (name != null){
                g.setColor(Color.GRAY);
                g.fillRect(x, y, width, height);
                g.setColor(Color.WHITE);
                g.drawString(name, x+20, y+60);
            } else {
                g.setColor(Color.DARK_GRAY);
                g.fillRect(x, y, width, height);
            }
        }
    }
}
