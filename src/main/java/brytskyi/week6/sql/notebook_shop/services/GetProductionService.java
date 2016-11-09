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
        productionDao.openConnection();
        List<NotebookModel> res = productionDao.getAllNotebookModels();
        productionDao.closeConnection();
        return res;
    }

    @Override
    public List<Processor> getAllProcessors() {
        productionDao.openConnection();
        List<Processor> res = productionDao.getAllProcessors();
        productionDao.closeConnection();
        return res;
    }

    @Override
    public List<HardMemory> getAllHardMemory() {
        productionDao.openConnection();
        List<HardMemory> res = productionDao.getAllHardMemory();
        productionDao.closeConnection();
        return res;
    }

    @Override
    public List<OperativeMemory> getAllOperativeMemory() {
        productionDao.openConnection();
        List<OperativeMemory> res = productionDao.getAllOperativeMemory();
        productionDao.closeConnection();
        return res;
    }

    @Override
    public List<VideoMemory> getAllVideoMemory() {
        productionDao.openConnection();
        List<VideoMemory> res = productionDao.getAllVideoMemory();
        productionDao.closeConnection();
        return res;
    }

    @Override
    public List<Display> getAllDisplays() {
        productionDao.openConnection();
        List<Display> res = productionDao.getAllDisplays();
        productionDao.closeConnection();
        return res;
    }

    @Override
    public List<NotebookType> getAllNotebookTypes() {
        productionDao.openConnection();
        List<NotebookType> res = productionDao.getAllNotebookTypes();
        productionDao.closeConnection();
        return res;
    }


}
