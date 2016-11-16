/**
 * Created by zyongliu on 16/11/16.
 */
public class HelpCmd implements Command{
    @Override
    public <T> STATUS execute(T obj) {
        return STATUS.WAIT_FOR_CMD;
    }
}
