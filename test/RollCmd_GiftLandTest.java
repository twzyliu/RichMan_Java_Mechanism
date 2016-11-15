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
public class RollCmd_GiftLandTest {
    private GameMap gameMap;
    private GiftLand giftLand;
    private Player player;
    private RollCmd rollCmd;

    @Before
    public void setUp() throws Exception {
        gameMap = mock(GameMap.class);
        giftLand = mock(GiftLand.class);
        player = new Player(TestHelper.PLAYER_A, gameMap);
        rollCmd = new RollCmd();
        when(gameMap.getPlace(anyInt())).thenReturn(giftLand);
    }

    @Test
    public void should_wait_for_gift_response_after_roll_to_giftland() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_CMD);
        when(giftLand.changeStatus(any())).thenReturn(STATUS.WAIT_FOR_GIFT_RESPONSE);

        player.command(rollCmd);

        assertThat(player.getStatus(), is(STATUS.WAIT_FOR_GIFT_RESPONSE));
    }

    @Test
    public void should_turn_end_after_wrong_cmd() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_GIFT_RESPONSE);

        player.sayWrongCommand();

        assertThat(player.getStatus(), is(STATUS.TURN_END));
    }

    @Test
    public void should_change_money_after_chose_1() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_GIFT_RESPONSE);
        int money = player.getMoney();

        player.choseOne();

        assertThat(player.getMoney(), is(money + GiftLand.GIFT_MONEY));
    }

    @Test
    public void should_change_point_after_chose_2() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_GIFT_RESPONSE);
        int point = player.getPoint();

        player.choseTwo();

        assertThat(player.getPoint(), is(point + GiftLand.GIFT_POINT));
    }

    @Test
    public void should_get_god_after_chose_3() throws Exception {
        player.setStatus(STATUS.WAIT_FOR_GIFT_RESPONSE);
        int godDays = player.getGodDays();

        player.choseThree();

        assertThat(player.getGodDays(), is(godDays + GiftLand.GIFT_GOD_DAYS));
    }
}
