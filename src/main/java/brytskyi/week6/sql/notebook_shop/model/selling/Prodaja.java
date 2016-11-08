package brytskyi.week6.sql.notebook_shop.model.selling;

import brytskyi.week6.sql.notebook_shop.model.production.NotebookForSail;
import brytskyi.week6.sql.notebook_shop.model.users.Buyer;
import brytskyi.week6.sql.notebook_shop.model.users.Seller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;


public class Prodaja {

    private int id;
    private Buyer buyer;
    private Seller seller;
    private NotebookForSail notebookForSail;

    public Prodaja(Buyer buyer, Seller seller, NotebookForSail notebookForSail) {
        this.buyer = buyer;
        this.seller = seller;
        this.notebookForSail = notebookForSail;
    }

    public Prodaja(int id, Buyer buyer, Seller seller, NotebookForSail notebookForSail) {
        this.id = id;
        this.buyer = buyer;
        this.seller = seller;
        this.notebookForSail = notebookForSail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public NotebookForSail getNotebookForSail() {
        return notebookForSail;
    }

    public void setNotebookForSail(NotebookForSail notebookForSail) {
        this.notebookForSail = notebookForSail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Prodaja prodaja = (Prodaja) o;

        if (id != prodaja.id) return false;
        return notebookForSail != null ? notebookForSail.equals(prodaja.notebookForSail) : prodaja.notebookForSail == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        return result;
    }

    @Override
    public String toString() {
        return "Prodaja{" +
                "id=" + id +
                ", buyer=" + buyer +
                ", seller=" + seller +
                ", notebookForSail=" + notebookForSail +
                '}';
    }
}
