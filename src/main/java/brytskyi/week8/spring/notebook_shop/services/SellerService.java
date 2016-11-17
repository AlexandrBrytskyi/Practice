package brytskyi.week8.spring.notebook_shop.services;

import brytskyi.week8.spring.notebook_shop.dao.IProductionDao;
import brytskyi.week8.spring.notebook_shop.dao.ISellingDAO;
import brytskyi.week8.spring.notebook_shop.model.production.NotebookForSail;
import brytskyi.week8.spring.notebook_shop.model.selling.Prodaja;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class SellerService extends GetProductionService implements ISellerService {


    @Autowired
    private IProductionDao productionDao;

    @Autowired
    private ISellingDAO sellingDAO;

    public SellerService() {
    }

    public SellerService(IProductionDao productionDao, ISellingDAO sellingDAO) {
        this.productionDao = productionDao;
        this.sellingDAO = sellingDAO;
    }

    @Override
    public Prodaja sellNotebook(int notebookID, int buyerID, int sellerID) {
        return sellingDAO.addProdaja(notebookID, buyerID, sellerID);
    }

    @Override
    public List<Prodaja> getMyProdaja(int sellerID) {
        Date start = Date.from(LocalDate.MIN.minusDays(1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(LocalDate.MAX.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        return sellingDAO.getProdajasSeller(sellerID, start, end);
    }

    @Override
    public List<NotebookForSail> getNotebooksByKriteria(int hardMemory, int operativeMemory, int processor, int videoMemory, int model, int display, double pricemin, double priceMax) {
        return productionDao.getNotebooksByKriteria(hardMemory, operativeMemory, processor,
                videoMemory, model, display, pricemin, priceMax);
    }
}
