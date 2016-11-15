import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by zyongliu on 15/11/16.
 */
public class RollCmd_OthersLandTest {
    private GameMap gameMap;
    private EmptyLand othersLand;
    private Player player;
    private Player other;
    private RollCmd rollCmd;

    @Before
    public void setUp() throws Exception {
        gameMap = mock(GameMap.class);
        othersLand = new EmptyLand(TestHelper.LAND_PRICE);
        player = new Player(TestHelper.PLAYER_A, gameMap);
        other = new Player(TestHelper.PLAYER_B, gameMap);
        othersLand.setOwner(other);
        rollCmd = new RollCmd();
        when(gameMap.getPlace(anyInt())).thenReturn(othersLand);
    }

    @Test
    public void should_turn_end_after_roll_to_other() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_CMD);
        player.setMoney(TestHelper.ENOUGH_MONEY);

        player.command(rollCmd);

        assertThat(player.getStatus(), is(STATUS.TURN_END));
    }

    @Test
    public void should_change_my_money_after_roll_to_other() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_CMD);
        player.setMoney(TestHelper.ENOUGH_MONEY);
        int money = player.getMoney();

        player.command(rollCmd);

        assertThat(player.getMoney(), is(money - othersLand.getBill()));
    }

    @Test
    public void should_change_others_money_after_roll_to_other() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_CMD);
        player.setMoney(TestHelper.ENOUGH_MONEY);
        int money = other.getMoney();

        player.command(rollCmd);

        assertThat(other.getMoney(), is(money + othersLand.getBill()));
    }

    @Test
    public void should_gameover_when_no_enough_money_to_pay() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_CMD);

        player.command(rollCmd);

        assertThat(player.getStatus(), is(STATUS.GAME_OVER));
    }
}
