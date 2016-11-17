package brytskyi.week8.spring.notebook_shop.model.production;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "displays")
public class Display {

    @Id
    @GeneratedValue
    private int id;
    private int width;
    private int height;

    public Display() {
    }

    public Display(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Display(int id, int width, int height) {
        this.id = id;
        this.width = width;
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Display display = (Display) o;

        if (id != display.id) return false;
        if (width != display.width) return false;
        return height == display.height;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Display{" +
                "id=" + id +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
