package TankGame_;

/**
 * @author Obamazzz
 * @version 1.0
 * 射击子弹
 */
public class Shot implements Runnable {
    private int x;//子弹x坐标
    private int y;//子弹y坐标
    private int direct = 0;//子弹方向
    private int speed = 4;//子弹速度
    private boolean isLive = true;
    boolean flag = true;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public boolean isLive() {
        return isLive;
    }


    public void setLive(boolean live) {
        isLive = live;
    }

    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() {//射击
        while (flag) {
            try {
                Thread.sleep(50);//休眠50ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (direct) {
                case 0://up
                    y -= speed;
                    break;
                case 1://right
                    x += speed;
                    break;
                case 2://down
                    y += speed;
                    break;
                case 3://left
                    x -= speed;
                    break;
            }
            //子弹进入边界时需要进行销毁
            //子弹碰到敌人坦克时(子弹死亡)也应该结束线程
            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750 && isLive)) {
                System.out.println("子弹线程结束");
                isLive = false;
                break;
            }
        }
    }
}
