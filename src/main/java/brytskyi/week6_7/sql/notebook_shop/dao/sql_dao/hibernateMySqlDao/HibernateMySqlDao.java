package brytskyi.week6_7.sql.notebook_shop.dao.sql_dao.hibernateMySqlDao;

import brytskyi.week6_7.sql.notebook_shop.dao.IProductionDao;
import brytskyi.week6_7.sql.notebook_shop.dao.ISellingDAO;
import brytskyi.week6_7.sql.notebook_shop.dao.IUsersDao;
import brytskyi.week6_7.sql.notebook_shop.model.exceptions.dao_exceptions.NullFieldException;
import brytskyi.week6_7.sql.notebook_shop.model.production.*;
import brytskyi.week6_7.sql.notebook_shop.model.selling.Prodaja;
import brytskyi.week6_7.sql.notebook_shop.model.users.Buyer;
import brytskyi.week6_7.sql.notebook_shop.model.users.Seller;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class HibernateMySqlDao implements IProductionDao, ISellingDAO, IUsersDao {

    private EntityManagerFactory factory;

    public HibernateMySqlDao() {
        factory = Persistence.createEntityManagerFactory("shop_unit");
    }


    @Override
    public Prodaja addProdaja(int notebookID, int buyerID, int sellerID) {
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            Prodaja newProdaja = new Prodaja(manager.find(Buyer.class, buyerID),
                    manager.find(Seller.class, sellerID),
                    manager.find(NotebookForSail.class, notebookID));
            manager.persist(newProdaja);
            System.out.println(newProdaja);
            transaction.commit();
            return newProdaja;
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            manager.close();
        }
        return null;
    }

    @Override
    public List<Prodaja> getProdajasBuyer(int buyer, Date begin, Date end) {
        EntityManager manager = factory.createEntityManager();
        try {
            List<Prodaja> res = manager.createQuery("SELECT p FROM Prodaja p where " +
                    "p.buyer.id = :buyer and p.notebookForSail.dateStateChanged " +
                    ">= :begin and p.notebookForSail.dateStateChanged <= :end", Prodaja.class).setParameter("buyer", buyer)
                    .setParameter("begin", begin)
                    .setParameter("end", end).getResultList();
            return res;
        } finally {
            manager.close();
        }
    }

    @Override
    public List<Prodaja> getProdajasSeller(int seller, Date begin, Date end) {
        EntityManager manager = factory.createEntityManager();
        try {
            List<Prodaja> res = manager.createQuery("SELECT p FROM Prodaja p where " +
                    "p.seller.id = :seller and p.notebookForSail.dateStateChanged " +
                    ">= :begin and p.notebookForSail.dateStateChanged <= :end", Prodaja.class)
                    .setParameter("seller", seller)
                    .setParameter("begin", begin)
                    .setParameter("end", end).getResultList();
            return res;
        } finally {
            manager.close();
        }
    }

    @Override
    public NotebookModel addNotebookModel(NotebookModel model) {
        return addAndReturn(model);
    }

    @Override
    public NotebookModel getNoteBookModelById(int id) {
        return findById(id, NotebookModel.class);
    }

    @Override
    public List<NotebookModel> getAllNotebookModels() {
        EntityManager manager = factory.createEntityManager();
        try {
            return manager.createQuery("SELECT n FROM NotebookModel n", NotebookModel.class).getResultList();
        } finally {
            manager.close();
        }
    }


    @Override
    public NotebookModel removeNotebookModel(int id) {
        return removeAndReturn(NotebookModel.class, id);
    }

    @Override
    public Processor addProcessor(Processor processor) {
        return addAndReturn(processor);
    }

    @Override
    public Processor getProcessorById(int id) {
        return findById(id, Processor.class);
    }

    @Override
    public List<Processor> getAllProcessors() {
        EntityManager manager = factory.createEntityManager();
        try {
            return manager.createQuery("SELECT p FROM Processor p", Processor.class).getResultList();
        } finally {
            manager.close();
        }
    }

    @Override
    public Processor removeProcessor(int id) {
        return removeAndReturn(Processor.class, id);
    }

    @Override
    public HardMemory addHardMemory(HardMemory hardMemory) {
        return addAndReturn(hardMemory);
    }

    @Override
    public List<HardMemory> getAllHardMemory() {
        EntityManager manager = factory.createEntityManager();
        try {
            return manager.createQuery("SELECT n FROM HardMemory n", HardMemory.class).getResultList();
        } finally {
            manager.close();
        }
    }

    @Override
    public HardMemory getHardMemoryById(int id) {
        return findById(id, HardMemory.class);
    }

    @Override
    public HardMemory removeHardMemory(int id) {
        return removeAndReturn(HardMemory.class, id);
    }

    @Override
    public OperativeMemory addOperativeMemory(OperativeMemory operativeMemory) {
        return addAndReturn(operativeMemory);
    }

    @Override
    public OperativeMemory removeOperativeMemory(int id) {
        return removeAndReturn(OperativeMemory.class, id);
    }

    @Override
    public List<OperativeMemory> getAllOperativeMemory() {
        EntityManager manager = factory.createEntityManager();
        try {
            return manager.createQuery("SELECT n FROM OperativeMemory n", OperativeMemory.class).getResultList();
        } finally {
            manager.close();
        }
    }

    @Override
    public OperativeMemory getOperativeMemoryById(int id) {
        return findById(id, OperativeMemory.class);
    }

    @Override
    public VideoMemory addVideoMemory(VideoMemory videoMemory) {
        return addAndReturn(videoMemory);
    }

    @Override
    public List<VideoMemory> getAllVideoMemory() {
        EntityManager manager = factory.createEntityManager();
        try {
            return manager.createQuery("SELECT n FROM VideoMemory n", VideoMemory.class).getResultList();
        } finally {
            manager.close();
        }
    }

    @Override
    public VideoMemory removeVideoMemory(int id) {
        return removeAndReturn(VideoMemory.class, id);
    }

    @Override
    public VideoMemory getVideoMemoryById(int id) {
        return findById(id, VideoMemory.class);
    }

    @Override
    public Display addDisplay(Display display) {
        return addAndReturn(display);
    }

    @Override
    public List<Display> getAllDisplays() {
        EntityManager manager = factory.createEntityManager();
        try {
            return manager.createQuery("SELECT n FROM Display n", Display.class).getResultList();
        } finally {
            manager.close();
        }
    }

    @Override
    public Display removeDisplay(int id) {
        return removeAndReturn(Display.class, id);
    }

    @Override
    public Display getDisplayById(int id) {
        return findById(id, Display.class);
    }

    @Override
    public NotebookType addNotebookType(NotebookType notebook) throws NullFieldException {
        return addAndReturn(notebook);
    }

    @Override
    public NotebookType removeNotebookType(int id) {
        return removeAndReturn(NotebookType.class, id);
    }

    @Override
    public List<NotebookType> getAllNotebookTypes() {
        EntityManager manager = factory.createEntityManager();
        try {
            return manager.createQuery("SELECT n FROM NotebookType n", NotebookType.class).getResultList();
        } finally {
            manager.close();
        }
    }

    @Override
    public NotebookType getNotebookTypeById(int id) {
        return findById(id, NotebookType.class);
    }


    @Override
    public Partiya addPartiya(Partiya partiya) throws NullFieldException {
      return   addAndReturn(partiya);
    }

    @Override
    public NotebookForSail updateNotebook(int id, String state) throws NullFieldException {
        EntityManager manager = factory.createEntityManager();
        try {
            NotebookForSail founded = manager.find(NotebookForSail.class, id);
            founded.setState(NotebookState.valueOf(state));
            manager.merge(founded);
            return founded;
        } finally {
            manager.close();
        }
    }

    @Override
    public List<NotebookForSail> getNotebooks(String company, NotebookState state) {
        EntityManager manager = factory.createEntityManager();
        try {
            return manager.createQuery("SELECT n FROM NotebookForSail n " +
                    "where n.state = :state AND n.type.processor.company = :company", NotebookForSail.class)
                    .setParameter("state", state)
                    .setParameter("company", company)
                    .getResultList();
        } finally {
            manager.close();
        }
    }

    @Override
    public List<NotebookForSail> getNotebooks(int operativeMemory, NotebookState state) {
        EntityManager manager = factory.createEntityManager();
        try {
            return manager.createQuery("SELECT n FROM NotebookForSail n " +
                    "where n.state = :state AND n.type.operativeMemory.size = :memory", NotebookForSail.class)
                    .setParameter("state", state)
                    .setParameter("memory", operativeMemory)
                    .getResultList();
        } finally {
            manager.close();
        }
    }

    @Override
    public List<NotebookForSail> getNotebooks(int width, int heigth, NotebookState state) {
        EntityManager manager = factory.createEntityManager();
        try {
            return manager.createQuery("SELECT n FROM NotebookForSail n " +
                    "where n.state = :state AND n.type.display.width = :width and n.type.display.height = :height", NotebookForSail.class)
                    .setParameter("state", state)
                    .setParameter("width", width)
                    .setParameter("height", heigth)
                    .getResultList();
        } finally {
            manager.close();
        }
    }

    @Override
    public List<NotebookForSail> getNotebooks(NotebookState state, Date periodStateChangedStart, Date periodStateChangedEnd) {
        EntityManager manager = factory.createEntityManager();
        try {
            return manager.createQuery("SELECT n FROM NotebookForSail n " +
                    "where n.state = :state AND n.dateStateChanged >= :start and n.dateStateChanged <= :end", NotebookForSail.class)
                    .setParameter("state", state)
                    .setParameter("start", periodStateChangedStart)
                    .setParameter("end", periodStateChangedEnd)
                    .getResultList();
        } finally {
            manager.close();
        }
    }

    @Override
    public List<NotebookForSail> getNotebooksByKriteria(int hardMemory, int operativeMemory, int processor, int videoMemory, int model, int display, double pricemin, double priceMax) {
        List<NotebookForSail> res = new LinkedList<>();
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

        EntityManager manager = factory.createEntityManager();
        try {
            return manager.createNativeQuery("SELECT * FROM notebooks_for_sail n " +
                    "INNER JOIN notebook_types t ON n.notebook_type = t.id " +
                    "WHERE" + querryConditionBuilder.toString(), NotebookForSail.class)
                    .getResultList();
        } finally {
            manager.close();
        }
    }

    @Override
    public Buyer addBuyer(Buyer buyer) throws NullFieldException {
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.persist(buyer);
            transaction.commit();
            return buyer;
        } finally {
            manager.close();
        }
    }

    @Override
    public List<Buyer> getAllBuyers() {
        EntityManager manager = factory.createEntityManager();
        try {
            return manager.createQuery("SELECT n FROM Buyer n", Buyer.class).getResultList();
        } finally {
            manager.close();
        }
    }

    @Override
    public Seller addSeller(Seller seller) throws NullFieldException {
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.persist(seller);
            transaction.commit();
            return seller;
        } finally {
            manager.close();
        }
    }

    @Override
    public List<Seller> getSellers(boolean working) {
        EntityManager manager = factory.createEntityManager();
        try {
            return manager.createQuery("SELECT n FROM Seller n where n.isWorking = true", Seller.class).getResultList();
        } finally {
            manager.close();
        }
    }

    @Override
    public Seller updateSeller(int sellerID, boolean isWorking) {
        EntityManager manager = factory.createEntityManager();
        try {
            Seller founded = manager.find(Seller.class, sellerID);
            founded.setWorking(isWorking);
            manager.merge(founded);
            return founded;
        } finally {
            manager.close();
        }
    }

    @Override
    public Buyer getBuyer(String phone) {
        EntityManager manager = factory.createEntityManager();
        try {
            return manager.createQuery("SELECT b FROM Buyer b where b.contacts.phone = :phone", Buyer.class)
                    .setParameter("phone", phone).getSingleResult();
        } finally {
            manager.close();
        }
    }

    @Override
    public Seller getSeller(String name, String pass) {
        EntityManager manager = factory.createEntityManager();
        try {
            return manager.createQuery("SELECT s FROM Seller s where s.contacts.name = :name and s.pass = :pass", Seller.class)
                    .setParameter("name", name)
                    .setParameter("pass", pass)
                    .getSingleResult();
        } finally {
            manager.close();
        }
    }


    private <T> T addAndReturn(T entity) {
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.persist(entity);
            transaction.commit();
            return entity;
        } finally {
            manager.close();
        }
    }

    private <T> T removeAndReturn(Class<T> entityClass, int id) {
        EntityManager manager = factory.createEntityManager();
        try {
            T founded = manager.find(entityClass, id);
            if (founded != null) {
                EntityTransaction transaction = manager.getTransaction();
                transaction.begin();
                manager.remove(founded);
                transaction.commit();
            }
            return founded;
        } finally {
            manager.close();
        }
    }

    private <T> T findById(int id, Class<T> tClass) {
        EntityManager manager = factory.createEntityManager();
        try {
            return manager.find(tClass, id);
        } finally {
            manager.close();
        }
    }
}
