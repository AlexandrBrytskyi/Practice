package brytskyi.week8.spring.notebook_shop;


import brytskyi.week8.spring.notebook_shop.model.exceptions.NotRegisteredTokenException;
import brytskyi.week8.spring.notebook_shop.model.exceptions.NullTokenException;
import brytskyi.week8.spring.notebook_shop.model.exceptions.UserAccessException;
import brytskyi.week8.spring.notebook_shop.model.exceptions.controller_exceptions.WrongLoginDataException;
import brytskyi.week8.spring.notebook_shop.model.production.Display;
import brytskyi.week8.spring.notebook_shop.services.CommonServiceWithToken;
import brytskyi.week8.spring.notebook_shop.services.ICommonServiceWithToken;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class DBCracherRunner {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:week8/notebook_shop_context.xml");
        ICommonServiceWithToken serviceWithToken = context.getBean(CommonServiceWithToken.class);
        NotebookDBCrasher notebookDBCrasher = context.getBean(NotebookDBCrasher.class);
        try {
            notebookDBCrasher.initExecutorAndGetAdminToken(serviceWithToken);
            notebookDBCrasher.startThreads();
        } catch (WrongLoginDataException e) {
            e.printStackTrace();
        }


    }
}
