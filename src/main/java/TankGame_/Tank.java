package TankGame_;

/**
 * @author Obamazzz
 * @version 1.0
 */
public class Tank {
    private int x;//坦克横坐标
    private int y;//坦克纵坐标
    private int direction;//方向
    private int speed = 2;//速度，默认为1
    private boolean isLive = true;

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public void moveUp() {
        if (y > 0) {
            y -= speed;
        }
    }

    public void moveRight() {
        if (x < (1000 - 50)) {
            x += speed;
        }
    }

    public void moveDown() {
        if (y < (750 - 60)) {
            y += speed;
        }
    }

    public void moveLeft() {
        if (x > 10) {
            x -= speed;
        }
    }

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
