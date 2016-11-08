package brytskyi.week6.sql.notebook_shop.model.production;

/**
 * Created by alexandr on 05.11.16.
 */
public class HardMemory extends Memory {


    public HardMemory(String company, int size) {
        super(company, size);
    }

    public HardMemory(int id, String company, int size) {
        super(id, company, size);
    }


    @Override
    public String toString() {
        return "HardMemory{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", size=" + size +
                '}';
    }
}
