package co.edu.poli.ces3.eventos.servlet;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;

public class Servlet extends HttpServlet {
    protected JsonObject getParamsFromPost(HttpServletRequest req) throws IOException {
        BufferedReader reader = req.getReader();
        //Es lo mismo que una clase de tipo string pero con más capacidad
        StringBuilder sb = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            sb.append(line + "\n");
            line = reader.readLine();
        }
        reader.close();
        return JsonParser.parseString(sb.toString()).getAsJsonObject();
    }
}
