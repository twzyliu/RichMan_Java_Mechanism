import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by zyongliu on 15/11/16.
 */
public class RollCmd_EmptyLandTest {

    private GameMap gameMap;
    private EmptyLand emptyLand;
    private Player player;
    private RollCmd rollCmd;

    @Before
    public void setUp() throws Exception {
        gameMap = mock(GameMap.class);
        emptyLand = mock(EmptyLand.class);
        player = new Player(TestHelper.PLAYER_A, gameMap);
        rollCmd = new RollCmd();
        when(gameMap.getPlace(anyInt())).thenReturn(emptyLand);
    }

    @Test
    public void should_wait_for_buy_response_after_roll_to_emptyland() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_CMD);
        when(emptyLand.changeStatus(any())).thenReturn(STATUS.WAIT_FOR_BUY_RESPONSE);

        player.command(rollCmd);

        assertThat(player.getStatus(), is(STATUS.WAIT_FOR_BUY_RESPONSE));
    }

    @Test
    public void should_turn_end_after_sayYes() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_BUY_RESPONSE);
        player.setMoney(TestHelper.ENOUGH_MONEY);

        player.command(RollCmd.sayYesToBuy);

        assertThat(player.getStatus(), is(STATUS.TURN_END));
    }

    @Test
    public void should_change_money_after_sayYes() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_BUY_RESPONSE);
        when(emptyLand.getPrice()).thenReturn(TestHelper.LAND_PRICE);
        player.setMoney(TestHelper.ENOUGH_MONEY);
        int money = player.getMoney();

        player.command(RollCmd.sayYesToBuy);

        assertThat(player.getMoney(), is(money - TestHelper.LAND_PRICE));
    }

    @Test
    public void should_add_land_after_sayYes() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_BUY_RESPONSE);
        when(gameMap.getPlace(anyInt())).thenReturn(emptyLand);
        player.setMoney(TestHelper.ENOUGH_MONEY);
        int landNum = player.getLandNum();

        player.command(RollCmd.sayYesToBuy);

        assertThat(player.getLandNum(), is(landNum + 1));
    }

    @Test
    public void should_change_land_owner_after_sayYes() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_BUY_RESPONSE);
        EmptyLand emptyLand = new EmptyLand(TestHelper.LAND_PRICE);
        when(gameMap.getPlace(anyInt())).thenReturn(emptyLand);
        player.setMoney(TestHelper.ENOUGH_MONEY);

        player.command(RollCmd.sayYesToBuy);

        assertThat(emptyLand.getOwner(), is(player));
    }

    @Test
    public void should_turn_end_when_sayYes_no_enough_money() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_BUY_RESPONSE);
        EmptyLand emptyLand = new EmptyLand(TestHelper.LAND_PRICE);
        when(gameMap.getPlace(anyInt())).thenReturn(emptyLand);
        int money = player.getMoney();
        int landNum = player.getLandNum();

        player.command(RollCmd.sayYesToBuy);

        assertThat(player.getMoney(), is(money));
        assertThat(player.getLandNum(), is(landNum));
        assertNull(emptyLand.getOwner());
    }

    @Test
    public void should_turn_end_after_sayNo() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_BUY_RESPONSE);
        when(gameMap.getPlace(anyInt())).thenReturn(emptyLand);
        player.setMoney(TestHelper.ENOUGH_MONEY);

        player.command(RollCmd.sayNoToBuy);

        assertThat(player.getStatus(), is(STATUS.TURN_END));
    }

    @Test
    public void should_wait_for_buy_response_after_wrong_cmd() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_BUY_RESPONSE);
        when(gameMap.getPlace(anyInt())).thenReturn(emptyLand);
        player.setMoney(TestHelper.ENOUGH_MONEY);

        player.command(RollCmd.wrongCommandToBuy);

        assertThat(player.getStatus(), is(STATUS.WAIT_FOR_BUY_RESPONSE));

    }
}
