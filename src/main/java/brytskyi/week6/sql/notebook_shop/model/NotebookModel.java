package brytskyi.week6.sql.notebook_shop.model;

/**
 * Created by alexandr on 05.11.16.
 */
public class NotebookModel {

    private int id;
    private String company;
    private String model;

    public NotebookModel(String company, String model) {
        this.company = company;
        this.model = model;
    }

    public NotebookModel(int id, String company, String model) {
        this.id = id;
        this.company = company;
        this.model = model;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "NotebookModel{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
