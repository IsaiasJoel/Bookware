package modeloDAO;

import conexion.ConnectionPool;
import interfaces.CRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Autor;

public class AutorDAO implements CRUD.CRUD_Autor{
    Autor autor;
    Connection con;
    PreparedStatement ps;
    Statement st;
    ResultSet rs;
    
    @Override
    public ArrayList<Autor> listar() {
        ArrayList<Autor> list = new ArrayList<>();
        String sql = "select * from autor";
        try {
            con = ConnectionPool.getInstance().getConnection();
            if(con != null){
                st = con.createStatement();
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    Autor autor = new Autor();
                    autor.setId_autor(rs.getInt(1));
                    autor.setNombres(rs.getString(2));
                    autor.setApellidos(rs.getString(3));
                    autor.setNacionalidad(rs.getString(4));
                    list.add(autor);
                }
            }

        } catch (SQLException e) {
            System.out.println("->>>>>>>>>>>>ERROR EN DAO-LISTAR" + e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                if (null != con) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public Autor buscarPorId(int id) {
        autor = new Autor();
        String sql = "select * from autor where id_autor=" + id;
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                autor.setId_autor(id);
                autor.setNombres(rs.getString(2));
                autor.setApellidos(rs.getString(3));
                autor.setNacionalidad(rs.getString(4));
            }
        } catch (SQLException e) {
            System.out.println("->>>>>>>>>>>>ERROR EN DAO-LIST" + e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                if (null != con) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return autor;
    }
    
    @Override
    public Autor buscarPorNombre(String nombreAutor) {
//      solución por mysql
        final String sql = "select * from autor where concat_ws(' ',nombres,apellidos) like '%"+nombreAutor+"%'"; //que fue acá? 
        try {
            con = ConnectionPool.getInstance().getConnection();
            st = con.createStatement();
//            ps.setString(1, nombreAutor);
            rs = st.executeQuery(sql);
            while (rs.next()) {
                autor = new Autor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
        } catch (SQLException e) {
            System.out.println("error en dao autor buscar por nombre: " + e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                if (null != con) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return autor;
    }
    
    @Override
    public boolean agregar(Autor autor) {
        boolean resultadoAgregar = false;
        final String sql = "insert into autor values(null,?,?,?)";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, autor.getNombres());
            ps.setString(2, autor.getApellidos());
            ps.setString(3, autor.getNacionalidad());
            
            if (ps.executeUpdate() != 0) {
                resultadoAgregar = true;
            }
        } catch (SQLException e) {
            System.out.println("->>>>>>>>>>>>ERROR EN DAO-ADD" + e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                if (null != con) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultadoAgregar;
    }

    @Override
    public boolean editar(Autor autor) {
        final String sql = "update autor set nombres=?, apellidos=?,nacionalidad=? where id_autor=?";
        boolean resultadoEditar = false;
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, autor.getNombres());
            ps.setString(2, autor.getApellidos());
            ps.setString(3, autor.getNacionalidad());
            ps.setInt(4, autor.getId_autor());
            if (ps.executeUpdate() != 0) {
                resultadoEditar = true;
            }
        } catch (SQLException e) {
            System.out.println("->>>>>>>>>>>>ERROR EN DAO-edit" + e.getMessage());
            e.printStackTrace();
        }finally {
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

    @Override
    public boolean eliminar(int id) {
        final String sql = "delete from autor where id_autor=?";
        boolean resultadoEliminar = false;
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            if (ps.executeUpdate() != 0) {
                resultadoEliminar = true;
            }
        } catch (SQLException e) {
            System.out.println("error en: DAO LISTAR (autor)"+e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                if (null != con) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultadoEliminar;
    }
    
    public String getViewComboBoxAutor() {
        String codeHTML = "";

        for (Autor oAutor : listar()) {
            codeHTML += "<option>" + oAutor.getNombres() + " " +oAutor.getApellidos() + "</option>";
        }
        return codeHTML;
    }
}
