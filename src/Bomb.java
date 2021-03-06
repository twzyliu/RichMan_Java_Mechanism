/**
 * Created by zyongliu on 16/11/16.
 */
public class Bomb extends Tool{
    private static final int BOMB_POINR = 50;

    public Bomb() {
        super();
        point = BOMB_POINR;
    }

    public int getPoint() {
        return point;
    }

    @Override
    public int work(Player player, int position, int hospitalPosition) {
        player.gotoHospital();
        player.setPosition(hospitalPosition);
        return hospitalPosition;
    }
}
