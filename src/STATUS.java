/**
 * Created by zyongliu on 15/11/16.
 */
public enum STATUS {
    WAIT_FOR_CMD, TURN_END, WAIT_FOR_BUY_RESPONSE, WAIT_FOR_UPGRADE_RESPONSE;

    public <T> STATUS action(Player player, T command) {
        try {
            return (STATUS) command.getClass().getMethod("execute", Player.class).invoke(command, player);
        } catch (Exception e) {
            return null;
        }
    }
}
