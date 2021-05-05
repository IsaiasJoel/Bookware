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
import modelo.Proveedor;

public class ProveedorDAO implements CRUD.CRUD_Proveedor{
    Proveedor proveedor;
    Connection con;
    PreparedStatement ps;
    Statement st;
    ResultSet rs;
    
    @Override
    public ArrayList<Proveedor> listar(Empresa _empresa) {
        ArrayList<Proveedor> list = new ArrayList<>();
        final String sql = "select distinct proveedor.id_proveedor, proveedor.nombres from compra inner join proveedor on compra.id_proveedor = proveedor.id_proveedor inner join empresa on compra.id_empresa = empresa.id_empresa where compra.id_empresa = ?";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, _empresa.getId_empresa());
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Proveedor(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            System.out.println("error en dao listar proveedor: " + e.getMessage());
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
    
    public ArrayList<String> listarNombres(Empresa _empresa) {
        ArrayList<String> list = new ArrayList<>();
//        final String sql = "select distinct proveedor.nombres from compra inner join proveedor on compra.id_proveedor = proveedor.id_proveedor inner join empresa on compra.id_empresa = empresa.id_empresa where compra.id_empresa = ?";
        final String sql = "select nombres from proveedor";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
//            ps.setInt(1, _empresa.getId_empresa());
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("error en dao listar nombres proveedor: " + e.getMessage());
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
    public Proveedor buscarPorId(int _id) {
        final String sql = "select * from proveedor where id_proveedor=?";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, _id);
            rs = ps.executeQuery();
            while (rs.next()) {
                proveedor = new Proveedor(_id, rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("error en dao buscar por id proveedor: " + e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return proveedor;
    }

    @Override
    public Proveedor buscarPorNombre(String nombreProveedor) {
        final String sql = "select * from proveedor where nombres=?";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombreProveedor);
            rs = ps.executeQuery();
            //rs.beforeFirst();
            while (rs.next()) {
                proveedor = new Proveedor(rs.getInt(1), rs.getString(2));
            }
            
            
        } catch (SQLException e) {
            System.out.println("error en dao buscar por nombre proveedor: " + e.getMessage());
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
        return proveedor;
    }

    @Override
    public boolean agregar(Proveedor _proveedor) {
        boolean resultado = false;
        final String sql = "insert into proveedor values(null,?)";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, _proveedor.getNombres());
            if (ps.executeUpdate() != 0) {
                resultado = true;
            }
        } catch (SQLException e) {
            System.out.println("error en dao agregar proveedor: " + e.getMessage());
        } finally {
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
    public boolean editar(Proveedor _proveedor) {
        boolean resultado = false;
        final String sql = "update proveedor set nombres=? where id_proveedor=?";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, _proveedor.getNombres());
            ps.setInt(2, _proveedor.getId_proveedor());
            if (ps.executeUpdate() != 0) {
                resultado = true;
            }
        } catch (SQLException e) {
            System.out.println("error en dao editar proveedor: " + e.getMessage());
        } finally {
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
        String sql = "delete from proveedor where id_proveedor=?";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            if (ps.executeUpdate() != 0) {
                resultado = true;
            }
        } catch (SQLException e) {
            System.out.println("error en dao eliminar proveedor" + e.getMessage());
        } finally {
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
    public String getViewComboBoxProveedor(Empresa _empresa) {
        String codeHTML = "";

        for (Proveedor oProveedor : listar(_empresa)) {
            codeHTML += "<option>" + oProveedor.getNombres() + "</option>";
        }
        return codeHTML;
    }
    
    @Override
    public ArrayList<Proveedor> filtrar(String filtro) {
        ArrayList<Proveedor> lst = new ArrayList<>();
        final String sql = "select * from proveedor where nombres LIKE '" + filtro + "%'";

        try {
            con = ConnectionPool.getInstance().getConnection();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                proveedor = new Proveedor(rs.getInt(1), rs.getString(2));
                lst.add(proveedor);
            }
        } catch (SQLException e) {
            System.out.println("error en dao proveedor filtrar: " + e.getMessage());
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
        return lst;
    }
    
    
}
