package GameAssets;

import java.awt.Color;
import java.awt.Graphics;

import Maps.*;

public class Map {
    private MapPreset map;
    
    Map(){
       
    }
    public void setup(int type){
        if (type == MapPreset.SMALLMAP){
            map = new SmallMap();
        }
    }

    public Point getSpawnPoint(int side){
        if (side == Game.RED){
            return new Point(map.spawnRX, map.spawnRY);
        } else {
            return new Point(map.spawnBX, map.spawnBY);
        }
    }
    public Point getCrysalPoint(int side) {
        if (side == Game.RED){
            return new Point(map.crystalRX, map.crystalRY);
        } else {
            return new Point(map.crystalBX, map.crystalBY);
        }
    }
    public int[][] getTowers(int side){
        if (side == Game.RED){
            return map.towersR;
        } else{
            return map.towersB;
        }
    }
    public void draw (int topLeftX, int topLeftY, Graphics g){
        int xdraw = 0;
        int ydraw = 0;
        int widthdraw = Consts.WINDOWWIDTH;
        int heightdraw = Consts.WINDOWHEIGHT;
        if (topLeftX < 0) {
           xdraw = - topLeftX;
           topLeftX = 0;
        }
        if (topLeftY < 0) {
           ydraw = - topLeftY;
           topLeftY = 0;
        }
        if (topLeftX + Consts.WINDOWWIDTH >= map.width){
            widthdraw = map.width - topLeftX;
        }
        if (topLeftY + Consts.WINDOWHEIGHT >= map.height){
            heightdraw =  map.height - topLeftY ;
        }try{
        g.drawImage(map.image.getSubimage(topLeftX/Consts.PIXELRARIO, topLeftY/Consts.PIXELRARIO, (widthdraw/Consts.PIXELRARIO), (heightdraw/Consts.PIXELRARIO)), xdraw, ydraw, widthdraw, heightdraw,null);
        }catch(Exception e){}
    }

    public boolean inBounds(int x, int y){
        if (x < 0 || y < 0 || x > map.width || y > map.height){
            return false;
        }
        int crossCounter;
        for (int[][] wallSection : map.boundries){
            crossCounter = 0;
            for (int i = 0; i < wallSection.length-1; i++){
                if (checkCrossover(wallSection[i], wallSection[i+1], x, y)){
                    crossCounter++;
                }
            }
            if (checkCrossover(wallSection[wallSection.length-1], wallSection[0], x, y)){
                crossCounter++;
            }
            if (crossCounter%2 == 1){
                return false;
            }
        }
        return true;
    }
    /*private String printArray(int[][]a){
        String output = "";
        for (int[]b:a){
            output += "("+b[0]+", "+b[1]+"), ";
        }
        return output;
    }*/

    private boolean checkCrossover(int[]a, int[]b, int x, int y){
        if (a[0] == b[0]){
            return x==a[0] && (Math.min(a[1], b[1]) < y && y < Math.max(a[1], b[1]));
        } else {
            if (x==a[0]){
                return y > a[1];
            }
            if (x==b[0]){
                return y > b[1];
            }
        }
        
        if (a[0]< b[0]){
            if (!(a[0]< x && x < b[0])){
                return false;
            }
            return (b[1]-a[1]+0.0)/(b[0]-a[0]+0.0) <= (y-a[1]+0.0)/(x-a[0]+0.0);
        } else {
            if (!(b[0]< x && x < a[0])){
                return false;
            }
            return (a[1]-b[1]+0.0)/(a[0]-b[0]+0.0) <= (y-b[1]+0.0)/(x-b[0]+0.0);
        }

    }
    public void drawBounds(int xoff, int yoff, Graphics g, int xx, int yy) {
        for (int i = 0; i < map.boundries.length; i++){
            int[] x = new int[map.boundries[i].length];
            int[] y = new int[map.boundries[i].length];
            if (inBounds(xx, yy)){
                g.setColor(Color.BLUE);
            } else {
                g.setColor(Color.RED);
            }
            for (int j = 0; j < map.boundries[i].length; j++){
                x[j] = map.boundries[i][j][0] - xoff;
                y[j] = map.boundries[i][j][1] - yoff;
            }
            
            g.fillPolygon(x, y, map.boundries[i].length);
        }
    }
    public MapPreset getPresetMap() {
        return map;
    }
    

}
