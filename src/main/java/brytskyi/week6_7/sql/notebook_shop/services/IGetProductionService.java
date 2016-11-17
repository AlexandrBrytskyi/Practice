package brytskyi.week6_7.sql.notebook_shop.services;

import brytskyi.week6_7.sql.notebook_shop.model.production.*;

import java.util.List;

/**
 * Created by alexandr on 09.11.16.
 */
public interface IGetProductionService {

    List<NotebookModel> getAllNotebookModels();

    List<Processor> getAllProcessors();

    List<HardMemory> getAllHardMemory();

    List<OperativeMemory> getAllOperativeMemory();

    List<VideoMemory> getAllVideoMemory();

    List<Display> getAllDisplays();

    List<NotebookType> getAllNotebookTypes();



}
