package brytskyi.week6.sql.notebook_shop.dao;

import brytskyi.week6.sql.notebook_shop.model.exceptions.dao_exceptions.NullFieldException;
import brytskyi.week6.sql.notebook_shop.model.selling.Prodaja;
import brytskyi.week6.sql.notebook_shop.model.users.Buyer;
import brytskyi.week6.sql.notebook_shop.model.users.Seller;

import java.util.Date;
import java.util.List;


public interface ISellingDAO extends MyCloseable {

    Prodaja addProdaja(Prodaja prodaja) throws NullFieldException;

    List<Prodaja> getProdajasBuyer(int buyer, Date begin, Date end);

    List<Prodaja> getProdajasSeller(int seller, Date begin, Date end);

}
