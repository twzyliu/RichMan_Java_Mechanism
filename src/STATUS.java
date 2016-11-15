/**
 * Created by zyongliu on 15/11/16.
 */
public enum STATUS {
    WAIT_FOR_CMD, TURN_END, WAIT_FOR_BUY_RESPONSE{
        @Override
        public Response sayYes() {
            return Response.sayYesToBuy;
        }
        @Override
        public Response sayNo() {
            return Response.sayNoToBuy;
        }

        @Override
        public Response sayWrongCommand() {
            return Response.wrongCommandToBuy;
        }
    }, WAIT_FOR_UPGRADE_RESPONSE{
        @Override
        public Response sayYes() {
            return Response.sayYesToUpgrade;
        }

        @Override
        public Response sayNo() {
            return Response.sayNoToUpgrade;
        }

        @Override
        public Response sayWrongCommand() {
            return Response.wrongCommandToUpgrade;
        }
    };

    public Response sayYes() {
        return null;
    }

    public Response sayNo() {
        return null;
    }

    public Response sayWrongCommand() {
        return null;
    }
}
