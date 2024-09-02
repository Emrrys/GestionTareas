

import dao.TareaDAO;
import java.util.List;
import modelo.Tarea;

public class TareaDAOTest {
     public static void main(String[] args) {
        TareaDAO dao = new TareaDAO();

        // Prueba registrar
        Tarea nuevaTarea = new Tarea(0, "Desarrollo de Login", 
                "Implementar funcionalidad de inicio de sesión", "En Progreso", 
                new java.sql.Date(System.currentTimeMillis()), 1);
        boolean registrada = dao.registrar(nuevaTarea);
        System.out.println("Tarea registrada: " + registrada);

        // Prueba listar
        List<Tarea> tareas = dao.listar();
        System.out.println("Total de tareas: " + tareas.size());
        for (Tarea tarea : tareas) {
            System.out.println(tarea.getIdTarea() + " - " + tarea.getTitulo() +
                    " - " + tarea.getDescripcion() + " - " + tarea.getEstado() + 
                    " - " + tarea.getFecha() + " - " + tarea.getIdUsuario());
        }

        // Prueba eliminar
        boolean eliminada = dao.eliminar(nuevaTarea.getIdTarea());
        System.out.println("Tarea eliminada: " + eliminada);
    }
}
