/**
 * Created by zyongliu on 15/11/16.
 */
public interface Response {

    public STATUS execute(Object obj);

    Response sayYesToBuy = obj ->
    {
        Player player = (Player) obj;
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
    Response sayNoToBuy = obj -> STATUS.TURN_END;
    Response wrongCommandToBuy = obj -> STATUS.WAIT_FOR_BUY_RESPONSE;


    Response sayYesToUpgrade = obj -> {
        Player player = (Player) obj;
        GameMap gameMap = player.getGameMap();
        int positon = player.getPosition();
        EmptyLand ownerLand = (EmptyLand) gameMap.getPlace(positon);
        if (player.getMoney() >= ownerLand.getPrice() & ownerLand.getLevel() < EmptyLand.MAX_LEVEL) {
            player.setMoney(player.getMoney() - ownerLand.getPrice());
            ownerLand.levelUp();
        }
        return STATUS.TURN_END;
    };
    Response sayNoToUpgrade = obj -> STATUS.TURN_END;
    Response wrongCommandToUpgrade = obj -> STATUS.WAIT_FOR_UPGRADE_RESPONSE;


    Response wrongCommandToGift = obj -> STATUS.TURN_END;
    Response giftChoseOne = obj -> {
        Player player = (Player) obj;
        player.setMoney(player.getMoney() + GiftLand.GIFT_MONEY);
        return STATUS.TURN_END;
    };
    Response giftChoseTwo = obj -> {
        Player player = (Player) obj;
        player.setPoint(player.getPoint() + GiftLand.GIFT_POINT);
        return STATUS.TURN_END;
    };
    Response giftChoseThree = obj -> {
        Player player = (Player) obj;
        player.gainGod();
        return STATUS.TURN_END;
    };


    static STATUS buyTool(Player player, Tool Tool) {
        if (player.getPoint() >= Tool.getPoint() & player.getToolsNum() < Player.MAX_TOOL_SPACE) {
            Tool.setNum(Tool.getNum() + 1);
            player.setPoint(player.getPoint() - Tool.getPoint());
        }
        return STATUS.WAIT_FOR_TOOL_RESPONSE;
    }

    Response toolChoseOne = obj -> {
        Player player = (Player) obj;
        Tool barricade = player.getBarricade();
        return buyTool(player, barricade);
    };

    Response toolChoseTwo = obj -> {
        Player player = (Player) obj;
        Tool robot = player.getRobot();
        return buyTool(player, robot);
    };
    Response toolChoseThree = obj -> {
        Player player = (Player) obj;
        Tool bomb = player.getBomb();
        return buyTool(player, bomb);
    };
    Response toolChoseExit = obj -> STATUS.TURN_END;
    Response toolWrongCommand = obj -> STATUS.WAIT_FOR_TOOL_RESPONSE;
}
