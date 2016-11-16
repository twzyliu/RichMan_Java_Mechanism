/**
 * Created by zyongliu on 16/11/16.
 */
public class SellCmd implements Command {
    private int step = 0;

    @Override
    public STATUS execute(Player player) {
        GameMap gameMap = player.getGameMap();
        int position = player.getPosition();
        EmptyLand emptyLand = (EmptyLand) gameMap.getPlace(position + this.step);
        if (emptyLand.getOwner() == player) {
            player.setMoney(emptyLand.selled() + player.getMoney());
            player.getLands().remove(emptyLand);
        }
        return STATUS.WAIT_FOR_CMD;
    }

    public void setStep(int step) {
        this.step = step;
    }
}