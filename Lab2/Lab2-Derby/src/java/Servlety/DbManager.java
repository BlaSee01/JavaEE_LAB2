/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlety;

import Trwałość.Clothes;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author student
 */

public final class DbManager {
public static final String DRIVER = "org.apache.derby.jdbc.ClientDriver"; 
public static final String JDBC_URL = "jdbc:derby://localhost:1527/db"; 
public static final String QUERY = "select * from app.clothes";
private static java.sql.Connection conn;

private DbManager() {
}

public static boolean Connect() throws ClassNotFoundException, SQLException {
    conn = DriverManager.getConnection (JDBC_URL);
    if (conn == null) {
        return false;
    } else {
        return true;
    }
}
public static boolean Disconnect() throws SQLException {
    if (conn == null) {
        return false;
    } else {
    }
    conn.close();
    return true;
}
public static String getData(HttpServletRequest request) throws SQLException { 
    Statement stat = conn.createStatement(); 
    String id = request.getParameter("id");
    String type = request.getParameter("type");
    
    // Utworzenie zapytania SQL z uwzględnieniem parametrów id i type
    String dynamicQuery = QUERY + " WHERE 1=1";
    if (id != null && !id.isEmpty()) {
        dynamicQuery += " AND clothes_id = " + id;
    }
    if (type != null && !type.isEmpty()) {
        dynamicQuery += " AND type = '" + type + "'";
    }
    
    ResultSet rs = stat.executeQuery(dynamicQuery); 
    ResultSetMetaData rsmd = rs.getMetaData(); 
    String wiersz = new String();

    int colCount = rsmd.getColumnCount(); 
    wiersz = wiersz.concat("<table><tr>");
    
    for (int i = 1; i <= colCount; i++) { 
        wiersz = wiersz.concat("<td><b>" + rsmd.getColumnName(i) + "</b></td>");
    }
    wiersz = wiersz.concat("</tr>");
    while (rs.next()) {
        wiersz = wiersz.concat("<tr>");
    
        for (int i = 1; i <= colCount; i++) {
            wiersz = wiersz.concat("<td>" + rs.getString(i) + "</td>");
        }
        wiersz = wiersz.concat("</tr>");
    }
    wiersz = wiersz.concat("</table>");
    
    if (stat != null) {
        stat.close();
    }
    return wiersz;
}
}
