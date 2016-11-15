/**
 * Created by zyongliu on 16/11/16.
 */
public class ToolLand extends Place{
    @Override
    public STATUS changeStatus(Player player) {
        if (player.getPoint() < Tool.CHEAPEST_POINT) {
            return STATUS.TURN_END;
        }
        return STATUS.WAIT_FOR_TOOL_RESPONSE;
    }
}
