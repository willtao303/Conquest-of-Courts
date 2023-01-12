/*
public abstract class TowerMagic extends Tower{
    
    //Single target ranged consistant damage 
     
    Enemy target;
    int range;
    int damage;
    TowerMagic(int x, int y) {
        super(x, y);
    }
    public void cast(EnemyManager enemies){
        for (Enemy e: enemies.units){
            if (distTo(e.x, e.y) < range){
                // shoot them
            }
        }
    }
    public void update(EnemyManager e, UnitManager u){
        cast(e);
    }

}
*/