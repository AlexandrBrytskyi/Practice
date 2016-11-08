package brytskyi.week6.sql.notebook_shop.controller;

import brytskyi.week6.sql.notebook_shop.dao.ISellingDAO;
import brytskyi.week6.sql.notebook_shop.model.selling.Prodaja;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * Created by alexandr on 09.11.16.
 */
public class BuyerService implements IBuyerService {

    private ISellingDAO dao;

    @Override
    public List<Prodaja> getMyProdajas(int buyerID) {
        Date start = Date.from(LocalDate.MIN.minusDays(1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(LocalDate.MAX.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        dao.openConnection();
        try {
            return dao.getProdajasBuyer(buyerID, start, end);
        } finally {
            dao.closeConnection();
        }
    }


}
