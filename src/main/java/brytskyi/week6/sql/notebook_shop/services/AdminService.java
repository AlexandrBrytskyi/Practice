package brytskyi.week6.sql.notebook_shop.services;


import brytskyi.week6.sql.notebook_shop.services.additional.Context;
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

public class AdminService extends GetProductionService implements IAdminService {

    private Context context;
    private IProductionDao productionDao;
    private IUsersDao usersDao;
    private ISellingDAO sellingDAO;

    public AdminService(Context context, IProductionDao productionDao, IUsersDao usersDao, ISellingDAO sellingDAO) {
        this.context = context;
        this.productionDao = productionDao;
        this.usersDao = usersDao;
        this.sellingDAO = sellingDAO;
    }

    @Override
    public NotebookModel addNotebookModel(NotebookModel model) {
        productionDao.openConnection();
        try {
            return productionDao.addNotebookModel(model);
        } finally {
            productionDao.closeConnection();
        }
    }

    @Override
    public NotebookModel getNoteBookModelById(int id) {
        productionDao.openConnection();
        try {
            return productionDao.getNoteBookModelById(id);
        } finally {
            productionDao.closeConnection();
        }
    }


    @Override
    public NotebookModel removeNotebookModel(int id) {
        productionDao.openConnection();
        try {
            return productionDao.removeNotebookModel(id);
        } finally {
            productionDao.closeConnection();
        }
    }

    @Override
    public Processor addProcessor(Processor processor) {
        productionDao.openConnection();
        try {
            return productionDao.addProcessor(processor);
        } finally {
            productionDao.closeConnection();
        }
    }

    @Override
    public Processor getProcessorById(int id) {
        productionDao.openConnection();
        try {
            return productionDao.getProcessorById(id);
        } finally {
            productionDao.closeConnection();
        }
    }


    @Override
    public Processor removeProcessor(int id) {
        productionDao.openConnection();
        try {
            return productionDao.removeProcessor(id);
        } finally {
            productionDao.closeConnection();
        }
    }

    @Override
    public HardMemory addHardMemory(HardMemory hardMemory) {
        productionDao.openConnection();
        try {
            return productionDao.addHardMemory(hardMemory);
        } finally {
            productionDao.closeConnection();
        }
    }


    @Override
    public HardMemory getHardMemoryById(int id) {
        productionDao.openConnection();
        try {
            return productionDao.getHardMemoryById(id);
        } finally {
            productionDao.closeConnection();
        }
    }

    @Override
    public HardMemory removeHardMemory(int id) {
        productionDao.openConnection();
        try {
            return productionDao.removeHardMemory(id);
        } finally {
            productionDao.closeConnection();
        }
    }

    @Override
    public OperativeMemory addOperativeMemory(OperativeMemory operativeMemory) {
        productionDao.openConnection();
        try {
            return productionDao.addOperativeMemory(operativeMemory);
        } finally {
            productionDao.closeConnection();
        }
    }

    @Override
    public OperativeMemory removeOperativeMemory(int id) {
        productionDao.openConnection();
        try {
            return productionDao.removeOperativeMemory(id);
        } finally {
            productionDao.closeConnection();
        }
    }


    @Override
    public OperativeMemory getOperativeMemoryById(int id) {
        productionDao.openConnection();
        try {
            return productionDao.getOperativeMemoryById(id);
        } finally {
            productionDao.closeConnection();
        }
    }

    @Override
    public VideoMemory addVideoMemory(VideoMemory videoMemory) {
        productionDao.openConnection();
        try {
            return productionDao.addVideoMemory(videoMemory);
        } finally {
            productionDao.closeConnection();
        }
    }


    @Override
    public VideoMemory removeVideoMemory(int id) {
        productionDao.openConnection();
        try {
            return productionDao.removeVideoMemory(id);
        } finally {
            productionDao.closeConnection();
        }
    }

    @Override
    public VideoMemory getVideoMemoryById(int id) {
        productionDao.openConnection();
        try {
            return productionDao.getVideoMemoryById(id);
        } finally {
            productionDao.closeConnection();
        }
    }

    @Override
    public Display addDisplay(Display display) {
        productionDao.openConnection();
        try {
            return productionDao.addDisplay(display);
        } finally {
            productionDao.closeConnection();
        }
    }

    @Override
    public Display removeDisplay(int id) {
        productionDao.openConnection();
        try {
            return productionDao.removeDisplay(id);
        } finally {
            productionDao.closeConnection();
        }
    }

    @Override
    public Display getDisplayById(int id) {
        productionDao.openConnection();
        try {
            return productionDao.getDisplayById(id);
        } finally {
            productionDao.closeConnection();
        }
    }


    @Override
    public Partiya priniatPartiyu(List<NotebookForSail> notebooks, int notebookTypeId) throws NullFieldException {
        productionDao.openConnection();
        try {
            Partiya partiya = new Partiya(productionDao.getNotebookTypeById(notebookTypeId),
                    notebooks);
            for (NotebookForSail notebook : notebooks) {
                notebook.setPartiya(partiya);
            }
            return productionDao.addPartiya(partiya);
        } finally {
            productionDao.closeConnection();
        }
    }


    // TODO: 08.11.16 maybe cache?????????
    @Override
    public NotebookType createNoteBookType(String desc, double price, int modelId, int processoId, int hardMemId, int operMemId, int videoMemId, int displayId) throws NullFieldException {
        productionDao.openConnection();
        try {
            return productionDao.addNotebookType(new NotebookType(
                    desc, productionDao.getHardMemoryById(hardMemId),
                    productionDao.getOperativeMemoryById(operMemId),
                    productionDao.getProcessorById(processoId),
                    productionDao.getVideoMemoryById(videoMemId),
                    productionDao.getNoteBookModelById(modelId),
                    productionDao.getDisplayById(displayId),
                    price
            ));
        } finally {
            productionDao.closeConnection();
        }
    }

    @Override
    public NotebookType getNotebookTypeById(int id) {
        productionDao.openConnection();
        try {
            return productionDao.getNotebookTypeById(id);
        } finally {
            productionDao.closeConnection();
        }
    }

    @Override
    public NotebookType removeNotebookType(int id) {
        productionDao.openConnection();
        try {
            return productionDao.removeNotebookType(id);
        } finally {
            productionDao.closeConnection();
        }
    }


    @Override
    public NotebookForSail spisatNotebook(int id) throws NullFieldException {
        productionDao.openConnection();
        try {
            return productionDao.updateNotebook(id, NotebookState.SPISAN.toString());
        } finally {
            productionDao.closeConnection();
        }
    }

    @Override
    public List<NotebookForSail> getSelledNotebooks(Date start, Date end) {
        productionDao.openConnection();
        try {
            return productionDao.getNotebooks(NotebookState.SELLED, start, end);
        } finally {
            productionDao.closeConnection();
        }
    }

    @Override
    public List<NotebookForSail> getSpisaniNotebooks(Date start, Date end) {
        productionDao.openConnection();
        try {
            return productionDao.getNotebooks(NotebookState.SPISAN, start, end);
        } finally {
            productionDao.closeConnection();
        }
    }

    @Override
    public Buyer addBuyer(Buyer buyer) throws NullFieldException {
        usersDao.openConnection();
        try {
            return usersDao.addBuyer(buyer);
        } finally {
            usersDao.closeConnection();
        }
    }

    @Override
    public List<Buyer> getBuyers() {
        usersDao.openConnection();
        try {
            return usersDao.getAllBuyers();
        } finally {
            usersDao.closeConnection();
        }
    }

    @Override
    public List<Seller> getSellers(boolean working) {
        usersDao.openConnection();
        try {
            return usersDao.getSellers(working);
        } finally {
            usersDao.closeConnection();
        }
    }

    @Override
    public List<Prodaja> getNoteBooksSelled(int buyer, Date begin, Date end) {
        sellingDAO.openConnection();
        try {
            return sellingDAO.getProdajasBuyer(buyer, begin, end);
        } finally {
            sellingDAO.closeConnection();
        }
    }

    @Override
    public List<Prodaja> getNoteBooksBuyed(int seller, Date begin, Date end) {
        sellingDAO.openConnection();
        try {
            return sellingDAO.getProdajasSeller(seller, begin, end);
        } finally {
            sellingDAO.closeConnection();
        }
    }
}
