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
public class RollCmd_HospitalTest {
    private GameMap gameMap;
    private Hospital hospital;
    private Player player;
    private RollCmd rollCmd;

    @Before
    public void setUp() throws Exception {
        gameMap = mock(GameMap.class);
        hospital = new Hospital();
        player = new Player(TestHelper.PLAYER_A, gameMap);
        rollCmd = new RollCmd();
        when(gameMap.getPlace(anyInt())).thenReturn(hospital);
    }

    @Test
    public void should_turn_end_after_roll_to_startingpoint() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_CMD);

        player.command(rollCmd);

        assertThat(player.getStatus(), is(STATUS.TURN_END));
    }

}
