package net.danielmaly.mdw.hw3;


import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class BookingServlet extends HttpServlet {

    public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();

        String newState = req.getParameter("state");

        if (newState != null && newState.equals("new")) {
            HttpSession session = req.getSession();
            String bookingData = req.getParameter("data");
            if(bookingData != null) {
                session.setAttribute("bookingData", bookingData);
                session.setAttribute("bookingState", newState);
                out.println("Created booking " + bookingData + " with state NEW.");
            }
            else {
                out.println("Missing parameter bookingData");
            }
        }
        else if(newState != null && (newState.equals("payment")|| newState.equals("completed"))) {
            HttpSession session = req.getSession(false);
            if(session == null) {
                out.println("No existing session for this booking.");
            }
            else {
                String currentState = (String) session.getAttribute("bookingState");
                String bookingData = (String) session.getAttribute("bookingData");
                if(currentState.equals("payment") && newState.equals("completed")) {
                    session.invalidate();
                    out.println("Booking " + bookingData  + " is completed. Terminating session. Goodbye!");
                }
                else if(currentState.equals("new") && newState.equals("payment")) {
                    session.setAttribute("bookingState", newState);
                    out.println("Booking " + bookingData + " has been transitioned to state PAYMENT.");
                }
            }
        }
        else {
            out.println("Invalid or missing state parameter. Request has been ignored.");
        }

        out.close();
    }
}
