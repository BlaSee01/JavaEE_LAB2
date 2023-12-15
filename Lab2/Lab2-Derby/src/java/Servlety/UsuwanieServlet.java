package Servlety;

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

@WebServlet(name = "UsuwanieServlet", urlPatterns = {"/UsuwanieServlet"})
public class UsuwanieServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        int idToDelete = Integer.parseInt(request.getParameter("id_del"));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lab2-DerbyPU");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Trwałość.Clothes clothing = em.find(Trwałość.Clothes.class, idToDelete);
            if (clothing != null) {
                em.remove(clothing);
                em.getTransaction().commit();
                em.close();

                PrintWriter out = response.getWriter();
                out.println("<html><body>");
                out.println("<h1>Ubranie usunięte pomyślnie!</h1>");
                out.println("</body></html>");
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception ex) {
            // Obsługa błędu
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
             ex.printStackTrace();
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
