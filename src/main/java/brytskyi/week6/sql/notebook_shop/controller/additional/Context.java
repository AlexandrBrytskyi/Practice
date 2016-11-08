package brytskyi.week6.sql.notebook_shop.controller.additional;

/**
 * Created by alexandr on 08.11.16.
 */
public class Context {

    private static volatile Context ctxt;

    private Context() {
    }

    public static Context getCtxt() {
        synchronized (Context.class) {
            if (null == ctxt) ctxt = new Context();
            return ctxt;
        }
    }
}
