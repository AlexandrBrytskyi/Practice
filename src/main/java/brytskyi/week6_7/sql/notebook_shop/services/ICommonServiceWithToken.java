package brytskyi.week6_7.sql.notebook_shop.services;


import brytskyi.week6_7.sql.notebook_shop.model.exceptions.NotRegisteredTokenException;
import brytskyi.week6_7.sql.notebook_shop.model.exceptions.NullTokenException;
import brytskyi.week6_7.sql.notebook_shop.model.exceptions.UserAccessException;
import brytskyi.week6_7.sql.notebook_shop.model.exceptions.dao_exceptions.NullFieldException;
import brytskyi.week6_7.sql.notebook_shop.model.production.*;
import brytskyi.week6_7.sql.notebook_shop.model.selling.Prodaja;
import brytskyi.week6_7.sql.notebook_shop.model.users.Buyer;
import brytskyi.week6_7.sql.notebook_shop.model.users.Seller;

import java.util.Date;
import java.util.List;

public interface ICommonServiceWithToken extends Loginable {

    NotebookModel addNotebookModel(NotebookModel model,String token) throws UserAccessException, NullTokenException, NotRegisteredTokenException;

    NotebookModel getNoteBookModelById(int id,String token) throws UserAccessException, NullTokenException, NotRegisteredTokenException;

    NotebookModel removeNotebookModel(int id,String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException;

    Processor addProcessor(Processor processor,String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException;

    Processor getProcessorById(int id,String token) throws UserAccessException, NullTokenException, NotRegisteredTokenException;

    Processor removeProcessor(int id,String token) throws UserAccessException, NullTokenException, NotRegisteredTokenException;

    HardMemory addHardMemory(HardMemory hardMemory,String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException;

    HardMemory getHardMemoryById(int id,String token) throws UserAccessException, NullTokenException, NotRegisteredTokenException;

    HardMemory removeHardMemory(int id,String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException;

    OperativeMemory addOperativeMemory(OperativeMemory operativeMemory,String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException;

    OperativeMemory removeOperativeMemory(int id,String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException;

    OperativeMemory getOperativeMemoryById(int id,String token) throws UserAccessException, NullTokenException, NotRegisteredTokenException;

    VideoMemory addVideoMemory(VideoMemory videoMemory,String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException;

    VideoMemory removeVideoMemory(int id,String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException;

    VideoMemory getVideoMemoryById(int id,String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException;

    Display addDisplay(Display display,String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException;

    Display removeDisplay(int id,String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException;

    Display getDisplayById(int id,String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException;

    Partiya priniatPartiyu(List<NotebookForSail> notebooks, int notebookTypeId,String token) throws NullFieldException, UserAccessException, NullTokenException, NotRegisteredTokenException;

    NotebookType createNoteBookType(String desc, double price, int modelId, int processoId, int hardMemId, int operMemId, int videoMemId, int displayId,String token) throws NullFieldException, NullTokenException, NotRegisteredTokenException, UserAccessException;

    NotebookType getNotebookTypeById(int id,String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException;

    NotebookType removeNotebookType(int id,String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException;

    NotebookForSail spisatNotebook(int id,String token) throws NullFieldException, UserAccessException, NullTokenException, NotRegisteredTokenException;

    List<NotebookForSail> getSelledNotebooks(Date start, Date end,String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException;

    List<NotebookForSail> getSpisaniNotebooks(Date start, Date end,String token) throws UserAccessException, NullTokenException, NotRegisteredTokenException;

    Buyer addBuyer(Buyer buyer,String token) throws NullFieldException, NullTokenException, NotRegisteredTokenException, UserAccessException;

    List<Buyer> getBuyers(String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException;

    List<Seller> getSellers(boolean working,String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException;

    List<Prodaja> getNoteBooksSelled(int buyer, Date begin, Date end,String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException;

    List<Prodaja> getNoteBooksBuyed(int seller, Date begin, Date end,String token) throws UserAccessException, NullTokenException, NotRegisteredTokenException;

    List<Prodaja> getMyProdajas(int buyerID,String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException;

    Prodaja sellNotebook(int notebookID, int buyerID, int sellerID,String token) throws UserAccessException, NullTokenException, NotRegisteredTokenException;

    List<Prodaja> getMyProdaja(int sellerID,String token) throws UserAccessException, NullTokenException, NotRegisteredTokenException;

    List<NotebookForSail> getNotebooksByKriteria(int hardMemory, int operativeMemory, int processor, int videoMemory, int model, int display, double pricemin, double priceMax,String token) throws UserAccessException, NullTokenException, NotRegisteredTokenException;

    List<NotebookModel> getAllNotebookModels(String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException;

    List<Processor> getAllProcessors(String token) throws UserAccessException, NullTokenException, NotRegisteredTokenException;

    List<HardMemory> getAllHardMemory(String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException;

    List<OperativeMemory> getAllOperativeMemory(String token) throws UserAccessException, NullTokenException, NotRegisteredTokenException;

    List<VideoMemory> getAllVideoMemory(String token) throws UserAccessException, NullTokenException, NotRegisteredTokenException;

    List<Display> getAllDisplays(String token) throws UserAccessException, NullTokenException, NotRegisteredTokenException;

    List<NotebookType> getAllNotebookTypes(String token) throws UserAccessException, NullTokenException, NotRegisteredTokenException;

}
