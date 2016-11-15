import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
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
        player = new Player(gameMap);
        rollCmd = new RollCmd();
    }

    @Test
    public void should_wait_for_response_after_roll_to_emptyland() throws Exception {
        when(gameMap.getPlace(anyInt())).thenReturn(emptyLand);

        player.execute(rollCmd);

        assertThat(player.getStatus(), is(STATUS.WAIT_FOR_RESPONSE));
    }

    @Test
    public void should_turn_end_after_sayYes() throws Exception {
        when(gameMap.getPlace(anyInt())).thenReturn(emptyLand);
        player.setMoney(TestHelper.ENOUGH_MONEY);

        player.execute(rollCmd);
        player.respond(rollCmd.sayYes);

        assertThat(player.getStatus(), is(STATUS.TURN_END));
    }

    @Test
    public void should_change_money_after_sayYes() throws Exception {
        when(gameMap.getPlace(anyInt())).thenReturn(emptyLand);
        when(emptyLand.getPrice()).thenReturn(TestHelper.LAND_PRICE);
        player.setMoney(TestHelper.ENOUGH_MONEY);
        int money = player.getMoney();

        player.execute(rollCmd);
        player.respond(rollCmd.sayYes);

        assertThat(player.getMoney(), is(money - TestHelper.LAND_PRICE));
    }

    @Test
    public void should_add_land_after_sayYes() throws Exception {
        when(gameMap.getPlace(anyInt())).thenReturn(emptyLand);
        player.setMoney(TestHelper.ENOUGH_MONEY);
        int landNum = player.getLandNum();
        player.execute(rollCmd);
        player.respond(rollCmd.sayYes);

        assertThat(player.getLandNum(), is(landNum + 1));
    }

    @Test
    public void should_change_land_owner_after_sayYes() throws Exception {
        EmptyLand emptyLand = new EmptyLand(TestHelper.LAND_PRICE);
        when(gameMap.getPlace(anyInt())).thenReturn(emptyLand);
        player.setMoney(TestHelper.ENOUGH_MONEY);

        player.execute(rollCmd);
        player.respond(rollCmd.sayYes);

        assertThat(emptyLand.getOwner(), is(player));
    }

    @Test
    public void should_turn_end_when_sayYes_no_enough_money() throws Exception {
        EmptyLand emptyLand = new EmptyLand(TestHelper.LAND_PRICE);
        when(gameMap.getPlace(anyInt())).thenReturn(emptyLand);
        int money = player.getMoney();
        int landNum = player.getLandNum();

        player.execute(rollCmd);
        player.respond(rollCmd.sayYes);

        assertThat(player.getMoney(), is(money));
        assertThat(player.getLandNum(), is(landNum));
        assertNull(emptyLand.getOwner());
    }

    @Test
    public void should_turn_end_after_sayNo() throws Exception {
        when(gameMap.getPlace(anyInt())).thenReturn(emptyLand);
        player.setMoney(TestHelper.ENOUGH_MONEY);

        player.execute(rollCmd);
        player.respond(rollCmd.sayNo);

        assertThat(player.getStatus(), is(STATUS.TURN_END));
    }
}
