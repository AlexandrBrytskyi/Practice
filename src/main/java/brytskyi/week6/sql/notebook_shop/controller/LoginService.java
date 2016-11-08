package brytskyi.week6.sql.notebook_shop.controller;

import brytskyi.week6.sql.notebook_shop.controller.additional.TokenGenerator;
import brytskyi.week6.sql.notebook_shop.dao.IUsersDao;
import brytskyi.week6.sql.notebook_shop.model.exceptions.controller_exceptions.NotRegisteredBuyerException;
import brytskyi.week6.sql.notebook_shop.model.exceptions.controller_exceptions.WrongLoginDataException;
import brytskyi.week6.sql.notebook_shop.model.exceptions.dao_exceptions.NullFieldException;
import brytskyi.week6.sql.notebook_shop.model.users.Buyer;
import brytskyi.week6.sql.notebook_shop.model.users.Contacts;
import brytskyi.week6.sql.notebook_shop.model.users.Seller;

/**
 * Created by alexandr on 08.11.16.
 */
public class LoginService implements Loginable {

    private IUsersDao usersDao;

    @Override
    public String register(String name, String pass) throws WrongLoginDataException {
        usersDao.openConnection();
        try {
            Seller s = usersDao.getSeller(name, pass);
            if (null == s) throw new WrongLoginDataException();
        } finally {
            usersDao.closeConnection();
        }
        return TokenGenerator.getToken();
    }

    @Override
    public String register(String phone) throws NotRegisteredBuyerException {
        usersDao.openConnection();
        try {
            Buyer buyer = usersDao.getBuyer(phone);
            if (null == buyer) throw new NotRegisteredBuyerException();
        } finally {
            usersDao.closeConnection();
        }
        return TokenGenerator.getToken();
    }

    public Buyer register(Contacts contacts) throws NullFieldException {
        Buyer buyer = new Buyer(contacts);
        usersDao.openConnection();
        try {
            return usersDao.addBuyer(buyer);
        } finally {
            usersDao.closeConnection();
        }
    }
}
