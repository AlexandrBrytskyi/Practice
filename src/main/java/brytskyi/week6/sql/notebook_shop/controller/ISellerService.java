package brytskyi.week6.sql.notebook_shop.controller;


import brytskyi.week6.sql.notebook_shop.model.production.NotebookForSail;
import brytskyi.week6.sql.notebook_shop.model.selling.Prodaja;

import java.util.List;

public interface ISellerService {

    /*TODO get notebooks by notebooktypes
    * get notebooks by criterias*/

    // TODO: 09.11.16 rmi service as a proxy. it must look through access token

    Prodaja sellNotebook(int notebookID, int buyerID);

    List<Prodaja> getMyProdaja(int sellerID);

}
