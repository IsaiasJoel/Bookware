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
import modelo.Empresa;

public class CompraDAO implements CRUD.CRUD_Compra{
    Compra compra;
    Connection con;
    PreparedStatement ps;
    Statement st;
    ResultSet rs;
    EmpresaDAO daoEmpresa = new EmpresaDAO();
    ProveedorDAO daoProveedor = new ProveedorDAO();
    private ArrayList<Compra> compras = listar();

    @Override
    public ArrayList<Compra> listar(Empresa _empresa) {
        ArrayList<Compra> list = new ArrayList<>();
        final String sql = "select * from compra where id_empresa = ?";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, _empresa.getId_empresa());
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Compra(rs.getInt(1), rs.getDouble(2), rs.getDate(3), daoEmpresa.buscarPorID(rs.getInt(4)), daoProveedor.buscarPorId(rs.getInt(5)), rs.getString(6)));
            }
        } catch (SQLException e) {
            System.out.println("error en dao compra listar" + e.getMessage());
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
    
    public ArrayList<Compra> listar() {
        ArrayList<Compra> list = new ArrayList<>();
        String sql = "select * from compra";
        try {
            con = ConnectionPool.getInstance().getConnection();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                compra = new Compra(rs.getInt(1),rs.getDouble(2),rs.getDate(3),daoEmpresa.buscarPorID(rs.getInt(4)), daoProveedor.buscarPorId(rs.getInt(5)),rs.getString(6));
                list.add(compra);
            }
        } catch (SQLException e) {
            System.out.println("error en dao compra listar" + e.getMessage());
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
    public Compra buscarPorCodigo(String _codigo) {
        final String sql = "select * from compra where codigoCompra=?";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, _codigo);
            rs = ps.executeQuery();
            while (rs.next()) {
                compra = new Compra(rs.getInt(1), rs.getDouble(2), rs.getDate(3), daoEmpresa.buscarPorID(rs.getInt(4)), daoProveedor.buscarPorId(rs.getInt(5)), rs.getString(6));
            }
        } catch (SQLException e) {
            System.out.println("" + e.getMessage());
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
        return compra;
    }

    @Override
    public boolean agregar(Compra _compra) {
        boolean resultadoAgregar = false;
        final String sql = "insert into compra values(null,?,?,?,?,?)";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setDouble(1, _compra.getTotalCompra());
            
            
//            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            java.sql.Date date2 = new java.sql.Date(_compra.getFechaCompra().getTime());
            
            ps.setDate(2, date2);
            
            ps.setInt(3, _compra.getoEmpresa().getId_empresa());
            ps.setInt(4, _compra.getoProveedor().getId_proveedor());
            ps.setString(5, _compra.getCodigoCompra());

            if (ps.executeUpdate() != 0) {
                resultadoAgregar = true;
            }
        } catch (SQLException e) {
            System.out.println("error en dao agregar compra: " + e.getMessage());
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
    public boolean editar(Compra _compra) {
        final String sql = "update autor set nombres=?, apellidos=?,nacionalidad=? where id_autor=?";
        boolean resultadoEditar = false;
//        try {
//            con = ConnectionPool.getInstance().getConnection();
//            ps = con.prepareStatement(sql);
//            ps.setString(1, compra.getNombres());
//            ps.setString(2, compra.getApellidos());
//            ps.setString(3, compra.getNacionalidad());
//            ps.setInt(4, compra.getId_autor());
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
    
    public String GenerarID_BD(String _prefijo, int _digitos) {
        String new_cod;

        if (compras != null) {
            if (compras.size() > 0) {

                ArrayList<Integer> codigos = new ArrayList();

                for (int i = 0; i < compras.size(); i++) {
                    String prod_id = compras.get(i).getCodigoCompra();

                    int codInt = Integer.parseInt(prod_id.substring(_prefijo.length(), prod_id.length())); //obtengo el último digito del código

                    codigos.add(codInt);
                }

                //Ordenar los códigos
                if (codigos.size() > 0) {

                    codigos.sort((o1, o2) -> o1.compareTo(o2));
                    //Fin ordenar los códigos
                    int ultimo_cod = codigos.get(codigos.size() - 1);
                    ultimo_cod++;
                    new_cod = _prefijo + convertirNumeroToCantidadDeLongitudEnCero(_digitos -1 ) + ultimo_cod;
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
        
        if (_n >0) {
            while (contador<_n) {               
                s += "0";
                contador++;
            }
        }
        return s;
    }
}
