package brytskyi.week6_7.sql.notebook_shop.dao;

import brytskyi.week6_7.sql.notebook_shop.model.exceptions.dao_exceptions.NullFieldException;
import brytskyi.week6_7.sql.notebook_shop.model.production.*;

import java.util.Date;
import java.util.List;

/**
 * Created by alexandr on 05.11.16.
 */
public interface IProductionDao {


    NotebookModel addNotebookModel(NotebookModel model);

    NotebookModel getNoteBookModelById(int id);

    List<NotebookModel> getAllNotebookModels();

    NotebookModel removeNotebookModel(int id);

    Processor addProcessor(Processor processor);

    Processor getProcessorById(int id);

    List<Processor> getAllProcessors();

    Processor removeProcessor(int id);

    HardMemory addHardMemory(HardMemory hardMemory);

    List<HardMemory> getAllHardMemory();

    HardMemory getHardMemoryById(int id);

    HardMemory removeHardMemory(int id);

    OperativeMemory addOperativeMemory(OperativeMemory operativeMemory);

    OperativeMemory removeOperativeMemory(int id);

    List<OperativeMemory> getAllOperativeMemory();

    OperativeMemory getOperativeMemoryById(int id);

    VideoMemory addVideoMemory(VideoMemory videoMemory);

    List<VideoMemory> getAllVideoMemory();

    VideoMemory removeVideoMemory(int id);

    VideoMemory getVideoMemoryById(int id);

    Display addDisplay(Display display);

    List<Display> getAllDisplays();

    Display removeDisplay(int id);

    Display getDisplayById(int id);

    NotebookType addNotebookType(NotebookType notebook) throws NullFieldException;

    NotebookType removeNotebookType(int id);

    List<NotebookType> getAllNotebookTypes();

    NotebookType getNotebookTypeById(int id);

    Partiya addPartiya(Partiya partiya) throws NullFieldException;

    NotebookForSail updateNotebook(int id, String state) throws NullFieldException;

    List<NotebookForSail> getNotebooks(String company, NotebookState state);

    List<NotebookForSail> getNotebooks(int operativeMemory, NotebookState state);

    List<NotebookForSail> getNotebooks(int width, int heigth, NotebookState state);

    List<NotebookForSail> getNotebooks(NotebookState state, Date periodStateChangedStart, Date periodStateChangedEnd);


    List<NotebookForSail> getNotebooksByKriteria(int hardMemory, int operativeMemory, int processor,
                                                 int videoMemory, int model, int display, double pricemin, double priceMax);
}
