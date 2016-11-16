/**
 * Created by zyongliu on 15/11/16.
 */
public class RollCmd implements Command {
    @Override
    public <T> STATUS execute(T obj) {
        Player player = (Player) obj;
        GameMap gameMap = player.getGameMap();
        int target = gameMap.move(player);
        Place place = gameMap.getPlace(target);
        return place.changeStatus(player);
    }
}
