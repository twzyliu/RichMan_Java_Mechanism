import static java.lang.Math.pow;

/**
 * Created by zyongliu on 15/11/16.
 */
public class EmptyLand extends Place {
    public static final int MAX_LEVEL = 3;
    private int price;
    private Player owner = null;
    private int level = 0;

    public EmptyLand(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    @Override
    public STATUS changeStatus(Player player) {
        if (getOwner() == null) {
            return STATUS.WAIT_FOR_BUY_RESPONSE;
        } else if (getOwner() == player) {
            return STATUS.WAIT_FOR_UPGRADE_RESPONSE;
        } else {
            player.setMoney(player.getMoney() - getBill());
            getOwner().setMoney(getOwner().getMoney() + getBill());
            return player.getMoney() >= getBill() ? STATUS.TURN_END : STATUS.GAME_OVER;
        }
    }

    public int getLevel() {
        return level;
    }

    public void levelUp() {
        level += 1;
    }

    public int getBill() {
        return (int) ((getPrice() / 2) * pow(2, getLevel()));
    }

    public int selled() {
        owner = null;
        level = 0;
        return (getPrice() * (getLevel() + 1)) * 2;
    }
}
