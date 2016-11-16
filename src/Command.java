/**
 * Created by zyongliu on 15/11/16.
 */
public interface Command {
    public <T> STATUS execute(T obj);
}
