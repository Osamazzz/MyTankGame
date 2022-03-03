package DrawTest_;

import javax.swing.*;
import java.awt.*;

/**
 * @author Obamazzz
 * @version 1.0
 */
public class Draw01 extends JFrame{// 对应一个窗口
    // 定义一个面板
    private MyPanel mp = null;
    public static void main(String[] args) {
        Draw01 draw01 = new Draw01();
    }
    public Draw01() {
        mp = new MyPanel();
        // 把面板放入到窗口中
        this.add(mp);
        // 设置窗口
        this.setSize(400, 300);
        // 当点击窗口的X程序就会退出
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

class MyPanel extends JPanel {
    // 画板对象
    // 可以把g理解为画笔

    // 当窗口最小化，再最大化或者窗口大小发生变化，repaint函数被调用时
    // 会调用paint函数
    @Override
    public void paint(Graphics g) {//绘图方法
        super.paint(g);//调用父类方法
        g.drawOval(10, 10, 100, 100);//圆形
        g.drawLine(20, 20, 20, 40);//直线,起点->终点
        g.drawRect(100, 100, 100, 100);//矩形
        g.setColor(Color.blue);//设置颜色
        g.fillRect(100,100,100,100);//填充矩形
//        g.fillOval();填充椭圆
//        g.drawImage();画图
        // 获取图片资源
        Image image = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/img.png"));
        g.drawImage(image, 300,300,120, 95,this);
        //设置字体
        g.setFont(new Font("隶书", Font.BOLD, 50));
        g.drawString("HelloWorld", 400, 400);//写字
    }
}
