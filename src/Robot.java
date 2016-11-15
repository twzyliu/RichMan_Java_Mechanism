/**
 * Created by zyongliu on 16/11/16.
 */
public class Robot extends Tool{
    private static final int ROBOT_POINR = 30;

    public Robot() {
        super();
        point = ROBOT_POINR;
    }

    public int getPoint() {
        return point;
    }
}
