package modeloDAO;

import conexion.ConnectionPool;
import interfaces.CRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Empresa;

public class EmpresaDAO implements CRUD.CRUD_Empresa{
    Empresa empresa = null;
    Connection con;
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    @Override
    public Empresa verificarLogeo(String usuario, String password) {
        final String sql = "select id_empresa,RUC, usuario,logo from empresa where usuario=? and password=?";

        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                empresa = new Empresa(rs.getInt("id_empresa"), rs.getString("RUC"),rs.getString("usuario"),rs.getString("logo"));
                System.out.println(empresa.toString());
            }
        } catch (SQLException e) {
            System.out.println("-> error en dao empresa verificar logeo: " + e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return empresa;
    }

    @Override
    public Empresa buscarPorRUC(String _RUC) {
        String sql = "select id_empresa, RUC, usuario from empresa where RUC=?";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                empresa = new Empresa(rs.getInt(1),_RUC,rs.getString(3));
            }
        } catch (SQLException e) {
            System.out.println("-> error en dao empresa buscar por RUC" + e.getMessage());
        } finally {
            try {
                if (null != con) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return empresa;
    }

    @Override
    public Empresa buscarPorID(int _id) {
        final String sql = "select id_empresa,RUC,usuario from empresa where id_empresa=?";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, _id);
            rs = ps.executeQuery();
            while (rs.next()) {
                empresa = new Empresa(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
        } catch (SQLException e) {
            System.out.println("error en dao empresa buscar por ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (null != con) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return empresa;
    }

    public boolean agregar(Empresa empresa){
        boolean resultado = false;
        final String sql = "insert into empresa values (null,?,?,?,?)";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, empresa.getRUC());
            ps.setString(2, empresa.getUsuario());
            ps.setString(3, empresa.getPassword());
            ps.setString(4, empresa.getLogo());
            
            if (ps.executeUpdate() != 0) {
                resultado = true;
            }
        } catch (SQLException e) {
            System.out.println("error en dao empresa: "+e.getMessage());
        }
        return resultado;
    }
    
    public boolean editar(Empresa empresa) {
        final String sql = "update empresa set RUC=?, usuario=?, password=?, logo=? where id_empresa=?";
        boolean resultadoEditar = false;
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, empresa.getRUC());
            ps.setString(2, empresa.getUsuario());
            ps.setString(3, empresa.getPassword());
            ps.setString(4, empresa.getLogo());
            ps.setInt(5, empresa.getId_empresa());
            if (ps.executeUpdate() != 0) {
                resultadoEditar = true;
            }
        } catch (SQLException e) {
            System.out.println("->>>>>>>>>>>>ERROR EN DAO-editar empresa" + e.getMessage());
        } finally {
            try {
                if (null != con) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultadoEditar;
    }
    
    public ArrayList<Empresa> listar(){
        ArrayList<Empresa> lst = new ArrayList<>();
        final String sql = "select RUC,usuario,logo from empresa";
        try {
            con = ConnectionPool.getInstance().getConnection();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            
            while (rs.next()) {                
                lst.add(new Empresa(rs.getString("RUC"), rs.getString("usuario"), rs.getString("logo")));
            }
            
        } catch (SQLException e) {
            System.out.println("error en dao empresa lista: "+e.getMessage());
        }
        return lst;
    }
    
}
