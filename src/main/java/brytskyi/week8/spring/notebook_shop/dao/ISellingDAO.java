package brytskyi.week8.spring.notebook_shop.dao;

import brytskyi.week8.spring.notebook_shop.model.selling.Prodaja;

import java.util.Date;
import java.util.List;


public interface ISellingDAO {

    Prodaja addProdaja(int notebookID, int buyerID, int sellerID);

    List<Prodaja> getProdajasBuyer(int buyer, Date begin, Date end);

    List<Prodaja> getProdajasSeller(int seller, Date begin, Date end);

}
