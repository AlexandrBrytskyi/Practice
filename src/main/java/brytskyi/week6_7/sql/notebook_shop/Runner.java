package brytskyi.week6_7.sql.notebook_shop;

import brytskyi.week6_7.sql.NotebookDBCrasher;
import brytskyi.week6_7.sql.notebook_shop.model.exceptions.controller_exceptions.WrongLoginDataException;
import brytskyi.week6_7.sql.notebook_shop.services.additional.Context;


public class Runner {

    public static void main(String[] args) {
        /*logger configure*/


        /*context initializing*/
        Context appCont = Context.getCtxt();
        try {
            NotebookDBCrasher crasher = new NotebookDBCrasher(appCont.getCommonServiceWithToken());
            crasher.startThreads();
        } catch (WrongLoginDataException e) {
            e.printStackTrace();
        }
    }
}
