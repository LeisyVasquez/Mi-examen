package co.edu.poli.ces3.eventos.servlet;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "appointmentServlet", value = "/appointment")
public class Events extends Servlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + "Hello mundo" + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}
