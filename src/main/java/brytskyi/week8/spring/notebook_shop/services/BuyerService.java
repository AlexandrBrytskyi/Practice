package brytskyi.week8.spring.notebook_shop.services;

import brytskyi.week8.spring.notebook_shop.dao.ISellingDAO;
import brytskyi.week8.spring.notebook_shop.model.selling.Prodaja;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class BuyerService extends GetProductionService implements IBuyerService {


    @Autowired
    private ISellingDAO dao;

    public BuyerService() {
    }

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
