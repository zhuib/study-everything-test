package signal;

/**
 * Date: 2021/7/30 21:09
 */
public class Vehicle {

    private String direct;
    private boolean line;
    private boolean flag;

    public Vehicle(String direct) {
        this.direct = direct;
    }

    public Vehicle(String direct, boolean line) {
        this.direct = direct;
        this.line = line;
    }

    public void simpleAndDirect(boolean flag,Lamp l, Arrow a, Vehicle v) {
        if (!flag) {
            arrowTurn(l,  a,  v);
        }else {
            turn(l, v);
        }
    }

    public void arrowTurn(Lamp l, Arrow a, Vehicle v) {
        if ("GREEN".equals(l.getColor()) && "FORWARD".equals(a.getDirect())) {
            if ("FORWARD".equals(v.getDirect()) || v.isLine()) {
                pass(l);
            } else {
                stop(l);
            }
        } else if ("RED".equals(l.getColor()) && "FORWARD".equals(a.getDirect())) {
            if ("FORWARD".equals(v.getDirect()) || v.isLine()) {
                stop(l);
            }
        } else if ("GREEN".equals(l.getColor()) && "RIGHT".equals(a.getDirect())) {
            if ("RIGHT".equals(v.getDirect()) || v.isLine()) {
                pass(l);
            } else {
                stop(l);
            }
        } else if ("RED".equals(l.getColor()) && "RIGHT".equals(a.getDirect())) {
            if ("RIGHT".equals(v.getDirect()) || v.isLine()) {
                stop(l);
            }
        } else if ("GREEN".equals(l.getColor()) && "LEFT".equals(a.getDirect())) {
            if ("LEFT".equals(v.getDirect()) || v.isLine()) {
                pass(l);
            } else {
                stop(l);
            }
        } else if ("RED".equals(l.getColor()) && "LEFT".equals(a.getDirect())) {
            if ("LEFT".equals(v.getDirect()) || v.isLine()) {
                stop(l);
            }
        } else {
            stop(l);
        }
    }

    public void turn(Lamp l, Vehicle v) {
        if ("RED".equals(l.getColor()) && "RIGHT".equals(v.getDirect())) {
            pass(l);
        } else if ("GREEN".equals(l.getColor())) {
            pass(l);
        } else if ("YELLOW".equals(l.getColor()) && v.isLine()) {
            pass(l);
        } else {
            stop(l);
        }
    }

    public void pass(Lamp l) {
        System.out.println(l.getColor() + " " + getDirect() + " 是否过线 " + isLine() + " 方向继续运行");
    }

    public void stop(Lamp l) {
        System.out.println(l.getColor() + " 是否过线 " + isLine() + " 停止运行");
    }

    public String getDirect() {
        return direct;
    }

    public void setDirect(String direct) {
        this.direct = direct;
    }

    public boolean isLine() {
        return line;
    }

    public void setLine(boolean line) {
        this.line = line;
    }
}
