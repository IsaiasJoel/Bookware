package modeloDAO;

import conexion.ConnectionPool;
import interfaces.CRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Cliente;
import modelo.Empresa;

public class ClienteDAO implements CRUD.CRUD_Cliente{
    Cliente cliente = null;
    Connection con;
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    @Override
    public ArrayList<Cliente> listar(Empresa _empresa) {
        ArrayList<Cliente> list = new ArrayList<>();
        final String sql = "select distinct cliente.id_cliente, cliente.dni, cliente.nombres from venta inner join cliente on venta.id_venta = cliente.id_cliente inner join empresa on empresa.id_empresa = venta.id_empresa where venta.id_empresa = ?";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, _empresa.getId_empresa());
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Cliente(rs.getInt(1), rs.getString(2),rs.getString(3)));
            }
        } catch (SQLException e) {
            System.out.println("error en dao listar cliente: " + e.getMessage());
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
        final String sql = "select distinct cliente.nombres from venta inner join cliente on venta.id_venta = cliente.id_cliente inner join empresa on empresa.id_empresa = venta.id_empresa where venta.id_empresa = ?";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, _empresa.getId_empresa());
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("error en dao listar cliente: " + e.getMessage());
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
    public Cliente buscarPorId(int _id) {
        final String sql = "select * from cliente where id_cliente=?";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, _id);
            rs = ps.executeQuery();
            while (rs.next()) {
                cliente = new Cliente(_id, rs.getString(2),rs.getString(3));
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
        return cliente;
    }

    @Override
    public Cliente buscarPorDNI(String dniCliente) {
        String sql = "select * from cliente where dni=?";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, dniCliente);
            rs = ps.executeQuery();
            while (rs.next()) {
                cliente = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
        } catch (SQLException e) {
            System.out.println("error en dao buscar por dni cliente: " + e.getMessage());
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
        return cliente;
    }
    
    public Cliente buscarPorNombre(String nombreCliente) {
        String sql = "select * from cliente where nombres=?";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombreCliente);
            rs = ps.executeQuery();
            while (rs.next()) {
                cliente = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3));
            }

        } catch (SQLException e) {
            System.out.println("error en dao buscar por dni cliente: " + e.getMessage());
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
        return cliente;
    }

    @Override
    public boolean agregar(Cliente _cliente) {
        boolean resultado = false;
        final String sql = "insert into cliente values(null,?,?)";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, _cliente.getDni());
            ps.setString(2, _cliente.getNombres());
            if (ps.executeUpdate() != 0) {
                resultado = true;
            }
        } catch (SQLException e) {
            System.out.println("error en dao agregar cliente: " + e.getMessage());
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
    public boolean editar(Cliente _cliente) {
        boolean resultado = false;
        String sql = "update cliente set nombres=?, dni=? where id_cliente=?";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, _cliente.getNombres());
            ps.setString(2, _cliente.getDni());
            ps.setInt(3, _cliente.getId_cliente());
            if (ps.executeUpdate() != 0) {
                resultado = true;
            }
        } catch (SQLException e) {
            System.out.println("error en dao editar cliente: " + e.getMessage());
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
    public boolean eliminar(int _id) {
        boolean resultado = false;
        final String sql = "delete from cliente where id_cliente=?";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, _id);
            if (ps.executeUpdate() != 0) {
                resultado = true;
            }
        } catch (SQLException e) {
            System.out.println("error en dao eliminar cliente" + e.getMessage());
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
    public String getViewComboBoxCliente(Empresa _empresa) {
        String html="";
        ArrayList<Cliente> lstClientes = listar(_empresa);
        
        for (Cliente oCliente : lstClientes) {
            html += "<option>"+oCliente.getNombres()+"</option>";
        }
        return html;
    }
    
    @Override
    public ArrayList<Cliente> filtrar(String filtro){
        ArrayList<Cliente> lst = new ArrayList<>();
        final String sql = "select * from cliente where nombres LIKE %"+filtro+"%";
        
        try {
            con = ConnectionPool.getInstance().getConnection();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                cliente = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3));
                lst.add(cliente);
            }

        } catch (SQLException e) {
            System.out.println("error en dao cliente filtrar: " + e.getMessage());
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
