package brytskyi.week8.spring.notebook_shop.services;


import brytskyi.week8.spring.notebook_shop.dao.IProductionDao;
import brytskyi.week8.spring.notebook_shop.dao.ISellingDAO;
import brytskyi.week8.spring.notebook_shop.dao.IUsersDao;
import brytskyi.week8.spring.notebook_shop.model.exceptions.dao_exceptions.NullFieldException;
import brytskyi.week8.spring.notebook_shop.model.production.*;
import brytskyi.week8.spring.notebook_shop.model.selling.Prodaja;
import brytskyi.week8.spring.notebook_shop.model.users.Buyer;
import brytskyi.week8.spring.notebook_shop.model.users.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdminService extends GetProductionService implements IAdminService {


    @Autowired
    private IProductionDao productionDao;

    @Autowired
    private IUsersDao usersDao;

    @Autowired
    private ISellingDAO sellingDAO;


    public AdminService() {
    }

    public AdminService(IProductionDao productionDao, IUsersDao usersDao, ISellingDAO sellingDAO) {
        this.productionDao = productionDao;
        this.usersDao = usersDao;
        this.sellingDAO = sellingDAO;
    }

    @Override
    public NotebookModel addNotebookModel(NotebookModel model) {
        return productionDao.addNotebookModel(model);
    }

    @Override
    public NotebookModel getNoteBookModelById(int id) {
        return productionDao.getNoteBookModelById(id);
    }


    @Override
    public NotebookModel removeNotebookModel(int id) {
        return productionDao.removeNotebookModel(id);
    }

    @Override
    public Processor addProcessor(Processor processor) {
        return productionDao.addProcessor(processor);
    }

    @Override
    public Processor getProcessorById(int id) {
        return productionDao.getProcessorById(id);
    }


    @Override
    public Processor removeProcessor(int id) {
        return productionDao.removeProcessor(id);
    }

    @Override
    public HardMemory addHardMemory(HardMemory hardMemory) {
        return productionDao.addHardMemory(hardMemory);
    }


    @Override
    public HardMemory getHardMemoryById(int id) {
        return productionDao.getHardMemoryById(id);
    }

    @Override
    public HardMemory removeHardMemory(int id) {
        return productionDao.removeHardMemory(id);
    }

    @Override
    public OperativeMemory addOperativeMemory(OperativeMemory operativeMemory) {
        return productionDao.addOperativeMemory(operativeMemory);
    }

    @Override
    public OperativeMemory removeOperativeMemory(int id) {
        return productionDao.removeOperativeMemory(id);
    }


    @Override
    public OperativeMemory getOperativeMemoryById(int id) {
        return productionDao.getOperativeMemoryById(id);
    }

    @Override
    public VideoMemory addVideoMemory(VideoMemory videoMemory) {
        return productionDao.addVideoMemory(videoMemory);
    }


    @Override
    public VideoMemory removeVideoMemory(int id) {
        return productionDao.removeVideoMemory(id);
    }

    @Override
    public VideoMemory getVideoMemoryById(int id) {
        return productionDao.getVideoMemoryById(id);
    }

    @Override
    public Display addDisplay(Display display) {
        return productionDao.addDisplay(display);
    }

    @Override
    public Display removeDisplay(int id) {
        return productionDao.removeDisplay(id);
    }

    @Override
    public Display getDisplayById(int id) {
        return productionDao.getDisplayById(id);
    }


    @Override
    public Partiya priniatPartiyu(List<NotebookForSail> notebooks, int notebookTypeId) throws NullFieldException {
        Partiya partiya = new Partiya(productionDao.getNotebookTypeById(notebookTypeId),
                notebooks);
        for (NotebookForSail notebook : notebooks) {
            notebook.setPartiya(partiya);
        }
        return productionDao.addPartiya(partiya);
    }


    // TODO: 08.11.16 maybe cache?????????
    @Override
    public NotebookType createNoteBookType(String desc, double price, int modelId, int processoId, int hardMemId, int operMemId, int videoMemId, int displayId) throws NullFieldException {

        return productionDao.addNotebookType(new NotebookType(
                desc, productionDao.getHardMemoryById(hardMemId),
                productionDao.getOperativeMemoryById(operMemId),
                productionDao.getProcessorById(processoId),
                productionDao.getVideoMemoryById(videoMemId),
                productionDao.getNoteBookModelById(modelId),
                productionDao.getDisplayById(displayId),
                price
        ));

    }

    @Override
    public NotebookType getNotebookTypeById(int id) {
        return productionDao.getNotebookTypeById(id);
    }

    @Override
    public NotebookType removeNotebookType(int id) {
        return productionDao.removeNotebookType(id);
    }


    @Override
    public NotebookForSail spisatNotebook(int id) throws NullFieldException {
        return productionDao.updateNotebook(id, NotebookState.SPISAN.toString());
    }

    @Override
    public List<NotebookForSail> getSelledNotebooks(Date start, Date end) {
        return productionDao.getNotebooks(NotebookState.SELLED, start, end);
    }

    @Override
    public List<NotebookForSail> getSpisaniNotebooks(Date start, Date end) {
        return productionDao.getNotebooks(NotebookState.SPISAN, start, end);
    }

    @Override
    public Buyer addBuyer(Buyer buyer) throws NullFieldException {
        return usersDao.addBuyer(buyer);
    }

    @Override
    public List<Buyer> getBuyers() {
        return usersDao.getAllBuyers();
    }

    @Override
    public List<Seller> getSellers(boolean working) {
        return usersDao.getSellers(working);
    }

    @Override
    public List<Prodaja> getNoteBooksSelled(int buyer, Date begin, Date end) {
        return sellingDAO.getProdajasBuyer(buyer, begin, end);
    }

    @Override
    public List<Prodaja> getNoteBooksBuyed(int seller, Date begin, Date end) {
        return sellingDAO.getProdajasSeller(seller, begin, end);
    }
}
