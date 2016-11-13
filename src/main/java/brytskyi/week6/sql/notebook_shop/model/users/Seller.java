package brytskyi.week6.sql.notebook_shop.model.users;

import brytskyi.week6.sql.notebook_shop.model.production.NotebookForSail;
import brytskyi.week6.sql.notebook_shop.model.selling.Prodaja;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "sellers")
public class Seller {

    @Id
    @GeneratedValue
    private int id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "contacts", referencedColumnName = "id")
    private Contacts contacts;

    @OneToMany(mappedBy = "seller", fetch = FetchType.EAGER)
    private List<Prodaja> selled;

    private double salary;
    private boolean isWorking;
    private String pass;

    public Seller() {
    }

    public Seller(Contacts contacts, double salary, String pass) {
        isWorking = true;
        this.contacts = contacts;
        this.salary = salary;
        this.selled = new LinkedList<>();
        this.pass = pass;
    }

    public Seller(int id, Contacts contacts, List<Prodaja> selled, double salary, boolean isWorking, String pass) {
        this.id = id;
        this.contacts = contacts;
        this.selled = selled;
        this.salary = salary;
        this.isWorking = isWorking;
        this.pass = pass;

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

    public List<Prodaja> getSelled() {
        return selled;
    }

    public void setSelled(List<Prodaja> selled) {
        this.selled = selled;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Seller seller = (Seller) o;

        return id == seller.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "id=" + id +
                ", contacts=" + contacts +
                ", selled=" + selled +
                ", salary=" + salary +
                '}';
    }
}
