import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by zyongliu on 16/11/16.
 */
public class GameTest {
    private Game game;
    private GameMap gameMap;
    private Player player;

    @Before
    public void setUp() throws Exception {
        game = new Game();
        game.initGameMap();
        gameMap = game.getGameMap();
        player = new Player(TestHelper.PLAYER_A, gameMap);
    }

    @Test
    public void should_return_land_when_get_by_position() throws Exception {
        Place startingpoint = gameMap.getPlace(0);

        assertThat(startingpoint instanceof StartingPoint, is(true));
    }

    @Test
    public void should_return_target_when_player_move_pass_by_startingpint() throws Exception {
        int size = gameMap.getSize();
        for (int i = 0; i < size; i++) {
            gameMap.move(player);
        }
        assertThat(player.getPosition() < size, is(true));
    }

    @Test
    public void should_stop_when_encounter_barricade() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_TOOL_RESPONSE);
        player.setPoint(TestHelper.ENOUGH_POINT);
        player.choseOne();
        int position = player.getPosition();
        BlockCmd command = new BlockCmd();
        command.setStep(TestHelper.TOOL_STEP);
        player.command(command);

        gameMap.move(player);

        assertThat(player.getPosition(), is(position + TestHelper.TOOL_STEP));
    }

    @Test
    public void should_goto_hospital_when_encounter_bomb() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_TOOL_RESPONSE);
        player.setPoint(TestHelper.ENOUGH_POINT);
        player.choseThree();
        BombCmd command = new BombCmd();
        command.setStep(TestHelper.TOOL_STEP);
        player.command(command);

        gameMap.move(player);

        assertThat(player.getHospitalDays(), is(Hospital.HOSPITAL_DAYS));
        assertThat(player.getPosition(), is(gameMap.getHospitalPosition()));
    }
}
