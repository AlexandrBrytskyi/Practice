package brytskyi.week6.sql.notebook_shop;

import brytskyi.week6.sql.NotebookDBCrasher;
import brytskyi.week6.sql.notebook_shop.model.exceptions.controller_exceptions.WrongLoginDataException;
import brytskyi.week6.sql.notebook_shop.services.additional.Context;

/**
 * Created by alexandr on 09.11.16.
 */
public class Runner {

    public static void main(String[] args) {
        Context appCont = Context.getCtxt();
        try {
            NotebookDBCrasher crasher = new NotebookDBCrasher(appCont.getCommonServiceWithToken());
            crasher.startThreads();
        } catch (WrongLoginDataException e) {
            e.printStackTrace();
        }
    }
}
