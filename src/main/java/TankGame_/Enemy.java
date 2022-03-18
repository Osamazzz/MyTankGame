package TankGame_;

import java.util.Vector;

/**
 * @author Obamazzz
 * @version 1.0
 */
public class Enemy extends Tank {
    //使用vector集合来保存敌人坦克的子弹
    Vector<Shot> shots = new Vector<>();
    private boolean isLive = true;

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public Enemy(int x, int y) {
        super(x, y);
    }

}
