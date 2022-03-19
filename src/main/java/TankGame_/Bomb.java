package TankGame_;

/**
 * @author Obamazzz
 * @version 1.0
 * 爆炸效果
 */
public class Bomb {
    int x, y;//炸弹坐标
    int life = 10;//炸弹的生命周期
    boolean isLive = true;

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }
    //减少生命值
    public void lifeDecrease() {
        if (life>0){
            life--;
        }else {
            isLive = false;
        }
    }
}
