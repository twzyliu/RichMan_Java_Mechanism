/**
 * Created by zyongliu on 16/11/16.
 */
public class BlockCmd implements Command {
    private int step = 0;

    @Override
    public STATUS execute(Player player) {
        if (player.getBarricade().getNum() > 0 & step > 0) {
            player.getBarricade().use(player, step);
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