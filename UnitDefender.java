
import Containters.Point;

public abstract class UnitDefender extends Unit{
    UnitDefender(int x, int y, Map m, EnemyManager e) {
        super(x, y, m, e);
    }

    Point station;
    
    public void provoke(UnitAttacker u){
        this.target = u;
        u.setTarget(this);
    }

    public void move(){
        if (station != null){
            if (distTo(station) > 30){
                moveTowards(station);
            }
        }
    }
    public void moveTo(Point p){
        station = p;
    }
    protected void clearPath(){
        station = null;
    }
}
