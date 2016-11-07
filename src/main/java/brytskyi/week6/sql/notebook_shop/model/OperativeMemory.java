package brytskyi.week6.sql.notebook_shop.model;

/**
 * Created by alexandr on 05.11.16.
 */
public class OperativeMemory extends Memory {


    public OperativeMemory(String company, int size) {
        super(company, size);
    }

    public OperativeMemory(int id, String company, int size) {
        super(id, company, size);
    }

    @Override
    public String toString() {
        return "OperativeMemory{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", size=" + size +
                '}';
    }
}
