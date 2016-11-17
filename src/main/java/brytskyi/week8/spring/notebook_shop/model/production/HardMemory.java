package brytskyi.week8.spring.notebook_shop.model.production;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "hard_memories")
public class HardMemory extends Memory {


    public HardMemory() {
    }

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
