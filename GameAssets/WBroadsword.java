package GameAssets;

import java.util.LinkedList;

public class WBroadsword extends Weapon{   
    WBroadsword(Player p){
        player = p;
        atk = 15;
        def = 6;
        speed = 2;
        critR = 0;
        critD = 0.3;
        autoSwing = true;
    }

    private final int adist = 100;
    private final int bdist = 90;
    private final int bdir = 85;
    @Override
    public LinkedList<AttackField> attack(int x, int y) {

        double cd = 1;
        if (Math.random() < critR+player.critRate){
            cd = player.critDmg+critD;
        }
        attackCD = 20;

        double angle = Math.toDegrees(Math.atan2( y-(ScreenConsts.WINDOWHEIGHT/2), x-(ScreenConsts.WINDOWWIDTH/2) ));
        Point a = new Point((int)(player.x() + adist*Math.cos(Math.toRadians(angle))), player.y() - (int)(-adist*Math.sin(Math.toRadians(angle))));
        Point b = new Point((int)(player.x() + bdist*Math.cos(Math.toRadians(angle-bdir))), player.y() - (int)(-bdist*Math.sin(Math.toRadians(angle-bdir))));
        Point c = new Point((int)(player.x() + bdist*Math.cos(Math.toRadians(angle+bdir))), player.y() - (int)(-bdist*Math.sin(Math.toRadians(angle+bdir))));
        LinkedList<AttackField> output = new LinkedList<AttackField>();
        output.add(new AttackFieldTriangle((int)((player.atk+atk)*cd), a, b, c));
        return output;
    }

    @Override
    public AttackField attackSkill(int x, int y) {
        // TODO Auto-generated method stub
        return null;
    }
    

}
