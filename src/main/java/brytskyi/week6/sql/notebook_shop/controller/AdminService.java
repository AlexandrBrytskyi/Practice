package brytskyi.week6.sql.notebook_shop.controller;


import brytskyi.week6.sql.notebook_shop.controller.additional.Context;
import brytskyi.week6.sql.notebook_shop.dao.IProductionDao;
import brytskyi.week6.sql.notebook_shop.dao.ISellingDAO;
import brytskyi.week6.sql.notebook_shop.dao.IUsersDao;
import brytskyi.week6.sql.notebook_shop.model.exceptions.dao_exceptions.NullFieldException;
import brytskyi.week6.sql.notebook_shop.model.production.*;
import brytskyi.week6.sql.notebook_shop.model.selling.Prodaja;
import brytskyi.week6.sql.notebook_shop.model.users.Buyer;
import brytskyi.week6.sql.notebook_shop.model.users.Seller;

import java.util.Date;
import java.util.List;

public class AdminService implements IAdminService {

    private Context context = Context.getCtxt();
    private IProductionDao productionDao;
    private IUsersDao usersDao;
    private ISellingDAO sellingDAO;


    @Override
    public NotebookModel addNotebookModel(NotebookModel model) {
        productionDao.openConnection();
        NotebookModel res = productionDao.addNotebookModel(model);
        productionDao.closeConnection();
        return res;
    }

    @Override
    public NotebookModel getNoteBookModelById(int id) {
        productionDao.openConnection();
        NotebookModel res = productionDao.getNoteBookModelById(id);
        productionDao.closeConnection();
        return res;
    }

    @Override
    public List<NotebookModel> getAllNotebookModels() {
        productionDao.openConnection();
        List<NotebookModel> res = productionDao.getAllNotebookModels();
        productionDao.closeConnection();
        return res;
    }

    @Override
    public NotebookModel removeNotebookModel(int id) {
        productionDao.openConnection();
        NotebookModel model = productionDao.removeNotebookModel(id);
        productionDao.closeConnection();
        return model;
    }

    @Override
    public Processor addProcessor(Processor processor) {
        productionDao.openConnection();
        Processor res = productionDao.addProcessor(processor);
        productionDao.closeConnection();
        return res;
    }

    @Override
    public Processor getProcessorById(int id) {
        productionDao.openConnection();
        Processor res = productionDao.getProcessorById(id);
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
    public Processor removeProcessor(int id) {
        productionDao.openConnection();
        Processor res = productionDao.removeProcessor(id);
        productionDao.closeConnection();
        return res;
    }

    @Override
    public HardMemory addHardMemory(HardMemory hardMemory) {
        productionDao.openConnection();
        HardMemory res = productionDao.addHardMemory(hardMemory);
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
    public HardMemory getHardMemoryById(int id) {
        productionDao.openConnection();
        HardMemory res = productionDao.getHardMemoryById(id);
        productionDao.closeConnection();
        return res;
    }

    @Override
    public HardMemory removeHardMemory(int id) {
        productionDao.openConnection();
        HardMemory res = productionDao.removeHardMemory(id);
        productionDao.closeConnection();
        return res;
    }

    @Override
    public OperativeMemory addOperativeMemory(OperativeMemory operativeMemory) {
        productionDao.openConnection();
        OperativeMemory res = productionDao.addOperativeMemory(operativeMemory);
        productionDao.closeConnection();
        return res;
    }

    @Override
    public OperativeMemory removeOperativeMemory(int id) {
        productionDao.openConnection();
        OperativeMemory res = productionDao.removeOperativeMemory(id);
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
    public OperativeMemory getOperativeMemoryById(int id) {
        productionDao.openConnection();
        OperativeMemory res = productionDao.getOperativeMemoryById(id);
        productionDao.closeConnection();
        return res;
    }

    @Override
    public VideoMemory addVideoMemory(VideoMemory videoMemory) {
        productionDao.openConnection();
        VideoMemory res = productionDao.addVideoMemory(videoMemory);
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
    public VideoMemory removeVideoMemory(int id) {
        productionDao.openConnection();
        VideoMemory res = productionDao.removeVideoMemory(id);
        productionDao.closeConnection();
        return res;
    }

    @Override
    public VideoMemory getVideoMemoryById(int id) {
        productionDao.openConnection();
        VideoMemory res = productionDao.getVideoMemoryById(id);
        productionDao.closeConnection();
        return res;
    }

    @Override
    public Display addDisplay(Display display) {
        productionDao.openConnection();
        Display res = productionDao.addDisplay(display);
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
    public Display removeDisplay(int id) {
        productionDao.openConnection();
        Display res = productionDao.removeDisplay(id);
        productionDao.closeConnection();
        return res;
    }

    @Override
    public Display getDisplayById(int id) {
        productionDao.openConnection();
        Display res = productionDao.getDisplayById(id);
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

    @Override
    public Partiya priniatPartiyu(List<NotebookForSail> notebooks, int notebookTypeId) throws NullFieldException {
        productionDao.openConnection();
        Partiya partiya = new Partiya(productionDao.getNotebookTypeById(notebookTypeId),
                notebooks);
        Partiya res = productionDao.addPartiya(partiya);
        productionDao.closeConnection();
        return res;
    }


    // TODO: 08.11.16 maybe cache?????????
    @Override
    public NotebookType createNoteBookType(String desc, double price, int modelId, int processoId, int hardMemId, int operMemId, int videoMemId, int displayId) throws NullFieldException {
        productionDao.openConnection();
        NotebookType added = productionDao.addNotebookType(new NotebookType(
                desc, productionDao.getHardMemoryById(hardMemId),
                productionDao.getOperativeMemoryById(operMemId),
                productionDao.getProcessorById(processoId),
                productionDao.getVideoMemoryById(videoMemId),
                productionDao.getNoteBookModelById(modelId),
                productionDao.getDisplayById(displayId),
                price
        ));
        return added;
    }

    @Override
    public NotebookType getNotebookTypeById(int id) {
        productionDao.openConnection();
        NotebookType res = productionDao.getNotebookTypeById(id);
        productionDao.closeConnection();
        return res;
    }

    @Override
    public NotebookType removeNotebookType(int id) {
        productionDao.openConnection();
        NotebookType res = productionDao.removeNotebookType(id);
        productionDao.closeConnection();
        return res;
    }

    @Override
    public List<NotebookType> getAllNoteBookTypes() {
        productionDao.openConnection();
        List<NotebookType> res = productionDao.getAllNotebookTypes();
        productionDao.closeConnection();
        return res;
    }

    @Override
    public NotebookForSail sellNoteBook(int notebookId) throws NullFieldException {
        productionDao.openConnection();
        NotebookForSail res = productionDao.updateNotebook(notebookId, NotebookState.SELLED.toString());
        productionDao.closeConnection();
        return res;
    }

    @Override
    public NotebookForSail spisatNotebook(int id) throws NullFieldException {
        productionDao.openConnection();
        NotebookForSail res = productionDao.updateNotebook(id, NotebookState.SPISAN.toString());
        productionDao.closeConnection();
        return res;
    }

    @Override
    public List<NotebookForSail> getSelledNotebooks(Date start, Date end) {
        productionDao.openConnection();
        List<NotebookForSail> res = productionDao.getNotebooks(NotebookState.SELLED, start, end);
        productionDao.closeConnection();
        return res;
    }

    @Override
    public List<NotebookForSail> getSpisaniNotebooks(Date start, Date end) {
        productionDao.openConnection();
        List<NotebookForSail> res = productionDao.getNotebooks(NotebookState.SPISAN, start, end);
        productionDao.closeConnection();
        return res;
    }

    @Override
    public Buyer addBuyer(Buyer buyer) throws NullFieldException {
        usersDao.openConnection();
        Buyer res = usersDao.addBuyer(buyer);
        usersDao.closeConnection();
        return res;
    }

    @Override
    public List<Buyer> getBuyers() {
        usersDao.openConnection();
        List<Buyer> res = usersDao.getAllBuyers();
        usersDao.closeConnection();
        return res;
    }

    @Override
    public List<Seller> getSellers(boolean working) {
        usersDao.openConnection();
        List<Seller> res = usersDao.getSellers(working);
        usersDao.closeConnection();
        return res;
    }

    @Override
    public List<Prodaja> getNoteBooksSelled(int buyer, Date begin, Date end) {
        sellingDAO.openConnection();
        List<Prodaja> res = sellingDAO.getProdajasBuyer(buyer, begin, end);
        sellingDAO.closeConnection();
        return res;
    }

    @Override
    public List<Prodaja> getNoteBooksBuyed(int seller, Date begin, Date end) {
        sellingDAO.openConnection();
        List<Prodaja> res = sellingDAO.getProdajasSeller(seller, begin, end);
        sellingDAO.closeConnection();
        return res;
    }
}
