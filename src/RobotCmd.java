/**
 * Created by zyongliu on 16/11/16.
 */
public class RobotCmd implements Command {

    @Override
    public STATUS execute(Player player) {
        Robot robot = player.getRobot();
        if (robot.getNum() > 0) {
            robot.use(player, 0);
        }
        return STATUS.WAIT_FOR_CMD;
    }
}
