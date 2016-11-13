package brytskyi.week6.sql.notebook_shop.model.users;

import brytskyi.week6.sql.notebook_shop.model.production.NotebookForSail;
import brytskyi.week6.sql.notebook_shop.model.selling.Prodaja;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "buyers")
public class Buyer {

    @Id
    @GeneratedValue
    private int id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "contacts", referencedColumnName = "id")
    private Contacts contacts;


    @OneToMany(mappedBy = "buyer", fetch = FetchType.EAGER)
    private List<Prodaja> buyed;
    private double moneySpent;

    public Buyer() {
    }

    public Buyer(Contacts contacts) {
        this.contacts = contacts;
        this.buyed = new LinkedList<>();
    }

    public Buyer(int id, Contacts contacts, List<Prodaja> buyedProduction, double moneySpent) {
        this.id = id;
        this.contacts = contacts;
        this.buyed = buyedProduction;
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

    public List<Prodaja> getBuyed() {
        return buyed;
    }

    public void setBuyed(List<Prodaja> buyed) {
        this.buyed = buyed;
    }

    public void buyNotebook(Prodaja prodaja) {
        buyed.add(prodaja);
        moneySpent += prodaja.getNotebookForSail().getType().getPrice();
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

        return id == buyer.id;

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
                ", buyed=" + buyed +
                ", moneySpent=" + moneySpent +
                '}';
    }
}
