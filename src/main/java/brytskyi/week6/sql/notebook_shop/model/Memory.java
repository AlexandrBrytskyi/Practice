package brytskyi.week6.sql.notebook_shop.model;

/**
 * Created by alexandr on 05.11.16.
 */
public abstract class Memory {

    protected int id;
    protected String company;
    protected int size;

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


}
