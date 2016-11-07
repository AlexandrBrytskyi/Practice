package brytskyi.week6.sql.notebook_shop.model;

/**
 * Created by alexandr on 05.11.16.
 */
public class NotebookType {

    private int id;
    private String desc;
    private Memory hardMemory;
    private Memory operativeMemory;
    private Processor processor;
    private Memory videoMemory;
    private NotebookModel model;
    private Display display;
    private double price;


    // TODO: 07.11.16 display miis in type!!!
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