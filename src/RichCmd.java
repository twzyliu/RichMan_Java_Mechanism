import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyongliu on 16/11/16.
 */
public class RichCmd implements Command{
    private int initMoney = 0;
    private List<String> playersName = new ArrayList<>();
    private Game game;

    @Override
    public <T> STATUS execute(T Obj) {
        if (initMoney == 0) {
            return STATUS.WAIT_FOR_INIT_MONEY;
        }
        if (playersName.size() == 0) {
            return STATUS.WAIT_FOR_INIT_PLAYERS;
        }
        init();
        return STATUS.WAIT_FOR_CMD;
    }

    private void init() {
        List<Player> players = game.getPlayers();
        game.initGameMap();
        GameMap gameMap = game.getGameMap();

        for (String name : playersName) {
            Player player = new Player(name, gameMap);
            player.setMoney(initMoney);
            players.add(player);
        }
    }

    public void setInitMoney(int initMoney) {
        this.initMoney = initMoney;
    }

    public void setInitPlayer(List<String> playersName) {
        this.playersName = playersName;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
