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
        richCmd.setGame(game);
    }

    @Test
    public void should_wait_for_init_money_after_rich() throws Exception {
        game.command(richCmd);

        assertThat(game.getStatus(), is(STATUS.WAIT_FOR_INIT_MONEY));
    }

    @Test
    public void should_wait_for_init_player_after_init_money() throws Exception {
        game.setStatus(STATUS.WAIT_FOR_INIT_MONEY);

        richCmd.setInitMoney(TestHelper.INIT_MONEY);
        game.command(richCmd);

        assertThat(game.getStatus(), is(STATUS.WAIT_FOR_INIT_PLAYERS));
    }

    @Test
    public void should_wait_for_cmd_after_init_player() throws Exception {
        game.setStatus(STATUS.WAIT_FOR_INIT_PLAYERS);
        richCmd.setInitMoney(TestHelper.INIT_MONEY);

        richCmd.setInitPlayer(TestHelper.PLAYERS_NAME);
        game.command(richCmd);

        assertThat(game.getStatus(), is(STATUS.WAIT_FOR_CMD));
    }
}


