package brytskyi.week6.sql.notebook_shop.dao;

/**
 * Created by alexandr on 08.11.16.
 */
public interface MyCloseable {

    boolean openConnection();

    boolean closeConnection();

}
