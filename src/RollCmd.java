/**
 * Created by zyongliu on 15/11/16.
 */
public class RollCmd implements Command {

    @Override
    public STATUS execute(Player player) {
        GameMap gameMap = player.getGameMap();
        int positon = gameMap.move(player.getPosition());
        Place place = gameMap.getPlace(positon);
        return place.changeStatus(player);
    }

    public static Response sayYesToBuy = player ->
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

    public static Response sayNoToBuy = player -> STATUS.TURN_END;

    public static Response wrongCommandToBuy = player -> STATUS.WAIT_FOR_BUY_RESPONSE;
}
