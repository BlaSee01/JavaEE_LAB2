package Servlety;

import Trwałość.Clothes;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DodawanieServlet", urlPatterns = {"/DodawanieServlet"})
public class DodawanieServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String name = request.getParameter("name");
        String gendre = request.getParameter("gendre");
        String type = request.getParameter("type");
        int price = Integer.parseInt(request.getParameter("price_pln"));
        String manufacturer = request.getParameter("manufacturer");
        boolean isPolishManufacturer = request.getParameter("is_polish_manufacturer") != null;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lab2-DerbyPU");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Trwałość.Clothes newClothing = new Trwałość.Clothes();
            newClothing.setName(name);
            newClothing.setGendre(gendre);
            newClothing.setType(type);
            newClothing.setPricePln(price);
            newClothing.setManufacturer(manufacturer);
            newClothing.setIsPolishManufacturer(isPolishManufacturer);

            em.persist(newClothing);
            em.getTransaction().commit();
            em.close();

            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>Ubranie dodane pomyślnie!</h1>");
            out.println("</body></html>");
        } catch (Exception ex) {
            // Obsługa błędu
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

