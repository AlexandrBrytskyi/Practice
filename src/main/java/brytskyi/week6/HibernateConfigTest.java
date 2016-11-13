package brytskyi.week6;

import brytskyi.week6.sql.notebook_shop.dao.sql_dao.hibernateMySqlDao.HibernateMySqlDao;
import brytskyi.week6.sql.notebook_shop.model.production.*;
import brytskyi.week6.sql.notebook_shop.model.selling.Prodaja;
import brytskyi.week6.sql.notebook_shop.model.users.Buyer;
import brytskyi.week6.sql.notebook_shop.model.users.Contacts;
import brytskyi.week6.sql.notebook_shop.model.users.Seller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alexandr on 12.11.16.
 */
public class HibernateConfigTest {


    public static void main(String[] args) {
        HibernateMySqlDao dao = new HibernateMySqlDao();
        NotebookModel model = new NotebookModel("Company", "Huy");
        dao.addNotebookModel(model);
        System.out.println(dao.removeNotebookModel(76));
        System.out.println(dao.getAllNotebookModels());
    }


    /*  NotebookType type = manager.find(NotebookType.class, 1);
        Partiya partiya = new Partiya(manager.find(NotebookType.class, 1), null);
        List<NotebookForSail> notebookForSails = new LinkedList<NotebookForSail>() {{
            add(new NotebookForSail(type, "serial1", partiya, NotebookState.NEW));
            add(new NotebookForSail(type, "serial2", partiya, NotebookState.NEW));
            add(new NotebookForSail(type, "serial3", partiya, NotebookState.NEW));
            add(new NotebookForSail(type, "serial4", partiya, NotebookState.NEW));
        }};
        partiya.setNotebooks(notebookForSails);
        manager.persist(partiya);*/
}
