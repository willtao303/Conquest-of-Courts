/*import java.util.LinkedList;
import java.awt.Color;
import java.awt.Graphics;

public class Section {
    private Section[] adjacent = new Section[3];
    private LinkedList<Minion> enemies = new LinkedList<Minion>();
    private LinkedList<Minion> defenders = new LinkedList<Minion>();
    private LinkedList<Minion> attackers = new LinkedList<Minion>();
    private Player player;
    private Point vertexA;
    private Point vertexB;
    private Point vertexC;
    public Point center;
    private double area;

    private Color color;

    Section(int [][] vertexes){
        vertexA = new Point(vertexes[0][0], vertexes[0][1]);
        vertexB = new Point(vertexes[1][0], vertexes[1][1]);
        vertexC = new Point(vertexes[2][0], vertexes[2][1]);
        center = new Point ((int)((vertexes[0][0]+vertexes[1][0]+vertexes[2][0])/3.0), (int)((vertexes[0][1]+vertexes[1][1]+vertexes[2][1])/3.0));
        area = triangleArea(vertexA, vertexB, vertexC)+0.1;
        this.color = new Color((int)(Math.random()*155) +100, (int)(Math.random()*155)+100, (int)(Math.random()*155)+100);
    }
    public void setAdjacent(int index, Section other){
        adjacent[index] = other;
    }
    public void addEnemy(Minion e){
        enemies.add(e);
    }
    public void addPlayer(Player p){
        this.player = p;
    }

    public void update(){
        if (player != null){
            if (!inBounds(player)){
                for (Section s: adjacent){
                    if (s.inBounds(player)){
                        s.addPlayer(player);
                        player = null;
                        break;
                    }
                }
            }
        }
        for (Minion e : enemies){
            if (!inBounds(e)){
                for (Section s: adjacent){
                    if (s.inBounds(e)){
                        s.addEnemy(e);
                        enemies.remove(e);
                        break;
                    }
                }
            }
        }
    }


    private boolean inBounds(Minion minion){
        Point other = new Point(minion.x, minion.y);
        double totalArea = (triangleArea(vertexA, vertexB, other) + triangleArea(vertexB, vertexC, other) + triangleArea(vertexA, vertexC, other));
        return area >= totalArea;

    }
    private boolean inBounds(Player player){
        Point other = new Point(player.x(), player.y());
        double totalArea = (triangleArea(vertexA, vertexB, other) + triangleArea(vertexB, vertexC, other) + triangleArea(vertexA, vertexC, other));
        return area >= totalArea;

    }
    public boolean inBounds(int x, int y){
        Point other = new Point(x, y);
        double totalArea = (triangleArea(vertexA, vertexB, other) + triangleArea(vertexB, vertexC, other) + triangleArea(vertexA, vertexC, other));
        return area >= totalArea;

    }
    private double triangleArea(Point a, Point b, Point c){
        return Math.abs((a.x*(b.y-c.y) + b.x*(c.y-a.y) + c.x*(a.y-b.y))/2.0);
    }

    public void draw(int xOffset, int yOffset, Graphics g){
        //if (inBounds(player)){
            g.setColor(color);
            g.fillPolygon(new int[]{vertexA.x- xOffset, vertexB.x- xOffset, vertexC.x- xOffset}, new int[]{vertexA.y- yOffset, vertexB.y- yOffset, vertexC.y- yOffset}, 3);

        //}
    }
    public void drawConnections(int xOffset, int yOffset, Graphics g){
        g.setColor(Color.black);
        for (Section s: adjacent){
            if (s != null){
                g.drawLine(center.x- xOffset, center.y-yOffset, s.center.x- xOffset, s.center.y-yOffset);
            }
        }
    }



    public String toString(){
        return "("+vertexA+")  ("+vertexB+")  ("+vertexC+")";
    }
}
*/


/*
 import java.util.LinkedList;

public class Section {
    private Point topLeft;
    private Point botRight;
    private Section[] adjSections;

    Section(int[] topR, int[] botL, int adjacencies){
        topLeft = new Point(topR[0], topR[1]);
        botRight = new Point(botL[0], botL[1]);
        adjSections = new Section[adjacencies];
    }
    public void addAdjacent(int i, Section s){
        adjSections[i] = s;
    }
    public boolean contains(Unit u){
        if (topLeft.x < u.x && u.x < botRight.x && topLeft.y < u.y && u.y < botRight.y){
            return true;
        }
        return false;
    }
}

 */