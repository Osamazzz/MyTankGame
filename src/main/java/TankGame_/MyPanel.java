package TankGame_;

import javax.swing.*;
import java.awt.*;

/**
 * @author Obamazzz
 * @version 1.0
 * 绘图区域
 */
public class MyPanel extends JPanel{
    //定义坦克
    Hero hero1 = null;
    Hero hero2 = null;
    public MyPanel() {
        hero1 = new Hero(100, 100);//初始化自己坦克
        hero2 = new Hero(200, 100);

    }

    @Override
    public void paint(Graphics g) {//画板
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);//填充矩形，默认黑色
        //画出坦克
        drawTank(hero1.getX(), hero1.getY(), g, 0, 0);//向上，自己的坦克
        drawTank(hero2.getX(), hero2.getY(), g, 0, 1);
    }

    /**
     * @param x 坦克左上角x坐标
     * @param y 坦克左上角y坐标
     * @param g 画笔
     * @param direction 坦克的朝向
     * @param type 坦克的类型
     */
    public void drawTank(int x, int y, Graphics g, int direction, int type) {
        switch (type) {//根据类型设置坦克颜色
            case 0:// 自己的坦克
                g.setColor(Color.BLUE);
                break;
            case 1:// 敌人的坦克
                g.setColor(Color.RED);
                break;
        }
        // 根据坦克方向来绘制坦克
        switch (direction) {
            case 0: // 向上
                g.fill3DRect(x, y, 10, 60, false);//左轮
                g.fill3DRect(x + 30, y,10, 60, false);//右轮
                g.fill3DRect(x + 10, y + 10, 20 , 40, false);//坦克中间
                g.fillOval(x + 10, y + 20, 20, 20);//画出圆盖
                g.drawLine(x + 20, y, x + 20, y + 30);
                break;
            default:
                break;
        }
    }
}
