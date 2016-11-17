package brytskyi.week6_7.sql.notebook_shop.services;

import brytskyi.week6_7.sql.notebook_shop.model.selling.Prodaja;

import java.util.List;

/**
 * Created by alexandr on 08.11.16.
 */
public interface IBuyerService extends IGetProductionService {

    List<Prodaja> getMyProdajas(int buyerID);

}
