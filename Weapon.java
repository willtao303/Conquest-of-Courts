
import java.util.LinkedList;

public abstract class Weapon {
    Player player;
    int atk;
    int speed;
    int def;
    double critR;
    double critD;
    int attackCD = 0;
    int skillCD = 0;
    int passiveCD = 0;  
    boolean autoSwing;

    public abstract LinkedList<AttackField> attack(int x, int y);
    public abstract AttackField attackSkill(int x, int y);
    public void nonattackSkill(){};
    public void passiveSkill(){};

}