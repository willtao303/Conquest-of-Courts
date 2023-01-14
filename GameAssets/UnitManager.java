package GameAssets;

import java.util.HashMap;
import java.util.LinkedList;

import java.awt.Graphics;

public class UnitManager {
    // referance vars
    private Camera cam;
    EnemyManager enemies;
    LinkedList<AttackField> atks;

    private int idCounter = 0; // assuming we will not have over 2^
    LinkedList<Unit> allUnits = new LinkedList<Unit>();
    HashMap<Integer, Unit> unitById = new HashMap<Integer, Unit>();
    LinkedList<UnitAttacker> attackingUnits = new LinkedList<UnitAttacker>();
    LinkedList<UnitDefender> defendingUnits = new LinkedList<UnitDefender>();

    public void setReferances(Camera c, EnemyManager e, LinkedList<AttackField> a){
        this.cam = c;
        this.enemies = e;
        this.atks = a;
    }
    public void update(){
        for (AttackField a: atks){
            if (a.tickcounter == 0){
                for (Unit u: allUnits){
                    u.collides(a);
                }
            }
            else;
            a.tickcounter++;
        }
        // TODO split this into updating by attacking units and defending units
        for (Unit u: allUnits){
            u.update();
            u.move();
            if (u.dead){
                allUnits.remove(u);
                break;
            }
        }
    }
    public void spawn(Unit u){
        allUnits.add(u);
        unitById.put(idCounter, u);
        idCounter++;
        if (u instanceof UnitAttacker){
            attackingUnits.add((UnitAttacker)u);
        } else if (u instanceof UnitDefender){
            defendingUnits.add((UnitDefender)u);
        }
    }
    public Unit clicked(int mouseX, int mouseY){
        for (Unit u: allUnits){
            if (u.distTo(mouseX, mouseY) < Math.max(u.height, u.width)){
                return u;
            }
        }
        return null;
    }
    public void draw(Graphics g){
        for (Unit u: allUnits){
            if (cam.onScreen((int)u.x(), (int)u.y(), u.height)){
                u.draw(cam.anchorX(), cam.anchorY(), g);
            }
        }
    }
}
