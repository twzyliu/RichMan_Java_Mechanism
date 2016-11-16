/**
 * Created by zyongliu on 16/11/16.
 */
public class BombCmd implements Command {
    private int step;

    @Override
    public STATUS execute(Player player) {
        if (player.getBomb().getNum() > 0 & step > 0) {
            player.getBomb().use(player, step);
        }
        return STATUS.WAIT_FOR_CMD;
    }

    public void setStep(int step) {
        if (step < 11 & step > -11) {
            this.step = step;
        } else {
            this.step = 0;
        }
    }
}
