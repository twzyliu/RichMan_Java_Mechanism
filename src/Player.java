import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyongliu on 15/11/16.
 */
public class Player {
    private String name;
    private STATUS status;
    private int position = 0;
    private GameMap gameMap;
    private int money = 0;
    private int point = 0;
    private int godDays = 0;
    private List<Place> lands = new ArrayList<>();

    public Player(String name, GameMap gameMap) {
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

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getGodDays() {
        return godDays;
    }

    public void gainGod() {
        godDays += GiftLand.GIFT_GOD_DAYS;
    }

    public int getLandNum() {
        return getLands().size();
    }

    public List<Place> getLands() {
        return lands;
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

    public void choseOne() {
        command(getStatus().choseOne());
    }

    public void choseTwo() {
        command(getStatus().choseTwo());
    }

    public void choseThree() {
        command(getStatus().choseThree());
    }

    public <T> void command(T command) {
        try {
            status = (STATUS) command.getClass().getMethod("execute", Player.class).invoke(command, this);
        } catch (Exception e) {
            status = null;
        }
    }
}








