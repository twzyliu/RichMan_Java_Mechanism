/**
 * Created by zyongliu on 16/11/16.
 */
public class MineLand extends Place{
    private int point;

    public MineLand(int point) {
        this.point = point;
    }

    public int getPoint() {
        return point;
    }

    @Override
    public STATUS changeStatus(Player player) {
        player.setPoint(player.getPoint() + getPoint());
        return STATUS.TURN_END;
    }
}
