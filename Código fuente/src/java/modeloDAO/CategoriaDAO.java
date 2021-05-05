package modeloDAO;

import conexion.ConnectionPool;
import interfaces.CRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Categoria;

public class CategoriaDAO implements CRUD.CRUD_Categoria{
    Categoria categoria;
    Connection con;
    PreparedStatement ps;
    Statement st;
    ResultSet rs;
    
    @Override
    public ArrayList<Categoria> listar() {
        ArrayList<Categoria> list = new ArrayList<>();
        final String sql = "select DISTINCT * from categoria";
        try {
            con = ConnectionPool.getInstance().getConnection();
            st = con.createStatement();
            //ps = con.prepareStatement(sql);
            rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(new Categoria(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            System.out.println("->>>>>>>>>>>>ERROR EN DAO-LISTAR (categoria)" + e.getMessage());
        }finally{
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public Categoria buscarPorId(int id) {
        categoria = new Categoria();
        final String sql = "select * from categoria where id_categoría=" + id;

        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                categoria.setId_categoria(id);
                categoria.setNombreCategoria(rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("->>>>>>>>>>>>ERROR EN DAO-buscar por ID" + e.getMessage());
            e.printStackTrace();
        }finally{
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return categoria; 
    }
    
    public Categoria buscarPorNombre(String nombreCategoria) {
        categoria = new Categoria();
        final String sql = "select * from categoria where nombreCategoria='" + nombreCategoria + "'"; ; 
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                categoria.setId_categoria(rs.getInt(1));
                categoria.setNombreCategoria(nombreCategoria);
            }
        } catch (Exception e) {
            System.out.println("->>>>>>>>>>>>ERROR EN DAO-buscar por Nombre" + e.getMessage());
            e.printStackTrace();
        }finally{
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return categoria;
    }

    @Override
    public boolean agregar(Categoria categoria) {
        boolean resultado = false;
        final String sql="insert into categoria values(null,?)";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, categoria.getNombreCategoria());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error en AGREGRAR CATEGORIA DAO: "+e.getMessage());
        }finally{
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultado;
    }

    @Override
    public boolean editar(Categoria categoria) {
        final String sql = "update categoria set nombreCategoria=? where id_categoría=?";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, categoria.getNombreCategoria());
            ps.setInt(2, categoria.getId_categoria());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("->>>>>>>>>>>>ERROR EN DAO-edit" + e.getMessage());
        }finally{
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        final String sql = "delete from categoria where id_categoría=?";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ERROR EN ELIMNAR-CATEGORIA DAO "+e.getMessage());
        }finally{
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    //esto se inserta en el t-body
    @Override
    public String getViewRowCategoria() {
        String codeHTML = "";
        
        for (Categoria oCategoria : listar()) {
            codeHTML += "";
        }
        
        return codeHTML;
    }

    @Override
    public String getViewComboBoxCategoria() {
        String codeHTML = "";
        
        for (Categoria oCategoria : listar()) {
            codeHTML += "<option>"+oCategoria.getNombreCategoria()+"</option>";
        }
        return codeHTML;
    }
}