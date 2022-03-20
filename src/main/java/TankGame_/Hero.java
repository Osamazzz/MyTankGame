package TankGame_;

import java.util.Vector;

/**
 * @author Obamazzz
 * @version 1.0
 */
public class Hero extends Tank {
    Shot shot;//射击线程
    Vector<Shot> shotList = new Vector<>();


    public Hero(int x, int y) {
        super(x, y);
    }

    public void shotStart() {
        if (shotList.size() == 5) {
            return;
        }
        //根据当先对象的坐标和方向来创建shot对象
        switch (getDirection()) {
            case 0://向上
                shot = new Shot(getX() + 20, getY(), 0);
                break;
            case 1://向右
                shot = new Shot(getX() + 50, getY() + 30, 1);
                break;
            case 2://向下
                shot = new Shot(getX() + 20, getY() + 60, 2);
                break;
            case 3://向左
                shot = new Shot(getX() - 10, getY() + 30, 3);
                break;
            default:
                break;
        }
        //把新建的shot加入到集合中
        shotList.add(shot);
        new Thread(shot).start();
    }
}
