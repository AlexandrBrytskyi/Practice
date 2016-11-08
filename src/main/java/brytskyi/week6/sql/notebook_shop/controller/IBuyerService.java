package brytskyi.week6.sql.notebook_shop.controller;

import brytskyi.week6.sql.notebook_shop.model.production.NotebookForSail;
import brytskyi.week6.sql.notebook_shop.model.selling.Prodaja;

import java.util.List;

/**
 * Created by alexandr on 08.11.16.
 */
public interface IBuyerService  {

   List<Prodaja> getMyProdajas(int buyerID);

}
