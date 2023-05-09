package com.emergentes.controlador;

import com.emergentes.modelo.Contacto;
import com.emergentes.utiles.ConexionDB;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String op;
            op = (request.getParameter("op") != null) ? request.getParameter("op") : "list";
            ArrayList<Contacto> lista = new ArrayList<Contacto>();
            ConexionDB canal = new ConexionDB();
            Connection conn = canal.conectar();
            PreparedStatement ps;
            ResultSet rs;

            if (op.equals("list")) {

                //obtiene la lista de registro
                String sql = "select * from contactos";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Contacto con = new Contacto();
                    con.setId(rs.getInt("id"));
                    con.setNombre(rs.getString("nombre"));
                    con.setCorreo(rs.getString("correo"));
                    con.setTelefono(rs.getString("telefono"));
                    lista.add(con);
                }
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

            if (op.equals("nuevo")) {
                //nuevo registro
                Contacto co = new Contacto();
                request.setAttribute("con", co);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
            }
            if (op.equals("editar")) {
                //editar registro
                int id = Integer.parseInt(request.getParameter("id"));
                String sql = "select * from contactos where id = ? ";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                Contacto co = new Contacto();
                while (rs.next()) {
                    co.setId(rs.getInt(1));
                    co.setNombre(rs.getString(2));
                    co.setCorreo(rs.getString(3));
                    co.setTelefono(rs.getString(4));
                }
                request.setAttribute("con", co);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
            }
            if (op.equals("eliminar")) {
                //eliminar registro
                int id = Integer.parseInt(request.getParameter("id"));
                String sql = "delete from contactos where id = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);

                ps.executeUpdate();
                response.sendRedirect("MainController");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            String correo = request.getParameter("correo");
            String telefono = request.getParameter("telefono");

            Contacto con = new Contacto();

            con.setNombre(nombre);
            con.setId(id);
            con.setCorreo(correo);
            con.setTelefono(telefono);

            ConexionDB canal = new ConexionDB();
            Connection conn = canal.conectar();

            PreparedStatement ps;

            if (id == 0) {
                //Nuevo registro
                String sql = "insert into contactos(nombre, correo, telefono) values (?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, con.getNombre());
                ps.setString(2, con.getCorreo());
                ps.setString(3, con.getTelefono());

                ps.executeUpdate();
                response.sendRedirect("MainController");
            } else {
                String sql = "update contactos set nombre =?, correo = ?, telefono = ? where id = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, con.getNombre());
                ps.setString(2, con.getCorreo());
                ps.setString(3, con.getTelefono());
                ps.setInt(4, con.getId());
                ps.executeUpdate();
                response.sendRedirect("MainController");
            }
        } catch (SQLException e) {
            System.out.println("Error en SQL " + e.getMessage());
        }

    }
}
