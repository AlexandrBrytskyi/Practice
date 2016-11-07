package brytskyi.week6.sql.notebook_shop.model;

/**
 * Created by alexandr on 05.11.16.
 */
public class VideoMemory extends Memory {


    public VideoMemory(String company, int size) {
        super(company, size);
    }

    public VideoMemory(int id, String company, int size) {
        super(id, company, size);
    }

    @Override
    public String toString() {
        return "VideoMemory{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", size=" + size +
                '}';
    }
}
