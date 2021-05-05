
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
import modelo.Venta;

public class VentaDAO implements CRUD.CRUD_Venta{
    Venta venta;
    Connection con;
    PreparedStatement ps;
    Statement st;
    ResultSet rs;
    EmpresaDAO daoEmpresa = new EmpresaDAO();
    ClienteDAO daoCliente = new ClienteDAO();

    @Override
    public ArrayList<Venta> listar(Empresa empresa) {
        ArrayList<Venta> list = new ArrayList<>();
        String sql = "select * from venta where id_empresa=?"; //acá va la empresa
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, empresa.getId_empresa());
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Venta(rs.getInt(1), rs.getDouble(2), rs.getDate(3),rs.getString(4) ,daoEmpresa.buscarPorID(rs.getInt(5)), daoCliente.buscarPorId(rs.getInt(6)),rs.getString(7)));
            }
        } catch (SQLException e) {
            System.out.println("error en dao venta listar" + e.getMessage());
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
        return list;
    }

    @Override
    public Venta buscarPorCodigo(String _codigo) {
        String sql = "select * from venta where codigoVenta=?";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, _codigo);
            rs = ps.executeQuery();
            while (rs.next()) {
                venta = new Venta(rs.getInt(1), rs.getDouble(2), rs.getDate(3), rs.getString(4), daoEmpresa.buscarPorID(rs.getInt(5)), daoCliente.buscarPorId(rs.getInt(6)), _codigo);
            }
        } catch (SQLException e) {
            System.out.println("error en dao venta buscar por codigo" + e.getMessage());
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
        return venta;
    }

    @Override
    public boolean agregar(Venta venta) {
        boolean resultadoAgregar = false;
        final String sql = "insert into venta values(null,?,?,?,?,?,?)";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setDouble(1, venta.getTotalVenta());
            java.sql.Date date2 = new java.sql.Date(venta.getFechaVenta().getTime());
            ps.setDate(2, date2);
            ps.setString(3, venta.getTipoDocumento());
            ps.setInt(4, venta.getEmpresa().getId_empresa());
            ps.setInt(5, venta.getCliente().getId_cliente());
            ps.setString(6, venta.getCodigoVenta());

            if (ps.executeUpdate() != 0) {
                resultadoAgregar = true;
            }
        } catch (SQLException e) {
            System.out.println("error en dao agregar venta: " + e.getMessage());
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
    public boolean editar(Venta _venta) {
        final String sql = "update autor set nombres=?, apellidos=?,nacionalidad=? where id_autor=?";
        boolean resultadoEditar = false;
//        try {
//            con = ConnectionPool.getInstance().getConnection();
//            ps = con.prepareStatement(sql);
//            ps.setString(1, venta.getNombres());
//            ps.setString(2, venta.getApellidos());
//            ps.setString(3, venta.getNacionalidad());
//            ps.setInt(4, venta.getId_autor());
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

    public String GenerarID_BD(ArrayList<Venta> ventas, String _prefijo, int _digitos) {
        String new_cod;

        if (ventas != null) {
            if (ventas.size() > 0) {

                ArrayList<Integer> codigos = new ArrayList();

                for (int i = 0; i < ventas.size(); i++) {
                    String prod_id = ventas.get(i).getCodigoVenta();

                    int codInt = Integer.parseInt(prod_id.substring(_prefijo.length(), prod_id.length())); //obtengo el último digito del código

                    codigos.add(codInt);
                }

                //Ordenar los códigos
                if (codigos.size() > 0) {

                    codigos.sort((o1, o2) -> o1.compareTo(o2));
                    //Fin ordenar los códigos
                    int ultimo_cod = codigos.get(codigos.size() - 1);
                    ultimo_cod++;
                    new_cod = _prefijo + convertirNumeroToCantidadDeLongitudEnCero(_digitos - 1) + ultimo_cod;
                    return new_cod;
                } else {
                    return null;
                }
            } else {
                new_cod = _prefijo + convertirNumeroToCantidadDeLongitudEnCero(_digitos - 1) + "1";
                return new_cod;
            }
        } else {
            return null;
        }
    }

    private String convertirNumeroToCantidadDeLongitudEnCero(int _n) {
        String s = "";
        int contador = 0;

        if (_n > 0) {
            while (contador < _n) {
                s += "0";
                contador++;
            }
        }
        return s;
    }
}
