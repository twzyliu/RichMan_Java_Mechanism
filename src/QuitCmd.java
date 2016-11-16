/**
 * Created by zyongliu on 16/11/16.
 */
public class QuitCmd implements Command {

    @Override
    public <T> STATUS execute(T obj) {
        return STATUS.EXIT;
    }
}
