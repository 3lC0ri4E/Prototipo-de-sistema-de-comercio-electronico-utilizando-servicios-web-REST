package com.tienda;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;

public class BuscarArticulosServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ArticuloDTO> articulos = new ArrayList<>();

        try {
            conn = DatabaseUtils.getConnection();
            String query = "SELECT * FROM articulos WHERE nombre LIKE ? OR descripcion LIKE ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                ArticuloDTO articulo = new ArticuloDTO();
                articulo.setIdArticulo(rs.getInt("idArticulo"));
                articulo.setNombre(rs.getString("nombre"));
                articulo.setDescripcion(rs.getString("descripcion"));
                articulo.setPrecio(rs.getDouble("precio"));
                articulo.setCantidad(rs.getInt("cantidad"));
                articulo.setFotoBase64(rs.getString("fotoBase64"));
                articulos.add(articulo);
            }

            response.setContentType("application/json");
            response.getWriter().write(new Gson().toJson(articulos));

        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(500);
        } finally {
            DatabaseUtils.cerrarConexiones(rs, stmt, conn);
        }
    }
}
