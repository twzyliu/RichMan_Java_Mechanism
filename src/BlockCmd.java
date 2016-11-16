/**
 * Created by zyongliu on 16/11/16.
 */
public class BlockCmd implements Command {
    private int step = 0;

    @Override
    public <T> STATUS execute(T obj) {
        Player player = (Player) obj;
        Barricade barricade = player.getBarricade();
        if (barricade.getNum() > 0 & step > 0) {
            barricade.use(player, step);
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
