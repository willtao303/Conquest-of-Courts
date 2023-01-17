package GameAssets;
public class Point {
    public int x;
    public int y;
    Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Point(double x, double y) {
        this.x = (int)x;
        this.y = (int)y;
    }

    public double distTo(Point other){
        return Math.sqrt((this.x-other.x)*(this.x-other.x) - (this.y-other.y)*(this.y-other.y));
    }
    public double distTo(int x, int y){
        return Math.sqrt((this.x-x)*(this.x-x) + (this.y-y)*(this.y-y));
    }
    public double dirTo(Point other){
        return Math.toDegrees(Math.atan2((other.x-this.x), (other.y-this.y)))+270;
    }
    public double dirTo(int x, int y){
        return Math.toDegrees(Math.atan2((x-this.x), (y-this.y)))+270;
    }
    public String toString(){
        return "{"+x + ", " + y+"}";
    }

    public static double dist(int ax, int ay, int bx, int by) {
        return Math.sqrt((ax-bx)*(ax-bx) - (ay-by)*(ay-by));
    }
}
