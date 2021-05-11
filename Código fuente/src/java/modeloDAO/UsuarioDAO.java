package modeloDAO;

import conexion.ConnectionPool;
import interfaces.CRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.Empresa;
import modelo.Perfil;
import modelo.Usuario;

public class UsuarioDAO implements CRUD.CRUD_Usuario{
    Empresa empresa = null;
    Usuario usuario = null;
    Perfil perfil = null;

    Connection con;
    PreparedStatement ps;
    Statement st;
    ResultSet rs;
    

    @Override
    public Object[] verificarLogeo(String usuario, String password) {
        final String sql = "exec pa_logeo ?,?";
        Object objetos[] = {perfil,empresa};
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareCall(sql);
            ps.setString(1, usuario);
            ps.setString(2, password);
            System.out.println("la sentencia sql obtenida es: " + sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                /**/
                perfil = new Perfil(rs.getString("perfil"), rs.getString("perfil_descripcion"));
                empresa = new Empresa(rs.getString("razón_social"), rs.getString("RUC"), rs.getString("logo"));
                objetos[0] = perfil;
                objetos[1] = empresa;
                System.out.println(perfil.toString());
            }
        } catch (SQLException e) {
            System.out.println("-> error en dao empresa verificar logeo: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return objetos;

    }
    
}
