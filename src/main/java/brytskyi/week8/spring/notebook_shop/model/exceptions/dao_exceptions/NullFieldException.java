package brytskyi.week8.spring.notebook_shop.model.exceptions.dao_exceptions;

/**
 * Created by alexandr on 07.11.16.
 */
public class NullFieldException extends Throwable {
    public NullFieldException(String localizedMessage) {
        super(localizedMessage);
    }
}
