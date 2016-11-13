package brytskyi.week6.sql.notebook_shop.model.production;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "video_memories")
public class VideoMemory extends Memory {


    public VideoMemory() {
    }

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
