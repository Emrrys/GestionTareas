
package controlador;

import dao.TareaDAO;
import modelo.Tarea;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TareaControlador extends HttpServlet{
    private TareaDAO tareaDAO = new TareaDAO();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if(action == null){
            action = "lista";
        }
        
        switch (action){
            case "lista":
                listarTareas(request, response);
                break;
            case "nuevo":
                mostrarFormularioCrear(request, response);
                break;
            case "insertar":
                insertarTarea(request, response);
                break;
            case "borrar":
                eliminarTarea(request, response);
                break;
            default:
                listarTareas(request, response);
                break;
        }
    }
    
    private void listarTareas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Tarea> listaTareas = tareaDAO.listar();
        request.setAttribute("listaTareas", listaTareas);
        request.getRequestDispatcher("tareas.jsp").forward(request, response);
    }

    private void mostrarFormularioCrear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("formulario-tarea.jsp").forward(request, response);
    }

    private void insertarTarea(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        String estado = request.getParameter("estado");
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));

        Tarea nuevaTarea = new Tarea();
        nuevaTarea.setTitulo(titulo);
        nuevaTarea.setDescripcion(descripcion);
        nuevaTarea.setEstado(estado);
        nuevaTarea.setIdUsuario(idUsuario);

        tareaDAO.registrar(nuevaTarea);
        response.sendRedirect("tareas?action=list");
    }

    private void eliminarTarea(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        tareaDAO.eliminar(id);
        response.sendRedirect("tareas?action=list");
    }
}
