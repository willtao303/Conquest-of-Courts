package GameAssets;

import java.util.LinkedList;

public abstract class UnitAttacker  extends Unit{

    UnitAttacker(int x, int y, Map m, EnemyManager e) {
        super(x, y, m, e);
    }

    LinkedList<Point> path = new LinkedList<Point>();

    public void setTarget(Unit u){
        target = u;
    }

    protected void clearPath(){
        path.clear();
    }
}
