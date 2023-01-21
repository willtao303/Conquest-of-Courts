package GameAssets;

import java.awt.Graphics;
import java.awt.Color;

public class AttackFieldTriangle extends AttackField{
    private double area;
    Point a;
    Point b;
    Point c;
    
    AttackFieldTriangle(int dmg, int tickdmg, Point a, Point b, Point c, int ticks){
        super(dmg, tickdmg, ticks);
        this.a = a;
        this.b = b;
        this.c = c;
        area = triangleArea(a, b, c)+0.1;
    }
    AttackFieldTriangle(int dmg, Point a, Point b, Point c){
        super(dmg);
        this.a = a;
        this.b = b;
        this.c = c;
        area = triangleArea(a, b, c)+0.1;
    }

    protected boolean collidesPoint(Point other){
        double totalArea = (triangleArea(a, b, other) + triangleArea(b, c, other) + triangleArea(a, c, other));
        return area >= totalArea;
    }
    private double triangleArea(Point a, Point b, Point c){
        return Math.abs((a.x*(b.y-c.y) + b.x*(c.y-a.y) + c.x*(a.y-b.y))/2.0);
    }
    @Override
    public void draw(int xOff, int yOff, Graphics g){
        g.setColor(Color.RED);
        g.fillPolygon(new int[]{a.x- xOff, b.x- xOff, c.x- xOff}, new int[]{a.y- yOff, b.y- yOff, c.y- yOff}, 3);
    }
    @Override
    protected Point[] getPoints() {
        return new Point[]{a, b, c};
    }

    @Override
    public String toString() {
        return "3/"+a.x+"/"+a.y+"/"+b.x+"/"+b.y+"/"+c.x+"/"+c.y+"/"+dmg();
    }
}
