package kingglory;

import java.awt.*;

/**
 * Date: 2022/1/5 22:32
 */
public class Minion extends GameObject {

    public Minion(GameFrame gameFrame) {
        super(gameFrame);
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(getX()-16,getY()-16,45,45);
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(getImage(),getX() -16,getY() -16,null);
        g.setColor(Color.RED);
        g.fillOval(getX() ,getY() ,10,10);
        g.drawRect(getX() -16,getY()-16,45,45);
    }
}
