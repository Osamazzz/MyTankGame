package TankGame_;

import java.util.Vector;

/**
 * @author Obamazzz
 * @version 1.0
 * 敌人坦克类-敌人线程
 */
public class Enemy extends Tank implements Runnable {
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

    @Override
    public void run() {
        while (true) {
            //如果敌人坦克的子弹消失了
            if (isLive && shots.size() < 1) {
                Shot s = null;
                switch (getDirection()) {
                    case 0:
                        s = new Shot(getX() + 20, getY(), 0);
                        break;
                    case 1:
                        s = new Shot(getX() + 50, getY() + 30, 1);
                        break;
                    case 2:
                        s = new Shot(getX() + 20, getY() + 60, 2);
                        break;
                    case 3:
                        s = new Shot(getX() - 10, getY() + 30, 3);
                        break;
                    default:
                        break;
                }

                shots.add(s);
                new Thread(s).start();//启动线程
            }
            //根据坦克方向来继续移动
            switch (getDirection()) {
                case 0://up
                    for (int i = 0; i < (10 + (int) (Math.random() * 30)); i++) {//让坦克走一会
                        moveUp();//过一会再动
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1://right
                    for (int i = 0; i < (10 + (int) (Math.random() * 30)); i++) {//让坦克走一会
                        moveRight();//过一会再动
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2://down
                    for (int i = 0; i < (10 + (int) (Math.random() * 30)); i++) {//让坦克走一会
                        moveDown();//过一会再动
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3://left
                    for (int i = 0; i < (10 + (int) (Math.random() * 30)); i++) {//让坦克走一会
                        moveLeft();//过一会再动
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                default:
                    break;
            }

            //随机改变坦克方向（0-3）
            setDirection((int) (Math.random() * 4));
            //写并发程序时要考虑好线程什么时候结束
            if (!isLive) {
                break;//结束线程
            }
        }
    }
}
