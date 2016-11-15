/**
 * Created by zyongliu on 15/11/16.
 */
public class GiftLand extends Place{
    public static final int GIFT_MONEY = 2000;
    public static final int GIFT_POINT = 200;
    public static final int GIFT_GOD_DAYS = 5;

    @Override
    public STATUS changeStatus(Player player) {
        return STATUS.WAIT_FOR_GIFT_RESPONSE;
    }
}
