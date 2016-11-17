package brytskyi.week8.spring.notebook_shop.services;

import brytskyi.week8.spring.notebook_shop.dao.IProductionDao;
import brytskyi.week8.spring.notebook_shop.model.production.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetProductionService implements IGetProductionService {

    @Autowired
    private IProductionDao productionDao;

    public GetProductionService() {
    }

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
