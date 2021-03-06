package brytskyi.week8.spring.notebook_shop.services.additional;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;

public class TokenGenerator {

    private static SecureRandom random = new SecureRandom();

    public static String getToken() {
        return new BigInteger(130, random).toString(32);
    }
}
