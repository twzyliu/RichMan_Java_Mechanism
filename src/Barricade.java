/**
 * Created by zyongliu on 16/11/16.
 */
public class Barricade extends Tool {
    private static final int BARRICADE_POINR = 50;

    public Barricade() {
        super();
        point = BARRICADE_POINR;
    }

    public int getPoint() {
        return point;
    }
}
