package brytskyi.week8.spring.notebook_shop.model.production;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "operative_memories")
public class OperativeMemory extends Memory {


    public OperativeMemory() {
    }

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
