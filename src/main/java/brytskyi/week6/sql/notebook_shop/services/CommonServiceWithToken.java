package brytskyi.week6.sql.notebook_shop.services;


import brytskyi.week6.sql.notebook_shop.model.exceptions.NotRegisteredTokenException;
import brytskyi.week6.sql.notebook_shop.model.exceptions.NullTokenException;
import brytskyi.week6.sql.notebook_shop.model.exceptions.UserAccessException;
import brytskyi.week6.sql.notebook_shop.model.exceptions.controller_exceptions.NotRegisteredBuyerException;
import brytskyi.week6.sql.notebook_shop.model.exceptions.controller_exceptions.WrongLoginDataException;
import brytskyi.week6.sql.notebook_shop.model.exceptions.dao_exceptions.NullFieldException;
import brytskyi.week6.sql.notebook_shop.model.production.*;
import brytskyi.week6.sql.notebook_shop.model.selling.Prodaja;
import brytskyi.week6.sql.notebook_shop.model.users.Buyer;
import brytskyi.week6.sql.notebook_shop.model.users.Contacts;
import brytskyi.week6.sql.notebook_shop.model.users.Seller;
import brytskyi.week6.sql.notebook_shop.services.additional.Context;
import brytskyi.week6.sql.notebook_shop.services.additional.UserType;

import java.util.Date;
import java.util.List;

public class CommonServiceWithToken implements ICommonServiceWithToken {

    private Loginable loginService;
    private IAdminService adminService;
    private ISellerService sellerService;
    private IBuyerService buyerService;
    private IGetProductionService getProdService;
    private Context context;

    public CommonServiceWithToken(Loginable loginService, IAdminService adminService, ISellerService sellerService, IBuyerService buyerService, IGetProductionService service, Context context) {
        this.loginService = loginService;
        this.adminService = adminService;
        this.sellerService = sellerService;
        this.buyerService = buyerService;
        this.getProdService = service;
        this.context = context;
    }

    private UserType defineUserType(String token) throws NullTokenException, NotRegisteredTokenException {
        if (token == null) throw new NullTokenException();
        UserType type = context.getTokens().get(token);
        if (type == null) throw new NotRegisteredTokenException();
        return type;
    }

    @Override
    public NotebookModel addNotebookModel(NotebookModel model, String token) throws UserAccessException, NullTokenException, NotRegisteredTokenException {
        if (defineUserType(token).equals(UserType.ADMIN)) return adminService.addNotebookModel(model);
        throw new UserAccessException("You can`t do such operations");
    }

    @Override
    public NotebookModel getNoteBookModelById(int id, String token) throws UserAccessException, NullTokenException, NotRegisteredTokenException {
        if (defineUserType(token).equals(UserType.ADMIN)) return adminService.getNoteBookModelById(id);
        throw new UserAccessException("You can`t do such operations");
    }

    @Override
    public NotebookModel removeNotebookModel(int id, String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException {
        if (defineUserType(token).equals(UserType.ADMIN)) return adminService.removeNotebookModel(id);
        throw new UserAccessException("You can`t do such operations");
    }

    @Override
    public Processor addProcessor(Processor processor, String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException {
        if (defineUserType(token).equals(UserType.ADMIN)) return adminService.addProcessor(processor);
        throw new UserAccessException("You can`t do such operations");
    }

    @Override
    public Processor getProcessorById(int id, String token) throws UserAccessException, NullTokenException, NotRegisteredTokenException {
        if (defineUserType(token).equals(UserType.ADMIN)) return adminService.getProcessorById(id);
        throw new UserAccessException("You can`t do such operations");
    }

    @Override
    public Processor removeProcessor(int id, String token) throws UserAccessException, NullTokenException, NotRegisteredTokenException {
        if (defineUserType(token).equals(UserType.ADMIN)) return adminService.removeProcessor(id);
        throw new UserAccessException("You can`t do such operations");
    }

    @Override
    public HardMemory addHardMemory(HardMemory hardMemory, String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException {
        if (defineUserType(token).equals(UserType.ADMIN)) return adminService.addHardMemory(hardMemory);
        throw new UserAccessException("You can`t do such operations");
    }

    @Override
    public HardMemory getHardMemoryById(int id, String token) throws UserAccessException, NullTokenException, NotRegisteredTokenException {
        if (defineUserType(token).equals(UserType.ADMIN)) return adminService.getHardMemoryById(id);
        throw new UserAccessException("You can`t do such operations");
    }

    @Override
    public HardMemory removeHardMemory(int id, String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException {
        if (defineUserType(token).equals(UserType.ADMIN)) return adminService.removeHardMemory(id);
        throw new UserAccessException("You can`t do such operations");
    }

    @Override
    public OperativeMemory addOperativeMemory(OperativeMemory operativeMemory, String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException {
        if (defineUserType(token).equals(UserType.ADMIN)) return adminService.addOperativeMemory(operativeMemory);
        throw new UserAccessException("You can`t do such operations");
    }

    @Override
    public OperativeMemory removeOperativeMemory(int id, String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException {
        if (defineUserType(token).equals(UserType.ADMIN)) return adminService.removeOperativeMemory(id);
        throw new UserAccessException("You can`t do such operations");
    }

    @Override
    public OperativeMemory getOperativeMemoryById(int id, String token) throws UserAccessException, NullTokenException, NotRegisteredTokenException {
        if (defineUserType(token).equals(UserType.ADMIN)) return adminService.getOperativeMemoryById(id);
        throw new UserAccessException("You can`t do such operations");
    }

    @Override
    public VideoMemory addVideoMemory(VideoMemory videoMemory, String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException {
        if (defineUserType(token).equals(UserType.ADMIN)) return adminService.addVideoMemory(videoMemory);
        throw new UserAccessException("You can`t do such operations");
    }

    @Override
    public VideoMemory removeVideoMemory(int id, String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException {
        if (defineUserType(token).equals(UserType.ADMIN)) return adminService.removeVideoMemory(id);
        throw new UserAccessException("You can`t do such operations");
    }

    @Override
    public VideoMemory getVideoMemoryById(int id, String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException {
        if (defineUserType(token).equals(UserType.ADMIN)) return adminService.getVideoMemoryById(id);
        throw new UserAccessException("You can`t do such operations");
    }

    @Override
    public Display addDisplay(Display display, String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException {
        if (defineUserType(token).equals(UserType.ADMIN)) return adminService.addDisplay(display);
        throw new UserAccessException("You can`t do such operations");
    }

    @Override
    public Display removeDisplay(int id, String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException {
        if (defineUserType(token).equals(UserType.ADMIN)) return adminService.removeDisplay(id);
        throw new UserAccessException("You can`t do such operations");
    }

    @Override
    public Display getDisplayById(int id, String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException {
        if (defineUserType(token).equals(UserType.ADMIN)) return adminService.getDisplayById(id);
        throw new UserAccessException("You can`t do such operations");
    }

    @Override
    public Partiya priniatPartiyu(List<NotebookForSail> notebooks, int notebookTypeId, String token) throws NullFieldException, UserAccessException, NullTokenException, NotRegisteredTokenException {
        if (defineUserType(token).equals(UserType.ADMIN)) return adminService.priniatPartiyu(notebooks, notebookTypeId);
        throw new UserAccessException("You can`t do such operations");
    }

    @Override
    public NotebookType createNoteBookType(String desc, double price, int modelId, int processoId, int hardMemId, int operMemId, int videoMemId, int displayId, String token) throws NullFieldException, NullTokenException, NotRegisteredTokenException, UserAccessException {
        if (defineUserType(token).equals(UserType.ADMIN))
            return adminService.createNoteBookType(desc, price, modelId, processoId, hardMemId, operMemId, videoMemId, displayId);
        throw new UserAccessException("You can`t do such operations");
    }

    @Override
    public NotebookType getNotebookTypeById(int id, String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException {
        if (defineUserType(token).equals(UserType.ADMIN)) return adminService.getNotebookTypeById(id);
        throw new UserAccessException("You can`t do such operations");
    }

    @Override
    public NotebookType removeNotebookType(int id, String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException {
        if (defineUserType(token).equals(UserType.ADMIN)) return adminService.removeNotebookType(id);
        throw new UserAccessException("You can`t do such operations");
    }


    @Override
    public NotebookForSail spisatNotebook(int id, String token) throws NullFieldException, UserAccessException, NullTokenException, NotRegisteredTokenException {
        if (defineUserType(token).equals(UserType.ADMIN)) return adminService.spisatNotebook(id);
        throw new UserAccessException("You can`t do such operations");
    }

    @Override
    public List<NotebookForSail> getSelledNotebooks(Date start, Date end, String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException {
        if (defineUserType(token).equals(UserType.ADMIN)) return adminService.getSelledNotebooks(start, end);
        throw new UserAccessException("You can`t do such operations");
    }

    @Override
    public List<NotebookForSail> getSpisaniNotebooks(Date start, Date end, String token) throws UserAccessException, NullTokenException, NotRegisteredTokenException {
        if (defineUserType(token).equals(UserType.ADMIN)) return adminService.getSpisaniNotebooks(start, end);
        throw new UserAccessException("You can`t do such operations");
    }

    @Override
    public Buyer addBuyer(Buyer buyer, String token) throws NullFieldException, NullTokenException, NotRegisteredTokenException, UserAccessException {
        if (defineUserType(token).equals(UserType.ADMIN)) return adminService.addBuyer(buyer);
        throw new UserAccessException("You can`t do such operations");
    }

    @Override
    public List<Buyer> getBuyers(String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException {
        if (defineUserType(token).equals(UserType.ADMIN)) return adminService.getBuyers();
        throw new UserAccessException("You can`t do such operations");
    }

    @Override
    public List<Seller> getSellers(boolean working, String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException {
        if (defineUserType(token).equals(UserType.ADMIN)) return adminService.getSellers(working);
        throw new UserAccessException("You can`t do such operations");
    }

    @Override
    public List<Prodaja> getNoteBooksSelled(int buyer, Date begin, Date end, String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException {
        if (defineUserType(token).equals(UserType.ADMIN)) return adminService.getNoteBooksSelled(buyer, begin, end);
        throw new UserAccessException("You can`t do such operations");
    }

    @Override
    public List<Prodaja> getNoteBooksBuyed(int seller, Date begin, Date end, String token) throws UserAccessException, NullTokenException, NotRegisteredTokenException {
        if (defineUserType(token).equals(UserType.ADMIN)) return adminService.getNoteBooksBuyed(seller, begin, end);
        throw new UserAccessException("You can`t do such operations");
    }

    @Override
    public List<Prodaja> getMyProdajas(int buyerID, String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException {
        if (defineUserType(token).equals(UserType.BUYER)) return buyerService.getMyProdajas(buyerID);
        throw new UserAccessException("You can`t do such operations");
    }

    @Override
    public Prodaja sellNotebook(int notebookID, int buyerID, int sellerID, String token) throws UserAccessException, NullTokenException, NotRegisteredTokenException {
        if (defineUserType(token).equals(UserType.SELLER))
            return sellerService.sellNotebook(notebookID, buyerID, sellerID);
        throw new UserAccessException("You can`t do such operations");
    }

    @Override
    public List<Prodaja> getMyProdaja(int sellerID, String token) throws UserAccessException, NullTokenException, NotRegisteredTokenException {
        if (defineUserType(token).equals(UserType.SELLER)) return sellerService.getMyProdaja(sellerID);
        throw new UserAccessException("You can`t do such operations");
    }

    @Override
    public List<NotebookForSail> getNotebooksByKriteria(int hardMemory, int operativeMemory, int processor, int videoMemory, int model, int display, double pricemin, double priceMax, String token) throws UserAccessException, NullTokenException, NotRegisteredTokenException {
        if (defineUserType(token).equals(UserType.ADMIN))
            return sellerService.getNotebooksByKriteria(hardMemory, operativeMemory, processor, videoMemory, model, display, pricemin, priceMax);
        throw new UserAccessException("You can`t do such operations");
    }

    @Override
    public List<NotebookModel> getAllNotebookModels(String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException {
        UserType type = defineUserType(token);
        if (type.equals(UserType.ADMIN) || type.equals(UserType.SELLER))
            return getProdService.getAllNotebookModels();
        throw new UserAccessException("You can`t do such operations");
    }

    @Override
    public List<Processor> getAllProcessors(String token) throws UserAccessException, NullTokenException, NotRegisteredTokenException {
        UserType type = defineUserType(token);
        if (type.equals(UserType.ADMIN) || type.equals(UserType.SELLER))
            return getProdService.getAllProcessors();
        throw new UserAccessException("You can`t do such operations");
    }

    @Override
    public List<HardMemory> getAllHardMemory(String token) throws NullTokenException, NotRegisteredTokenException, UserAccessException {
        UserType type = defineUserType(token);
        if (type.equals(UserType.ADMIN) || type.equals(UserType.SELLER))
            return getProdService.getAllHardMemory();
        throw new UserAccessException("You can`t do such operations");
    }

    @Override
    public List<OperativeMemory> getAllOperativeMemory(String token) throws UserAccessException, NullTokenException, NotRegisteredTokenException {
        UserType type = defineUserType(token);
        if (type.equals(UserType.ADMIN) || type.equals(UserType.SELLER))
            return getProdService.getAllOperativeMemory();
        throw new UserAccessException("You can`t do such operations");
    }

    @Override
    public List<VideoMemory> getAllVideoMemory(String token) throws UserAccessException, NullTokenException, NotRegisteredTokenException {
        UserType type = defineUserType(token);
        if (type.equals(UserType.ADMIN) || type.equals(UserType.SELLER))
            return getProdService.getAllVideoMemory();
        throw new UserAccessException("You can`t do such operations");
    }

    @Override
    public List<Display> getAllDisplays(String token) throws UserAccessException, NullTokenException, NotRegisteredTokenException {
        UserType type = defineUserType(token);
        if (type.equals(UserType.ADMIN) || type.equals(UserType.SELLER))
            return getProdService.getAllDisplays();
        throw new UserAccessException("You can`t do such operations");
    }

    @Override
    public List<NotebookType> getAllNotebookTypes(String token) throws UserAccessException, NullTokenException, NotRegisteredTokenException {
        UserType type = defineUserType(token);
        if (type.equals(UserType.ADMIN) || type.equals(UserType.SELLER))
            return getProdService.getAllNotebookTypes();
        throw new UserAccessException("You can`t do such operations");
    }

    @Override
    public String login(String name, String pass) throws WrongLoginDataException {
        return loginService.login(name, pass);
    }

    @Override
    public String login(String phone) throws NotRegisteredBuyerException {
        return loginService.login(phone);
    }

    @Override
    public Buyer register(Contacts contacts) throws NullFieldException {
        return loginService.register(contacts);
    }
}
