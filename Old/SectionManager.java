/*import java.util.LinkedList;

import sun.swing.SwingUtilities2.Section;

public class SectionManager {
    private final int[][] adjMatrix = {{1,1},{-1,1},{-1,-1},{1,-1},{0,1},{1,0},{0,-1},{-1,0}};
    Section[] sections;
    LinkedList<UnitAttacker> attackers;
    LinkedList<UnitDefender> defenders;
    EnemyManager enemies;

    public void setup(EnemyManager e, Map m){
        this.enemies = e;
        Section[][] temp = new Section[m.getPresetMap().sectorsVertical][m.getPresetMap().sectorsHorizontal];

        for (int i = 0; i < m.getPresetMap().sectorsVertical; i++){
            for (int j = 0; j < m.getPresetMap().sectorsHorizontal; j++){
                int adj = 0;
                if (i == 0 || i == m.getPresetMap().sectorsVertical-1){
                    if (j == 0 || j == m.getPresetMap().sectorsHorizontal-1){
                        adj = 3;
                    } else {
                        adj = 5;
                    }
                } else {
                    if (j == 0 || j == m.getPresetMap().sectorsHorizontal-1){
                        adj = 5;
                    } else {
                        adj = 8;
                    }
                }
                temp[i][j] = new Section({i*m.getPresetMap().height, j*m.getPresetMap().width}, {(i+1)*m.getPresetMap().height, (j+1)*m.getPresetMap().width}, adj);
            }
        }
        int k = 0;
        for (int i = 0; i < m.getPresetMap().sectorsVertical; i++){
            for (int j = 0; j < m.getPresetMap().sectorsHorizontal; j++){
                int m = 0;
                for(int[] adjacencies: adjMatrix){
                    try {
                        temp[i][j].setAdjacent(m, temp[i+adjacencies[1]][j+adjacencies[0]]);
                        m++;
                    } catch (Exception e) {}
                }
                sections[k] = temp[i][j];
                k++;
            }
        }
    }

    public void update(){
        for (int i = 0; i < sections.length; i++){
            sections[i].update(enemies.getEnemiesBySector(i));
        }
    }

}
*/