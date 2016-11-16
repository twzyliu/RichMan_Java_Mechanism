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
public class RobotCmdTest {
    private Player player;
    private RobotCmd robotCmd;
    private GameMap gameMap;
    private EmptyLand emptyLand;

    @Before
    public void setUp() throws Exception {
        gameMap = mock(GameMap.class);
        player = new Player(TestHelper.PLAYER_A, gameMap);
        robotCmd = new RobotCmd();
        emptyLand = new EmptyLand(TestHelper.LAND_PRICE);
        when(gameMap.getPlace(anyInt())).thenReturn(emptyLand);
    }

    @Test
    public void should_wait_for_cmd_after_robot() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_CMD);

        player.command(robotCmd);

        assertThat(player.getStatus(), is(STATUS.WAIT_FOR_CMD));
    }

    @Test
    public void should_change_toolnum_when_robot() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_TOOL_RESPONSE);
        player.setPoint(TestHelper.ENOUGH_POINT);
        player.choseTwo();
        int toolnum = player.getRobot().getNum();

        player.command(robotCmd);

        assertThat(player.getRobot().getNum(), is(toolnum - 1));
    }

    @Test
    public void should_not_change_when_no_tool_to_use() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_CMD);
        int toolnum = player.getRobot().getNum();

        player.command(robotCmd);

        assertThat(player.getRobot().getNum(), is(toolnum));
    }

    @Test
    public void should_clear_tool_on_gamemap_when_robot() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_TOOL_RESPONSE);
        emptyLand.setTool(new Bomb());
        player.setPoint(TestHelper.ENOUGH_POINT);
        player.choseTwo();

        player.command(robotCmd);

        assertNull(emptyLand.getTool());
    }
}
