package co.edu.poli.ces3.events.servlet;

import java.io.*;
import java.util.ArrayList;

import co.edu.poli.ces3.events.controller.EventCtr;
import co.edu.poli.ces3.events.dto.EventDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "eventServlet", value = "/event")
public class EventServlet extends Servlet {

    private Gson gson;
    private GsonBuilder gsonBuilder;

    public void init() {
        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        EventCtr eventCtr = new EventCtr();

        String eventId = req.getParameter("eventId");
        if (eventId == null) {
            ArrayList<EventDto> eventsDto = eventCtr.getAllEvents();
            out.println(this.gson.toJson(eventsDto));
        } else {
            EventDto eventDto = eventCtr.getEventById(Integer.parseInt(eventId));
            if (eventDto != null) {
                out.println(this.gson.toJson(eventDto));
            } else {
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
        }
        out.flush();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("application/json");

        EventCtr eventCtr = new EventCtr();

        JsonObject body = this.getParamsFromPost(req);

        EventDto eventDto = new EventDto(body.get("title").getAsString(), body.get("description").getAsString(), body.get("date").getAsString(), body.get("location").getAsString());
        EventDto newEvent = eventCtr.addEvent(eventDto);
        resp.setStatus(HttpServletResponse.SC_CREATED);
        out.println(gson.toJson(newEvent));
        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("application/json");

        EventCtr eventCtr = new EventCtr();
        
        String eventId = req.getParameter("eventId");
        int isDeletedEvent = eventCtr.deleteEventById(Integer.parseInt(eventId));
        if (isDeletedEvent == 1) {
            out.println("Evento con id " + eventId + " eliminado");
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            out.println("Evento no encontrado");
        }
        out.flush();
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("application/json");
        EventCtr eventCtr = new EventCtr();

        String eventId = request.getParameter("eventId");
        JsonObject body = this.getParamsFromPost(request);


        // Verificar que el usuario si exista
        EventDto eventBefore = eventCtr.getEventById(Integer.parseInt(eventId));

        if (eventBefore != null) {
            int isUpdatedEvent = eventCtr.updateEvent(new EventDto(Integer.parseInt(eventId), body.get("title").getAsString(), body.get("description").getAsString(), body.get("date").getAsString(), body.get("location").getAsString()));
            if (isUpdatedEvent == 1) {
                out.println("Evento con id " + eventId + " actualizado");
            } else {
                out.println("Ocurrió un error inesperado");
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            out.println("Evento no encontrado");
        }
        out.flush();
    }
}
