/**
 * Created by zyongliu on 16/11/16.
 */
public class Barricade extends Tool {
    private static final int BARRICADE_POINR = 50;

    public Barricade() {
        super();
        point = BARRICADE_POINR;
    }

    public int getPoint() {
        return point;
    }

    @Override
    public void use(Player player, int step) {
        GameMap gameMap = player.getGameMap();
        Place place = gameMap.getPlace(player.getPosition() + step);
        if (place.getPlayer() == null & place.getTool()==null) {
            place.setTool(this);
            num -= 1;
        }
    }
}
