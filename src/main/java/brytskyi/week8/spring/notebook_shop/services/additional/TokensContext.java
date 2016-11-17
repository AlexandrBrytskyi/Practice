package brytskyi.week8.spring.notebook_shop.services.additional;


import brytskyi.week8.spring.notebook_shop.model.exceptions.controller_exceptions.WrongLoginDataException;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component()
public class TokensContext {

    private static final String adminToken = TokenGenerator.getToken();
    private static final String adminPass = "admin";
    private static final String adminLogin = "admin";
    private ConcurrentHashMap<String, UserType> tokens;


    private TokensContext() {
        initTokensMap();
    }

    private void initTokensMap() {
        tokens = new ConcurrentHashMap<>();
        tokens.put(adminToken, UserType.ADMIN);
    }

    public ConcurrentHashMap<String, UserType> getTokens() {
        return tokens;
    }

    public String adminLogin(String name, String pass) throws WrongLoginDataException {
        if (name.equals(adminLogin) && pass.equals(adminPass)) return adminToken;
        throw new WrongLoginDataException();
    }

}
