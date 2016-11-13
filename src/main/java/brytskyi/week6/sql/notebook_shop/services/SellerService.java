package brytskyi.week6.sql.notebook_shop.services;

import brytskyi.week6.sql.notebook_shop.dao.IProductionDao;
import brytskyi.week6.sql.notebook_shop.dao.ISellingDAO;
import brytskyi.week6.sql.notebook_shop.model.production.*;
import brytskyi.week6.sql.notebook_shop.model.selling.Prodaja;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * Created by alexandr on 09.11.16.
 */
public class SellerService extends GetProductionService implements ISellerService {

    private IProductionDao productionDao;
    private ISellingDAO sellingDAO;

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
