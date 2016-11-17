package brytskyi.week8.spring.notebook_shop.dao.sql_dao.hibernateMySqlDao;

import brytskyi.week8.spring.notebook_shop.dao.IProductionDao;
import brytskyi.week8.spring.notebook_shop.dao.ISellingDAO;
import brytskyi.week8.spring.notebook_shop.dao.IUsersDao;
import brytskyi.week8.spring.notebook_shop.model.exceptions.dao_exceptions.NullFieldException;
import brytskyi.week8.spring.notebook_shop.model.production.*;
import brytskyi.week8.spring.notebook_shop.model.selling.Prodaja;
import brytskyi.week8.spring.notebook_shop.model.users.Buyer;
import brytskyi.week8.spring.notebook_shop.model.users.Seller;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Component()
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class HibernateMySqlDao implements IProductionDao, ISellingDAO, IUsersDao {

    @PersistenceContext()
    private EntityManager manager;

    public HibernateMySqlDao() {
    }


    @Override
    @Transactional
    public Prodaja addProdaja(int notebookID, int buyerID, int sellerID) {

        Prodaja newProdaja = new Prodaja(manager.find(Buyer.class, buyerID),
                manager.find(Seller.class, sellerID),
                manager.find(NotebookForSail.class, notebookID));
        addAndReturn(newProdaja);

        return newProdaja;
    }

    @Override
    public List<Prodaja> getProdajasBuyer(int buyer, Date begin, Date end) {

        List<Prodaja> res = manager.createQuery("SELECT p FROM Prodaja p where " +
                "p.buyer.id = :buyer and p.notebookForSail.dateStateChanged " +
                ">= :begin and p.notebookForSail.dateStateChanged <= :end", Prodaja.class).setParameter("buyer", buyer)
                .setParameter("begin", begin)
                .setParameter("end", end).getResultList();

        return res;
    }

    @Override
    public List<Prodaja> getProdajasSeller(int seller, Date begin, Date end) {

        List<Prodaja> res = manager.createQuery("SELECT p FROM Prodaja p where " +
                "p.seller.id = :seller and p.notebookForSail.dateStateChanged " +
                ">= :begin and p.notebookForSail.dateStateChanged <= :end", Prodaja.class)
                .setParameter("seller", seller)
                .setParameter("begin", begin)
                .setParameter("end", end).getResultList();

        return res;
    }

    @Override
    @Transactional
    public NotebookModel addNotebookModel(NotebookModel model) {
        return addAndReturn(model);
    }

    @Override
    public NotebookModel getNoteBookModelById(int id) {
        return findById(id, NotebookModel.class);
    }

    @Override
    public List<NotebookModel> getAllNotebookModels() {
        return manager.createQuery("SELECT n FROM NotebookModel n", NotebookModel.class).getResultList();
    }


    @Override
    @Transactional
    public NotebookModel removeNotebookModel(int id) {
        return removeAndReturn(NotebookModel.class, id);
    }

    @Override
    @Transactional
    public Processor addProcessor(Processor processor) {
        return addAndReturn(processor);
    }

    @Override
    public Processor getProcessorById(int id) {
        return findById(id, Processor.class);
    }

    @Override
    public List<Processor> getAllProcessors() {
        return manager.createQuery("SELECT p FROM Processor p", Processor.class).getResultList();
    }

    @Override
    @Transactional
    public Processor removeProcessor(int id) {
        return removeAndReturn(Processor.class, id);
    }

    @Override
    @Transactional
    public HardMemory addHardMemory(HardMemory hardMemory) {
        return addAndReturn(hardMemory);
    }

    @Override
    public List<HardMemory> getAllHardMemory() {
        return manager.createQuery("SELECT n FROM HardMemory n", HardMemory.class).getResultList();
    }

    @Override
    public HardMemory getHardMemoryById(int id) {
        return findById(id, HardMemory.class);
    }

    @Override
    @Transactional
    public HardMemory removeHardMemory(int id) {
        return removeAndReturn(HardMemory.class, id);
    }

    @Override
    @Transactional
    public OperativeMemory addOperativeMemory(OperativeMemory operativeMemory) {
        return addAndReturn(operativeMemory);
    }

    @Override
    @Transactional
    public OperativeMemory removeOperativeMemory(int id) {
        return removeAndReturn(OperativeMemory.class, id);
    }

    @Override
    public List<OperativeMemory> getAllOperativeMemory() {
        return manager.createQuery("SELECT n FROM OperativeMemory n", OperativeMemory.class).getResultList();
    }

    @Override
    public OperativeMemory getOperativeMemoryById(int id) {
        return findById(id, OperativeMemory.class);
    }

    @Override
    @Transactional
    public VideoMemory addVideoMemory(VideoMemory videoMemory) {
        return addAndReturn(videoMemory);
    }

    @Override
    public List<VideoMemory> getAllVideoMemory() {
        return manager.createQuery("SELECT n FROM VideoMemory n", VideoMemory.class).getResultList();
    }

    @Override
    @Transactional
    public VideoMemory removeVideoMemory(int id) {
        return removeAndReturn(VideoMemory.class, id);
    }

    @Override
    public VideoMemory getVideoMemoryById(int id) {
        return findById(id, VideoMemory.class);
    }

    @Override
    @Transactional
    public Display addDisplay(Display display) {
        return addAndReturn(display);
    }

    @Override
    public List<Display> getAllDisplays() {
        return manager.createQuery("SELECT n FROM Display n", Display.class).getResultList();
    }

    @Override
    @Transactional
    public Display removeDisplay(int id) {
        return removeAndReturn(Display.class, id);
    }

    @Override
    public Display getDisplayById(int id) {
        return findById(id, Display.class);
    }

    @Override
    @Transactional
    public NotebookType addNotebookType(NotebookType notebook) throws NullFieldException {
        return addAndReturn(notebook);
    }

    @Override
    @Transactional
    public NotebookType removeNotebookType(int id) {
        return removeAndReturn(NotebookType.class, id);
    }

    @Override
    public List<NotebookType> getAllNotebookTypes() {
        return manager.createQuery("SELECT n FROM NotebookType n", NotebookType.class).getResultList();
    }

    @Override
    public NotebookType getNotebookTypeById(int id) {
        return findById(id, NotebookType.class);
    }


    @Override
    @Transactional
    public Partiya addPartiya(Partiya partiya) throws NullFieldException {
        return addAndReturn(partiya);
    }

    @Override
    @Transactional
    public NotebookForSail updateNotebook(int id, String state) throws NullFieldException {
        NotebookForSail founded = manager.find(NotebookForSail.class, id);
        founded.setState(NotebookState.valueOf(state));
        manager.merge(founded);
        return founded;
    }

    @Override
    public List<NotebookForSail> getNotebooks(String company, NotebookState state) {
        return manager.createQuery("SELECT n FROM NotebookForSail n " +
                "where n.state = :state AND n.type.processor.company = :company", NotebookForSail.class)
                .setParameter("state", state)
                .setParameter("company", company)
                .getResultList();
    }

    @Override
    public List<NotebookForSail> getNotebooks(int operativeMemory, NotebookState state) {
        return manager.createQuery("SELECT n FROM NotebookForSail n " +
                "where n.state = :state AND n.type.operativeMemory.size = :memory", NotebookForSail.class)
                .setParameter("state", state)
                .setParameter("memory", operativeMemory)
                .getResultList();
    }

    @Override
    public List<NotebookForSail> getNotebooks(int width, int heigth, NotebookState state) {
        return manager.createQuery("SELECT n FROM NotebookForSail n " +
                "where n.state = :state AND n.type.display.width = :width and n.type.display.height = :height", NotebookForSail.class)
                .setParameter("state", state)
                .setParameter("width", width)
                .setParameter("height", heigth)
                .getResultList();
    }

    @Override
    public List<NotebookForSail> getNotebooks(NotebookState state, Date periodStateChangedStart, Date periodStateChangedEnd) {
        return manager.createQuery("SELECT n FROM NotebookForSail n " +
                "where n.state = :state AND n.dateStateChanged >= :start and n.dateStateChanged <= :end", NotebookForSail.class)
                .setParameter("state", state)
                .setParameter("start", periodStateChangedStart)
                .setParameter("end", periodStateChangedEnd)
                .getResultList();
    }

    @Override
    public List<NotebookForSail> getNotebooksByKriteria(int hardMemory, int operativeMemory, int processor, int videoMemory, int model, int display, double pricemin, double priceMax) {
        StringBuilder querryConditionBuilder = new StringBuilder();
        querryConditionBuilder.append(hardMemory != 0 ? " t.hard_memory = " + hardMemory + " AND" : null);
        querryConditionBuilder.append(operativeMemory != 0 ? " t.operative_memory = " + operativeMemory + " AND" : null);
        querryConditionBuilder.append(videoMemory != 0 ? " t.video_memory = " + videoMemory + " AND" : null);
        querryConditionBuilder.append(model != 0 ? " t.model = " + model + " AND" : null);
        querryConditionBuilder.append(display != 0 ? " t.display = " + display + " AND" : null);
        if (pricemin != 0 && priceMax != 0) {
            querryConditionBuilder.append(" t.price BETWEEN " + pricemin + " AND " + priceMax + " AND ");
        } else if (priceMax != 0) {
            querryConditionBuilder.append(" t.price < " + priceMax);
        } else {
            querryConditionBuilder.append(" t.price > " + pricemin);
        }

        return manager.createNativeQuery("SELECT * FROM notebooks_for_sail n " +
                "INNER JOIN notebook_types t ON n.notebook_type = t.id " +
                "WHERE" + querryConditionBuilder.toString(), NotebookForSail.class)
                .getResultList();
    }

    @Override
    @Transactional
    public Buyer addBuyer(Buyer buyer) throws NullFieldException {
        return addAndReturn(buyer);
    }

    @Override
    public List<Buyer> getAllBuyers() {
        return manager.createQuery("SELECT n FROM Buyer n", Buyer.class).getResultList();
    }

    @Override
    @Transactional
    public Seller addSeller(Seller seller) throws NullFieldException {
        return addAndReturn(seller);
    }

    @Override
    public List<Seller> getSellers(boolean working) {
        return manager.createQuery("SELECT n FROM Seller n where n.isWorking = true", Seller.class).getResultList();
    }

    @Override
    @Transactional
    public Seller updateSeller(int sellerID, boolean isWorking) {
        Seller founded = manager.find(Seller.class, sellerID);
        founded.setWorking(isWorking);
        manager.merge(founded);
        return founded;
    }

    @Override
    public Buyer getBuyer(String phone) {
        return manager.createQuery("SELECT b FROM Buyer b where b.contacts.phone = :phone", Buyer.class)
                .setParameter("phone", phone).getSingleResult();
    }

    @Override
    public Seller getSeller(String name, String pass) {
        return manager.createQuery("SELECT s FROM Seller s where s.contacts.name = :name and s.pass = :pass", Seller.class)
                .setParameter("name", name)
                .setParameter("pass", pass)
                .getSingleResult();
    }

    private <T> T addAndReturn(T entity) {
        manager.persist(entity);
        return entity;
    }

    private <T> T removeAndReturn(Class<T> entityClass, int id) {
        T founded = manager.find(entityClass, id);
        if (founded != null) {
            manager.remove(founded);
        }
        return founded;
    }


    private <T> T findById(int id, Class<T> tClass) {
        return manager.find(tClass, id);
    }


}
