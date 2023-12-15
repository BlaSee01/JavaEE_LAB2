/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlety;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author student
 */
@WebServlet(name = "Wypisywanie", urlPatterns = {"/Wypisywanie"})
public class Wypisywanie extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html; charset=UTF-8");
    String id = request.getParameter("id");
    String type = request.getParameter("type");
    String sort = request.getParameter("sort");

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lab2-DerbyPU");
    EntityManager em = emf.createEntityManager();

    TypedQuery<Trwałość.Clothes> query;

    if ((id != null && !id.isEmpty()) && (type != null && !type.isEmpty())) {
        query = em.createNamedQuery("Clothes.findByClothesIdAndType", Trwałość.Clothes.class);
        query.setParameter("clothesId", Integer.parseInt(id));
        query.setParameter("type", type);
    } else if (id != null && !id.isEmpty()) {
        query = em.createNamedQuery("Clothes.findByClothesId", Trwałość.Clothes.class);
        query.setParameter("clothesId", Integer.parseInt(id));
    } else if (type != null && !type.isEmpty()) {
        query = em.createNamedQuery("Clothes.findByType", Trwałość.Clothes.class);
        query.setParameter("type", type);
    } else {
        // Domyślnie pobierz wszystkie dane
        query = em.createNamedQuery("Clothes.findAll", Trwałość.Clothes.class);
    }

    try (PrintWriter out = response.getWriter()) {
        out.println("<html>");
        out.println("<head> <meta><link rel='stylesheet' href='Style/css/components.css'>");
        out.println("<link rel='stylesheet' href='Style/css/icons.css'>");
        out.println("<link rel='stylesheet' href='Style/css/responsee.css'>");
        out.println("<body>");

        List<Trwałość.Clothes> results = query.getResultList();

        if (sort != null && sort.equals("on")) {
            results.sort(Comparator.comparing(Trwałość.Clothes::getName));
        }

        Trwałość.Clothes clothes = new Trwałość.Clothes();
        String htmlTable = clothes.getListaHTML(results);
        out.println(htmlTable);
        
        out.println("<a class=\' button rounded-full-btn reload-btn s-2 margin-bottom' href=");
        out.println(request.getHeader("referer"));
        out.println("><i class='icon-sli-arrow-left'>Powrót</i></a>");
        out.println("</body></html>");
    } catch (PersistenceException ex) {
        Logger.getLogger(Wypisywanie.class.getName()).log(Level.SEVERE, null, ex);
    }
}

private boolean polaczenie = false; 
private String data;

private String getDataFromDb(HttpServletRequest request) {
    try {
        polaczenie = DbManager.Connect(); 
        if (polaczenie) {
            data = DbManager.getData(request); // Przekazanie żądania do getData()
            polaczenie = DbManager.Disconnect();
        }
    } catch (ClassNotFoundException | SQLException ex) {
        Logger.getLogger(Wypisywanie.class.getName()).log(Level.SEVERE, null, ex);
    }
    return data;
}

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
