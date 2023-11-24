package co.edu.poli.ces3.events.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EventAttendees extends ConnectionMySQL {
    int eventId;
    String docAttendees;
    String registerDate;

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getDocAttendees() {
        return docAttendees;
    }

    public void setDocAttendees(String docAttendees) {
        this.docAttendees = docAttendees;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public int getNumberAttendeesToEvent(int eventId) throws SQLException {
        Connection cnn = this.getConnectionMySQL();
        if (cnn != null) {
            try {
                String sql = "SELECT COUNT(*) FROM event_attendees WHERE event_id = ?;";
                PreparedStatement stmt = cnn.prepareStatement(sql);
                stmt.setInt(1, eventId);
                ResultSet rs = stmt.executeQuery();
                if(rs.next()) {
                    return rs.getInt("COUNT(*)");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                cnn.close();
            }
        }
        return 0;
    }
}
