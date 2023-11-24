package co.edu.poli.ces3.events.model;

import co.edu.poli.ces3.events.dto.EventDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CRUD {
    public Event create(EventDto eventDto) throws SQLException;

    public ArrayList<Event> all() throws SQLException;

    public Event findByPk(int id) throws SQLException;

    public int update(EventDto eventDto) throws SQLException;

    public int delete(int id) throws SQLException;
}