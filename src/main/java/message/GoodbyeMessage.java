package message;

/**
 * Static farewell message displayed when the application exits.
 */
public class GoodbyeMessage implements Message {

    private static final String BYE_CONTENT =
            "Ok lah, mee go already. Catch you later!";

    @Override
    public String getMessage() {
        return BYE_CONTENT;
    }

}
