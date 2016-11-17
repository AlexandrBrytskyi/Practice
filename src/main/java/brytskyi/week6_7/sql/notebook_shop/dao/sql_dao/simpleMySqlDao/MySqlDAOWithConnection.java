package brytskyi.week6_7.sql.notebook_shop.dao.sql_dao.simpleMySqlDao;


import brytskyi.week6_7.sql.notebook_shop.model.exceptions.dao_exceptions.NullFieldException;
import brytskyi.week6_7.sql.notebook_shop.model.production.*;
import brytskyi.week6_7.sql.notebook_shop.model.selling.Prodaja;
import brytskyi.week6_7.sql.notebook_shop.model.users.Buyer;
import brytskyi.week6_7.sql.notebook_shop.model.users.Contacts;
import brytskyi.week6_7.sql.notebook_shop.model.users.Seller;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class MySqlDAOWithConnection implements IMySqlDaoWithConnection {

    @Override
    public NotebookModel addNotebookModel(NotebookModel model, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO notebook_models (company, model) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, model.getCompany());
            ps.setString(2, model.getModel());
            if (ps.executeUpdate() == 1) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        NotebookModel toReturn = getNoteBookModelById(rs.getInt(1), connection);
                        return toReturn;
                    }
                    return null;
                }
            } else return null;
        }
    }

    @Override
    public NotebookModel getNoteBookModelById(int id, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM notebook_models m WHERE m.id = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    NotebookModel toReturn = new NotebookModel(rs.getInt("id"), rs.getString("company"), rs.getString("model"));
                    return toReturn;
                }
                throw new NoSuchElementException("No model with id " + id);
            }
        }
    }

    @Override
    public List<NotebookModel> getAllNotebookModels(Connection connection) throws SQLException {
        List<NotebookModel> notebookModels;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM notebook_models m")) {
            try (ResultSet resultSet = ps.executeQuery()) {
                notebookModels = new LinkedList<>();
                while (resultSet.next()) {
                    notebookModels.add(new NotebookModel(resultSet.getInt("id"), resultSet.getString("company"), resultSet.getString("model")));
                }
                return notebookModels;
            }
        }
    }

    @Override
    public NotebookModel removeNotebookModel(int id, Connection connection) throws SQLException {
        NotebookModel removed = getNoteBookModelById(id, connection);
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM notebook_models WHERE id = ?")) {
            ps.setInt(1, id);
            if (ps.executeUpdate() == 1) {
                return removed;
            }
            throw new NoSuchElementException("No model with id " + id);
        }
    }

    @Override
    public Processor addProcessor(Processor processor, Connection connection) throws SQLException {
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
        }
    }

    @Override
    public Processor getProcessorById(int id, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM processors p WHERE p.id = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Processor toReturn = new Processor(rs.getInt("id"), rs.getString("company"), rs.getInt("frequency"));
                    return toReturn;
                }
                throw new NoSuchElementException("No processor with id " + id);
            }
        }
    }

    @Override
    public List<Processor> getAllProcessors(Connection connection) throws SQLException {
        List<Processor> processors;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM processors p")) {
            try (ResultSet rs = ps.executeQuery()) {
                processors = new LinkedList<>();
                while (rs.next()) {
                    processors.add(new Processor(rs.getInt("id"), rs.getString("company"), rs.getInt("frequency")));
                }
                return processors;
            }
        }
    }

    @Override
    public Processor removeProcessor(int id, Connection connection) throws SQLException {
        Processor removed = getProcessorById(id, connection);
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM processors WHERE id = ?")) {
            ps.setInt(1, id);
            if (ps.executeUpdate() == 1) {
                return removed;
            }
            throw new NoSuchElementException("No processor with id " + id);
        }
    }

    @Override
    public HardMemory addHardMemory(HardMemory hardMemory, Connection connection) throws SQLException {
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
        }
    }

    @Override
    public HardMemory getHardMemoryById(int id, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM hard_memories h WHERE h.id = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    HardMemory toReturn = new HardMemory(rs.getInt("id"), rs.getString("company"), rs.getInt("size"));
                    return toReturn;
                }
                throw new NoSuchElementException("No Hard memory with id " + id);
            }
        }
    }

    @Override
    public List<HardMemory> getAllHardMemory(Connection connection) throws SQLException {
        List<HardMemory> hardMemories;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM hard_memories h")) {
            try (ResultSet rs = ps.executeQuery()) {
                hardMemories = new LinkedList<>();
                while (rs.next()) {
                    hardMemories.add(new HardMemory(rs.getInt("id"), rs.getString("company"), rs.getInt("size")));
                }
                return hardMemories;
            }
        }
    }

    @Override
    public HardMemory removeHardMemory(int id, Connection connection) throws SQLException {
        HardMemory removed = getHardMemoryById(id, connection);
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM hard_memories WHERE id = ?")) {
            ps.setInt(1, id);
            if (ps.executeUpdate() == 1) {
                return removed;
            }
            throw new NoSuchElementException("No hard memory with id " + id);
        }
    }


    @Override
    public OperativeMemory addOperativeMemory(OperativeMemory operativeMemory, Connection connection) throws SQLException {
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
        }
    }

    @Override
    public OperativeMemory getOperativeMemoryById(int id, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM operative_memories m WHERE m.id = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    OperativeMemory toReturn = new OperativeMemory(rs.getInt("id"), rs.getString("company"), rs.getInt("size"));
                    return toReturn;
                }
                throw new NoSuchElementException("No Operative memory with id " + id);
            }
        }
    }

    @Override
    public List<OperativeMemory> getAllOperativeMemory(Connection connection) throws SQLException {
        List<OperativeMemory> operativeMemories;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM operative_memories m")) {
            try (ResultSet rs = ps.executeQuery()) {
                operativeMemories = new LinkedList<>();
                while (rs.next()) {
                    operativeMemories.add(new OperativeMemory(rs.getInt("id"), rs.getString("company"), rs.getInt("size")));
                }
                return operativeMemories;
            }
        }
    }

    @Override
    public OperativeMemory removeOperativeMemory(int id, Connection connection) throws SQLException {
        OperativeMemory removed = getOperativeMemoryById(id, connection);
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM operative_memories WHERE id = ?")) {
            ps.setInt(1, id);
            if (ps.executeUpdate() == 1) {
                return removed;
            }
            throw new NoSuchElementException("No Operative memory with id " + id);
        }
    }

    @Override
    public VideoMemory addVideoMemory(VideoMemory videoMemory, Connection connection) throws SQLException {
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
        }
    }

    @Override
    public VideoMemory getVideoMemoryById(int id, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM video_memories m WHERE m.id = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    VideoMemory toReturn = new VideoMemory(rs.getInt("id"), rs.getString("company"), rs.getInt("size"));
                    return toReturn;
                }
                throw new NoSuchElementException("No Video memory with id " + id);
            }
        }
    }

    @Override
    public List<VideoMemory> getAllVideoMemory(Connection connection) throws SQLException {
        List<VideoMemory> videoMemories;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM video_memories m")) {
            try (ResultSet rs = ps.executeQuery()) {
                videoMemories = new LinkedList<>();
                while (rs.next()) {
                    videoMemories.add(new VideoMemory(rs.getInt("id"), rs.getString("company"), rs.getInt("size")));
                }
                return videoMemories;
            }
        }
    }

    @Override
    public VideoMemory removeVideoMemory(int id, Connection connection) throws SQLException {
        VideoMemory removed = getVideoMemoryById(id, connection);
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM video_memories WHERE id = ?")) {
            ps.setInt(1, id);
            if (ps.executeUpdate() == 1) {
                return removed;
            }
            throw new NoSuchElementException("No Video memory with id " + id);
        }
    }


    @Override
    public Display addDisplay(Display display, Connection connection) throws SQLException {
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
        }
    }

    @Override
    public Display getDisplayById(int id, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM displays d WHERE d.id = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Display toReturn = new Display(rs.getInt("id"), rs.getInt("width"), rs.getInt("heigth"));
                    return toReturn;
                }
                throw new NoSuchElementException("No Display with id " + id);
            }
        }
    }

    @Override
    public List<Display> getAllDisplays(Connection connection) throws SQLException {
        List<Display> displays;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM displays m")) {
            try (ResultSet rs = ps.executeQuery()) {
                displays = new LinkedList<>();
                while (rs.next()) {
                    displays.add(new Display(rs.getInt("id"), rs.getInt("width"), rs.getInt("heigth")));
                }
                return displays;
            }
        }
    }

    @Override
    public Display removeDisplay(int id, Connection connection) throws SQLException {
        Display removed = getDisplayById(id, connection);
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM displays WHERE id = ?")) {
            ps.setInt(1, id);
            if (ps.executeUpdate() == 1) {
                return removed;
            }
            throw new NoSuchElementException("No Display with id " + id);
        }
    }

    @Override
    public NotebookType addNotebookType(NotebookType notebook, Connection connection) throws NullFieldException, SQLException {
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
        } catch (NullPointerException e) {
            throw new NullFieldException(e.getMessage());
        }
    }

    @Override
    public NotebookType removeNotebookType(int id, Connection connection) throws SQLException {
        NotebookType removed = getNotebookTypeById(id, connection);
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM notebook_types WHERE id = ?")) {
            ps.setInt(1, id);
            if (ps.executeUpdate() == 1) {
                return removed;
            }
            throw new NoSuchElementException("No NotebookType with id " + id);
        }
    }

    @Override
    public List<NotebookType> getAllNotebookTypes(Connection connection) throws SQLException {
        List<NotebookType> notebookTypes;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM notebook_types t")) {
            try (ResultSet rs = ps.executeQuery()) {
                notebookTypes = new LinkedList<>();
                while (rs.next()) {
                    notebookTypes.add(new NotebookType(
                            rs.getInt("id"),
                            rs.getString("descr"),
                            getHardMemoryById(rs.getInt("hard_memory"), connection),
                            getOperativeMemoryById(rs.getInt("operative_memory"), connection),
                            getProcessorById(rs.getInt("processor"), connection),
                            getVideoMemoryById(rs.getInt("video_memory"), connection),
                            getNoteBookModelById(rs.getInt("model"), connection),
                            rs.getDouble("price"),
                            getDisplayById(rs.getInt("display"), connection)));
                }
                return notebookTypes;
            }
        }
    }

    @Override
    public NotebookType getNotebookTypeById(int id, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM notebook_types WHERE id = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new NotebookType(
                            rs.getInt("id"),
                            rs.getString("descr"),
                            getHardMemoryById(rs.getInt("hard_memory"), connection),
                            getOperativeMemoryById(rs.getInt("operative_memory"), connection),
                            getProcessorById(rs.getInt("processor"), connection),
                            getVideoMemoryById(rs.getInt("video_memory"), connection),
                            getNoteBookModelById(rs.getInt("model"), connection),
                            rs.getDouble("price"),
                            getDisplayById(rs.getInt("display"), connection));
                }
                throw new NoSuchElementException("No Notebook type with id " + id);
            }
        }
    }

    @Override
    public Partiya addPartiya(Partiya partiya, Connection connection) throws NullFieldException, SQLException {
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
                    notebookForSail.setId(addNoteBookForSail(notebookForSail, connection));
                }
                return partiya;
            }
            return null;
        }
    }


    /*we can not add notebook for sail from outside
    * it must be added only with it`s partiya*/
    private int addNoteBookForSail(NotebookForSail notebookForSail, Connection connection) throws NullFieldException {
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
    public NotebookForSail updateNotebook(int id, String state, Connection connection) throws NullFieldException, SQLException {
        try (PreparedStatement ps = connection.prepareStatement("UPDATE notebooks_for_sail n SET " +
                "state=?, n.dateStateChanged=? WHERE n.id=?")) {
            ps.setString(1, state);
            ps.setInt(3, id);
            ps.setTimestamp(2, new Timestamp(new java.util.Date().getTime()));
            if (ps.executeUpdate() == 1) {
                return getNotebookForSail(id, connection);
            }
            return null;
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw new NullFieldException(e.getMessage());
        }
    }


    private NotebookForSail getNotebookForSail(int id, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * " +
                "FROM notebooks_for_sail n WHERE n.id = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new NotebookForSail(rs.getInt("id"),
                            getNotebookTypeById(rs.getInt("notebook_type"), connection),
                            rs.getString("serial_num"),
                            null,
                            NotebookState.valueOf(rs.getString("state")),
                            rs.getTimestamp("dateStateChanged"));
                }
                return null;
            }
        }
    }


    @Override
    public List<NotebookForSail> getNotebooks(String company, NotebookState state, Connection connection) throws SQLException {
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
                            getNotebookTypeById(rs.getInt("notebook_type"), connection),
                            rs.getString("serial_num"),
                            null,
                            NotebookState.valueOf(rs.getString("state")),
                            rs.getTimestamp("dateStateChanged")));
                }
                return res;
            }
        }
    }

    @Override
    public List<NotebookForSail> getNotebooks(int operativeMemory, NotebookState state, Connection connection) throws SQLException {
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
                            getNotebookTypeById(rs.getInt("notebook_type"), connection),
                            rs.getString("serial_num"),
                            null,
                            NotebookState.valueOf(rs.getString("state")),
                            rs.getTimestamp("dateStateChanged")));
                }
                return res;
            }
        }
    }

    @Override
    public List<NotebookForSail> getNotebooks(int width, int heigth, NotebookState state, Connection connection) throws SQLException {
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
                            getNotebookTypeById(rs.getInt("notebook_type"), connection),
                            rs.getString("serial_num"),
                            null,
                            NotebookState.valueOf(rs.getString("state")),
                            rs.getTimestamp("dateStateChanged")));
                }
                return res;
            }
        }
    }

    @Override
    public List<NotebookForSail> getNotebooks(NotebookState state, java.util.Date periodStateChangedStart, java.util.Date periodStateChangedEnd, Connection connection) throws SQLException {
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
                            getNotebookTypeById(rs.getInt("notebook_type"), connection),
                            rs.getString("serial_num"),
                            null,
                            NotebookState.valueOf(rs.getString("state")),
                            rs.getTimestamp("dateStateChanged")));
                }
                return res;
            }
        }
    }

    @Override
    public List<NotebookForSail> getNotebooksByKriteria(int hardMemory,
                                                        int operativeMemory, int processor,
                                                        int videoMemory, int model, int display,
                                                        double pricemin, double priceMax, Connection connection) throws SQLException {
        List<NotebookForSail> res = new LinkedList<>();
        StringBuilder querryConditionBuilder = new StringBuilder();
        querryConditionBuilder.append(hardMemory != 0 ? " t.hard_memory = " + hardMemory + " AND" : null);
        querryConditionBuilder.append(operativeMemory != 0 ? " t.operative_memory = " + operativeMemory + " AND" : null);
        querryConditionBuilder.append(videoMemory != 0 ? " t.video_memory = " + videoMemory + " AND" : null);
        querryConditionBuilder.append(model != 0 ? " t.model = " + model + " AND" : null);
        querryConditionBuilder.append(display != 0 ? " t.display = " + display + " AND" : null);
        if (pricemin != 0 && priceMax != 0) {
            querryConditionBuilder.append(" t.price BETWEEN " + pricemin + " AND " + priceMax + " AND ");
        } else if (priceMax != 0) {
            querryConditionBuilder.append(" t.price < " + priceMax);
        } else {
            querryConditionBuilder.append(" t.price > " + pricemin);
        }
        try (PreparedStatement s = connection.prepareStatement("SELECT * FROM notebooks_for_sail n " +
                "INNER JOIN notebook_types t ON n.notebook_type = t.id " +
                "WHERE" + querryConditionBuilder.toString())) {
            try (ResultSet rs = s.executeQuery()) {
                while (rs.next()) {
                    res.add(new NotebookForSail(rs.getInt("id"),
                            getNotebookTypeById(rs.getInt("notebook_type"), connection),
                            rs.getString("serial_num"),
                            null,
                            NotebookState.valueOf(rs.getString("state")),
                            rs.getTimestamp("dateStateChanged")));
                }
            }
            return res;
        }
    }

    @Override
    public Buyer addBuyer(Buyer buyer, Connection connection) throws NullFieldException, SQLException {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO buyers(" +
                "contact, money) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, addContact(buyer.getContacts(), connection).getId());
            ps.setDouble(2, buyer.getMoneySpent());
            if (ps.executeUpdate() == 1) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        buyer.setId(rs.getInt(1));
                        return buyer;
                    }
                }
            }
            return null;
        }
    }

    private Contacts addContact(Contacts contacts, Connection connection) throws NullFieldException, SQLException {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO contacts(" +
                "name, surname, phone) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, contacts.getName());
            ps.setString(2, contacts.getSurname());
            ps.setString(3, contacts.getPhone());
            if (ps.executeUpdate() == 1) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        contacts.setId(rs.getInt(1));
                        return contacts;
                    }
                }
            }
            return null;
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw new NullFieldException(e.getMessage());
        }
    }

    private Contacts getContact(int id, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM contacts c WHERE " +
                "c.id = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Contacts(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getString("phone"));
                }
            }
            return null;
        }
    }

    @Override
    public List<Buyer> getAllBuyers(Connection connection) throws SQLException {
        List<Buyer> buyers = new LinkedList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM buyers")) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    buyers.add(new Buyer(rs.getInt("id"), getContact(rs.getInt("contact"), connection),
                            getProdajasBuyer(rs.getInt("id"),new Date(Long.MIN_VALUE), new Date(Long.MAX_VALUE), connection),
                            rs.getDouble("money")));
                }
            }
            return buyers;
        }
    }

    @Override
    public Seller addSeller(Seller seller, Connection connection) throws NullFieldException, SQLException {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO sellers(" +
                "contact, salary, isworking) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, addContact(seller.getContacts(), connection).getId());
            ps.setDouble(2, seller.getSalary());
            ps.setBoolean(3, seller.isWorking());
            if (ps.executeUpdate() == 1) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        seller.setId(rs.getInt(1));
                        return seller;
                    }
                }
            }
            return null;
        }
    }

    @Override
    public List<Seller> getSellers(boolean working, Connection connection) throws SQLException {
        List<Seller> sellers = new LinkedList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM sellers s WHERE s.isworking = ?")) {
            ps.setBoolean(1, working);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    sellers.add(new Seller(rs.getInt("id"), getContact(rs.getInt("contact"), connection), new LinkedList<Prodaja>(),
                            rs.getDouble("salary"), rs.getBoolean("isworking"), ""));
                }
            }
            return sellers;
        }
    }


    @Override
    public Seller updateSeller(int sellerID, boolean isWorking, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("UPDATE sellers s SET s.isworking = ? WHERE s.id = ?")) {
            ps.setBoolean(1, isWorking);
            ps.setInt(2, sellerID);
            if (ps.executeUpdate() == 1) {
                return getSeller(sellerID, connection);
            }
            return null;
        }
    }

    @Override
    public Buyer getBuyer(String phone, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM buyers b WHERE b.phone = ?")) {
            ps.setString(1, phone);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Buyer(rs.getInt("id"), getContact(rs.getInt("contact"), connection),
                            getProdajasBuyer(rs.getInt("id"),new Date(Long.MIN_VALUE), new Date(Long.MAX_VALUE), connection),
                            rs.getDouble("money"));
                }
            }
            return null;
        }
    }

    @Override
    public Seller getSeller(String name, String pass, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM sellers s " +
                "INNER JOIN contcts c  on s.id = c.id WHERE c.name = ? AND s.pass = ?")) {
            ps.setString(1, name);
            ps.setString(1, pass);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Seller(rs.getInt("id"), getContact(rs.getInt("contact"), connection),
                            getProdajasSeller(rs.getInt("id"),new Date(Long.MIN_VALUE), new Date(Long.MAX_VALUE), connection),
                            rs.getDouble("salary"), rs.getBoolean("isworking"), "");
                }
            }
            return null;
        }
    }

    private Seller getSeller(int sellerID, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM sellers s WHERE s.id = ?")) {
            ps.setInt(1, sellerID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Seller(rs.getInt("id"), getContact(rs.getInt("contact"), connection),
                            getProdajasSeller(rs.getInt("id"), new Date(Long.MIN_VALUE), new Date(Long.MAX_VALUE), connection),
                            rs.getDouble("salary"), rs.getBoolean("isworking"), "");
                }
            }
            return null;
        }
    }

    private Buyer getBuyer(int buyerId, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM buyers b WHERE b.id = ?")) {
            ps.setInt(1, buyerId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Buyer(rs.getInt("id"), getContact(rs.getInt("contact"), connection),
                            getProdajasBuyer(rs.getInt("id"),new Date(Long.MIN_VALUE), new Date(Long.MAX_VALUE), connection),
                            rs.getDouble("money"));
                }
            }
            return null;
        }
    }

    @Override
    public Prodaja addProdaja(int notebookID, int buyerID, int sellerID, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO prodajas(" +
                "seller, buyer, notebook) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, sellerID);
            ps.setInt(2, buyerID);
            ps.setInt(3, notebookID);
            if (ps.executeUpdate() == 1) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        return getProdaja(rs.getInt(1), connection);
                    }
                }
            }
            return null;
        }
    }

    private Prodaja getProdaja(int id, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM prodajas p" +
                " WHERE p.id=?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Prodaja(rs.getInt("id"), getBuyer(rs.getInt("buyer"), connection), getSeller(rs.getInt("seller"), connection),
                            getNotebookForSail(rs.getInt("notebook"), connection));
                }
            }
            return null;
        }
    }

    public List<Prodaja> getProdajasBuyer(int buyer, java.util.Date begin, java.util.Date end, Connection connection) throws SQLException {
        List<Prodaja> prodajas = new LinkedList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM prodajas p" +
                " INNER JOIN notebooks_for_sail n ON (p.notebook=n.id) WHERE" +
                " p.buyer=? and n.dateStateChanged BETWEEN ? AND ?")) {
            ps.setInt(1, buyer);
            ps.setDate(2, new java.sql.Date(begin.getTime()));
            ps.setDate(3, new java.sql.Date(end.getTime()));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    prodajas.add(new Prodaja(rs.getInt("id"), getBuyer(rs.getInt("buyer"), connection), getSeller(rs.getInt("seller"), connection),
                            getNotebookForSail(rs.getInt("notebook"), connection)));
                }
            }
            return prodajas;
        }
    }

    @Override
    public List<Prodaja> getProdajasSeller(int seller, java.util.Date begin, java.util.Date end, Connection connection) throws SQLException {
        List<Prodaja> prodajas = new LinkedList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM prodajas p" +
                " INNER JOIN notebooks_for_sail n ON (p.notebook=n.id) WHERE" +
                " p.seller=? and n.dateStateChanged BETWEEN ? AND ?")) {
            ps.setInt(1, seller);
            ps.setDate(2, new java.sql.Date(begin.getTime()));
            ps.setDate(3, new java.sql.Date(end.getTime()));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    prodajas.add(new Prodaja(rs.getInt("id"), getBuyer(rs.getInt("buyer"), connection), getSeller(rs.getInt("seller"), connection),
                            getNotebookForSail(rs.getInt("notebook"), connection)));
                }
            }
            return prodajas;
        }
    }

}
