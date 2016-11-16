/**
 * Created by zyongliu on 16/11/16.
 */
public class SellToolCmd implements Command {
    private int toolNum = 0;

    @Override
    public <T> STATUS execute(T obj) {
        Player player = (Player) obj;
        player.getTools().get(toolNum - 1).selled(player);
        return STATUS.WAIT_FOR_CMD;
    }

    public void setToolNum(int toolNum) {
        this.toolNum = toolNum;
    }
}
