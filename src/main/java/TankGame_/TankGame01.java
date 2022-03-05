package TankGame_;

import javax.swing.*;
import java.awt.*;

/**
 * @author Obamazzz
 * @version 1.0
 */
public class TankGame01 extends JFrame {//窗口
    //定义MyPanel
    MyPanel mp = null;
    public static void main(String[] args) {
        TankGame01 tankGame01 = new TankGame01();
    }

    public TankGame01() {
        mp = new MyPanel();
        this.add(mp);//游戏的绘图区
        this.setSize(1000, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);//可视化
    }
}
