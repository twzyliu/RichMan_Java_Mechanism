import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by zyongliu on 16/11/16.
 */
public class SellCmdTest {
    private Player player;
    private SellCmd sellCmd;
    private GameMap gameMap;
    private EmptyLand emptyLand;

    @Before
    public void setUp() throws Exception {
        gameMap = mock(GameMap.class);
        player = new Player(TestHelper.PLAYER_A, gameMap);
        sellCmd = new SellCmd();
        emptyLand = new EmptyLand(TestHelper.LAND_PRICE);
        emptyLand.setOwner(player);
        when(gameMap.getPlace(anyInt())).thenReturn(emptyLand);
    }

    @Test
    public void should_wait_for_cmd_after_sell() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_CMD);

        sellCmd.setStep(TestHelper.SELL_STEP);
        player.command(sellCmd);

        assertThat(player.getStatus(), is(STATUS.WAIT_FOR_CMD));
    }

    @Test
    public void should_change_landnum_when_sell() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_CMD);
        player.getLands().add(emptyLand);
        int landNum = player.getLandNum();

        sellCmd.setStep(TestHelper.SELL_STEP);
        player.command(sellCmd);

        assertThat(player.getLandNum(), is(landNum - 1));
    }

    @Test
    public void should_change_land_ownner_when_sell() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_CMD);
        player.getLands().add(emptyLand);

        sellCmd.setStep(TestHelper.SELL_STEP);
        player.command(sellCmd);

        assertNull(emptyLand.getOwner());
    }

    @Test
    public void should_change_land_level_when_sell() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_CMD);
        player.getLands().add(emptyLand);

        sellCmd.setStep(TestHelper.SELL_STEP);
        player.command(sellCmd);

        assertThat(emptyLand.getLevel(), is(0));
    }

    @Test
    public void should_gain_money_when_sell() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_CMD);
        player.getLands().add(emptyLand);
        int money = player.getMoney();

        sellCmd.setStep(TestHelper.SELL_STEP);
        player.command(sellCmd);
        assertThat(player.getMoney(), is(money + (emptyLand.getPrice() * (emptyLand.getLevel() + 1) * 2)));
    }

    @Test
    public void should_not_change_when_no_land_to_sell() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_CMD);
        emptyLand.setOwner(null);
        int money = player.getMoney();
        int landNum = player.getLandNum();

        sellCmd.setStep(TestHelper.SELL_STEP);
        player.command(sellCmd);

        assertThat(player.getMoney(), is(money));
        assertThat(player.getLandNum(), is(landNum));
    }
}
