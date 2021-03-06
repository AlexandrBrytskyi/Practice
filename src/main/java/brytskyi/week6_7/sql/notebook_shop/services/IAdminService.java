package brytskyi.week6_7.sql.notebook_shop.services;

import brytskyi.week6_7.sql.notebook_shop.model.exceptions.dao_exceptions.NullFieldException;
import brytskyi.week6_7.sql.notebook_shop.model.production.*;
import brytskyi.week6_7.sql.notebook_shop.model.selling.Prodaja;
import brytskyi.week6_7.sql.notebook_shop.model.users.Buyer;
import brytskyi.week6_7.sql.notebook_shop.model.users.Seller;

import java.util.Date;
import java.util.List;

/**
 * Created by alexandr on 08.11.16.
 */
public interface IAdminService extends IGetProductionService {

    NotebookModel addNotebookModel(NotebookModel model);

    NotebookModel getNoteBookModelById(int id);

    NotebookModel removeNotebookModel(int id);

    Processor addProcessor(Processor processor);

    Processor getProcessorById(int id);

    Processor removeProcessor(int id);

    HardMemory addHardMemory(HardMemory hardMemory);

    HardMemory getHardMemoryById(int id);

    HardMemory removeHardMemory(int id);

    OperativeMemory addOperativeMemory(OperativeMemory operativeMemory);

    OperativeMemory removeOperativeMemory(int id);

    OperativeMemory getOperativeMemoryById(int id);

    VideoMemory addVideoMemory(VideoMemory videoMemory);

    VideoMemory removeVideoMemory(int id);

    VideoMemory getVideoMemoryById(int id);

    Display addDisplay(Display display);

    Display removeDisplay(int id);

    Display getDisplayById(int id);

    Partiya priniatPartiyu(List<NotebookForSail> notebooks, int notebookTypeId) throws NullFieldException;

    NotebookType createNoteBookType(String desc, double price, int modelId, int processoId, int hardMemId, int operMemId, int videoMemId, int displayId) throws NullFieldException;

    NotebookType getNotebookTypeById(int id);

    NotebookType removeNotebookType(int id);

    NotebookForSail spisatNotebook(int id) throws NullFieldException;

    List<NotebookForSail> getSelledNotebooks(Date start, Date end);

    List<NotebookForSail> getSpisaniNotebooks(Date start, Date end);

    Buyer addBuyer(Buyer buyer) throws NullFieldException;

    List<Buyer> getBuyers();

    List<Seller> getSellers(boolean working);

    List<Prodaja> getNoteBooksSelled(int buyer, Date begin, Date end);

    List<Prodaja> getNoteBooksBuyed(int seller, Date begin, Date end);

}
