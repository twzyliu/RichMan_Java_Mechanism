/**
 * Created by zyongliu on 15/11/16.
 */
public class RollCmd implements Command {
    @Override
    public <T> STATUS execute(T obj) {
        Player player = (Player) obj;
        GameMap gameMap = player.getGameMap();
        int positon = gameMap.move(player.getPosition());
        Place place = gameMap.getPlace(positon);
        return place.changeStatus(player);
    }
}
