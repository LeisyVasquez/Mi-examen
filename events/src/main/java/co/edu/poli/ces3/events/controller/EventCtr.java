package co.edu.poli.ces3.events.controller;


import co.edu.poli.ces3.events.model.Event;
import co.edu.poli.ces3.events.dto.EventDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class EventCtr {

    private Event modelEvent;

    public EventCtr() {
        modelEvent = new Event();
    }

    public EventDto addEvent(EventDto event) {
        try {
            Event newEvent = modelEvent.create(event);
            return new EventDto(newEvent.getId(), newEvent.getTitle(), newEvent.getDescription(), newEvent.getDate(), newEvent.getLocation());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<EventDto> getAllEvents() {
        try {
            ArrayList<Event> events = modelEvent.all();
            ArrayList<EventDto> eventsDto = new ArrayList<>();
            for (Event event : events) {
                eventsDto.add(new EventDto(event.getId(), event.getTitle(), event.getDescription(), event.getDate(), event.getLocation()));
            }
            return eventsDto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public EventDto getEventById(int id) {
        try {
            Event event = modelEvent.findByPk(id);
            if(event != null) {
                EventDto eventDto = new EventDto(event.getId(), event.getTitle(), event.getDescription(), event.getDate(), event.getLocation());
                return eventDto;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int updateEvent(EventDto beforeEvent) {
        try {
            return modelEvent.update(beforeEvent);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteEventById(int id) {
        try {
            return modelEvent.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
