
import config.ConexionDB;


public class ProbarConexion {
    public static void main(String[] args) {
        ConexionDB conexion = new ConexionDB();
        conexion.getConnection();
    }
}
