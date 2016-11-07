package brytskyi.week6.sql.notebook_shop.dao.sql_dao;

import brytskyi.week6.sql.notebook_shop.dao.IProductionDao;
import brytskyi.week6.sql.notebook_shop.model.*;


import java.sql.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by alexandr on 05.11.16.
 */
public class MySQL_DAO implements IProductionDao {

    public static final String DB_URL = "jdbc:mysql://localhost:3306/notebookDB";
    public static final String USER = "root";
    public static final String PASSWORD = "";
    Connection connection = null;

    public MySQL_DAO() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
    }

    public boolean openConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                connection = null;
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public NotebookModel addNotebookModel(NotebookModel model) {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO notebook_models (company, model) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, model.getCompany());
            ps.setString(2, model.getModel());
            if (ps.executeUpdate() == 1) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        NotebookModel toReturn = getNoteBookModelById(rs.getInt(1));
                        return toReturn;
                    }
                    return null;
                }
            } else return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public NotebookModel getNoteBookModelById(int id) {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM notebook_models m WHERE m.id = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    NotebookModel toReturn = new NotebookModel(rs.getInt("id"), rs.getString("company"), rs.getString("model"));
                    return toReturn;
                }
                throw new NoSuchElementException("No model with id " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<NotebookModel> getAllNotebookModels() {
        List<NotebookModel> notebookModels;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM notebook_models m")) {
            try (ResultSet resultSet = ps.executeQuery()) {
                notebookModels = new LinkedList<>();
                while (resultSet.next()) {
                    notebookModels.add(new NotebookModel(resultSet.getInt("id"), resultSet.getString("company"), resultSet.getString("model")));
                }
                return notebookModels;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public NotebookModel removeNotebookModel(int id) {
        NotebookModel removed = getNoteBookModelById(id);
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM notebook_models WHERE id = ?")) {
            ps.setInt(1, id);
            if (ps.executeUpdate() == 1) {
                return removed;
            }
            throw new NoSuchElementException("No model with id " + id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Processor addProcessor(Processor processor) {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO processors (company, frequency) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, processor.getCompany());
            ps.setInt(2, processor.getFrequency());
            if (ps.executeUpdate() == 1)
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        processor.setId(rs.getInt(1));
                        return processor;
                    }
                    return null;
                }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Processor getProcessorById(int id) {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM processors p WHERE p.id = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Processor toReturn = new Processor(rs.getInt("id"), rs.getString("company"), rs.getInt("frequency"));
                    return toReturn;
                }
                throw new NoSuchElementException("No processor with id " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Processor> getAllProcessors() {
        List<Processor> processors;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM processors p")) {
            try (ResultSet rs = ps.executeQuery()) {
                processors = new LinkedList<>();
                while (rs.next()) {
                    processors.add(new Processor(rs.getInt("id"), rs.getString("company"), rs.getInt("frequency")));
                }
                return processors;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Processor removeProcessor(int id) {
        Processor removed = getProcessorById(id);
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM processors WHERE id = ?")) {
            ps.setInt(1, id);
            if (ps.executeUpdate() == 1) {
                return removed;
            }
            throw new NoSuchElementException("No processor with id " + id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public HardMemory addHardMemory(HardMemory hardMemory) {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO hard_memories (company, size) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, hardMemory.getCompany());
            ps.setInt(2, hardMemory.getSize());
            if (ps.executeUpdate() == 1)
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        hardMemory.setId(rs.getInt(1));
                        return hardMemory;
                    }
                    return null;
                }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public HardMemory getHardMemoryById(int id) {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM hard_memories h WHERE h.id = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    HardMemory toReturn = new HardMemory(rs.getInt("id"), rs.getString("company"), rs.getInt("size"));
                    return toReturn;
                }
                throw new NoSuchElementException("No Hard memory with id " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<HardMemory> getAllHardMemory() {
        List<HardMemory> hardMemories;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM hard_memories h")) {
            try (ResultSet rs = ps.executeQuery()) {
                hardMemories = new LinkedList<>();
                while (rs.next()) {
                    hardMemories.add(new HardMemory(rs.getInt("id"), rs.getString("company"), rs.getInt("size")));
                }
                return hardMemories;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public HardMemory removeHardMemory(int id) {
        HardMemory removed = getHardMemoryById(id);
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM hard_memories WHERE id = ?")) {
            ps.setInt(1, id);
            if (ps.executeUpdate() == 1) {
                return removed;
            }
            throw new NoSuchElementException("No hard memory with id " + id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public OperativeMemory addOperativeMemory(OperativeMemory operativeMemory) {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO operative_memories (company, size) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, operativeMemory.getCompany());
            ps.setInt(2, operativeMemory.getSize());
            if (ps.executeUpdate() == 1)
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        operativeMemory.setId(rs.getInt(1));
                        return operativeMemory;
                    }
                    return null;
                }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public OperativeMemory getOperativeMemoryById(int id) {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM operative_memories m WHERE m.id = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    OperativeMemory toReturn = new OperativeMemory(rs.getInt("id"), rs.getString("company"), rs.getInt("size"));
                    return toReturn;
                }
                throw new NoSuchElementException("No Operative memory with id " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<OperativeMemory> getAllOperativeMemory() {
        List<OperativeMemory> operativeMemories;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM operative_memories m")) {
            try (ResultSet rs = ps.executeQuery()) {
                operativeMemories = new LinkedList<>();
                while (rs.next()) {
                    operativeMemories.add(new OperativeMemory(rs.getInt("id"), rs.getString("company"), rs.getInt("size")));
                }
                return operativeMemories;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public OperativeMemory removeOperativeMemory(int id) {
        OperativeMemory removed = getOperativeMemoryById(id);
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM operative_memories WHERE id = ?")) {
            ps.setInt(1, id);
            if (ps.executeUpdate() == 1) {
                return removed;
            }
            throw new NoSuchElementException("No Operative memory with id " + id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public VideoMemory addVideoMemory(VideoMemory videoMemory) {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO video_memories (company, size) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, videoMemory.getCompany());
            ps.setInt(2, videoMemory.getSize());
            if (ps.executeUpdate() == 1)
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        videoMemory.setId(rs.getInt(1));
                        return videoMemory;
                    }
                    return null;
                }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public VideoMemory getVideoMemoryById(int id) {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM video_memories m WHERE m.id = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    VideoMemory toReturn = new VideoMemory(rs.getInt("id"), rs.getString("company"), rs.getInt("size"));
                    return toReturn;
                }
                throw new NoSuchElementException("No Video memory with id " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<VideoMemory> getAllVideoMemory() {
        List<VideoMemory> videoMemories;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM video_memories m")) {
            try (ResultSet rs = ps.executeQuery()) {
                videoMemories = new LinkedList<>();
                while (rs.next()) {
                    videoMemories.add(new VideoMemory(rs.getInt("id"), rs.getString("company"), rs.getInt("size")));
                }
                return videoMemories;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public VideoMemory removeVideoMemory(int id) {
        VideoMemory removed = getVideoMemoryById(id);
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM video_memories WHERE id = ?")) {
            ps.setInt(1, id);
            if (ps.executeUpdate() == 1) {
                return removed;
            }
            throw new NoSuchElementException("No Video memory with id " + id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public Display addDisplay(Display display) {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO displays (width, heigth) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, display.getWidth());
            ps.setInt(2, display.getHeight());
            if (ps.executeUpdate() == 1)
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        display.setId(rs.getInt(1));
                        return display;
                    }
                    return null;
                }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Display getDisplayById(int id) {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM displays d WHERE d.id = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Display toReturn = new Display(rs.getInt("id"), rs.getInt("width"), rs.getInt("heigth"));
                    return toReturn;
                }
                throw new NoSuchElementException("No Display with id " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Display> getAllDisplays() {
        List<Display> displays;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM displays m")) {
            try (ResultSet rs = ps.executeQuery()) {
                displays = new LinkedList<>();
                while (rs.next()) {
                    displays.add(new Display(rs.getInt("id"), rs.getInt("width"), rs.getInt("heigth")));
                }
                return displays;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Display removeDisplay(int id) {
        Display removed = getDisplayById(id);
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM displays WHERE id = ?")) {
            ps.setInt(1, id);
            if (ps.executeUpdate() == 1) {
                return removed;
            }
            throw new NoSuchElementException("No Display with id " + id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public NotebookType addNotebookType(NotebookType notebook) throws NullFieldException {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO " +
                "notebook_types(descr,hard_memory,operative_memory,processor,video_memory,model,display,price)" +
                "VALUES (?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, notebook.getDesc());
            ps.setInt(2, notebook.getHardMemory().getId());
            ps.setInt(3, notebook.getOperativeMemory().getId());
            ps.setInt(4, notebook.getProcessor().getId());
            ps.setInt(5, notebook.getVideoMemory().getId());
            ps.setInt(6, notebook.getModel().getId());
            ps.setInt(7, notebook.getDisplay().getId());
            ps.setDouble(8, notebook.getPrice());
            if (ps.executeUpdate() == 1) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        notebook.setId(rs.getInt(1));
                        return notebook;
                    }
                }
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (NullPointerException e) {
            throw new NullFieldException(e.getMessage());
        }
    }

    @Override
    public NotebookType removeNotebookType(int id) {
        NotebookType removed = getNotebookTypeById(id);
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM notebook_types WHERE id = ?")) {
            ps.setInt(1, id);
            if (ps.executeUpdate() == 1) {
                return removed;
            }
            throw new NoSuchElementException("No NotebookType with id " + id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<NotebookType> getAllNotebookTypes() {
        List<NotebookType> notebookTypes;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM notebook_types t")) {
            try (ResultSet rs = ps.executeQuery()) {
                notebookTypes = new LinkedList<>();
                while (rs.next()) {
                    notebookTypes.add(new NotebookType(
                            rs.getInt("id"),
                            rs.getString("descr"),
                            getHardMemoryById(rs.getInt("hard_memory")),
                            getOperativeMemoryById(rs.getInt("operative_memory")),
                            getProcessorById(rs.getInt("processor")),
                            getVideoMemoryById(rs.getInt("video_memory")),
                            getNoteBookModelById(rs.getInt("model")),
                            rs.getDouble("price"),
                            getDisplayById(rs.getInt("display"))));
                }
                return notebookTypes;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public NotebookType getNotebookTypeById(int id) {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM notebook_types WHERE id = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new NotebookType(
                            rs.getInt("id"),
                            rs.getString("descr"),
                            getHardMemoryById(rs.getInt("hard_memory")),
                            getOperativeMemoryById(rs.getInt("operative_memory")),
                            getProcessorById(rs.getInt("processor")),
                            getVideoMemoryById(rs.getInt("video_memory")),
                            getNoteBookModelById(rs.getInt("model")),
                            rs.getDouble("price"),
                            getDisplayById(rs.getInt("display")));
                }
                throw new NoSuchElementException("No Notebook type with id " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Partiya addPartiya(Partiya partiya) throws NullFieldException {
        if (partiya == null) throw new NullPointerException("Partiya is null!");
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO " +
                "partiyas(patriya_type,price,amountOfNotebooks,dateOfTake) " +
                "VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, partiya.getPatriyaType().getId());
            ps.setDouble(2, partiya.getPrice());
            ps.setInt(3, partiya.getAmountOfNotebooks());
            ps.setTimestamp(4, new Timestamp(partiya.getDateOfTake().getTime()));
            if (ps.executeUpdate() == 1) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        partiya.setId(rs.getInt(1));
                    } else {
                        return null;
                    }
                }
                for (NotebookForSail notebookForSail : partiya.getNotebooks()) {
                    notebookForSail.setId(addNoteBookForSail(notebookForSail));
                }
                return partiya;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    /*we can not add notebook for sail from outside
    * it must be added only with it`s partiya*/
    private int addNoteBookForSail(NotebookForSail notebookForSail) throws NullFieldException {
        if (notebookForSail == null) throw new NullPointerException("Notebook for sail is null");
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO " +
                "notebooks_for_sail (notebook_type,serial_num,partiya,state,dateStateChanged) " +
                "VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, notebookForSail.getType().getId());
            ps.setString(2, notebookForSail.getSerial());
            ps.setInt(3, notebookForSail.getPartiya().getId());
            ps.setString(4, notebookForSail.getState().toString());
            ps.setTimestamp(5, new Timestamp(notebookForSail.getDateStateChanged().getTime()));
            if (ps.executeUpdate() == 1) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } catch (NullPointerException e) {
            throw new NullFieldException(e.getMessage());
        }
    }

    @Override
    public NotebookForSail updateNotebook(NotebookForSail notebookForSail) throws NullFieldException {
        if (notebookForSail == null) throw new NullPointerException("Notebook for sail is null");
        try (PreparedStatement ps = connection.prepareStatement("UPDATE notebooks_for_sail n SET " +
                "notebook_type=?,serial_num=?,partiya=?,state=?,dateStateChanged=? WHERE n.id=?")) {
            ps.setInt(1, notebookForSail.getType().getId());
            ps.setString(2, notebookForSail.getSerial());
            ps.setInt(3, notebookForSail.getPartiya().getId());
            ps.setString(4, notebookForSail.getState().toString());
            ps.setTimestamp(5, new Timestamp(notebookForSail.getDateStateChanged().getTime()));
            ps.setInt(6, notebookForSail.getId());
            if (ps.executeUpdate() == 1) {
                return notebookForSail;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (NullPointerException e) {
            throw new NullFieldException(e.getMessage());
        }
    }

    @Override
    public List<NotebookForSail> getNotebooks(String company, NotebookState state) {
        List<NotebookForSail> res = new LinkedList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT " +
                "n.id, n.notebook_type, n.serial_num, n.partiya, n.state, n.dateStateChanged, p.id " +
                "FROM notebooks_for_sail n " +
                "INNER JOIN notebook_types nt ON n.notebook_type = nt.id " +
                "INNER JOIN processors p ON nt.processor = p.id " +
                "WHERE p.company=? AND n.state=?")) {
            ps.setString(1, company);
            ps.setString(2, state.toString());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    res.add(new NotebookForSail(rs.getInt("id"),
                            getNotebookTypeById(rs.getInt("notebook_type")),
                            rs.getString("serial_num"),
                            null,
                            NotebookState.valueOf(rs.getString("state")),
                            rs.getTimestamp("dateStateChanged")));
                }
                return res;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<NotebookForSail> getNotebooks(int operativeMemory, NotebookState state) {
        List<NotebookForSail> res = new LinkedList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT " +
                "n.id, n.notebook_type, n.serial_num, n.partiya, n.state, n.dateStateChanged, om.id " +
                "FROM notebooks_for_sail n " +
                "INNER JOIN notebook_types nt ON n.notebook_type = nt.id " +
                "INNER JOIN operative_memories om ON nt.operative_memory = om.id " +
                "WHERE om.size=? AND n.state=?")) {
            ps.setInt(1, operativeMemory);
            ps.setString(2, state.toString());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    res.add(new NotebookForSail(rs.getInt("id"),
                            getNotebookTypeById(rs.getInt("notebook_type")),
                            rs.getString("serial_num"),
                            null,
                            NotebookState.valueOf(rs.getString("state")),
                            rs.getTimestamp("dateStateChanged")));
                }
                return res;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<NotebookForSail> getNotebooks(int width, int heigth, NotebookState state) {
        List<NotebookForSail> res = new LinkedList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT " +
                "n.id, n.notebook_type, n.serial_num, n.partiya, n.state, n.dateStateChanged, d.id " +
                "FROM notebooks_for_sail n " +
                "INNER JOIN notebook_types nt ON n.notebook_type = nt.id " +
                "INNER JOIN displays d ON nt.operative_memory = d.id " +
                "WHERE d.width=? AND d.heigth=? AND n.state=?")) {
            ps.setInt(1, width);
            ps.setInt(2, heigth);
            ps.setString(3, state.toString());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    res.add(new NotebookForSail(rs.getInt("id"),
                            getNotebookTypeById(rs.getInt("notebook_type")),
                            rs.getString("serial_num"),
                            null,
                            NotebookState.valueOf(rs.getString("state")),
                            rs.getTimestamp("dateStateChanged")));
                }
                return res;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<NotebookForSail> getNotebooks(NotebookState state, Date periodStateChangedStart, Date periodStateChangedEnd) {
        List<NotebookForSail> res = new LinkedList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT " +
                "n.id, n.notebook_type, n.serial_num, n.partiya, n.state, n.dateStateChanged " +
                "FROM notebooks_for_sail n " +
                "WHERE n.state=? AND n.dateStateChanged BETWEEN ? AND ?")) {
            ps.setTimestamp(2, new Timestamp(periodStateChangedStart.getTime()));
            ps.setTimestamp(3, new Timestamp(periodStateChangedEnd.getTime()));
            ps.setString(1, state.toString());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    res.add(new NotebookForSail(rs.getInt("id"),
                            getNotebookTypeById(rs.getInt("notebook_type")),
                            rs.getString("serial_num"),
                            null,
                            NotebookState.valueOf(rs.getString("state")),
                            rs.getTimestamp("dateStateChanged")));
                }
                return res;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}