/**
 * Created by zyongliu on 15/11/16.
 */
public class EmptyLand extends Place{
    private int price;
    private Player owner = null;

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
        return player == getOwner() ? STATUS.WAIT_FOR_UPGRADE_RESPONSE : STATUS.WAIT_FOR_BUY_RESPONSE;
    }
}
