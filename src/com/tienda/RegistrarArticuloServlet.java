package com.tienda;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class RegistrarArticuloServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        double precio = Double.parseDouble(request.getParameter("precio"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        String fotoBase64 = request.getParameter("fotoBase64");

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtils.getConnection();
            String query = "INSERT INTO articulos (nombre, descripcion, precio, cantidad, fotoBase64) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, nombre);
            stmt.setString(2, descripcion);
            stmt.setDouble(3, precio);
            stmt.setInt(4, cantidad);
            stmt.setString(5, fotoBase64);

            int rows = stmt.executeUpdate();
            response.setStatus(rows > 0 ? 200 : 500);
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(500);
        } finally {
            DatabaseUtils.cerrarConexiones(null, stmt, conn);
        }
    }
}
