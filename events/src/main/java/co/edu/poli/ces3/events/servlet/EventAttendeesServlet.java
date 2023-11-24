package co.edu.poli.ces3.events.servlet;

import java.io.*;

import co.edu.poli.ces3.events.controller.EventAttendeesCtr;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "eventAttendeesServlet", value = "/event-attendees")
public class EventAttendeesServlet extends Servlet {
    public void init() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        EventAttendeesCtr eventAttendeesCtr = new EventAttendeesCtr();

        String eventId = req.getParameter("eventId");
        if (eventId == null) {
            out.println("Debe de enviar un id");
        } else {

            int numberAttendees = eventAttendeesCtr.getNumberAttendeesToEvent(Integer.parseInt(eventId));
            out.println(numberAttendees);
        }
        out.flush();
    }
}

