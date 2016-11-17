package brytskyi.week8.spring.notebook_shop.services;

import brytskyi.week8.spring.notebook_shop.model.selling.Prodaja;

import java.util.List;

/**
 * Created by alexandr on 08.11.16.
 */
public interface IBuyerService extends IGetProductionService {

    List<Prodaja> getMyProdajas(int buyerID);

}
