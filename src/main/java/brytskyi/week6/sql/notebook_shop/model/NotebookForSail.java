package brytskyi.week6.sql.notebook_shop.model;

import java.util.Date;

/**
 * Created by alexandr on 05.11.16.
 */
public class NotebookForSail {

    private int id;
    private NotebookType type;
    private String serial;
    private Partiya partiya;
    private NotebookState state;
    private Date dateStateChanged;

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

        if (id != that.id) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (serial != null ? !serial.equals(that.serial) : that.serial != null) return false;
        if (partiya != null ? !partiya.equals(that.partiya) : that.partiya != null) return false;
        if (state != that.state) return false;
        return dateStateChanged != null ? dateStateChanged.equals(that.dateStateChanged) : that.dateStateChanged == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (serial != null ? serial.hashCode() : 0);
        result = 31 * result + (partiya != null ? partiya.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (dateStateChanged != null ? dateStateChanged.hashCode() : 0);
        return result;
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
