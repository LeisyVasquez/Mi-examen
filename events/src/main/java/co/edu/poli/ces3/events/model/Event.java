package co.edu.poli.ces3.events.model;

import co.edu.poli.ces3.events.dto.EventDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Event extends ConnectionMySQL implements CRUD{

    int id;
    String title;
    String description;
   String date;
   String location;

   public Event() {

   }
    public Event(int id, String title, String description, String date, String location) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;
    }

    public Event(String title, String description, String date, String location) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "El evento se llama: " + this.title + " su ubicacion es: " + this.location;
    }

    @Override
    public Event create(EventDto eventDto) throws SQLException {
        Connection cnn = this.getConnectionMySQL();
        if (cnn != null) {
            String sql = "INSERT INTO events(title, description, date, location) VALUES(?,?,?,?);";
            try {
                PreparedStatement stmt = cnn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                stmt.setString(1, eventDto.getTitle());
                stmt.setString(2, eventDto.getDescription());
                stmt.setString(3, String.valueOf(eventDto.getDate()));
                stmt.setString(4, eventDto.getLocation());
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                rs.next();
                this.id = rs.getInt(1);
                this.title = eventDto.getTitle();
                this.description = eventDto.getDescription();
                this.date = eventDto.getDate();
                this.location = eventDto.getLocation();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                cnn.close();
            }
            return this;
        }
        else {
            System.out.println("Error de conexión");
        }
        return null;
    }

    @Override
    public ArrayList<Event> all() throws SQLException {
        Connection cnn = this.getConnectionMySQL();
        if (cnn != null) {
            try {
                ArrayList<Event> list = new ArrayList<>();
                String sql = "SELECT * FROM events ORDER BY date;";
                PreparedStatement stmt = cnn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    list.add(new Event(rs.getInt("id"), rs.getString("title"), rs.getString("description"),rs.getString("date"), rs.getString("location")));
                }
                return list;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                cnn.close();
            }
        }
        return null;
    }

    @Override
    public Event findByPk(int id) throws SQLException {
        Connection cnn = this.getConnectionMySQL();
        if (cnn != null) {
            try {
                String sql = "SELECT * FROM events WHERE id = ?;";
                PreparedStatement stmt = cnn.prepareStatement(sql);
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                if(rs.next()) {
                    this.id = rs.getInt("id");
                    this.title =   rs.getString("title");
                    this.description = rs.getString("description");
                    this.date = rs.getString("date");
                    this.location = rs.getString("location");
                    return this;
                } else {
                    return null;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                cnn.close();
            }
        }
        return null;
    }

    @Override
    public int update(EventDto eventDto) throws SQLException{
        Connection cnn = this.getConnectionMySQL();
        if (cnn != null) {
            try {
                String sql = "UPDATE events SET title = ?, description = ?, date = ?, location = ? WHERE id = ?;";
                PreparedStatement stmt = cnn.prepareStatement(sql);
                stmt.setString(1, eventDto.getTitle());
                stmt.setString(2, eventDto.getDescription());
                stmt.setString(3, eventDto.getDate());
                stmt.setString(4, eventDto.getLocation());
                stmt.setInt(5, eventDto.getId());
                return stmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                cnn.close();
            }
        }
        return 0;
    }

    @Override
    public int delete(int id) throws SQLException  {
        Connection cnn = this.getConnectionMySQL();
        if (cnn != null) {
            try {
                String sql = "DELETE FROM events WHERE id = ?;";
                PreparedStatement stmt = cnn.prepareStatement(sql);
                stmt.setInt(1, id);
                return stmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                cnn.close();
            }
        }
        return 0;
    }
}
