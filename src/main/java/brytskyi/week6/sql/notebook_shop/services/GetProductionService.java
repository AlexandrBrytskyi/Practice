package brytskyi.week6.sql.notebook_shop.services;

import brytskyi.week6.sql.notebook_shop.dao.IProductionDao;
import brytskyi.week6.sql.notebook_shop.model.production.*;

import java.util.List;

/**
 * Created by alexandr on 09.11.16.
 */
public class GetProductionService implements IGetProductionService {

    private IProductionDao productionDao;

    @Override
    public List<NotebookModel> getAllNotebookModels() {
        return productionDao.getAllNotebookModels();
    }

    @Override
    public List<Processor> getAllProcessors() {
        return productionDao.getAllProcessors();
    }

    @Override
    public List<HardMemory> getAllHardMemory() {
        return productionDao.getAllHardMemory();
    }

    @Override
    public List<OperativeMemory> getAllOperativeMemory() {
        return productionDao.getAllOperativeMemory();
    }

    @Override
    public List<VideoMemory> getAllVideoMemory() {
        return productionDao.getAllVideoMemory();
    }

    @Override
    public List<Display> getAllDisplays() {
        return productionDao.getAllDisplays();
    }

    @Override
    public List<NotebookType> getAllNotebookTypes() {
        return productionDao.getAllNotebookTypes();
    }


}
