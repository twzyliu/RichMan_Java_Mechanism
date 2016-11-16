import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by zyongliu on 15/11/16.
 */
public class Player implements WithCommandability {
    public static final int MAX_TOOL_SPACE = 10;
    private String name;
    private STATUS status = STATUS.WAIT_FOR_CMD;
    private int position = 0;
    private GameMap gameMap;
    private int money = 0;
    private int point = 0;
    private int godDays = 0;
    private int prisonDays = 0;
    private int hospitalDays = 0;
    private List<Place> lands = new ArrayList<>();
    private Barricade barricade = new Barricade();
    private Robot robot = new Robot();
    private Bomb bomb = new Bomb();
    private List<Tool> tools = new ArrayList<>(asList(barricade, robot, bomb));

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

    public void wrongCommand() {
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

    @Override
    public <T> void command(T command) {
        try {
            status = (STATUS) command.getClass().getMethod("execute", Object.class).invoke(command, this);
        } catch (Exception e) {
            status = null;
        }
    }

    public int getPrisonDays() {
        return prisonDays;
    }

    public void gotoPtison() {
        prisonDays += Prison.PRISONDAYS;
    }

    public int getToolsNum() {
        return barricade.getNum() + robot.getNum() + bomb.getNum();
    }

    public Barricade getBarricade() {
        return barricade;
    }

    public Robot getRobot() {
        return robot;
    }

    public Bomb getBomb() {
        return bomb;
    }

    public void choseExit() {
        command(getStatus().choseExit());
    }

    public List<Tool> getTools() {
        return tools;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void gotoHospital() {
        hospitalDays += Hospital.HOSPITAL_DAYS;
    }

    public int getHospitalDays() {
        return hospitalDays;
    }
}








