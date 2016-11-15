/**
 * Created by zyongliu on 15/11/16.
 */
public interface Response {
    public STATUS execute(Player player);

    Response sayYesToBuy = player ->
    {
        GameMap gameMap = player.getGameMap();
        int positon = player.getPosition();
        EmptyLand emptyLand = (EmptyLand) gameMap.getPlace(positon);
        if (player.getMoney() >= emptyLand.getPrice()) {
            player.setMoney(player.getMoney() - emptyLand.getPrice());
            player.getLands().add(emptyLand);
            emptyLand.setOwner(player);
        }
        return STATUS.TURN_END;
    };
    Response sayNoToBuy = player -> STATUS.TURN_END;
    Response wrongCommandToBuy = player -> STATUS.WAIT_FOR_BUY_RESPONSE;

    Response sayYesToUpgrade = player -> {
        GameMap gameMap = player.getGameMap();
        int positon = player.getPosition();
        EmptyLand ownerLand = (EmptyLand) gameMap.getPlace(positon);
        if (player.getMoney() >= ownerLand.getPrice()) {
            player.setMoney(player.getMoney() - ownerLand.getPrice());
            ownerLand.levelUp();
        }
        return STATUS.TURN_END;
    };
    Response sayNoToUpgrade = player -> STATUS.TURN_END;
    Response wrongCommandToUpgrade = player -> STATUS.WAIT_FOR_UPGRADE_RESPONSE;
}
