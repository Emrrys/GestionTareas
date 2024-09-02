package controlador;

import dao.TareaDAO;
import modelo.Tarea;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.sql.Date;

@WebServlet("/TareaControlador")
public class TareaControlador extends HttpServlet {

    private TareaDAO tareaDAO = new TareaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "lista";
        }

        switch (action) {
            case "lista":
                listarTareas(request, response);
                break;
            case "editar":
                mostrarFormularioCrear(request, response);
                break;
            case "borrar":
                eliminarTarea(request, response);
                break;
            default:
                listarTareas(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "insertar";
        }

        switch (action) {
            case "insertar":
                insertarTarea(request, response);
                break;
            case "actualizar":
                actualizarTarea(request, response);
                break;
            default:
                listarTareas(request, response);
                break;
        }
    }

    private void listarTareas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Tarea> listaTareas = tareaDAO.listar(); // Obtener la lista de tareas
        request.setAttribute("listaTareas", listaTareas); // Establecer el atributo de la solicitud
        request.getRequestDispatcher("tareas.jsp").forward(request, response); // Reenviar a la página JSP
    }

    private void mostrarFormularioCrear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idTarea = request.getParameter("id");
        if (idTarea != null) {
            Tarea tarea = tareaDAO.obtenerPorId(Integer.parseInt(idTarea));
            request.setAttribute("tarea", tarea);
        }
        request.getRequestDispatcher("formularioTarea.jsp").forward(request, response);
    }

    private void actualizarTarea(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idTarea = Integer.parseInt(request.getParameter("idTarea"));
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        String estado = request.getParameter("estado");
        Date fecha = Date.valueOf(request.getParameter("fecha"));
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));

        Tarea tareaActualizada = new Tarea(idTarea, titulo, descripcion, estado, fecha, idUsuario);

        tareaDAO.actualizar(tareaActualizada);
        response.sendRedirect("TareaControlador?action=listar");
    }

    private void insertarTarea(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        String estado = request.getParameter("estado");
        String fechaString = request.getParameter("fecha");
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));

        java.sql.Date fecha = null;
        try {
            if (fechaString != null && !fechaString.isEmpty()) {
                fecha = java.sql.Date.valueOf(fechaString);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        Tarea nuevaTarea = new Tarea();
        nuevaTarea.setTitulo(titulo);
        nuevaTarea.setDescripcion(descripcion);
        nuevaTarea.setEstado(estado);
        nuevaTarea.setFecha(fecha);
        nuevaTarea.setIdUsuario(idUsuario);

        tareaDAO.registrar(nuevaTarea);
        response.sendRedirect("TareaControlador?action=lista");
    }

    private void eliminarTarea(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        tareaDAO.eliminar(id);
        response.sendRedirect("TareaControlador?action=lista");
    }
}
