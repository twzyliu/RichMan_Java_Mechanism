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


    Response wrongCommandToGift = player -> STATUS.TURN_END;
    Response giftChoseOne = player -> {
        player.setMoney(player.getMoney() + GiftLand.GIFT_MONEY);
        return STATUS.TURN_END;
    };
    Response giftChoseTwo = player -> {
        player.setPoint(player.getPoint() + GiftLand.GIFT_POINT);
        return STATUS.TURN_END;
    };
    Response giftChoseThree = player -> {
        player.gainGod();
        return STATUS.TURN_END;
    };


    static STATUS getStatus(Player player, Tool Tool) {
        if (player.getPoint() >= Tool.getPoint() & player.getToolsNum() < Player.MAX_TOOL_SPACE) {
            Tool.setNum(Tool.getNum() + 1);
            player.setPoint(player.getPoint() - Tool.getPoint());
        }
        return STATUS.WAIT_FOR_TOOL_RESPONSE;
    }
    Response toolChoseOne = player -> {
        Tool barricade = player.getBarricade();
        return getStatus(player, barricade);
    };

    Response toolChoseTwo = player -> {
        Tool robot = player.getRobot();
        return getStatus(player, robot);
    };
    Response toolChoseThree = player -> {
        Tool bomb = player.getBomb();
        return getStatus(player, bomb);
    };
    Response toolChoseExit = player -> STATUS.TURN_END;
    Response toolWrongCommand = player -> STATUS.WAIT_FOR_TOOL_RESPONSE;

}
