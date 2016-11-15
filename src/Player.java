import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyongliu on 15/11/16.
 */
public class Player {
    private STATUS status;
    private int position = 0;
    private GameMap gameMap;
    private int money;
    private List<Place> lands = new ArrayList<>();
    private String name;

    public Player(String name,GameMap gameMap) {
        this.name = name;
        this.gameMap = gameMap;
    }

    public STATUS getStatus() {
        return status;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public int getPosition() {
        return position;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getLandNum() {
        return getLands().size();
    }

    public List<Place> getLands() {
        return lands;
    }

    public <T> void command(T command) {
        try {
            status = (STATUS) command.getClass().getMethod("execute", Player.class).invoke(command, this);
        } catch (Exception e) {
            status = null;
        }
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public void sayYes() {
        command(getStatus().sayYes());
    }

    public void sayNo() {
        command(getStatus().sayNo());
    }

    public void sayWrongCommand() {
        command(getStatus().sayWrongCommand());
    }
}
