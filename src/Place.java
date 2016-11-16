/**
 * Created by zyongliu on 15/11/16.
 */
public class Place {
    private Tool tool = null;
    protected Player player = null;
    protected STATUS changeStatus(Player player) {
        return STATUS.TURN_END;
    }

    Tool getTool() {
        return tool;
    }

    void setTool(Tool tool) {
        this.tool = tool;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
