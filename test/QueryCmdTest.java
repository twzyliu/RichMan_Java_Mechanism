import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Created by zyongliu on 16/11/16.
 */
public class QueryCmdTest {
    private Player player;
    private QueryCmd queryCmd;
    private GameMap gameMap;

    @Before
    public void setUp() throws Exception {
        gameMap = mock(GameMap.class);
        player = new Player(TestHelper.PLAYER_A, gameMap);
        queryCmd = new QueryCmd();
    }

    @Test
    public void should_wait_for_cmd_after_query() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_CMD);

        player.command(queryCmd);

        assertThat(player.getStatus(), is(STATUS.WAIT_FOR_CMD));
    }
}
