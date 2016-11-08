package brytskyi.week6.sql.notebook_shop.model.production;

/**
 * Created by alexandr on 05.11.16.
 */
public class Display {
    private int id;
    private int width;
    private int height;

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
