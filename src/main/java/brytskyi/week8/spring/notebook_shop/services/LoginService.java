package brytskyi.week8.spring.notebook_shop.services;

import brytskyi.week8.spring.notebook_shop.dao.IUsersDao;
import brytskyi.week8.spring.notebook_shop.model.exceptions.controller_exceptions.NotRegisteredBuyerException;
import brytskyi.week8.spring.notebook_shop.model.exceptions.controller_exceptions.WrongLoginDataException;
import brytskyi.week8.spring.notebook_shop.model.exceptions.dao_exceptions.NullFieldException;
import brytskyi.week8.spring.notebook_shop.model.users.Buyer;
import brytskyi.week8.spring.notebook_shop.model.users.Contacts;
import brytskyi.week8.spring.notebook_shop.model.users.Seller;
import brytskyi.week8.spring.notebook_shop.services.additional.TokenGenerator;
import brytskyi.week8.spring.notebook_shop.services.additional.TokensContext;
import brytskyi.week8.spring.notebook_shop.services.additional.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements Loginable {

    @Autowired
    private IUsersDao usersDao;

    @Autowired
    private TokensContext tokensCtxt;

    public LoginService() {
    }

    public LoginService(IUsersDao usersDao, TokensContext ctxt) {
        this.usersDao = usersDao;
        this.tokensCtxt = ctxt;
    }

    @Override
    public String login(String name, String pass) throws WrongLoginDataException {
        try {
            return tokensCtxt.adminLogin(name, pass);
        } catch (WrongLoginDataException e) {
            Seller s = usersDao.getSeller(name, pass);
            if (null == s) throw new WrongLoginDataException();
            String token = TokenGenerator.getToken();
            tokensCtxt.getTokens().put(token, UserType.SELLER);
            return token;
        }
    }

    @Override
    public String login(String phone) throws NotRegisteredBuyerException {
        Buyer buyer = usersDao.getBuyer(phone);
        if (null == buyer) throw new NotRegisteredBuyerException();
        String token = TokenGenerator.getToken();
        tokensCtxt.getTokens().put(token, UserType.BUYER);
        return token;
    }

    public Buyer register(Contacts contacts) throws NullFieldException {
        Buyer buyer = new Buyer(contacts);
        return usersDao.addBuyer(buyer);
    }
}
