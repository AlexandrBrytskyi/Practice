package brytskyi.week8.spring.notebook_shop.model.production;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notebooks_for_sail")
public class NotebookForSail {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "notebook_type")
    private NotebookType type;

    private String serial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partiya", referencedColumnName = "id")
    private Partiya partiya;

    @Enumerated(value = EnumType.STRING)
    private NotebookState state;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date dateStateChanged;


    public NotebookForSail() {
    }

    public NotebookForSail(NotebookType type, String serial, Partiya partiya, NotebookState state) {
        this.type = type;
        this.serial = serial;
        this.partiya = partiya;
        this.state = state;
        this.dateStateChanged = new Date();
    }

    public NotebookForSail(int id, NotebookType type, String serial, Partiya partiya, NotebookState state, Date dateStateChanged) {
        this.id = id;
        this.type = type;
        this.serial = serial;
        this.partiya = partiya;
        this.state = state;
        this.dateStateChanged = dateStateChanged;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NotebookType getType() {
        return type;
    }

    public void setType(NotebookType type) {
        this.type = type;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Partiya getPartiya() {
        return partiya;
    }

    public void setPartiya(Partiya partiya) {
        this.partiya = partiya;
    }

    public NotebookState getState() {
        return state;
    }

    public void setState(NotebookState state) {
        this.state = state;
    }

    public Date getDateStateChanged() {
        return dateStateChanged;
    }

    public void setDateStateChanged(Date dateStateChanged) {
        this.dateStateChanged = dateStateChanged;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotebookForSail that = (NotebookForSail) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "NotebookForSail{" +
                "id=" + id +
                ", type=" + type +
                ", serial='" + serial + '\'' +
                ", state=" + state +
                ", dateStateChanged=" + dateStateChanged +
                '}';
    }
}
