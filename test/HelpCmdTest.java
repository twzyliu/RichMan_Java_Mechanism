import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Created by zyongliu on 16/11/16.
 */
public class HelpCmdTest {
    private Player player;
    private HelpCmd helpCmd;
    private GameMap gameMap;

    @Before
    public void setUp() throws Exception {
        gameMap = mock(GameMap.class);
        player = new Player(TestHelper.PLAYER_A, gameMap);
        helpCmd = new HelpCmd();
    }

    @Test
    public void should_wait_for_cmd_after_help() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_CMD);

        player.command(helpCmd);

        assertThat(player.getStatus(), is(STATUS.WAIT_FOR_CMD));
    }
}
