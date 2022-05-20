package kingglory;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Date: 2022/1/5 21:22
 * Ӣ����
 */
public class Champion extends GameObject{

    // �ƶ�
    public boolean up,down,left,right;
    // �ƶ�ͼ��
    static String[] imgs = new String[8];
    // �ڼ���ͼƬ
    int moveCount = 1;
    static {
        for (int i = 1; i < 8; i++) {
            imgs[i] = "D:\\javaweb\\studyEverything\\heroking\\src\\main\\resources\\img\\" + i + ".png";
        }
    }
    public Champion(GameFrame gameFrame) {
        super(gameFrame);
        // ����Ӣ��ͼƬ ������
        setImage("D:\\javaweb\\studyEverything\\heroking\\src\\main\\resources\\img\\stand.png");
//        setX(80);
//        setY(80);
        // λ��ͼƬ�����½�
        setX(499);
        setY(820);
        setSpd(25);
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(getX(),getY(),0,0);
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_D) {
            right = true;
        }
        if (key == KeyEvent.VK_A) {
            left = true;
        }
        if (key == KeyEvent.VK_W) {
            up = true;
        }
        if (key == KeyEvent.VK_S) {
            down = true;
        }

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_D) {
            right = false;
        }
        if (key == KeyEvent.VK_A) {
            left = false;
        }
        if (key == KeyEvent.VK_W) {
            up = false;
        }
        if (key == KeyEvent.VK_S) {
            down = false;
        }

    }
    public void  move() {
        if (up) {
            setY(getY() - getSpd());
        }
        if (down) {
            setY(getY() + getSpd());
        } if (left) {
            setX(getX() - getSpd());
        }
        if (right) {
            setX(getX() + getSpd());
        }
        // ����ƶ�����ͼƬ
        if (up||down||left|| right) {
            setImage(imgs[moveCount]);
            moveCount ++; // ÿ���ƶ�ʱ�л���һ��ͼƬ
            if (moveCount == 8) { // ���һʱ���ص�һ��
                moveCount = 1;
            }
        }else { // ֹͣ�ƶ� ��ԭ
            setImage("D:\\javaweb\\studyEverything\\heroking\\src\\main\\resources\\img\\stand.png");
        }
    }

    @Override
    public void paintSelf(Graphics g) {
        // ����ͼƬ
        g.drawImage(getImage(),getX()-32,getY()-50,null);
        // �ı仭����ɫ
        g.setColor(Color.GREEN);
        // ��������Բ��
        g.fillOval(getX(),getY(),10,10);
        // ���ƾ��α߿�
        g.drawRect(getX()-23,getY()-50,60,120);
        move();
    }
}
