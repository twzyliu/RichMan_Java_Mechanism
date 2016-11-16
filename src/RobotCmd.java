/**
 * Created by zyongliu on 16/11/16.
 */
public class RobotCmd implements Command {

    @Override
    public <T> STATUS execute(T obj) {
        Player player = (Player) obj;
        Robot robot = player.getRobot();
        if (robot.getNum() > 0) {
            robot.use(player, 0);
        }
        return STATUS.WAIT_FOR_CMD;
    }
}
