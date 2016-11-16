/**
 * Created by zyongliu on 16/11/16.
 */
public class RichCmd implements Command{
    @Override
    public <T> STATUS execute(T Obj) {
        return STATUS.WAIT_FOR_CMD;
    }
}
