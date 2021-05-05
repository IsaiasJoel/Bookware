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
import modelo.Categoria;
import modelo.Editorial;
import modelo.Empresa;
import modelo.Libro;
import modelo.Subcategoria;

public class LibroDAO implements CRUD.CRUD_Libro{
    Libro libro;
    Autor autor;
    Editorial editorial;
    Categoria categoria;
    Connection con;
    PreparedStatement ps;
    Statement st;
    ResultSet rs;
    
    AutorDAO daoAutor = new AutorDAO();
    CategoriaDAO daoCategoria = new CategoriaDAO();
    SubcategoriaDAO daoSubcategoria = new SubcategoriaDAO();
    EditorialDAO daoEditorial = new EditorialDAO();
    
    @Override
    public ArrayList<Libro> listar(Empresa _empresa) {
        ArrayList<Libro> list = new ArrayList<>();
        String sql = "select * from libro INNER JOIN autor ON libro.id_autor = autor.id_autor INNER JOIN categoria ON libro.id_categoría = categoria.id_categoría"
                + " INNER JOIN editorial ON libro.id_editorial = editorial.id_editorial";
        try {
            con = ConnectionPool.getInstance().getConnection();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                autor = new Autor(rs.getInt("id_autor"),rs.getString("nombres"),rs.getString("apellidos"),rs.getString("nacionalidad"));
                editorial = new Editorial(rs.getInt("id_editorial"),rs.getString("nombreEditorial"));
                categoria = new Categoria(rs.getInt("id_categoría"),rs.getString("nombrecategoria"));
                
                libro = new Libro(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getDouble(4),rs.getDouble(5),autor,editorial,categoria);

                list.add(libro);
            }
        } catch (SQLException e) {
            System.out.println("->>>>>>>>>>>>ERROR EN DAO-LISTAR" + e.getMessage());
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
    
    public ArrayList<String> listarTitulo(Empresa _empresa) {
        ArrayList<String> list = new ArrayList<>();
        final String sql = "select titulo from libro";
        try {
            con = ConnectionPool.getInstance().getConnection();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("->>>>>>>>>>>>ERROR EN DAO-LISTAR por nombre" + e.getMessage());
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
    public Libro buscarPorId(int _id) {
        final String sql = "select * from libro where id_libro=?";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, _id);
            rs = ps.executeQuery();
            while (rs.next()) {
                libro = new Libro(_id, rs.getString(2), rs.getInt(3), rs.getDouble(4), daoAutor.buscarPorId(rs.getInt(5)), daoEditorial.buscarPorId(rs.getInt(6)), daoCategoria.buscarPorId(rs.getInt(7)));
            }
        } catch (SQLException e) {
            System.out.println("->>>>>>>>>>>>ERROR EN DAO-LIST" + e.getMessage());
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
        return this.libro;
    }
    
    public Libro buscarPorNombre(String tituloLibro) {
        String sql = "select * from libro where titulo=?";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, tituloLibro);
            rs = ps.executeQuery();
            while (rs.next()) {
                libro = new Libro(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getDouble(5),daoAutor.buscarPorId(rs.getInt(6)), daoEditorial.buscarPorId(rs.getInt(7)), daoCategoria.buscarPorId(rs.getInt(8)));
            }
        } catch (SQLException e) {
            System.out.println("error en libro dao - buscar libro por nombre" + e.getMessage());
        }finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
            }
        }
        return libro;
    }
    
    @Override
    public boolean agregar(Libro libro) {
        boolean resultado = false;
        String sql = "insert into libro values(null,?,?,?,?,?,?,?)";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, libro.getTitulo());
            ps.setInt(2, libro.getStock());
            ps.setDouble(3, libro.getPrecioSugerido());
            ps.setDouble(4, libro.getGanancia());
            
            ps.setInt(5, libro.getAutor().getId_autor());
            ps.setInt(6, libro.getEditorial().getId_editorial());
            ps.setInt(7, libro.getCategoria().getId_categoria());
            if (ps.executeUpdate() != 0) {
                resultado = true;
            }
        } catch (SQLException e) {
            System.out.println("error en dao agregar libro: " + e.getMessage());
        }finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
            }
        }
        return resultado;
    }

    @Override
    public boolean editar(Libro libro) {
        boolean resultado = false;
        final String sql = "update libro set titulo=?, stock=?, precioSugerido=?, id_autor=?, id_editorial=?, id_categoría=?  where id_libro=?";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, libro.getTitulo());
            ps.setInt(2, libro.getStock());
            ps.setDouble(3, libro.getPrecioSugerido());
            ps.setInt(4, libro.getAutor().getId_autor());
            ps.setInt(5, libro.getEditorial().getId_editorial());
            ps.setInt(6, libro.getCategoria().getId_categoria());
            ps.setInt(7, libro.getId_libro());
            if (ps.executeUpdate() != 0) {
                resultado = true;
            }
        } catch (SQLException e) {
            System.out.println("error en dao editar libro: " + e.getMessage());
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
        final String sql = "delete from libro where id_libro=?";
        try {
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            if (ps.executeUpdate() != 0) {
                resultado = true;
            }
        } catch (SQLException e) {
            System.out.println("error en dao eliminar libro: "+e.getMessage());
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
    public String getViewRowLibro(Empresa _empresa) {
        String codeHTML = "";
        for (Libro oLibro : listar(_empresa)) {
            codeHTML += "";
        }
        return codeHTML;
    }
    

    public String getViewComboBoxLibro(Empresa _empresa) {
        String codeHTML = "";

        for (Libro oLibro : listar(_empresa)) {
            codeHTML += "<option>"+ oLibro.getTitulo()+"</option>";
        }

        return codeHTML;
    }

    @Override
    public ArrayList<Libro> filtrar(String filtro) {
        ArrayList<Libro> lst = new ArrayList<>();
        final String sql = "select titulo from libro where titulo LIKE '" + filtro + "%'";

        try {
            con = ConnectionPool.getInstance().getConnection();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                libro = new Libro(rs.getString(1));
                lst.add(libro);
            }
        } catch (SQLException e) {
            System.out.println("error en dao libro filtrar: " + e.getMessage());
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

    public String imprimirSubcategorias(int _idCategoria){
        String html="";
        
        ArrayList<Subcategoria> lstSubcategorias = daoSubcategoria.listar(_idCategoria);
        
        for (Subcategoria oSubcategoria : lstSubcategorias) {
            html += oSubcategoria.getNombre() + "\n"; 
        }
        
        return html;
    }
}
    

