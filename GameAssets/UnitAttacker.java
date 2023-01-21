package GameAssets;

import java.util.LinkedList;

public abstract class UnitAttacker  extends Unit{

    UnitAttacker(int x, int y, Map m) {
        super(x, y, m);
    }

    LinkedList<Point> path = new LinkedList<Point>();

    public void setTarget(int id){
        target = id;
    }

    protected boolean isMoving(){
        return !path.isEmpty();
    }
    protected void clearPath(){
        path.clear();
    }
}
