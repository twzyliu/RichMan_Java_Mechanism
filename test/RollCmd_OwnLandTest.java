import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by zyongliu on 15/11/16.
 */
public class RollCmd_OwnLandTest {
    private GameMap gameMap;
    private EmptyLand ownLand;
    private Player player;
    private RollCmd rollCmd;
    private Player other;

    @Before
    public void setUp() throws Exception {
        gameMap = mock(GameMap.class);
        ownLand = mock(EmptyLand.class);
        player = new Player(TestHelper.PLAYER_A, gameMap);
        other = new Player(TestHelper.PLAYER_B, gameMap);
        rollCmd = new RollCmd();
        when(gameMap.getPlace(anyInt())).thenReturn(ownLand);
        when(ownLand.getOwner()).thenReturn(other);
    }
    @Test
    public void should_wait_for_upgrade_response_after_roll_to_Ownland() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_CMD);
        when(ownLand.changeStatus(any())).thenReturn(STATUS.WAIT_FOR_UPGRADE_RESPONSE);

        player.command(rollCmd);

        assertThat(player.getStatus(), is(STATUS.WAIT_FOR_UPGRADE_RESPONSE));
    }
}
