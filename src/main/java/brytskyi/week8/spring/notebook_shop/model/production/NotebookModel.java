package brytskyi.week8.spring.notebook_shop.model.production;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "notebook_models")
public class NotebookModel {

    @Id
    @GeneratedValue
    private int id;
    private String company;
    private String model;

    public NotebookModel() {
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotebookModel that = (NotebookModel) o;

        if (id != that.id) return false;
        if (company != null ? !company.equals(that.company) : that.company != null) return false;
        return model != null ? model.equals(that.model) : that.model == null;

    }

    @Override
    public int hashCode() {
        return id;
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
