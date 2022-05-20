package kingglory;

import java.awt.*;
import java.awt.image.ImageProducer;

/**
 * Date: 2022/1/5 21:13
 * ��Ϸ����
 */
public abstract class GameObject {

    // ��С
    private int x;
    private int y;
    //ͼƬ
    Image image;
    // ��Ϸ����
    GameFrame gameFrame;
    // ����ƶ��ٶ�
    private int spd;

    public GameObject(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    public GameObject(int x, int y, GameFrame gameFrame) {
        this.x = x;
        this.y = y;
        this.gameFrame = gameFrame;
    }

    public abstract Rectangle getRec();
    public abstract void paintSelf(Graphics g);

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

    public int getSpd() {
        return spd;
    }

    public void setSpd(int spd) {
        this.spd = spd;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = Toolkit.getDefaultToolkit().getImage(image);
    }

    public GameFrame getGameFrame() {
        return gameFrame;
    }

    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }
}
