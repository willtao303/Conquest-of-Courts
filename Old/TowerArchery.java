/* 
public abstract class TowerArchery extends Tower{
     
    //shoots single target projectiles, larger ranges
     
    int range;
    int damage;
    int reload;
    private int reloadCounter = 0;
    TowerArchery(int x, int y) {
        super(x, y);
    }
    public void shoot(EnemyManager enemies){
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
        shoot(e);
    }

}
*/