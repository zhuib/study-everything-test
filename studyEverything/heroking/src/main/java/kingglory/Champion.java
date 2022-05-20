package kingglory;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Date: 2022/1/5 21:22
 * 英雄类
 */
public class Champion extends GameObject{

    // 移动
    public boolean up,down,left,right;
    // 移动图集
    static String[] imgs = new String[8];
    // 第几张图片
    int moveCount = 1;
    static {
        for (int i = 1; i < 8; i++) {
            imgs[i] = "D:\\javaweb\\studyEverything\\heroking\\src\\main\\resources\\img\\" + i + ".png";
        }
    }
    public Champion(GameFrame gameFrame) {
        super(gameFrame);
        // 定义英雄图片 和坐标
        setImage("D:\\javaweb\\studyEverything\\heroking\\src\\main\\resources\\img\\stand.png");
//        setX(80);
//        setY(80);
        // 位于图片的左下角
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
        // 玩家移动设置图片
        if (up||down||left|| right) {
            setImage(imgs[moveCount]);
            moveCount ++; // 每次移动时切换下一张图片
            if (moveCount == 8) { // 最后一时换回第一张
                moveCount = 1;
            }
        }else { // 停止移动 复原
            setImage("D:\\javaweb\\studyEverything\\heroking\\src\\main\\resources\\img\\stand.png");
        }
    }

    @Override
    public void paintSelf(Graphics g) {
        // 绘制图片
        g.drawImage(getImage(),getX()-32,getY()-50,null);
        // 改变画笔颜色
        g.setColor(Color.GREEN);
        // 绘制中心圆点
        g.fillOval(getX(),getY(),10,10);
        // 绘制矩形边框
        g.drawRect(getX()-23,getY()-50,60,120);
        move();
    }
}
