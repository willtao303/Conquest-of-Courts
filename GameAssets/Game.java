package GameAssets;

import java.awt.Graphics;
import java.util.LinkedList;
import Maps.MapPreset;
import java.awt.Color;

public class Game {
    public static int SPECTATOR = 0;
    public static int RED = -1;
    public static int BLUE = -2;
    Input input;

    Map map = new Map();
    Camera camera = new Camera();
    Overlay overlay = new Overlay();
    Selection selection = new Selection();
    boolean playerFocus = true;

    Resources resources = new Resources();

    Player player = new Player();
    LinkedList<AttackField> attacks = new LinkedList<AttackField>();

    LinkedList<Point> temp = new LinkedList<Point>();

    UnitManager playerUnits = new UnitManager();
    EnemyManager enemyUnits = new EnemyManager();

    TowerSlot[] towers;
    BaseCrystal crystal;

    public Game(){}
    public void setup(Input i, int side){
        input = i;
        map.setup(MapPreset.SMALLMAP);
        player.setup(map.getSpawnPoint(side), RED, map);
        crystal = new BaseCrystal(map.getCrysalPoint(side));
        camera.setup(this);
        camera.moveTo(map.getSpawnPoint(side));
        overlay.addResoures(this);
        towers = new TowerSlot[map.getTowers(side).length];
        int j = 0;
        for (int[] towerCoords: map.getTowers(side)){
            towers[j] = new TowerSlot(towerCoords[0], towerCoords[1]);
            towers[j].setEnemies(enemyUnits);
            j++;
        }
        playerUnits.setReferances(camera, enemyUnits);
    }


    public void run(){
        // TODO 

        /*
         *  updating objects
         */
        player.update();
        for (AttackField a: attacks){
            if (a.dead()){
                attacks.remove(a);
                break;
            }
        }
        for (AttackField a: attacks){
            a.update();
            if (a instanceof AttackFieldBullet){
                ((AttackFieldBullet)a).collidesWall(map);
            }
        }
        playerUnits.update();
        
        for (TowerSlot t: towers){
            t.update(attacks);
        }

        if (player.isDead()){
            this.playerFocus = false;
            overlay.setCurrent(Overlay.DEAD);
        }
        if (player.respawned()){
            this.playerFocus = true;
            overlay.setCurrent(Overlay.NONE);
        }


        if (overlay.getCurrent() == Overlay.NONE && !player.isDead()){
            /*
             *  proccessing keypresses
             */
            if (input.keyIsTapped(Input.SWITCH)){
                this.playerFocus = !this.playerFocus;
                selection.deselect();
            }
            if (playerFocus){
                if (player.weapon.autoSwing){
                    if (input.mouseIsDown(Input.LMB)){
                        if (player.weapon.attackCD <= 0){
                            attacks.addAll(player.weapon.attack(input.mousePosX(), input.mousePosY()));
                        }
                    }
                }
                else {
                    if (input.mouseIsTapped(Input.LMB)){
                        if (player.weapon.attackCD <= 0){
                            attacks.addAll(player.weapon.attack(input.mousePosX(), input.mousePosY()));
                        }
                    }
                }
                if (input.mouseIsDown(Input.MIDDLECLICK)){
                    player.shortPath(camera.anchorX() + input.mousePosX(), camera.anchorY() + input.mousePosY());
                }
                if (input.keyIsDown(Input.UP)||input.keyIsDown(Input.DOWN)||input.keyIsDown(Input.LEFT)||input.keyIsDown(Input.RIGHT)){
                    player.move(input.keyIsDown(Input.UP),input.keyIsDown(Input.DOWN),input.keyIsDown(Input.LEFT),input.keyIsDown(Input.RIGHT));
                }
                camera.moveTo(player.x(), player.y());
            } else {
                camera.move(input.keyIsDown(Input.UP),input.keyIsDown(Input.DOWN),input.keyIsDown(Input.LEFT),input.keyIsDown(Input.RIGHT));
                
                if (input.mouseIsTapped(Input.LMB)){
                    boolean somethingWasClicked = false;
                    if (selection.object == Selection.TOWER){
                        if (selection.tower().buttonClicked(input.mousePosX()+camera.anchorX(), (input.mousePosY()+camera.anchorY()))){
                            somethingWasClicked = true;
                        }
                    }
                    for (TowerSlot t : towers){
                        if (t.clicked(input.mousePosX()+camera.anchorX(), (input.mousePosY()+camera.anchorY()))){
                            selection.select(t);
                            somethingWasClicked = true;
                        } 
                    }

                    Unit selectedUnit = playerUnits.clicked(input.mousePosX()+camera.anchorX(), (input.mousePosY()+camera.anchorY()));
                    if (selectedUnit != null){
                        selection.select(selectedUnit);
                        somethingWasClicked = true;
                    }

                    
                    if (!somethingWasClicked){
                        selection.selectGround();
                    }
                }
                
                if (input.mouseIsDown(Input.LMB) && !input.keyIsDown(Input.MULTISELECT)){
                    camera.drag(input.mousePosX(), input.mousePosY());
                    if (player.collidesPoint(input.mousePosX()+camera.anchorX(), (input.mousePosY()+camera.anchorY()))){
                        player.hit(3);
                    }
                } else {
                    camera.dragged = false;
                }
                
                if (input.mouseIsTapped(Input.RMB)){
                    if (selection.object == Selection.NONE){
                        System.out.println("{"+input.mousePosX()+camera.anchorX()+", "+(input.mousePosY()+camera.anchorY())+"},");
                        if (Math.random() < 0.5){
                            playerUnits.spawn(new UTestDummy(input.mousePosX()+camera.anchorX(), input.mousePosY()+camera.anchorY(), map, null));
                        } else {
                            playerUnits.spawn(new UKnight(input.mousePosX()+camera.anchorX(), input.mousePosY()+camera.anchorY(), map, enemyUnits));
                        }
                    } else if (selection.object == Selection.UNIT){
                        if (selection.unit() instanceof UnitDefender){
                            ((UnitDefender)selection.unit()).moveTo(new Point(input.mousePosX()+camera.anchorX(), input.mousePosY()+camera.anchorY()));
                        }
                    }
                }
                if (input.keyIsTapped(Input.PAUSE)){
                    //System.out.println("---");
                    System.out.println(temp);
                    temp.clear();
                }
            }
        } else {
            if (input.keyIsTapped(Input.ESC)){
                if (overlay.getCurrent() != Overlay.NONE){
                    overlay.setCurrent(Overlay.NONE);
                }
            }
        }

        enemyUnits.updateAttacks();
    }
    Color BgNullColor = new Color(66, 60, 77);
    public void render(Graphics g){
        g.setColor(BgNullColor);
        g.fillRect(0, 0, Consts.WINDOWWIDTH, Consts.WINDOWHEIGHT);
        devDraw(g);
        if (camera.onScreen(player.x(), player.y(), 30)){
            player.draw(camera.anchorX(), camera.anchorY(), g);
        }
        playerUnits.draw(g);
        enemyUnits.draw(camera, g);
        for (TowerSlot t: towers){
            if (camera.onScreen(t.x(), t.y(), TowerSlot.SIZE)){
                t.draw(camera.anchorX(), camera.anchorY(), g);
            }
        }
        crystal.draw(g, camera);


        g.setColor(Color.DARK_GRAY);
        for(Point p: temp){
            g.fillRect(p.x-camera.anchorX(), p.y-camera.anchorY(), 10, 10);
        }


        if (!playerFocus){
            g.setColor(new Color(200,200,200,100));
            g.fillRect(20, 100, 60, 60);
            g.setColor(Color.black);
            g.drawString("x: "+camera.x(), 30, 120);
            g.drawString("y: "+camera.y(), 30, 140);
            g.fillRect(Consts.WINDOWWIDTH/2-2, Consts.WINDOWHEIGHT/2-2, 4, 4);
        }

        
        overlay.draw(g);
        overlay.drawOverlay(g);
    }

    public void devDraw(Graphics g){
        map.draw(camera.anchorX(), camera.anchorY(), g);
        map.drawBounds(camera.anchorX(), camera.anchorY(),g, player.x(), player.y());
        
        for (AttackField a : attacks){
            a.draw(camera.anchorX(), camera.anchorY(),g);
        }
        for (AttackField a: enemyUnits.attacks()){
            a.draw(camera.anchorX(), camera.anchorY(),g);
        }
    }

    public EnemyManager getEnemyManager(){
        return this.enemyUnits;
    }

    public UnitManager getUnits(){
        return this.playerUnits;
    }
    
    public Camera getCamera(){
        return this.camera;
    }
    public Player getPlayer() {
        return this.player;
    }
    public String attacksToString(){
        String output = "";
        for (AttackField a: attacks){
            output = output + " " + a.toString();
        }
        return output;
    }
}
