package brytskyi.week6.sql.notebook_shop.controller;


import brytskyi.week6.sql.notebook_shop.model.exceptions.controller_exceptions.NotRegisteredBuyerException;
import brytskyi.week6.sql.notebook_shop.model.exceptions.controller_exceptions.WrongLoginDataException;
import brytskyi.week6.sql.notebook_shop.model.exceptions.dao_exceptions.NullFieldException;
import brytskyi.week6.sql.notebook_shop.model.users.Buyer;
import brytskyi.week6.sql.notebook_shop.model.users.Contacts;

public interface Loginable {

    String register(String name, String pass) throws WrongLoginDataException;

    String register(String phone) throws NotRegisteredBuyerException;

    Buyer register(Contacts contacts) throws NullFieldException;

}
