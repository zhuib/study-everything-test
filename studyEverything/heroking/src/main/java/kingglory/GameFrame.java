package kingglory;



import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Date: 2022/1/5 20:46
 */
public class GameFrame extends JFrame {
    // 窗口大小
    private int windowWidth = 986;
    private int windowHeight = 740;
    // 双缓冲图片
    private Image offScreenImage = null;
    // 游戏背景
    private Background background = new Background(this);
    // 游戏界面初始化一个英雄
    Champion player = new Champion(this);
    // 双方小兵
    MinionBlue mb = new MinionBlue(this);
    MinionRed mr = new MinionRed(this);
    public void launch() {
        //设置尺寸
        setSize(windowWidth,windowHeight);
        //窗口居中
        setLocationRelativeTo(null);
        //关闭事件
        setDefaultCloseOperation(3);
        //用户不能调整窗口大小
        setResizable(false);
        //标题
        setTitle("王者荣耀");
        //窗口可见
        setVisible(true);
        // 添加键盘监视器
        this.addKeyListener(new GameFrame.KeyMonitor());
        while (true) {
            repaint();
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void paint(Graphics g) {
        // 获取图片大小的图片
        if (offScreenImage == null) {
            offScreenImage = this.createImage(1587,935);
        }
        Graphics gImage = offScreenImage.getGraphics(); // 获取图片画笔
        //background.paintSelf(g);
        //player.paintSelf(g); // 绘制英雄
        background.paintSelf(gImage);
        player.paintSelf(gImage); // 绘制英雄

        // g.drawImage(offScreenImage,0,0,null); // 重新绘制图片
        mb.paintSelf(gImage);
        mr.paintSelf(gImage);

        g.drawImage(offScreenImage,-player.getX() + 440,-player.getY() + 600,null); // 玩家每次移动时 在窗口中间
    }
    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame();
        gameFrame.launch();
    }

    class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            System.out.println(key);
            player.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }
    }
}
