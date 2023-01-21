package GameAssets;
import java.awt.Color;
public class TowerPreset {
    public static final int ARCHER = 0;
    public static final int BOMBER = 1;
    public static final int BARRACK = 2;
    public static final int MAGE = 3;
    public static final int CASTLE = 4;
    public static final int ALCHEMIST = 5;
    public static final int GCASTLE = 6;

    public static final int COST = 10;

    public static int[][] STATS = {
        { // ARCHER
            120, // maxHp
            15, // dmg
            Tower.PROJECTILE, // shoot type
            1000, // range
            30, // cooldown
            Tower.NONE, // spawn type
            -1, // spawn type
            -1, // cooldown
            0, // max units
            -1, // spawn range
            150 // cost
        },
        { // BOMBER
            120, // maxHp
            20, // dmg
            Tower.BALLISTIC, // shoot type
            200, // range
            30, // cooldown
            Tower.NONE, // spawn type
            -1, // spawn type
            -1, // cooldown
            0, // max units
            -1, // spawn range
            150 // cost
        },
        { // BARRACK
            80, // maxHp
            30, // dmg
            Tower.NONE, // shoot type
            -1, // range
            -1, // cooldown
            Tower.ATTACKING, // spawn type
            -1, // spawn type ------------------- TODO
            30, // cooldown
            -1, // max units
            -1, // spawn range
            150 // cost
        },
        { // MAGE
            100, // maxHp
            1, // dmg
            Tower.DRAIN, // shoot type
            200, // range
            3, // cooldown
            Tower.NONE, // spawn type
            -1, // spawn type
            -1, // cooldown
            0, // max units
            -1, // spawn range
            150 // cost
        },
        { // CASTLE
            200, // maxHp
            20, // dmg
            Tower.NONE, // shoot type
            -1, // range
            -1, // cooldown
            Tower.DEFENDING, // spawn type
            UnitDefender.KNIGHT, // spawn type
            250, // cooldown
            3, // max units
            UKnight.RANGE, // spawn radius
            150 // cost
        },
        { // ALCHEMIST
            200, // maxHp
            20, // dmg
            Tower.BALLISTIC, // shoot type
            -1, // range
            -1, // cooldown
            Tower.NONE, // spawn type
            -1, // spawn type
            250, // cooldown
            3, // max units
            -1, // spawn radius
            150 // cost
        },
        { // GREATER CASTLE
            300, // maxHp
            20, // dmg
            Tower.PROJECTILE, // shoot type
            300, // range
            200, // cooldown
            Tower.DEFENDING, // spawn type
            UnitDefender.KNIGHT, // spawn type
            200, // cooldown
            5, // max units
            UKnight.RANGE, // spawn radius
            150 // cost
        }
    };
    public static int[][] UPGRADES = {
        {-1, -1}, // archer
        {-1 ,-1}, // bomber
        {-1 ,-1}, // barrack
        {-1 ,-1}, // wiz
        {GCASTLE}, // castle
        {-1}, // alchemist
        {-1}, // greater castle

    };

    public static Color[] COLOR = {
        new Color(72, 156, 80),
        new Color(235, 182, 77),
        new Color(168, 153, 131),
        new Color(167, 154, 252),
        new Color(160, 160, 160),
        new Color(164, 227, 109),
        Color.YELLOW//new Color(120, 120, 120)

    };
}
