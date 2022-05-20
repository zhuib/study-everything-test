package kingglory;

import java.awt.*;

/**
 * Date: 2022/1/5 20:57
 */
public class Background extends GameObject{

    Image bg = Toolkit.getDefaultToolkit().getImage("D:\\javaweb\\studyEverything\\heroking\\src\\main\\resources\\img\\map.png");

    public Background(GameFrame gameFrame) {
        super(gameFrame);
    }

    @Override
    public Rectangle getRec() {
        return null;
    }

    public void paintSelf(Graphics g) {
        g.drawImage(bg,0,0,null);
    }

}
