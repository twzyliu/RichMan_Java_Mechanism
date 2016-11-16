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
    }, WAIT_FOR_GIFT_RESPONSE{
        @Override
        public Response sayWrongCommand() {
            return Response.wrongCommandToGift;
        }

        @Override
        public Response choseOne() {
            return Response.giftChoseOne;
        }

        @Override
        public Response choseTwo() {
            return Response.giftChoseTwo;
        }

        @Override
        public Response choseThree() {
            return Response.giftChoseThree;
        }
    }, GAME_OVER, WAIT_FOR_TOOL_RESPONSE{
        @Override
        public Response choseOne() {
            return Response.toolChoseOne;
        }

        @Override
        public Response choseTwo() {
            return Response.toolChoseTwo;
        }

        @Override
        public Response choseThree() {
            return Response.toolChoseThree;
        }

        @Override
        public Response choseExit() {
            return Response.toolChoseExit;
        }

        @Override
        public Response sayWrongCommand() {
            return Response.toolWrongCommand;
        }
    }, EXIT, WAIT_FOR_INIT_MONEY, WAIT_FOR_INIT_PLAYERS, GAME_START;

    public Response sayYes() {
        return null;
    }

    public Response sayNo() {
        return null;
    }

    public Response sayWrongCommand() {
        return null;
    }

    public Response choseOne() {
        return null;
    }

    public Response choseTwo() {
        return null;
    }

    public Response choseThree() {
        return null;
    }

    public Response choseExit() {
        return null;
    }

}
