/**
 * Created by zyongliu on 16/11/16.
 */
public class QueryCmd implements Command {
    @Override
    public <T> STATUS execute(T obj) {
        return STATUS.WAIT_FOR_CMD;
    }
}
