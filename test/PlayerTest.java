import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by zyongliu on 15/11/16.
 */
public class PlayerTest {

    private Command command;
    private Player player;
    private Response response;

    @Before
    public void setUp() throws Exception {
        command = mock(Command.class);
        response = mock(Response.class);
        player = new Player(TestHelper.PLAYER_A, new GameMap());
        player.setStatus(STATUS.WAIT_FOR_CMD);
    }

    @Test
    public void should_wait_for_cmd_after_repondless_cmd() throws Exception {
        when(command.execute(any())).thenReturn(STATUS.WAIT_FOR_CMD);
        player.command(command);
        assertThat(player.getStatus(), is(STATUS.WAIT_FOR_CMD));
    }

    @Test
    public void should_wait_for_response_after_respondful_cmd() throws Exception {
        when(command.execute(any())).thenReturn(STATUS.WAIT_FOR_BUY_RESPONSE);
        player.command(command);
        assertThat(player.getStatus(), is(STATUS.WAIT_FOR_BUY_RESPONSE));
    }

    @Test
    public void should_turn_end_after_noneed_respond_cmd() throws Exception {
        when(command.execute(any())).thenReturn(STATUS.TURN_END);
        player.command(command);
        assertThat(player.getStatus(), is(STATUS.TURN_END));
    }

    @Test
    public void should_turn_end_after_respond() throws Exception {
        when(response.execute(any())).thenReturn(STATUS.TURN_END);
        player.command(response);
        assertThat(player.getStatus(), is(STATUS.TURN_END));
    }

}
