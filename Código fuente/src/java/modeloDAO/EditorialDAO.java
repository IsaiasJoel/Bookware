package modeloDAO;

import conexion.ConnectionPool;
import interfaces.CRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Editorial;


public class EditorialDAO implements CRUD.CRUD_Editorial{
    Editorial editorial;
    Connection con;
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    @Override
    public ArrayList<Editorial> listar() {
        ArrayList<Editorial> list = new ArrayList<Editorial>();
        String sql = "select * from editorial";
        try {
            con = ConnectionPool.getInstance().getConnection();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(new Editorial(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            System.out.println("->>>>>>>>>>>>ERROR EN DAO-LISTAR" + e.getMessage());
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

    @Override
    public Editorial buscarPorId(int id) {
        editorial = new Editorial();
        String sql = "select * from editorial where id_editorial=" + id;
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            //ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                editorial.setId_editorial(id);
                editorial.setNombre(rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("->>>>>>>>>>>>ERROR EN DAO-buscar por ID" + e.getMessage());
        }finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return editorial;
    }
    
    @Override
    public Editorial buscarPorNombre(String nombreEditorial) {
        final String sql = "select * from editorial where nombreEditorial=?";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombreEditorial);
            rs = ps.executeQuery();
            while (rs.next()) {
                this.editorial = new Editorial(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("error en dao editorial buscar por nombre: " + e.getMessage());
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
        return this.editorial;
    }

    @Override
    public boolean agregar(Editorial editorial) {
        boolean resultado = false;
        String sql = "insert into editorial values(null,?)";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, editorial.getNombre());
            if (ps.executeUpdate() != 0) {
                resultado = true;
            }
        } catch (SQLException e) {
            System.out.println("error en AGREGRAR editorial DAO: " + e.getMessage());
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
    public boolean editar(Editorial editorial) {
        boolean resultado = false;
        String sql = "update editorial set nombreEditorial=? where id_editorial=?";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, editorial.getNombre());
            ps.setInt(2, editorial.getId_editorial());
            if (ps.executeUpdate() != 0) {
                resultado = true;
            }
        } catch (SQLException e) {
            System.out.println("error en dao editar editorial" + e.getMessage());
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
        String sql = "delete from editorial where id_editorial=?";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            if (ps.executeUpdate() != 0) {
                resultado = true;
            }
        } catch (SQLException e) {
            System.out.println("ERROR EN ELIMNAR-editorial DAO" + e.getMessage());
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

    public String getViewComboBoxEditorial() {
        String codeHTML = "";

        for (Editorial oEditorial : listar()) {
            codeHTML += "<option>" + oEditorial.getNombre() +"</option>";
        }
        return codeHTML;
    }

    @Override
    public String getViewRowEditorial() {
        return "";
    }
}
