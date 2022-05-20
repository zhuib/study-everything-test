package kingglory;



import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Date: 2022/1/5 20:46
 */
public class GameFrame extends JFrame {
    // ���ڴ�С
    private int windowWidth = 986;
    private int windowHeight = 740;
    // ˫����ͼƬ
    private Image offScreenImage = null;
    // ��Ϸ����
    private Background background = new Background(this);
    // ��Ϸ�����ʼ��һ��Ӣ��
    Champion player = new Champion(this);
    // ˫��С��
    MinionBlue mb = new MinionBlue(this);
    MinionRed mr = new MinionRed(this);
    public void launch() {
        //���óߴ�
        setSize(windowWidth,windowHeight);
        //���ھ���
        setLocationRelativeTo(null);
        //�ر��¼�
        setDefaultCloseOperation(3);
        //�û����ܵ������ڴ�С
        setResizable(false);
        //����
        setTitle("������ҫ");
        //���ڿɼ�
        setVisible(true);
        // ��Ӽ��̼�����
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
        // ��ȡͼƬ��С��ͼƬ
        if (offScreenImage == null) {
            offScreenImage = this.createImage(1587,935);
        }
        Graphics gImage = offScreenImage.getGraphics(); // ��ȡͼƬ����
        //background.paintSelf(g);
        //player.paintSelf(g); // ����Ӣ��
        background.paintSelf(gImage);
        player.paintSelf(gImage); // ����Ӣ��

        // g.drawImage(offScreenImage,0,0,null); // ���»���ͼƬ
        mb.paintSelf(gImage);
        mr.paintSelf(gImage);

        g.drawImage(offScreenImage,-player.getX() + 440,-player.getY() + 600,null); // ���ÿ���ƶ�ʱ �ڴ����м�
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
