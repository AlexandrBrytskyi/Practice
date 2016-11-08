package brytskyi.week6.sql.notebook_shop.model.users;

import brytskyi.week6.sql.notebook_shop.model.production.NotebookForSail;

import java.util.LinkedList;
import java.util.List;


public class Buyer {

    private int id;
    private Contacts contacts;
    private List<NotebookForSail> buyedProduction;
    private double moneySpent;

    public Buyer(Contacts contacts) {
        this.contacts = contacts;
        this.buyedProduction = new LinkedList<>();
    }

    public Buyer(int id, Contacts contacts, List<NotebookForSail> buyedProduction, double moneySpent) {
        this.id = id;
        this.contacts = contacts;
        this.buyedProduction = buyedProduction;
        this.moneySpent = moneySpent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    public List<NotebookForSail> getBuyedProduction() {
        return buyedProduction;
    }

    public void setBuyedProduction(List<NotebookForSail> buyedProduction) {
        this.buyedProduction = buyedProduction;
    }

    public void buyNotebook(NotebookForSail notebookForSail) {
        buyedProduction.add(notebookForSail);
        moneySpent += notebookForSail.getType().getPrice();
    }

    public double getMoneySpent() {
        return moneySpent;
    }

    public void setMoneySpent(double moneySpent) {
        this.moneySpent = moneySpent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Buyer buyer = (Buyer) o;

        if (id != buyer.id) return false;
        if (Double.compare(buyer.moneySpent, moneySpent) != 0) return false;
        if (contacts != null ? !contacts.equals(buyer.contacts) : buyer.contacts != null) return false;
        return buyedProduction != null ? buyedProduction.equals(buyer.buyedProduction) : buyer.buyedProduction == null;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "id=" + id +
                ", contacts=" + contacts +
                ", buyedProduction=" + buyedProduction +
                ", moneySpent=" + moneySpent +
                '}';
    }
}
