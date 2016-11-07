package brytskyi.week6.sql.notebook_shop.model;

import java.util.Date;
import java.util.List;

/**
 * Created by alexandr on 05.11.16.
 */
public class Partiya {

    private int id;
    private NotebookType patriyaType;
    private double price;
    private int amountOfNotebooks;
    private Date dateOfTake;
    private List<NotebookForSail> notebooks;

    public Partiya(NotebookType patriyaType, List<NotebookForSail> notebooks) {
        this.patriyaType = patriyaType;
        this.notebooks = notebooks;
        if (notebooks != null) this.amountOfNotebooks = notebooks.size();
        this.price = patriyaType.getPrice() * amountOfNotebooks;
        dateOfTake = new Date();
    }

    public Partiya(int id, NotebookType patriyaType, List<NotebookForSail> notebooks) {
        this.patriyaType = patriyaType;
        this.notebooks = notebooks;
        if (notebooks != null) this.amountOfNotebooks = notebooks.size();
        this.price = patriyaType.getPrice() * amountOfNotebooks;
        dateOfTake = new Date();
        this.id = id;
    }

    public List<NotebookForSail> getNotebooks() {
        return notebooks;
    }

    public void setNotebooks(List<NotebookForSail> notebooks) {
        this.notebooks = notebooks;
        if (notebooks != null) setAmountOfNotebooks(notebooks.size());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NotebookType getPatriyaType() {
        return patriyaType;
    }

    public void setPatriyaType(NotebookType patriyaType) {
        this.patriyaType = patriyaType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmountOfNotebooks() {
        return amountOfNotebooks;
    }

    public void setAmountOfNotebooks(int amountOfNotebooks) {
        this.amountOfNotebooks = amountOfNotebooks;
    }

    public Date getDateOfTake() {
        return dateOfTake;
    }

    public void setDateOfTake(Date dateOfTake) {
        this.dateOfTake = dateOfTake;
    }


    @Override
    public String toString() {
        return "Partiya{" +
                "id=" + id +
                ", patriyaType=" + patriyaType +
                ", price=" + price +
                ", amountOfNotebooks=" + amountOfNotebooks +
                ", dateOfTake=" + dateOfTake +
                '}';
    }
}
