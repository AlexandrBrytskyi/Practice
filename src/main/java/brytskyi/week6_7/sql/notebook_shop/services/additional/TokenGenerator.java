package brytskyi.week6_7.sql.notebook_shop.services.additional;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by alexandr on 08.11.16.
 */
public class TokenGenerator {

    private static SecureRandom random = new SecureRandom();

    public static String getToken() {
        return new BigInteger(130, random).toString(32);
    }
}
