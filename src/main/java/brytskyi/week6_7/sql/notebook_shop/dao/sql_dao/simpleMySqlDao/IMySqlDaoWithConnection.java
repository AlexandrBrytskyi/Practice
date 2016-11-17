package brytskyi.week6_7.sql.notebook_shop.dao.sql_dao.simpleMySqlDao;

import brytskyi.week6_7.sql.notebook_shop.model.exceptions.dao_exceptions.NullFieldException;
import brytskyi.week6_7.sql.notebook_shop.model.production.*;
import brytskyi.week6_7.sql.notebook_shop.model.selling.Prodaja;
import brytskyi.week6_7.sql.notebook_shop.model.users.Buyer;
import brytskyi.week6_7.sql.notebook_shop.model.users.Seller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by alexandr on 10.11.16.
 */
public interface IMySqlDaoWithConnection {

    NotebookModel addNotebookModel(NotebookModel model, Connection conn) throws SQLException;

    NotebookModel getNoteBookModelById(int id, Connection conn) throws SQLException;

    List<NotebookModel> getAllNotebookModels( Connection conn) throws SQLException;

    NotebookModel removeNotebookModel(int id, Connection conn) throws SQLException;

    Processor addProcessor(Processor processor, Connection conn) throws SQLException;

    Processor getProcessorById(int id, Connection conn) throws SQLException;

    List<Processor> getAllProcessors( Connection conn) throws SQLException;

    Processor removeProcessor(int id, Connection conn) throws SQLException;

    HardMemory addHardMemory(HardMemory hardMemory, Connection conn) throws SQLException;

    List<HardMemory> getAllHardMemory(Connection conn) throws SQLException;

    HardMemory getHardMemoryById(int id, Connection conn) throws SQLException;

    HardMemory removeHardMemory(int id, Connection conn) throws SQLException;

    OperativeMemory addOperativeMemory(OperativeMemory operativeMemory, Connection conn) throws SQLException;

    OperativeMemory removeOperativeMemory(int id, Connection conn) throws SQLException;

    List<OperativeMemory> getAllOperativeMemory(Connection conn) throws SQLException;

    OperativeMemory getOperativeMemoryById(int id, Connection conn) throws SQLException;

    VideoMemory addVideoMemory(VideoMemory videoMemory, Connection conn) throws SQLException;

    List<VideoMemory> getAllVideoMemory(Connection conn) throws SQLException;

    VideoMemory removeVideoMemory(int id, Connection conn) throws SQLException;

    VideoMemory getVideoMemoryById(int id, Connection conn) throws SQLException;

    Display addDisplay(Display display, Connection conn) throws SQLException;

    List<Display> getAllDisplays(Connection conn) throws SQLException;

    Display removeDisplay(int id, Connection conn) throws SQLException;

    Display getDisplayById(int id, Connection conn) throws SQLException;

    NotebookType addNotebookType(NotebookType notebook, Connection conn) throws NullFieldException, SQLException;

    NotebookType removeNotebookType(int id, Connection conn) throws SQLException;

    List<NotebookType> getAllNotebookTypes(Connection conn) throws SQLException;

    NotebookType getNotebookTypeById(int id, Connection conn) throws SQLException;

    Partiya addPartiya(Partiya partiya, Connection conn) throws NullFieldException, SQLException;

    NotebookForSail updateNotebook(int id, String state, Connection conn) throws NullFieldException, SQLException;

    List<NotebookForSail> getNotebooks(String company, NotebookState state, Connection conn) throws SQLException;

    List<NotebookForSail> getNotebooks(int operativeMemory, NotebookState state, Connection conn) throws SQLException;

    List<NotebookForSail> getNotebooks(int width, int heigth, NotebookState state, Connection conn) throws SQLException;

    List<NotebookForSail> getNotebooks(NotebookState state, Date periodStateChangedStart, Date periodStateChangedEnd, Connection conn) throws SQLException;


    List<NotebookForSail> getNotebooksByKriteria(int hardMemory, int operativeMemory, int processor,
                                                 int videoMemory, int model, int display, double pricemin, double priceMax, Connection conn) throws SQLException;

    Prodaja addProdaja(int notebookID, int buyerID, int sellerID, Connection conn) throws SQLException;

    List<Prodaja> getProdajasBuyer(int buyer, Date begin, Date end, Connection conn) throws SQLException;

    List<Prodaja> getProdajasSeller(int seller, Date begin, Date end, Connection conn) throws SQLException;

    Buyer addBuyer(Buyer buyer, Connection conn) throws NullFieldException, SQLException;

    List<Buyer> getAllBuyers(Connection conn) throws SQLException;

    Seller addSeller(Seller seller, Connection conn) throws NullFieldException, SQLException;

    List<Seller> getSellers(boolean working, Connection conn) throws SQLException;

    Seller updateSeller(int sellerID, boolean isWorking, Connection conn) throws SQLException;

    Buyer getBuyer(String phone, Connection conn) throws SQLException;

    Seller getSeller(String name, String pass, Connection conn) throws SQLException;

}
