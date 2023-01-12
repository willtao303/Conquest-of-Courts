/*
public abstract class TowerBomber extends Tower{
    
    // ranged, shoots a bomb that explodes and that may persist
    
    int range;
    int damage;
    int reload;
    private int reloadCounter = 0;
    TowerBomber(int x, int y) {
        super(x, y);
    }
    public void lob(EnemyManager enemies){
        if (reloadCounter <= 0){
            for (Enemy e: enemies.units){
                if (distTo(e.x, e.y) < range){
                    // shoot them
                }
            }
        } else {
            reloadCounter--;
        }
    }
    public void update(EnemyManager e, UnitManager u){
        lob(e);
    }

}
*/