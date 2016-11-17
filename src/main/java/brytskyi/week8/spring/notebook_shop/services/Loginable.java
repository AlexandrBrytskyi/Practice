package brytskyi.week8.spring.notebook_shop.services;


import brytskyi.week8.spring.notebook_shop.model.exceptions.controller_exceptions.NotRegisteredBuyerException;
import brytskyi.week8.spring.notebook_shop.model.exceptions.controller_exceptions.WrongLoginDataException;
import brytskyi.week8.spring.notebook_shop.model.exceptions.dao_exceptions.NullFieldException;
import brytskyi.week8.spring.notebook_shop.model.users.Buyer;
import brytskyi.week8.spring.notebook_shop.model.users.Contacts;

public interface Loginable {

    String login(String name, String pass) throws WrongLoginDataException;

    String login(String phone) throws NotRegisteredBuyerException;

    Buyer register(Contacts contacts) throws NullFieldException;

}
