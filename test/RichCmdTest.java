import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by zyongliu on 16/11/16.
 */
public class RichCmdTest {
    private RichCmd richCmd;
    private Game game;

    @Before
    public void setUp() throws Exception {
        richCmd = new RichCmd();
        game = new Game();
    }

    @Test
    public void should_wait_for_cmd_after_query() throws Exception {
        game.command(richCmd);

        assertThat(game.getStatus(), is(STATUS.WAIT_FOR_CMD));
    }
}


