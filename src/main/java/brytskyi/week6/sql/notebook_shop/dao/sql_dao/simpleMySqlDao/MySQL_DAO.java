package brytskyi.week6.sql.notebook_shop.dao.sql_dao.simpleMySqlDao;

import brytskyi.week6.sql.notebook_shop.dao.IProductionDao;
import brytskyi.week6.sql.notebook_shop.dao.ISellingDAO;
import brytskyi.week6.sql.notebook_shop.dao.IUsersDao;
import brytskyi.week6.sql.notebook_shop.model.exceptions.dao_exceptions.NullFieldException;
import brytskyi.week6.sql.notebook_shop.model.production.*;
import brytskyi.week6.sql.notebook_shop.model.selling.Prodaja;
import brytskyi.week6.sql.notebook_shop.model.users.Buyer;
import brytskyi.week6.sql.notebook_shop.model.users.Seller;


import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created by alexandr on 05.11.16.
 */
public class MySQL_DAO implements IProductionDao, ISellingDAO, IUsersDao {

    public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/notebookDB";
    public static final String USER = "root";
    public static final String PASSWORD = "";
    private IMySqlDaoWithConnection mySqlDaoWithConnection;

    public MySQL_DAO(IMySqlDaoWithConnection mySqlDaoWithConnection) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.mySqlDaoWithConnection = mySqlDaoWithConnection;
    }


    @Override
    public NotebookModel addNotebookModel(NotebookModel model) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.addNotebookModel(model, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public NotebookModel getNoteBookModelById(int id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.getNoteBookModelById(id, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<NotebookModel> getAllNotebookModels() {
        List<NotebookModel> notebookModels;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.getAllNotebookModels(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public NotebookModel removeNotebookModel(int id) {
        NotebookModel removed = getNoteBookModelById(id);
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.removeNotebookModel(id, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Processor addProcessor(Processor processor) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.addProcessor(processor, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Processor getProcessorById(int id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.getProcessorById(id, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Processor> getAllProcessors() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.getAllProcessors(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Processor removeProcessor(int id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.removeProcessor(id, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public HardMemory addHardMemory(HardMemory hardMemory) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.addHardMemory(hardMemory, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public HardMemory getHardMemoryById(int id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.getHardMemoryById(id, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<HardMemory> getAllHardMemory() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.getAllHardMemory(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public HardMemory removeHardMemory(int id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.removeHardMemory(id, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public OperativeMemory addOperativeMemory(OperativeMemory operativeMemory) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.addOperativeMemory(operativeMemory, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public OperativeMemory getOperativeMemoryById(int id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.getOperativeMemoryById(id, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<OperativeMemory> getAllOperativeMemory() {
        List<OperativeMemory> operativeMemories;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.getAllOperativeMemory(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public OperativeMemory removeOperativeMemory(int id) {
        OperativeMemory removed = getOperativeMemoryById(id);
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.removeOperativeMemory(id, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public VideoMemory addVideoMemory(VideoMemory videoMemory) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.addVideoMemory(videoMemory, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public VideoMemory getVideoMemoryById(int id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.getVideoMemoryById(id, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<VideoMemory> getAllVideoMemory() {
        List<VideoMemory> videoMemories;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.getAllVideoMemory(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public VideoMemory removeVideoMemory(int id) {
        VideoMemory removed = getVideoMemoryById(id);
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.removeVideoMemory(id, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public Display addDisplay(Display display) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.addDisplay(display, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Display getDisplayById(int id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.getDisplayById(id, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Display> getAllDisplays() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.getAllDisplays(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Display removeDisplay(int id) {
        Display removed = getDisplayById(id);
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.removeDisplay(id, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public NotebookType addNotebookType(NotebookType notebook) throws NullFieldException {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.addNotebookType(notebook, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (NullPointerException e) {
            throw new NullFieldException(e.getMessage());
        }
    }

    @Override
    public NotebookType removeNotebookType(int id) {
        NotebookType removed = getNotebookTypeById(id);
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.removeNotebookType(id, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<NotebookType> getAllNotebookTypes() {
        List<NotebookType> notebookTypes;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.getAllNotebookTypes(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public NotebookType getNotebookTypeById(int id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.getNotebookTypeById(id, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Partiya addPartiya(Partiya partiya) throws NullFieldException {
        if (partiya == null) throw new NullPointerException("Partiya is null!");
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.addPartiya(partiya, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public NotebookForSail updateNotebook(int id, String state) throws NullFieldException {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.updateNotebook(id, state, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw new NullFieldException(e.getMessage());
        }
    }


    @Override
    public List<NotebookForSail> getNotebooks(String company, NotebookState state) {
        List<NotebookForSail> res = new LinkedList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.getNotebooks(company, state, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<NotebookForSail> getNotebooks(int operativeMemory, NotebookState state) {
        List<NotebookForSail> res = new LinkedList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.getNotebooks(operativeMemory, state, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<NotebookForSail> getNotebooks(int width, int heigth, NotebookState state) {
        List<NotebookForSail> res = new LinkedList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.getNotebooks(width, heigth, state, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<NotebookForSail> getNotebooks(NotebookState state, Date periodStateChangedStart, Date periodStateChangedEnd) {
        List<NotebookForSail> res = new LinkedList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.getNotebooks(state, periodStateChangedStart, periodStateChangedEnd, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<NotebookForSail> getNotebooksByKriteria(int hardMemory,
                                                        int operativeMemory, int processor,
                                                        int videoMemory, int model, int display,
                                                        double pricemin, double priceMax) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.getNotebooksByKriteria(hardMemory, operativeMemory, processor,
                    videoMemory, model, display, pricemin, priceMax, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Buyer addBuyer(Buyer buyer) throws NullFieldException {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.addBuyer(buyer, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public List<Buyer> getAllBuyers() {
        List<Buyer> buyers = new LinkedList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.getAllBuyers(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Seller addSeller(Seller seller) throws NullFieldException {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.addSeller(seller, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Seller> getSellers(boolean working) {
        List<Seller> sellers = new LinkedList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.getSellers(working, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Seller updateSeller(int sellerID, boolean isWorking) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.updateSeller(sellerID, isWorking, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Buyer getBuyer(String phone) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.getBuyer(phone, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Seller getSeller(String name, String pass) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.getSeller(name, pass, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Prodaja addProdaja(int notebookID, int buyerID, int sellerID) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.addProdaja(notebookID, buyerID, sellerID, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<Prodaja> getProdajasBuyer(int buyer, Date begin, Date end) {
        List<Prodaja> prodajas = new LinkedList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.getProdajasBuyer(buyer, begin, end, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Prodaja> getProdajasSeller(int seller, Date begin, Date end) {
        List<Prodaja> prodajas = new LinkedList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return mySqlDaoWithConnection.getProdajasSeller(seller, begin, end, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
