package co.edu.poli.ces3.events.controller;

import co.edu.poli.ces3.events.model.EventAttendees;

import java.sql.SQLException;

public class EventAttendeesCtr {
    private EventAttendees modelEventAttendees;

    public EventAttendeesCtr() {
        modelEventAttendees = new EventAttendees();
    }

    public int getNumberAttendeesToEvent(int eventId) {
        try {
            return modelEventAttendees.getNumberAttendeesToEvent(eventId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
