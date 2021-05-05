package modeloDAO;

import conexion.ConnectionPool;
import interfaces.CRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.DetalleVenta;
import modelo.Libro;
import modelo.Venta;

public class DetalleVentaDAO implements CRUD.CRUD_detalleVenta{
    DetalleVenta detalleVenta;
    Libro libro;
    Venta compra;

    Connection con;
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    LibroDAO daoLibro = new LibroDAO();
    VentaDAO daoVenta = new VentaDAO();

    @Override
    public ArrayList<DetalleVenta> listar(Venta _venta) {
        ArrayList<DetalleVenta> list = new ArrayList<>();
        final String sql = "select * from detalleVenta where id_venta=?";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, _venta.getId_venta());
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new DetalleVenta(rs.getInt(1), rs.getDouble(2), rs.getDouble(3), rs.getInt(4), _venta, daoLibro.buscarPorId(rs.getInt(6))));
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
    public DetalleVenta buscarPorId(int _id) {
        String sql = "select * from autor where id_autor=?";
//        try {
//            con = ConnectionPool.getInstance().getConnection();
//            ps = con.prepareStatement(sql);
//            ps.setInt(1, _id);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                detalleVenta = new DetalleVenta(rs.getInt(1), rs.getDouble(2), rs.getDouble(3), rs.getInt(4), daoVenta.bu, daoLibro.buscarPorId(rs.getInt(6)));
//                detalleVenta.setId_autor(_id);
//                detalleVenta.setNombres(rs.getString(2));
//                detalleVenta.setApellidos(rs.getString(3));
//                detalleVenta.setNacionalidad(rs.getString(4));
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
        return detalleVenta;
    }

    @Override
    public boolean agregar(DetalleVenta _detalleVenta) {
        boolean resultadoAgregar = false;
        final String sql = "insert into detalleVenta values(null,?,?,?,?,?)";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setDouble(1, _detalleVenta.getSubtotal());
            ps.setDouble(2, _detalleVenta.getPrecioVenta());
            ps.setInt(3, _detalleVenta.getCantidad());
            ps.setInt(4, _detalleVenta.getVenta().getId_venta());
            ps.setInt(5, _detalleVenta.getLibro().getId_libro());

            if (ps.executeUpdate() != 0) {
                resultadoAgregar = true;
            }
        } catch (SQLException e) {
            System.out.println("error en dao agregar detalle venta: " + e.getMessage());
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
    public boolean editar(DetalleVenta _detalleVenta) {
        final String sql = "update autor set nombres=?, apellidos=?,nacionalidad=? where id_autor=?";
        boolean resultadoEditar = false;
//        try {
//            con = ConnectionPool.getInstance().getConnection();
//            ps = con.prepareStatement(sql);
//            ps.setString(1, detalleVenta.getNombres());
//            ps.setString(2, detalleVenta.getApellidos());
//            ps.setString(3, detalleVenta.getNacionalidad());
//            ps.setInt(4, detalleVenta.getId_autor());
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
//        try {
//            con = ConnectionPool.getInstance().getConnection();
//            ps = con.prepareStatement(sql);
//            ps.setInt(1, id);
//            if (ps.executeUpdate() != 0) {
//                resultadoEliminar = true;
//            }
//        } catch (SQLException e) {
//            System.out.println("error en: DAO LISTAR (autor)" + e.getMessage());
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
        return resultadoEliminar;
    }
}
