/**
 * Created by zyongliu on 16/11/16.
 */
public class Tool {
    public static final int CHEAPEST_POINT = 30;
    protected int point = 0;
    protected int num = 0;

    public int getPoint() {
        return point;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void use(Player player, int step) {
        GameMap gameMap = player.getGameMap();
        Place place = gameMap.getPlace(player.getPosition() + step);
        if (place.getPlayer() == null & place.getTool() == null) {
            place.setTool(this);
            num -= 1;
        }
    }

    public void selled(Player player) {
        if (getNum() > 0) {
            player.setPoint(player.getPoint() + getPoint());
            num -= 1;
        }
    }
}
