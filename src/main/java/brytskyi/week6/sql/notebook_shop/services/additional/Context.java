package brytskyi.week6.sql.notebook_shop.services.additional;

import brytskyi.week6.sql.notebook_shop.dao.IProductionDao;
import brytskyi.week6.sql.notebook_shop.dao.ISellingDAO;
import brytskyi.week6.sql.notebook_shop.dao.IUsersDao;
import brytskyi.week6.sql.notebook_shop.dao.sql_dao.MySQL_DAO;
import brytskyi.week6.sql.notebook_shop.model.exceptions.controller_exceptions.WrongLoginDataException;
import brytskyi.week6.sql.notebook_shop.services.*;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by alexandr on 08.11.16.
 */
public class Context {

    private static volatile Context ctxt;
    private static final String adminToken = TokenGenerator.getToken();
    private static final String adminPass = "admin";
    private static final String adminLogin = "admin";
    private ConcurrentHashMap<String, UserType> tokens;

    private IProductionDao productionDao;
    private ISellingDAO sellingDAO;
    private IUsersDao usersDao;
    private Loginable loginSevice;
    private IGetProductionService getProductionService;
    private ISellerService sellerService;
    private IBuyerService buyerService;
    private IAdminService adminService;
    private ICommonServiceWithToken commonServiceWithToken;

    private Context() {
        initTokensMap();
        MySQL_DAO dao = new MySQL_DAO();
        productionDao = dao;
        sellingDAO = dao;
        usersDao = dao;
        sellerService = new SellerService(productionDao, sellingDAO);
        buyerService = new BuyerService(sellingDAO);
        adminService = new AdminService(this, productionDao, usersDao, sellingDAO);
        loginSevice = new LoginService(usersDao, this);
        getProductionService = adminService;
        commonServiceWithToken = new CommonServiceWithToken(loginSevice, adminService, sellerService, buyerService,
                getProductionService, this);
    }

    public static Context getCtxt() {
        synchronized (Context.class) {
            if (null == ctxt) ctxt = new Context();
            return ctxt;
        }
    }

    private void initTokensMap() {
        tokens = new ConcurrentHashMap<>();
        tokens.put(adminToken, UserType.ADMIN);
    }

    public ConcurrentHashMap<String, UserType> getTokens() {
        return tokens;
    }

    public String adminLogin(String name, String pass) throws WrongLoginDataException {
        if (name.equals(adminLogin) && pass.equals(adminPass)) return adminToken;
        throw new WrongLoginDataException();
    }

    public ICommonServiceWithToken getCommonServiceWithToken() {
        return commonServiceWithToken;
    }
}
