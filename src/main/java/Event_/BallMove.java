package Event_;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Obamazzz
 * @version 1.0
 * 小球通过键盘控制移动->JAVA事件处理机制
 */
public class BallMove extends JFrame {
    // java事件处理是采取委派事件模型，当事件发生时，
    // 产生事件的对象会把此信息传递给监听者来处理，
    // 信息就是java.awt.event事件类库里某个类所创建的对象
    MyPanel mp = null;
    public static void main(String[] args) {
        BallMove ballMove = new BallMove();

    }

    public BallMove(){
        mp = new MyPanel();
        this.add(mp);
        this.setSize(400, 300);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
// KeyListener是监听器，可以监听键盘事件
class MyPanel extends Panel implements KeyListener {
    int x = 10;
    int y = 10;
    // 有字符输出，该方法被触发
    @Override
    public void keyTyped(KeyEvent e) {

    }
    // 当某个键被按下时该方法触发
    @Override
    public void keyPressed(KeyEvent e) {
//        System.out.println((char) e.getKeyCode() + "键被按下");
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {//向下键
            y++;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            y--;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            x++;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT){
            x--;
        } else {

        }
        this.repaint();//repaint调用来使程序调用paint->重绘->移动
    }
    //当某个键释放了该方法就会触发
    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval(x, y, 20, 20);
    }
}