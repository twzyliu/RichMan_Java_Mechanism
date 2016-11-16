import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by zyongliu on 16/11/16.
 */
public class Game implements WithCommandability{
    private STATUS status = STATUS.GAME_START;
    private GameMap gameMap;
    private List<Player> players = new ArrayList<>();

    public <T> void command(T command) {
        try {
            status = (STATUS) command.getClass().getMethod("execute", Object.class).invoke(command, this);
        } catch (Exception e) {
            status = null;
        }
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void initGameMap() {
        List<Place> places = new ArrayList<>();
        List<Integer> mineLandPointS = new ArrayList<>(
                asList(20, 80, 100, 40, 80, 60)
        );
        places.add(new StartingPoint());

        for (int index = 0; index < 13 * 2 + 1; index++) {
            if (index == 13) {
                places.add(new Hospital());
            } else {
                places.add(new EmptyLand(200));
            }
        }
        places.add(new ToolLand());

        for (int index = 0; index < 6; index++) {
            places.add(new EmptyLand(500));
        }

        places.add(new GiftLand());

        for (int index = 0; index < 13 * 2 + 1; index++) {
            if (index == 13) {
                places.add(new Prison());
            } else {
                places.add(new EmptyLand(300));
            }
        }
        places.add(new MagicLand());

        for (int index = 0; index < 6; index++) {
            places.add(new MineLand(mineLandPointS.get(index)));
        }
        gameMap = new GameMap(places);
    }
}
