package GameAssets;
import java.awt.Color;
public class TowerPreset {
    public static final int ARCHER = 0;
    public static final int BOMBER = 1;
    public static final int BARRACK = 2;
    public static final int MAGE = 3;
    public static final int CASTLE = 4;
    public static final int ALCHEMIST = 5;

    public static int[][] STATS = {
        { // ARCHER
            120, // maxHp
            1000, // range
            Tower.PROJECTILE, // shoot type
            Tower.NONE, // spawn type
            15, // dmg
            30, // cooldown
            2, // number of upgrades
            150 // cost
        },
        { // BOMBER
            120, // maxHp
            200, // range
            Tower.BALLISTIC, // shoot type
            Tower.NONE, // spawn type
            20, // dmg
            30, // cooldown
            2, // number of upgrades
            150 // cost
        },
        { // BARRACK
            80, // maxHp
            -1, // range
            Tower.NONE, // shoot type
            Tower.ATTACKING, // spawn type
            20, // dmg
            30, // cooldown
            2, // number of upgrades
            150 // cost
        },
        { // MAGE
            100, // maxHp
            200, // range
            Tower.DRAIN, // shoot type
            Tower.NONE, // spawn type
            1, // dmg
            30, // cooldown
            2, // number of upgrades
            150 // cost
        },
        { // CASTLE
            200, // maxHp
            100, // range
            Tower.PROJECTILE, // shoot type
            Tower.DEFENDING, // spawn type
            20, // dmg
            70, // cooldown
            2, // number of upgrades
            150 // cost
        }
    };
    public static int[][] UPGRADES = {
        {-1, -1},
        {-1 ,-1},
        {-1 ,-1},
        {-1 ,-1},
        {-1 ,-1},
    };

    public static Color[] COLOR = {
        new Color(72, 156, 80),
        new Color(235, 182, 77),
        new Color(168, 153, 131),
        new Color(167, 154, 252),
        new Color(120, 120, 120)

    };
}
