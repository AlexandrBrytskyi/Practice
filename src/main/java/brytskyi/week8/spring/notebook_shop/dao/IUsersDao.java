package brytskyi.week8.spring.notebook_shop.dao;

import brytskyi.week8.spring.notebook_shop.model.exceptions.dao_exceptions.NullFieldException;
import brytskyi.week8.spring.notebook_shop.model.users.Buyer;
import brytskyi.week8.spring.notebook_shop.model.users.Seller;

import java.util.List;

/**
 * Created by alexandr on 08.11.16.
 */
public interface IUsersDao {

    Buyer addBuyer(Buyer buyer) throws NullFieldException;

    List<Buyer> getAllBuyers();

    Seller addSeller(Seller seller) throws NullFieldException;

    List<Seller> getSellers(boolean working);

    Seller updateSeller(int sellerID, boolean isWorking);

    Buyer getBuyer(String phone);

    Seller getSeller(String name, String pass);

}
