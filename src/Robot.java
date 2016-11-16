/**
 * Created by zyongliu on 16/11/16.
 */
public class Robot extends Tool {
    private static final int ROBOT_POINR = 30;
    private static final int ROBOT_STEP = 10;

    public Robot() {
        super();
        point = ROBOT_POINR;
    }

    public int getPoint() {
        return point;
    }

    @Override
    public void use(Player player, int step) {
        GameMap gameMap = player.getGameMap();
        int position = player.getPosition();
        for (int i = 0; i < ROBOT_STEP; i++) {
            Place place = gameMap.getPlace(position + i + 1);
            place.setTool(null);
        }
        num -= 1;
    }
}
