package brytskyi.week6.sql.notebook_shop.services;

import brytskyi.week6.sql.notebook_shop.services.additional.Context;
import brytskyi.week6.sql.notebook_shop.services.additional.TokenGenerator;
import brytskyi.week6.sql.notebook_shop.services.additional.UserType;
import brytskyi.week6.sql.notebook_shop.dao.IUsersDao;
import brytskyi.week6.sql.notebook_shop.model.exceptions.controller_exceptions.NotRegisteredBuyerException;
import brytskyi.week6.sql.notebook_shop.model.exceptions.controller_exceptions.WrongLoginDataException;
import brytskyi.week6.sql.notebook_shop.model.exceptions.dao_exceptions.NullFieldException;
import brytskyi.week6.sql.notebook_shop.model.users.Buyer;
import brytskyi.week6.sql.notebook_shop.model.users.Contacts;
import brytskyi.week6.sql.notebook_shop.model.users.Seller;


public class LoginService implements Loginable {

    private IUsersDao usersDao;
    private Context ctxt;

    public LoginService(IUsersDao usersDao, Context ctxt) {
        this.usersDao = usersDao;
        this.ctxt = ctxt;
    }

    @Override
    public String login(String name, String pass) throws WrongLoginDataException {
        try {
            return ctxt.adminLogin(name, pass);
        } catch (WrongLoginDataException e) {
            Seller s = usersDao.getSeller(name, pass);
            if (null == s) throw new WrongLoginDataException();
            String token = TokenGenerator.getToken();
            ctxt.getTokens().put(token, UserType.SELLER);
            return token;
        }
    }

    @Override
    public String login(String phone) throws NotRegisteredBuyerException {
        Buyer buyer = usersDao.getBuyer(phone);
        if (null == buyer) throw new NotRegisteredBuyerException();
        String token = TokenGenerator.getToken();
        ctxt.getTokens().put(token, UserType.BUYER);
        return token;
    }

    public Buyer register(Contacts contacts) throws NullFieldException {
        Buyer buyer = new Buyer(contacts);
        return usersDao.addBuyer(buyer);
    }
}
