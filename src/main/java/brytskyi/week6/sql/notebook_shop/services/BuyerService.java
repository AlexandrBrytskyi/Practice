package brytskyi.week6.sql.notebook_shop.services;

import brytskyi.week6.sql.notebook_shop.dao.ISellingDAO;
import brytskyi.week6.sql.notebook_shop.model.selling.Prodaja;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * Created by alexandr on 09.11.16.
 */
public class BuyerService extends GetProductionService implements IBuyerService {

    private ISellingDAO dao;

    public BuyerService(ISellingDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<Prodaja> getMyProdajas(int buyerID) {
        Date start = Date.from(LocalDate.MIN.minusDays(1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(LocalDate.MAX.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        return dao.getProdajasBuyer(buyerID, start, end);
    }


}
