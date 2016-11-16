/**
 * Created by zyongliu on 16/11/16.
 */
public class Game implements WithCommandability{
    private STATUS status;

    public <T> void command(T command) {
        try {
            status = (STATUS) command.getClass().getMethod("execute", Object.class).invoke(command, this);
        } catch (Exception e) {
            status = null;
        }
    }

    public STATUS getStatus() {
        return status;
    }
}
