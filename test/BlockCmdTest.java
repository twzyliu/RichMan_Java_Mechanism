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
public class BlockCmdTest {
    private Player player;
    private Player other;
    private BlockCmd blockCmd;
    private GameMap gameMap;
    private EmptyLand emptyLand;

    @Before
    public void setUp() throws Exception {
        gameMap = mock(GameMap.class);
        player = new Player(TestHelper.PLAYER_A, gameMap);
        other = new Player(TestHelper.PLAYER_B, gameMap);
        blockCmd = new BlockCmd();
        emptyLand = new EmptyLand(TestHelper.LAND_PRICE);
        when(gameMap.getPlace(anyInt())).thenReturn(emptyLand);
    }

    @Test
    public void should_wait_for_cmd_after_block() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_CMD);

        blockCmd.setStep(TestHelper.TOOL_STEP);
        player.command(blockCmd);

        assertThat(player.getStatus(), is(STATUS.WAIT_FOR_CMD));
    }

    @Test
    public void should_change_toolnum_when_block() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_TOOL_RESPONSE);
        player.setPoint(TestHelper.ENOUGH_POINT);
        player.choseOne();
        int toolnum = player.getBarricade().getNum();

        blockCmd.setStep(TestHelper.TOOL_STEP);
        player.command(blockCmd);

        assertThat(player.getBarricade().getNum(), is(toolnum - 1));
    }

    @Test
    public void should_change_land_status_when_block() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_TOOL_RESPONSE);
        player.setPoint(TestHelper.ENOUGH_POINT);
        player.choseOne();

        blockCmd.setStep(TestHelper.TOOL_STEP);
        player.command(blockCmd);

        assertThat(emptyLand.getTool() instanceof Barricade, is(true));
    }

    @Test
    public void should_not_change_when_no_tool_to_use() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_CMD);

        blockCmd.setStep(TestHelper.TOOL_STEP);
        player.command(blockCmd);

        assertNull(emptyLand.getTool());
    }

    @Test
    public void should_not_change_when_use_tool_but_player_on_target_land() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_TOOL_RESPONSE);
        emptyLand.setPlayer(other);
        player.setPoint(TestHelper.ENOUGH_POINT);
        player.choseOne();

        blockCmd.setStep(TestHelper.TOOL_STEP);
        player.command(blockCmd);

        assertNull(emptyLand.getTool());
    }

    @Test
    public void should_not_change_when_use_tool_but_some_tool_on_target_land() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_TOOL_RESPONSE);
        Bomb bomb = new Bomb();
        emptyLand.setTool(bomb);
        player.setPoint(TestHelper.ENOUGH_POINT);
        player.choseOne();

        blockCmd.setStep(TestHelper.TOOL_STEP);
        player.command(blockCmd);

        assertThat(emptyLand.getTool(), is(bomb));
    }

    @Test
    public void should_not_change_when_target_land_too_faraway() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_TOOL_RESPONSE);
        player.setPoint(TestHelper.ENOUGH_POINT);
        player.choseOne();
        int toolNum = player.getBarricade().getNum();

        blockCmd.setStep(TestHelper.TOOL_LARGE_STEP);
        player.command(blockCmd);

        assertThat(player.getBarricade().getNum(), is(toolNum));
    }
}
