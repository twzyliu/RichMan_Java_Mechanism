import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Created by zyongliu on 16/11/16.
 */
public class SellToolCmdTest {
    private Player player;
    private SellToolCmd sellToolCmd;
    private GameMap gameMap;

    @Before
    public void setUp() throws Exception {
        gameMap = mock(GameMap.class);
        player = new Player(TestHelper.PLAYER_A, gameMap);
        sellToolCmd = new SellToolCmd();
    }

    @Test
    public void should_wait_for_cmd_after_selltool() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_CMD);
        player.getBarricade().setNum(1);

        sellToolCmd.setToolNum(TestHelper.SELL_TOOL_NUM);
        player.command(sellToolCmd);

        assertThat(player.getStatus(), is(STATUS.WAIT_FOR_CMD));
    }

    @Test
    public void should_change_point_and_toolsnum_when_selltool() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_CMD);
        Barricade barricade = player.getBarricade();
        barricade.setNum(1);
        int point = player.getPoint();
        int toolsNum = player.getToolsNum();

        sellToolCmd.setToolNum(TestHelper.SELL_TOOL_NUM);
        player.command(sellToolCmd);

        assertThat(player.getPoint(), is(point + barricade.getPoint()));
        assertThat(player.getToolsNum(), is(toolsNum - 1));
    }


    @Test
    public void should_not_change_when_no_tool_to_sell() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_CMD);
        int point = player.getPoint();
        int toolsNum = player.getToolsNum();

        sellToolCmd.setToolNum(TestHelper.SELL_TOOL_NUM);
        player.command(sellToolCmd);

        assertThat(player.getPoint(), is(point));
        assertThat(player.getToolsNum(), is(toolsNum));
    }
}
