package brytskyi.week6.sql.notebook_shop.model.production;

import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;

@Entity
@Table(name = "notebook_types")
public class NotebookType {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "descr")
    private String desc;

    @ManyToOne(targetEntity = HardMemory.class, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "hard_memory", referencedColumnName = "id")
    private Memory hardMemory;

    @ManyToOne(targetEntity = OperativeMemory.class, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "operative_memory", referencedColumnName = "id")
    private Memory operativeMemory;

    @ManyToOne(targetEntity = Processor.class, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "processor", referencedColumnName = "id")
    private Processor processor;

    @ManyToOne(targetEntity = VideoMemory.class,cascade = {CascadeType.MERGE})
    @JoinColumn(name = "video_memory", referencedColumnName = "id")
    private Memory videoMemory;

    @ManyToOne(targetEntity = NotebookModel.class, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "model", referencedColumnName = "id")
    private NotebookModel model;

    @ManyToOne(targetEntity = Display.class, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "display", referencedColumnName = "id")
    private Display display;

    private double price;


    public NotebookType() {
    }

    public NotebookType(int id, String desc, Memory hardMemory, Memory operativeMemory, Processor processor, Memory videoMemory, NotebookModel model, double price, Display display) {
        this.id = id;
        this.desc = desc;
        this.display = display;
        this.hardMemory = hardMemory;
        this.operativeMemory = operativeMemory;
        this.processor = processor;
        this.videoMemory = videoMemory;
        this.model = model;
        this.price = price;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public NotebookType(String desc, Memory hardMemory, Memory operativeMemory, Processor processor, Memory videoMemory, NotebookModel model, Display display, double price) {
        this.desc = desc;
        this.hardMemory = hardMemory;
        this.display = display;
        this.operativeMemory = operativeMemory;
        this.processor = processor;
        this.videoMemory = videoMemory;
        this.model = model;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Memory getHardMemory() {
        return hardMemory;
    }

    public void setHardMemory(Memory hardMemory) {
        this.hardMemory = hardMemory;
    }

    public Memory getOperativeMemory() {
        return operativeMemory;
    }

    public void setOperativeMemory(Memory operativeMemory) {
        this.operativeMemory = operativeMemory;
    }

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public Memory getVideoMemory() {
        return videoMemory;
    }

    public void setVideoMemory(Memory videoMemory) {
        this.videoMemory = videoMemory;
    }

    public NotebookModel getModel() {
        return model;
    }

    public void setModel(NotebookModel model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotebookType that = (NotebookType) o;

        if (id != that.id) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (desc != null ? !desc.equals(that.desc) : that.desc != null) return false;
        if (hardMemory != null ? !hardMemory.equals(that.hardMemory) : that.hardMemory != null) return false;
        if (operativeMemory != null ? !operativeMemory.equals(that.operativeMemory) : that.operativeMemory != null)
            return false;
        if (processor != null ? !processor.equals(that.processor) : that.processor != null) return false;
        if (videoMemory != null ? !videoMemory.equals(that.videoMemory) : that.videoMemory != null) return false;
        if (model != null ? !model.equals(that.model) : that.model != null) return false;
        return display != null ? display.equals(that.display) : that.display == null;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "NotebookType{" +
                "id=" + id +
                ", desc='" + desc + '\'' +
                ", hardMemory=" + hardMemory +
                ", operativeMemory=" + operativeMemory +
                ", processor=" + processor +
                ", videoMemory=" + videoMemory +
                ", model=" + model +
                ", price=" + price +
                '}';
    }
}
