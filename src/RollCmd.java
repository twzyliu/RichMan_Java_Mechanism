/**
 * Created by zyongliu on 15/11/16.
 */
public class RollCmd implements Command {

    @Override
    public STATUS execute(Player player) {
        GameMap gameMap = player.getGameMap();
        int positon = gameMap.move(player.getPosition());
        Place place = gameMap.getPlace(positon);
        if (place instanceof EmptyLand) {
            return STATUS.WAIT_FOR_RESPONSE;
        }
        return null;
    }

    public static Response sayYes = player ->
    {
        GameMap gameMap = player.getGameMap();
        int positon = gameMap.move(player.getPosition());
        EmptyLand emptyLand = (EmptyLand) gameMap.getPlace(positon);
        if (player.getMoney() >= emptyLand.getPrice()) {
            player.setMoney(player.getMoney() - emptyLand.getPrice());
            player.getLands().add(emptyLand);
            emptyLand.setOwner(player);
        }
        return STATUS.TURN_END;
    };

    public static Response sayNo = player -> STATUS.TURN_END;
}
