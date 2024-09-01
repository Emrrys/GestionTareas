import dao.UsuarioDAO;
import java.util.List;
import modelo.Usuario;

public class UsuarioDAOTest {
    public static void main(String[] args) {
        UsuarioDAO dao = new UsuarioDAO();

        // Prueba registrar
        Usuario nuevoUsuario = new Usuario(0, "Sebastian", "sebastian@mail.com", "1234");
        boolean registrado = dao.registrar(nuevoUsuario);
        System.out.println("Registrado: " + registrado);

        // Prueba validar
        Usuario usuario = dao.validar("sebastian@mail.com", "1234");
        System.out.println("Usuario encontrado: " + (usuario != null));

        // Prueba listar
        List<Usuario> usuarios = dao.listar();
        System.out.println("Total de usuarios: " + usuarios.size());

        // Prueba eliminar
        boolean eliminado = dao.eliminar(usuario.getIdUsuario());
        System.out.println("Usuario eliminado: " + eliminado);
    }
}
