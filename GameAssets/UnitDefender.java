package GameAssets;
import java.awt.Graphics;
import java.awt.Color;
public abstract class UnitDefender extends Unit{
    public static int KNIGHT = 0;
    private Point anchor;
    private int range;
    private Point station;
    UnitDefender(int x, int y, Map m, Point towerPos, int range) {
        super(x, y, m);
        anchor = towerPos;
        this.range = range;
    }

    @Override
    public void draw(int xOff, int yOff, Graphics g){
        if (selected && station != null){
            g.setColor(Color.white);
            g.fillPolygon(
                new int[]{station.x - xOff, station.x - xOff - 10, station.x - xOff + 10}, 
                new int[]{station.y - yOff - 5, station.y - yOff - 40, station.y - yOff - 40},
                3
            );
            g.drawLine((int)x - xOff, (int)y + height/2 - yOff, station.x - xOff, station.y - yOff);
        }
        super.draw(xOff, yOff, g);
    }
    
    public void provoke(EnemyManager e){
        int minDist = 9999;
        int enemyId = -1;
        for (Enemy enemy: e.attackingEnemies){
            if (Point.dist((int)x, (int)y, enemy.x, enemy.y) < minDist){
                minDist = (int)Point.dist((int)x, (int)y, enemy.x, enemy.y);
                enemyId = enemy.id;
            }
        }
        if (enemyId != -1){
            this.target = enemyId;
            // e.provoke(enemyId, this.id);
        }
    }

    public void move(){
        if (station != null){
            if (distTo(station) > 30){
                if (distTo(anchor) > range){
                    moveTowards(anchor);
                    station = null;
                } else {
                    moveTowards(station);
                }
            } else {
                station = null;
            }
        }
    }


    protected boolean isMoving(){
        return station != null;
    }
    public void moveTo(Point p){
        station = p;
    }
    protected void clearPath(){
        station = null;
    }
}
