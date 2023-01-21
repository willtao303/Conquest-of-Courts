import java.util.ArrayList;

import Components.*;
import GameAssets.ScreenConsts;
import GameAssets.Game;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;


public class Room {
    private SlotTile red = new SlotTile(Game.RED);
    private SlotTile blue = new SlotTile(Game.BLUE);
    public static final int NONE_CLICKED = -69;
    private ArrayList<SlotTile> spectators = new ArrayList<SlotTile>();
    private int size;
    private String code;

    Room (String roomCode, int roomSize){
        code = roomCode;
        size = roomSize;
        for (int i = 0; i < roomSize-2; i++){
            spectators.add(new SlotTile(i));
        }
    }

    //private Button startButton = new BasicButton(0, 0, 0, 0)

    public String getUserAt(int index){
        SlotTile user = getUser(index);
        if (user != null){
            return user.name;
        }
        return null;
    }
    
    public String set(int slot, String name){
        if (slot >= size-2){
            return null;
        }
        String temp = null;
        if (slot == Game.RED){
            if (red != null){
                temp = red.name;
            }
            red.name = name;
        } else if (slot == Game.BLUE){
            if (blue != null){
                temp = blue.name;
            }
            blue.name = name;
        } else {
            if (spectators.get(slot) != null){
                temp = spectators.get(slot).name;
            }
            spectators.get(slot).name = name;
        }
        return temp;
    }

    public void swap(int userA, int userB){
        if (userA == userB){
            return;
        }
        SlotTile temp = getUser(userA);
        setUser(userA, getUser(userA));
        setUser(userB, temp);
    }

    public void draw(Graphics g){
        g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 45));
        // players
        g.setColor(Color.BLUE);
        blue.draw(g);
        g.setColor(Color.RED);
        red.draw(g);

        // spectators
        g.setColor(Color.gray);
        for (int i = 0; i < size-2; i++){
            spectators.get(i).draw(g);
        }
    }

    /*
     *   helper functions 
     */
    private SlotTile getUser(int index){
        if (index == Game.RED){
            return red;
        } else if (index == Game.BLUE){
            return blue;
        } 
        return spectators.get(index);
    }
    private void setUser(int index, SlotTile user){
        if (index == Game.RED){
            red = user;
        } else if (index == Game.BLUE){
            blue = user;
        } 
         spectators.set(index, user);
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

    

    class SlotTile{
        int slot;
        String name = null;
        private int x, y; // top right, not center
        private int width = ScreenConsts.WINDOWWIDTH/4 - 20, height = 60;

        SlotTile(int slotIndex){
            slot = slotIndex;
            if (slotIndex >= 0){
                x = 10 + (width+20)*(slotIndex%2);
                y = (slotIndex/2)*(height+20) + ScreenConsts.WINDOWHEIGHT/2 - 100;
            } else {
                x = 10;
                y = ScreenConsts.WINDOWHEIGHT/2 - 260;
                if (slotIndex == Game.BLUE){
                    x += (width+20);
                } 
            }
        }

        public boolean clicked(int mouseX, int mouseY){
            return (x < mouseX && mouseX < x+width) && (y < mouseY && mouseY < y+height);
        }
        public void draw(Graphics g){
            if (name != null){
                //g.setColor(Color.GRAY);
                g.fillRect(x, y, width, height);
                g.setColor(Color.WHITE);
                g.drawString(name, x+20, y+50);
            } else {
                g.setColor(Color.DARK_GRAY);
                g.fillRect(x, y, width, height);
            }
        }
    }
}
