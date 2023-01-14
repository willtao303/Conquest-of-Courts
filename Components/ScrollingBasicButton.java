package Components;

import java.awt.Color;

public class ScrollingBasicButton extends BasicButton{

    int xOffset = 0;
    int yOffset = 0;

    public ScrollingBasicButton(int x, int y, int width, int height) {
        super(x, y, width, height, Color.white);
    }

    public ScrollingBasicButton(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }
    
    public void offsets(int xOff, int yOff){
        xOffset = xOff;
        yOffset = yOff;
    }

    @Override
    public int x(){
        return x + xOffset;
    }
    @Override
    public int y(){
        return y + yOffset;
    }
}
