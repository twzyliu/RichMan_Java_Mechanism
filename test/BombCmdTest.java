import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by zyongliu on 16/11/16.
 */
public class BombCmdTest {
    private Player player;
    private Player other;
    private BombCmd bombCmd;
    private GameMap gameMap;
    private EmptyLand emptyLand;

    @Before
    public void setUp() throws Exception {
        gameMap = mock(GameMap.class);
        player = new Player(TestHelper.PLAYER_A, gameMap);
        other = new Player(TestHelper.PLAYER_B, gameMap);
        bombCmd = new BombCmd();
        emptyLand = new EmptyLand(TestHelper.LAND_PRICE);
        when(gameMap.getPlace(anyInt())).thenReturn(emptyLand);
    }

    @Test
    public void should_wait_for_cmd_after_bomb() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_CMD);

        bombCmd.setStep(TestHelper.TOOL_STEP);
        player.command(bombCmd);

        assertThat(player.getStatus(), is(STATUS.WAIT_FOR_CMD));
    }

    @Test
    public void should_change_toolnum_when_bomb() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_TOOL_RESPONSE);
        player.setPoint(TestHelper.ENOUGH_POINT);
        player.choseThree();
        int toolnum = player.getBomb().getNum();

        bombCmd.setStep(TestHelper.TOOL_STEP);
        player.command(bombCmd);

        assertThat(player.getBomb().getNum(), is(toolnum - 1));
    }

    @Test
    public void should_change_land_status_when_bomb() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_TOOL_RESPONSE);
        player.setPoint(TestHelper.ENOUGH_POINT);
        player.choseThree();

        bombCmd.setStep(TestHelper.TOOL_STEP);
        player.command(bombCmd);

        assertThat(emptyLand.getTool() instanceof Bomb, is(true));
    }

    @Test
    public void should_not_change_when_no_tool_to_use() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_CMD);

        bombCmd.setStep(TestHelper.TOOL_STEP);
        player.command(bombCmd);

        assertNull(emptyLand.getTool());
    }

    @Test
    public void should_not_change_when_use_tool_but_player_on_target_land() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_TOOL_RESPONSE);
        emptyLand.setPlayer(other);
        player.setPoint(TestHelper.ENOUGH_POINT);
        player.choseThree();

        bombCmd.setStep(TestHelper.TOOL_STEP);
        player.command(bombCmd);

        assertNull(emptyLand.getTool());
    }

    @Test
    public void should_not_change_when_use_tool_but_some_tool_on_target_land() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_TOOL_RESPONSE);
        Barricade barricade = new Barricade();
        emptyLand.setTool(barricade);
        player.setPoint(TestHelper.ENOUGH_POINT);
        player.choseThree();

        bombCmd.setStep(TestHelper.TOOL_STEP);
        player.command(bombCmd);

        assertThat(emptyLand.getTool(), is(barricade));
    }

    @Test
    public void should_not_change_when_target_land_too_faraway() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_TOOL_RESPONSE);
        player.setPoint(TestHelper.ENOUGH_POINT);
        player.choseThree();
        int toolNum = player.getBomb().getNum();

        bombCmd.setStep(TestHelper.TOOL_LARGE_STEP);
        player.command(bombCmd);

        assertThat(player.getBomb().getNum(), is(toolNum));
    }
}
