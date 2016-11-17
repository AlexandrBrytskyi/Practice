package brytskyi.week8.spring.notebook_shop.model.production;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Memory {

    @Id
    @GeneratedValue
    protected int id;
    protected String company;
    protected int size;

    public Memory() {
    }

    public Memory(String company, int size) {
        this.company = company;
        this.size = size;
    }

    public Memory(int id, String company, int size) {
        this.id = id;
        this.company = company;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Memory memory = (Memory) o;

        if (id != memory.id) return false;
        if (size != memory.size) return false;
        return company != null ? company.equals(memory.company) : memory.company == null;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
