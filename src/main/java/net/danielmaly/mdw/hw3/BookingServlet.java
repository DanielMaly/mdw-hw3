package net.danielmaly.mdw.hw3;


import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class BookingServlet extends HttpServlet {

    public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();

        out.println("Hello, world!");
        out.close();
    }
}
