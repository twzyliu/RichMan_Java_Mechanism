import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Created by zyongliu on 16/11/16.
 */
public class QuitCmdTest {
    private Player player;
    private QuitCmd quitCmd;
    private GameMap gameMap;

    @Before
    public void setUp() throws Exception {
        gameMap = mock(GameMap.class);
        player = new Player(TestHelper.PLAYER_A, gameMap);
        quitCmd = new QuitCmd();
    }

    @Test
    public void should_wait_for_cmd_after_quit() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_CMD);

        player.command(quitCmd);

        assertThat(player.getStatus(), is(STATUS.EXIT));
    }
}
