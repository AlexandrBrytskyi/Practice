package week6.sql.networkshop.dao.sql_dao;

import brytskyi.week6.sql.notebook_shop.dao.sql_dao.simpleMySqlDao.MySQL_DAO;
import brytskyi.week6.sql.notebook_shop.dao.sql_dao.simpleMySqlDao.MySqlDAOWithConnection;
import brytskyi.week6.sql.notebook_shop.model.exceptions.dao_exceptions.NullFieldException;
import brytskyi.week6.sql.notebook_shop.model.production.*;
import brytskyi.week6.sql.notebook_shop.model.selling.Prodaja;
import brytskyi.week6.sql.notebook_shop.model.users.Buyer;
import brytskyi.week6.sql.notebook_shop.model.users.Contacts;
import brytskyi.week6.sql.notebook_shop.model.users.Seller;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by alexandr on 05.11.16.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestMySqlSimpleDao {

    private static MySQL_DAO dao = null;
    private static Display display;
    private static HardMemory hardMemory;
    private static OperativeMemory operativeMemory;
    private static Processor processor;
    private static NotebookModel notebookModel;
    private static VideoMemory videoMemory;
    private static NotebookType notebookType;
    private static NotebookForSail notebookForSail;
    private static Buyer buyer;
    private static Seller seller;

    @BeforeClass
    public static void initDao() {
            dao = new MySQL_DAO(new MySqlDAOWithConnection());
    }


    @Test
    public void _01testAddNoteBookModel() {
        NotebookModel added = dao.addNotebookModel(new NotebookModel("Samsung", "2000asd"));
        System.out.println("Added notebook model " + added);
        Assert.assertTrue(added.getId() != 0);
        notebookModel = added;
    }

    @Test
    public void _02testGetAllNoteBookModel() {
        List<NotebookModel> models = dao.getAllNotebookModels();
        Assert.assertTrue("Array is not empty and not null", (models != null && !models.isEmpty()));
    }

    @Test
    public void _03testRemoveNotebookModel() {
        int addedId = dao.addNotebookModel(new NotebookModel("test removing", "huy")).getId();
        NotebookModel removed = dao.removeNotebookModel(addedId);
        System.out.println("removed notebook model " + removed);
        try {
            dao.getNoteBookModelById(addedId);
        } catch (NoSuchElementException e) {
            Assert.assertTrue("success deleted, db does not contain elem with this id", true);
        }
    }

    @Test
    public void _04testAddProcessor() {
        Processor added = dao.addProcessor(new Processor("Intel", 2500));
        System.out.println("Added processor " + added);
        Assert.assertTrue(added.getId() != 0);
        processor = added;
    }

    @Test
    public void _05testGetAllProcessor() {
        List<Processor> processors = dao.getAllProcessors();
        Assert.assertTrue("processors array is not empty and not null", (processors != null && !processors.isEmpty()));
    }

    @Test
    public void _06testRemoveProcessor() {
        int addedId = dao.addProcessor(new Processor("test removing", 5555)).getId();
        Processor removed = dao.removeProcessor(addedId);
        System.out.println("removed processor " + removed);
        try {
            dao.getProcessorById(addedId);
        } catch (NoSuchElementException e) {
            Assert.assertTrue("success deleted, db does not contain elem with this id", true);
        }
    }

    @Test
    public void _07testAddHardMemory() {
        HardMemory added = dao.addHardMemory(new HardMemory("SSD", 250));
        System.out.println("Added HardMemory " + added);
        Assert.assertTrue(added.getId() != 0);
        hardMemory = added;
    }

    @Test
    public void _08testGetAllHardMemory() {
        List<HardMemory> hardMemories = dao.getAllHardMemory();
        Assert.assertTrue("hardMemories array is not empty and not null", (hardMemories != null && !hardMemories.isEmpty()));
    }

    @Test
    public void _09testRemoveHardMemory() {
        int addedId = dao.addHardMemory(new HardMemory("test removing", 5555)).getId();
        HardMemory removed = dao.removeHardMemory(addedId);
        System.out.println("removed hard memory " + removed);
        try {
            dao.getHardMemoryById(addedId);
        } catch (NoSuchElementException e) {
            Assert.assertTrue("success deleted, db does not contain elem with this id", true);
        }
    }


    @Test
    public void _10testAddOperativeMemory() {
        OperativeMemory added = dao.addOperativeMemory(new OperativeMemory("DDR3", 2048));
        System.out.println("Added Operative Memory " + added);
        Assert.assertTrue(added.getId() != 0);
        operativeMemory = added;
    }

    @Test
    public void _11testGetAllOperativeMemory() {
        List<OperativeMemory> operativeMemories = dao.getAllOperativeMemory();
        Assert.assertTrue("operatives memory array is not empty and not null", (operativeMemories != null && !operativeMemories.isEmpty()));
    }

    @Test
    public void _12testRemoveOperativeMemory() {
        int addedId = dao.addOperativeMemory(new OperativeMemory("test removing", 5555)).getId();
        OperativeMemory removed = dao.removeOperativeMemory(addedId);
        System.out.println("removed operative memory " + removed);
        try {
            dao.getOperativeMemoryById(addedId);
        } catch (NoSuchElementException e) {
            Assert.assertTrue("success deleted, db does not contain elem with this id", true);
        }
    }

    @Test
    public void _13testAddVideoMemory() {
        VideoMemory added = dao.addVideoMemory(new VideoMemory("GForce", 2048));
        System.out.println("Added Video Memory " + added);
        Assert.assertTrue(added.getId() != 0);
        videoMemory = added;
    }

    @Test
    public void _14testGetAllVideoMemory() {
        List<VideoMemory> videoMemories = dao.getAllVideoMemory();
        Assert.assertTrue("video memory array is not empty and not null", (videoMemories != null && !videoMemories.isEmpty()));
    }

    @Test
    public void _15testRemoveVideoMemory() {
        int addedId = dao.addVideoMemory(new VideoMemory("test removing", 5555)).getId();
        VideoMemory removed = dao.removeVideoMemory(addedId);
        System.out.println("video operative memory " + removed);
        try {
            dao.getVideoMemoryById(addedId);
        } catch (NoSuchElementException e) {
            Assert.assertTrue("success deleted, db does not contain elem with this id", true);
        }
    }


    @Test
    public void _16testAddDisplay() {
        Display added = dao.addDisplay(new Display(1640, 1480));
        System.out.println("Added display " + added);
        Assert.assertTrue(added.getId() != 0);
        display = added;
    }

    @Test
    public void _17testGetAllDisplays() {
        List<Display> displays = dao.getAllDisplays();
        Assert.assertTrue("displays array is not empty and not null", (displays != null && !displays.isEmpty()));
    }

    @Test
    public void _18testRemoveDisplay() {
        int addedId = dao.addDisplay(new Display(1900, 1600)).getId();
        Display removed = dao.removeDisplay(addedId);
        System.out.println("display memory " + removed);
        try {
            dao.getDisplayById(addedId);
        } catch (NoSuchElementException e) {
            Assert.assertTrue("success deleted, db does not contain elem with this id", true);
        }
    }


    @Test
    public void _19testAddNotebookType() {
        NotebookType notebookType = new NotebookType("Test adding", hardMemory, operativeMemory, processor, videoMemory, notebookModel, display, 5000);
        System.out.println(display);
       /*if all is ok*/
        try {
            NotebookType added = dao.addNotebookType(notebookType);
            Assert.assertNotNull(added);
            System.out.println("Added new type " + notebookType);
        } catch (NullFieldException e) {
            e.printStackTrace();
        }

          /*if have null field*/
        try {
            notebookType.setHardMemory(null);
            NotebookType added = dao.addNotebookType(notebookType);
        } catch (NullFieldException e) {
            e.printStackTrace();
            Assert.assertTrue(true);
        }
    }

    @Test
    public void _20testRemoveNotebookType() {
        NotebookType notebookType = new NotebookType("Test adding", hardMemory, operativeMemory, processor, videoMemory, notebookModel, display, 5000);
        try {
            NotebookType added = dao.addNotebookType(notebookType);
            Assert.assertNotNull(added);
            System.out.println("Added new type " + added);
            NotebookType removed = dao.removeNotebookType(added.getId());
            Assert.assertNotNull(removed);
            System.out.println("Removed new type " + removed);
        } catch (NullFieldException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void _21testGetAllNoteBookTypes() {
        List<NotebookType> notebookTypes = dao.getAllNotebookTypes();
        notebookType = notebookTypes.get(notebookTypes.size() - 1);
        System.out.println("All types " + notebookTypes);
        Assert.assertTrue("notebookTypes array is not empty and not null", (notebookTypes != null && !notebookTypes.isEmpty()));
    }

    @Test
    public void _22addPartiyaTest() {
        Partiya partiya = new Partiya(notebookType, null);
        List<NotebookForSail> list = new LinkedList<NotebookForSail>() {{
            add(new NotebookForSail(notebookType, "serial test", partiya, NotebookState.NEW));
            add(new NotebookForSail(notebookType, "serial test2", partiya, NotebookState.SELLED));
        }};
        partiya.setNotebooks(list);

        try {
            Partiya added = dao.addPartiya(partiya);
            Assert.assertNotNull(added);
            notebookForSail = added.getNotebooks().get(0);
            System.out.println("added partiya id " + added);
        } catch (NullFieldException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }


    @Test
    public void _23updateNotebookForSailTest() {
        System.out.println("Notebook for sail = " + notebookForSail);
        try {
            NotebookForSail updated = dao.updateNotebook(notebookForSail.getId(), NotebookState.SELLED.toString());
            Assert.assertEquals(updated.getState(), NotebookState.SELLED);
            System.out.println();
        } catch (NullFieldException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }


    @Test
    public void _24getNotebooksByProcCompanyTest() {
        long temp = System.currentTimeMillis();

        NotebookType type = new NotebookType("test 24", hardMemory, operativeMemory, dao.addProcessor(new Processor("AMD" + temp, 3500)),
                videoMemory, dao.addNotebookModel(new NotebookModel("gavno kota", "kakashka 2000")), display, 5000);
        try {
            type = dao.addNotebookType(type);
        } catch (NullFieldException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }

        Partiya partiya = new Partiya(type, null);
        NotebookType finalType = type;
        List<NotebookForSail> forTest = new LinkedList<NotebookForSail>() {{
            add(new NotebookForSail(finalType, "adasadfdsfadsf", partiya, NotebookState.NEW));
            add(new NotebookForSail(finalType, "adfadfdsfaff", partiya, NotebookState.NEW));
        }};
        partiya.setNotebooks(forTest);
        try {
            dao.addPartiya(partiya);
        } catch (NullFieldException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }

        List<NotebookForSail> res = dao.getNotebooks("AMD" + temp, NotebookState.NEW);
        Assert.assertTrue(res.size() == 2);
        System.out.println();
        for (NotebookForSail re : res) {
            System.out.println(re);
        }
    }

    @Test
    public void _25getNotebooksByOperativeMemoryTest() {
        long temp = System.currentTimeMillis();

        NotebookType type = new NotebookType("test 25", hardMemory, dao.addOperativeMemory(new OperativeMemory("huychik", (int) (9000 + temp))), processor,
                videoMemory, dao.addNotebookModel(new NotebookModel("gavno kota", "kakashka 2000")), display, 5000);
        try {
            type = dao.addNotebookType(type);
        } catch (NullFieldException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }

        Partiya partiya = new Partiya(type, null);
        NotebookType finalType = type;
        List<NotebookForSail> forTest = new LinkedList<NotebookForSail>() {{
            add(new NotebookForSail(finalType, "adasadfdsfadsf", partiya, NotebookState.NEW));
            add(new NotebookForSail(finalType, "adfadfdsfaff", partiya, NotebookState.NEW));
        }};
        partiya.setNotebooks(forTest);
        try {
            dao.addPartiya(partiya);
        } catch (NullFieldException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }

        List<NotebookForSail> res = dao.getNotebooks((int) (9000 + temp), NotebookState.NEW);
        Assert.assertTrue(res.size() == 2);
        System.out.println();
        for (NotebookForSail re : res) {
            System.out.println(re);
        }
    }

    @Test
    public void _26getNotebooksByDisplaysTest() {
        long temp = System.currentTimeMillis();

        NotebookType type = new NotebookType("test 26", hardMemory, dao.addOperativeMemory(new OperativeMemory("huychik", (int) (9000 + temp))), processor,
                videoMemory, dao.addNotebookModel(new NotebookModel("gavno kota", "kakashka 2000")), dao.addDisplay(new Display((int) (900 + temp), (int) (600 + temp))), 5000);
        try {
            type = dao.addNotebookType(type);
        } catch (NullFieldException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }

        Partiya partiya = new Partiya(type, null);
        NotebookType finalType = type;
        List<NotebookForSail> forTest = new LinkedList<NotebookForSail>() {{
            add(new NotebookForSail(finalType, "adasadfdsfadsf", partiya, NotebookState.NEW));
            add(new NotebookForSail(finalType, "adfadfdsfaff", partiya, NotebookState.NEW));
        }};
        partiya.setNotebooks(forTest);
        try {
            dao.addPartiya(partiya);
        } catch (NullFieldException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }

        List<NotebookForSail> res = dao.getNotebooks((int) (900 + temp), (int) (600 + temp), NotebookState.NEW);
        System.out.println();
        for (NotebookForSail re : res) {
            System.out.println(re);
        }
        Assert.assertTrue(res.size() == 2);
    }

    @Test
    public void _27addBuyerTest() {
        buyer = new Buyer(new Contacts("Vasia", "Petia", "+380973991848"));
        try {
            buyer = dao.addBuyer(buyer);
            Assert.assertNotNull(buyer);
        } catch (NullFieldException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void _28getBuyersTest() {
        buyer = new Buyer(new Contacts("Vasia", "Petia", "+380973991848"));
        try {
            buyer = dao.addBuyer(buyer);
            List<Buyer> buyers = dao.getAllBuyers();
            System.out.println("All buyers " + buyers);
            Buyer last = buyers.get(buyers.size() - 1);
            Assert.assertEquals(buyer, last);
        } catch (NullFieldException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void _29addSellerTest() {
        seller = new Seller(new Contacts("Huy", "Sabaka", "+380973991848"), 5000, "");
        try {
            seller = dao.addSeller(seller);
            Assert.assertNotNull(seller);
        } catch (NullFieldException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void _28getSellersTest() {
        seller = new Seller(new Contacts("Vasia", "Petia", "+380973991848"), 8000, "");
        try {
            seller = dao.addSeller(seller);
            List<Seller> sellers = dao.getSellers(true);
            System.out.println("All sellers " + sellers);
            Seller last = sellers.get(sellers.size() - 1);
            Assert.assertEquals(seller, last);
        } catch (NullFieldException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void _29updateSellerTest() {
        seller = new Seller(new Contacts("Vasia", "Petia", "+380973991848"), 8000, "");
        try {
            seller = dao.addSeller(seller);
            seller.setWorking(false);
            seller = dao.updateSeller(seller.getId(), false);
            Assert.assertFalse(seller.isWorking());
        } catch (NullFieldException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void _30addProdajaTest() {

        Date start = Date.from(LocalDate.now().minusDays(1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(LocalDate.now().plusDays(1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

        System.out.println("buyer" + buyer.getId());
        System.out.println("seller" + seller.getId());
        Prodaja prodaja = dao.addProdaja( notebookForSail.getId(),buyer.getId(), seller.getId());
        List prodajas1 = dao.getProdajasBuyer(buyer.getId(), start, end);
        List prodajas2 = dao.getProdajasSeller(seller.getId(), start, end);
        Assert.assertEquals(prodajas1.get(0), prodajas2.get(0));

    }


}
