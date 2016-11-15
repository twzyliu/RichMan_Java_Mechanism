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
public class RollCmd_PrisonTest {
    private GameMap gameMap;
    private Prison prison;
    private Player player;
    private RollCmd rollCmd;

    @Before
    public void setUp() throws Exception {
        gameMap = mock(GameMap.class);
        prison = new Prison();
        player = new Player(TestHelper.PLAYER_A, gameMap);
        rollCmd = new RollCmd();
        when(gameMap.getPlace(anyInt())).thenReturn(prison);
    }

    @Test
    public void should_turn_end_after_roll_to_prison() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_CMD);

        player.command(rollCmd);

        assertThat(player.getStatus(), is(STATUS.TURN_END));
    }

    @Test
    public void should_change_prisondays_after_roll_to_prison() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_CMD);
        int prisonDays = player.getPrisonDays();

        player.command(rollCmd);

        assertThat(player.getPrisonDays(), is(prisonDays + Prison.PRISONDAYS));

    }
}
