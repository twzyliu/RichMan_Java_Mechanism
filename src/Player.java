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

    public Player(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public STATUS getStatus() {
        return status;
    }

    public void execute(Command command) {
        status = command.execute(this);
    }

    public void respond(Response response) {
        status = response.execute(this);
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
}
