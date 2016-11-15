import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by zyongliu on 16/11/16.
 */
public class RollCmd_ToolLandTest {
    private GameMap gameMap;
    private ToolLand toolLand;
    private Player player;
    private RollCmd rollCmd;

    @Before
    public void setUp() throws Exception {
        gameMap = mock(GameMap.class);
        toolLand = new ToolLand();
        player = new Player(TestHelper.PLAYER_A, gameMap);
        rollCmd = new RollCmd();
        when(gameMap.getPlace(anyInt())).thenReturn(toolLand);
    }

    @Test
    public void should_wait_for_tool_response_after_roll_to_toolland() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_CMD);
        player.setPoint(TestHelper.ENOUGH_POINT);

        player.command(rollCmd);

        assertThat(player.getStatus(), is(STATUS.WAIT_FOR_TOOL_RESPONSE));
    }

    @Test
    public void should_turn_end_when_no_enough_point() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_CMD);

        player.command(rollCmd);

        assertThat(player.getStatus(), is(STATUS.TURN_END));
    }

    @Test
    public void should_change_point_when_chose_tool_1_and_have_enough_poit() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_TOOL_RESPONSE);
        player.setPoint(TestHelper.ENOUGH_POINT);
        int point = player.getPoint();

        player.choseOne();

        assertThat(player.getPoint(), is(point - new Barricade().getPoint()));
    }

    @Test
    public void should_change_toolNum_when_chose_tool_1_and_have_enough_potin() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_TOOL_RESPONSE);
        player.setPoint(TestHelper.ENOUGH_POINT);
        int toolNum = player.getToolsNum();

        player.choseOne();

        assertThat(player.getToolsNum(), is(toolNum + 1));
    }

    @Test
    public void should_wait_for_tool_response_afer_chose_tool() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_TOOL_RESPONSE);
        player.setPoint(TestHelper.ENOUGH_POINT);

        player.choseOne();

        assertThat(player.getStatus(), is(STATUS.WAIT_FOR_TOOL_RESPONSE));
    }

    @Test
    public void should_no_change_when_no_enough_point_to_buy_tool() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_TOOL_RESPONSE);
        int point = player.getPoint();
        int toolNum = player.getToolsNum();

        player.choseOne();

        assertThat(player.getPoint(), is(point));
        assertThat(player.getToolsNum(), is(toolNum));
        assertThat(player.getStatus(), is(STATUS.WAIT_FOR_TOOL_RESPONSE));
    }

    @Test
    public void should_no_change_when_no_space_to_buy_tool() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_TOOL_RESPONSE);
        player.setPoint(TestHelper.ENOUGH_POINT);
        for (int i = 0; i< TestHelper.MAX_TOOL_SPACE; i++) {
            player.choseOne();
        }
        int point = player.getPoint();
        int toolNum = player.getToolsNum();

        player.choseOne();

        assertThat(player.getPoint(), is(point));
        assertThat(player.getToolsNum(), is(toolNum));
        assertThat(player.getStatus(), is(STATUS.WAIT_FOR_TOOL_RESPONSE));
    }

    @Test
    public void should_turn_end_when_chose_exit_cmd() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_TOOL_RESPONSE);
        player.setPoint(TestHelper.ENOUGH_POINT);

        player.choseExit();

        assertThat(player.getStatus(), is(STATUS.TURN_END));
    }

    @Test
    public void should_wait_for_tool_response_when_chose_wrong_cmd() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_TOOL_RESPONSE);
        player.setPoint(TestHelper.ENOUGH_POINT);

        player.wrongCommand();

        assertThat(player.getStatus(), is(STATUS.WAIT_FOR_TOOL_RESPONSE));

    }
}
