/**
 * Created by zyongliu on 16/11/16.
 */
public class BombCmd implements Command {
    private int step;

    @Override
    public <T> STATUS execute(T obj) {
        Player player = (Player) obj;
        Bomb bomb = player.getBomb();
        if (bomb.getNum() > 0 & step > 0) {
            bomb.use(player, step);
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
