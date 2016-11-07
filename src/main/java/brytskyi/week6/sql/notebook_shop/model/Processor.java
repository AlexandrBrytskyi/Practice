package brytskyi.week6.sql.notebook_shop.model;

/**
 * Created by alexandr on 05.11.16.
 */
public class Processor {

    private int id;
    private String company;
    private int frequency;

    public Processor(String company, int frequency) {
        this.company = company;
        this.frequency = frequency;
    }

    public Processor(int id, String company, int frequency) {
        this.id = id;
        this.company = company;
        this.frequency = frequency;
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

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "Processor{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", frequency=" + frequency +
                '}';
    }
}
