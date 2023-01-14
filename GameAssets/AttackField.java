package GameAssets;
import java.awt.Color;
import java.awt.Graphics;

public class AttackField {
    int dmg;
    int tickdmg = 0;
    int tickcounter = 0;
    int ticks = 1;
    private double area;
    Point a;
    Point b;
    Point c;
    int xmove = 0;
    int ymove = 0;
    AttackField(int dmg, Point a, Point b, Point c){
        this.dmg = dmg;
        this.a = a;
        this.b = b;
        this.c = c;
        area = triangleArea(a, b, c)+0.1;
    }
    AttackField(int dmg, Point a, Point b, Point c, int ticks){
        this(dmg, a, b, c);
        this.ticks = ticks;
    }
    
    private boolean collidesPoint(Point other){
        double totalArea = (triangleArea(a, b, other) + triangleArea(b, c, other) + triangleArea(a, c, other));
        return area >= totalArea;
    }
    private double triangleArea(Point a, Point b, Point c){
        return Math.abs((a.x*(b.y-c.y) + b.x*(c.y-a.y) + c.x*(a.y-b.y))/2.0);
    }

    public boolean hitPlayer(Player p){
        return false;
    }

    public boolean hitUnit(Unit unit) {
        return collidesPoint(new Point(unit.x, unit.y));
    }


    public void move(){
        if (xmove != 0){
        a.x+=xmove;
        b.x+=xmove;
        c.x+=xmove;}
        if (ymove != 0){
        a.y+=ymove;
        b.y+=ymove;
        c.y+=ymove;}
    }

    public void draw(int xOffset, int yOffset, Graphics g){
        g.setColor(Color.RED);
        g.fillPolygon(new int[]{a.x- xOffset, b.x- xOffset, c.x- xOffset}, new int[]{a.y- yOffset, b.y- yOffset, c.y- yOffset}, 3);
    }

}
