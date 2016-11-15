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
public class RollCmd_OwnLandTest {
    private GameMap gameMap;
    private EmptyLand ownLand;
    private Player player;
    private RollCmd rollCmd;

    @Before
    public void setUp() throws Exception {
        gameMap = mock(GameMap.class);
        ownLand = new EmptyLand(TestHelper.LAND_PRICE);
        player = new Player(TestHelper.PLAYER_A, gameMap);
        rollCmd = new RollCmd();
        ownLand.setOwner(player);
        when(gameMap.getPlace(anyInt())).thenReturn(ownLand);
    }

    @Test
    public void should_wait_for_upgrade_response_after_roll_to_Ownland() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_CMD);

        player.command(rollCmd);

        assertThat(player.getStatus(), is(STATUS.WAIT_FOR_UPGRADE_RESPONSE));
    }

    @Test
    public void should_turn_end_after_sayYes() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_UPGRADE_RESPONSE);
        player.setMoney(TestHelper.ENOUGH_MONEY);

        player.sayYes();

        assertThat(player.getStatus(), is(STATUS.TURN_END));
    }

    @Test
    public void should_change_money_after_sayYes() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_UPGRADE_RESPONSE);
        player.setMoney(TestHelper.ENOUGH_MONEY);
        int money = player.getMoney();

        player.sayYes();

        assertThat(player.getMoney(), is(money - TestHelper.LAND_PRICE));
    }

    @Test
    public void should_change_land_level_after_sayYes() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_UPGRADE_RESPONSE);
        EmptyLand myland = new EmptyLand(TestHelper.LAND_PRICE);
        myland.setOwner(player);
        when(gameMap.getPlace(anyInt())).thenReturn(myland);
        player.setMoney(TestHelper.ENOUGH_MONEY);
        int level = myland.getLevel();

        player.sayYes();

        assertThat(myland.getLevel(), is(level + 1));
    }

    @Test
    public void should_turn_end_after_sayNo() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_UPGRADE_RESPONSE);
        player.setMoney(TestHelper.ENOUGH_MONEY);

        player.sayNo();

        assertThat(player.getStatus(), is(STATUS.TURN_END));
    }

    @Test
    public void should_wait_for_buy_response_after_wrong_cmd() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_UPGRADE_RESPONSE);
        player.setMoney(TestHelper.ENOUGH_MONEY);

        player.wrongCommand();

        assertThat(player.getStatus(), is(STATUS.WAIT_FOR_UPGRADE_RESPONSE));

    }
}
