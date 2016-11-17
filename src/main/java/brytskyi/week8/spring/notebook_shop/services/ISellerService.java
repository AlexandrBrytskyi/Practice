package brytskyi.week8.spring.notebook_shop.services;


import brytskyi.week8.spring.notebook_shop.model.production.NotebookForSail;
import brytskyi.week8.spring.notebook_shop.model.selling.Prodaja;

import java.util.List;

public interface ISellerService extends IGetProductionService {


    Prodaja sellNotebook(int notebookID, int buyerID, int sellerID);

    List<Prodaja> getMyProdaja(int sellerID);

    List<NotebookForSail> getNotebooksByKriteria(int hardMemory, int operativeMemory, int processor, int videoMemory, int model, int display, double pricemin, double priceMax);

}
