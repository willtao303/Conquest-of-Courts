
public class Selection {
    public static final int NONE = -1;
    public static final int TOWER = 1;
    public static final int UNIT = 2;
    public static final int MULTIUNIT = 3;
    int object = -1;
    int index = -1;
    private TowerSlot t = null;
    private Unit u = null;
    private Unit[] us= null;
    public void select(TowerSlot t){
        if (object == TOWER){
            this.t.unfocus();
        }
        if (object == UNIT){
            this.u.unfocus();
            this.u = null;
        }
        t.focus();
        this.t = t;
        object = TOWER;
    }
    public void select(Unit u){
        if (object == TOWER){
            this.t.unfocus();
            this.t = null;
        }
        if (object == UNIT){
            this.u.unfocus();
        }
        u.focus();
        this.u = u;
        object = UNIT;
    }
    public void selectGround(){
        if (object == TOWER){
            object = NONE;
            t.unfocus();
            t = null;
        } else if (object == UNIT){
            // nothing happens
        }
    }
    public void deselect(){
        if (object == TOWER){
            object = NONE;
            t.unfocus();
            t = null;
        } else if (object == UNIT){
            object = NONE;
            u.unfocus();
            u = null;
        }

    }

    public TowerSlot tower(){
        return t;
    }
    public Unit unit(){
        return u;
    }
    
}
