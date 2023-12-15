package Servlety;

import Trwałość.Clothes;
import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet(name = "AktualizacjaServlet", urlPatterns = {"/AktualizacjaServlet"})
public class AktualizacjaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        int idToUpdate = Integer.parseInt(request.getParameter("id_upd"));
        String newName = request.getParameter("name_upd");
        String newGendre = request.getParameter("gendre_upd");
        String newType = request.getParameter("type_upd");
        int newPrice = Integer.parseInt(request.getParameter("price_pln_upd"));
        String newManufacturer = request.getParameter("manufacturer_upd");
        boolean isPolishManufacturer = request.getParameter("is_polish_manufacturer_upd") != null;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lab2-DerbyPU");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Trwałość.Clothes clothing = em.find(Trwałość.Clothes.class, idToUpdate);
            if (clothing != null) {
                clothing.setName(newName);
                clothing.setGendre(newGendre);
                clothing.setType(newType);
                clothing.setPricePln(newPrice);
                clothing.setManufacturer(newManufacturer);
                clothing.setIsPolishManufacturer(isPolishManufacturer);

                em.merge(clothing);
                em.getTransaction().commit();
                em.close();

                PrintWriter out = response.getWriter();
                out.println("<html><body>");
                out.println("<h1>Ubranie zaktualizowane pomyślnie!</h1>");
                out.println("</body></html>");
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
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

