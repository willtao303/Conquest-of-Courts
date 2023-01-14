package GameAssets;

public class Camera {
    private int x, y;
    private final int speed = 20;
    boolean locked = false;

    boolean dragged = false;
    int mouseX, mouseY;

    Game game;

    public void setup(Game game){
        this.game = game;
        this.x = 0;
        this.y = 0;
    }

    public void move(boolean up, boolean down, boolean left, boolean right){
        int h = 0, v = 0;
        if (up){v--;}
        if (down){v++;}
        if (left){h--;}
        if (right){h++;}

        move(h*speed, v*speed);
    }

    public void move(int horizontal, int vertical){
        if (this.x + horizontal > 0){
            this.x += horizontal;
        } else if (this.x - 1 > 0){
            this.x --;
        }
        if (!(this.y + vertical < 0)){
            this.y += vertical;
        } else if (this.y - 1 > 0){
            this.y --;
        }
    }

    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void moveTo(Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    public void drag(int mouseX, int mouseY){
        if (!dragged){
            dragged = true;
        } else {
            move(this.mouseX - mouseX, this.mouseY - mouseY);
        }
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    public int anchorX(){
        return this.x - (Consts.WINDOWWIDTH/2);
    }
    public int anchorY(){
        return this.y - (Consts.WINDOWHEIGHT/2);
    }
    public int x(){
        return this.x;
    }
    public int y(){
        return this.y;
    }

    public boolean onScreen(int x, int y, int padding) {
        return this.x - (Consts.WINDOWWIDTH/2) - padding < x && x < this.x + (Consts.WINDOWWIDTH/2) + padding && this.y - (Consts.WINDOWHEIGHT/2) - padding < y && y < this.y + (Consts.WINDOWHEIGHT/2) + padding;
    }

}
