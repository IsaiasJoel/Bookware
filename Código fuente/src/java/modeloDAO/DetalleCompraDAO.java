package modeloDAO;

import conexion.ConnectionPool;
import interfaces.CRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Compra;
import modelo.DetalleCompra;
import modelo.Libro;

public class DetalleCompraDAO implements CRUD.CRUD_detalleCompra{
    DetalleCompra detalleCompra;
    Libro libro;
    Compra compra;
    
    Connection con;
    PreparedStatement ps;
    Statement st;
    ResultSet rs;
    
    LibroDAO daoLibro = new LibroDAO();

    @Override
    public ArrayList<DetalleCompra> listar(Compra _compra) {
        ArrayList<DetalleCompra> list = new ArrayList<>();
        final String sql = "select * from detalleCompra where id_compra=?";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, _compra.getId_compra());
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new DetalleCompra(rs.getInt(1), rs.getDouble(2), rs.getDouble(3), rs.getInt(4), _compra, daoLibro.buscarPorId(rs.getInt(6)))); 
            }
        } catch (SQLException e) {
            System.out.println("error en dao listar detalle de compra: " + e.getMessage());
        } finally {
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
    public DetalleCompra buscarPorId(int id) {
        detalleCompra = new DetalleCompra();
//        String sql = "select * from autor where id_autor=" + id;
//        try {
//            con = ConnectionPool.getInstance().getConnection();
//            ps = con.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                detalleCompra.setId_autor(id);
//                detalleCompra.setNombres(rs.getString(2));
//                detalleCompra.setApellidos(rs.getString(3));
//                detalleCompra.setNacionalidad(rs.getString(4));
//            }
//        } catch (SQLException e) {
//            System.out.println("->>>>>>>>>>>>ERROR EN DAO-LIST" + e.getMessage());
//            e.printStackTrace();
//        } finally {
//            try {
//                if (null != con) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
        return detalleCompra;
    }

    @Override
    public boolean agregar(DetalleCompra _detalleCompra) {
        boolean resultadoAgregar = false;
        final String sql = "insert into detalleCompra values(null,?,?,?,?,?)";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setDouble(1, _detalleCompra.getSubtotal());
            ps.setDouble(2, _detalleCompra.getPrecioCompra());
            ps.setInt(3, _detalleCompra.getCantidad());
            ps.setInt(4, _detalleCompra.getoCompra().getId_compra());
            ps.setInt(5, _detalleCompra.getoLibro().getId_libro());

            if (ps.executeUpdate() != 0) {
                resultadoAgregar = true;
            }
        } catch (SQLException e) {
            System.out.println("error en dao agregar detalle compra: " + e.getMessage());
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
        return resultadoAgregar;
    }

    @Override
    public boolean editar(DetalleCompra _detalleCompra) {
        final String sql = "update autor set nombres=?, apellidos=?,nacionalidad=? where id_autor=?";
        boolean resultadoEditar = false;
//        try {
//            con = ConnectionPool.getInstance().getConnection();
//            ps = con.prepareStatement(sql);
//            ps.setString(1, detalleCompra.getNombres());
//            ps.setString(2, detalleCompra.getApellidos());
//            ps.setString(3, detalleCompra.getNacionalidad());
//            ps.setInt(4, detalleCompra.getId_autor());
//            if (ps.executeUpdate() != 0) {
//                resultadoEditar = true;
//            }
//        } catch (SQLException e) {
//            System.out.println("->>>>>>>>>>>>ERROR EN DAO-edit" + e.getMessage());
//            e.printStackTrace();
//        } finally {
//            try {
//                if (null != con) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
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
            System.out.println("error en: DAO LISTAR (autor)" + e.getMessage());
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
        return resultadoEliminar;
    }
    
}
