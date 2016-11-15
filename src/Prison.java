/**
 * Created by zyongliu on 16/11/16.
 */
public class Prison extends Place {
    public static final int PRISONDAYS = 2;

    @Override
    public STATUS changeStatus(Player player) {
        player.gotoPtison();
        return STATUS.TURN_END;
    }
}
