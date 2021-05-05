package modeloDAO;

import conexion.ConnectionPool;
import interfaces.CRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import modelo.Categoria;
import modelo.Subcategoria;

public class SubcategoriaDAO implements CRUD.CRUD_Subcategoria{
    Subcategoria subcategoria;
    Connection con;
    PreparedStatement ps;
    Statement st;
    ResultSet rs;
    CategoriaDAO daoCategoria = new CategoriaDAO();

    @Override
    public ArrayList<Subcategoria> listar() {
        ArrayList<Subcategoria> list = new ArrayList<>();
        String sql = "select * from categoria INNER JOIN subcategoria USING(id_categoría)";
        try {
            con = ConnectionPool.getInstance().getConnection();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId_categoria(rs.getInt(1));
                categoria.setNombreCategoria(rs.getString(2));
                
                Subcategoria subcategoria = new Subcategoria();
                subcategoria.setId_subcategoria(rs.getInt(3));
                subcategoria.setNombre(rs.getString(4));

                subcategoria.setCategoria(categoria);
                list.add(subcategoria);
            }
        } catch (SQLException e) {
            System.out.println("->>>>>>>>>>>>ERROR EN DAO-LISTAR (subcategoria)" + e.getMessage());
            e.printStackTrace();
        }finally {
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
    
    public ArrayList<Subcategoria> listar(int _idCategoria) {
        ArrayList<Subcategoria> list = new ArrayList<>();
        final String sql = "select distinct * from subcategoria where id_categoría=?";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, _idCategoria);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Subcategoria(rs.getInt(1),rs.getString(2), daoCategoria.buscarPorId(_idCategoria)));
            }
        } catch (SQLException e) {
            System.out.println("->>>>>>>>>>>>ERROR EN DAO-LISTAR (subcategoria)" + e.getMessage());
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
        return list;
    }

    @Override
    public Subcategoria buscarPorId(int id) {
        subcategoria = new Subcategoria();
        String sql = "select * from subcategoria where id_subcategoria=" + id;
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            //ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                subcategoria.setId_subcategoria(id);
                subcategoria.setNombre(rs.getString(2));
                subcategoria.setCategoria((Categoria) rs.getObject(3));
            }
        } catch (SQLException e) {
            System.out.println("->>>>>>>>>>>>ERROR EN DAO-buscar por ID" + e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return subcategoria; 
    }
    
    @Override
    public boolean agregar(Subcategoria subcategoria) {
        boolean resultado = false;
        final String sql="insert into subcategoria values(null,?,?)";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, subcategoria.getNombre());
//            ps.setObject(2, (Categoria) subcategoria.getCategoria());
            ps.setInt(2, subcategoria.getCategoria().getId_categoria());
            if (ps.executeUpdate() != 0) {
                resultado = true;
            }
        } catch (SQLException e) {
            System.out.println("error en AGREGRAR sub CATEGORIA DAO: "+e.getMessage());
            e.printStackTrace();
        }finally {
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
    public boolean editar(Subcategoria subcategoria) {
        boolean resultado = false;
        final String sql = "update subcategoria set nombreSubcategoria=?, id_categoría=? where id_subcategoria=?";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, subcategoria.getNombre());
            ps.setInt(2, subcategoria.getCategoria().getId_categoria());
            ps.setInt(3, subcategoria.getId_subcategoria());
            if (ps.executeUpdate() != 0 ) {
                resultado = true;
            }
        } catch (SQLException e) {
            System.out.println("->>>>>>>>>>>>ERROR EN DAO-edit" + e.getMessage());
        }finally {
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
    public boolean eliminar(int id) {
        boolean resultado = false;
        final String sql = "delete from subcategoria where id_subcategoria=?";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            if (ps.executeUpdate() != 0) {
                resultado = true;
            }
        } catch (SQLException e) {
            System.out.println("ERROR EN ELIMNAR-subcategoria DAO"+e.getMessage());
        }finally {
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
    public String getViewRowSubcategoria() {
        String codeHTML = "";
        
        for (Subcategoria oSubcategoria : listar()) {
            codeHTML += "";
        }
        return codeHTML;
    }
    
    @Override
    public String getViewComboBoxSubcategoria(){
        String codeHTML = "";
        
        for (Subcategoria oSubcategoria : listar()) {
            codeHTML += "<option>"+oSubcategoria.getNombre()+"</option>";
        }
        return codeHTML;
    }
    
}
