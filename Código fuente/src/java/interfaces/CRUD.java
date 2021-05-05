package interfaces;

import java.util.ArrayList;
import modelo.Autor;
import modelo.Categoria;
import modelo.Cliente;
import modelo.Compra;
import modelo.DetalleCompra;
import modelo.DetalleVenta;
import modelo.Editorial;
import modelo.Empresa;
import modelo.Libro;
import modelo.Proveedor;
import modelo.Subcategoria;
import modelo.Venta;

public interface CRUD {
    public interface CRUD_Autor {
//        public String getViewAutor();
        public ArrayList<Autor> listar();
        public Autor buscarPorId(int id);
        public Autor buscarPorNombre(String nombreAutor);
        public boolean agregar(Autor autor);
        public boolean editar(Autor autor);
        public boolean eliminar(int id);
    }
    
    public interface CRUD_Categoria {
        public String getViewRowCategoria();
        public String getViewComboBoxCategoria();
        public ArrayList<Categoria> listar();
        public Categoria buscarPorId(int id);
        public boolean agregar(Categoria categoria);
        public boolean editar(Categoria categoria);
        public boolean eliminar(int id);
    }
    
    public interface CRUD_Editorial {
        public String getViewRowEditorial();
        public ArrayList<Editorial> listar();
        public Editorial buscarPorId(int id);
        public Editorial buscarPorNombre(String nombreEditorial);
        public boolean agregar(Editorial editorial);
        public boolean editar(Editorial editorial);
        public boolean eliminar(int id);
    }
    
    public interface CRUD_Libro {
        public String getViewRowLibro(Empresa _empresa);
        public ArrayList<Libro> listar(Empresa _empresa);
        public Libro buscarPorId(int id);
        public boolean agregar(Libro libro);
        public boolean editar(Libro libro);
        public boolean eliminar(int id);
        public ArrayList<Libro> filtrar(String filtro);
    }
    
    public interface CRUD_Subcategoria {
        public String getViewRowSubcategoria();
        public String getViewComboBoxSubcategoria();
        public ArrayList<Subcategoria> listar();
        public Subcategoria buscarPorId(int id);
        public boolean agregar(Subcategoria subcategoria);
        public boolean editar(Subcategoria subcategoria);
        public boolean eliminar(int id);
    }
    
    public interface CRUD_Empresa{
        public Empresa verificarLogeo(String usuario,String password);
        public Empresa buscarPorRUC(String _RUC);
        public Empresa buscarPorID(int _id);
    }
    
    public interface CRUD_Proveedor {
        public String getViewComboBoxProveedor(Empresa _empresa);
        public ArrayList<Proveedor> listar(Empresa _empresa);
        public Proveedor buscarPorId(int id);
        public Proveedor buscarPorNombre(String nombreProveedor);
        public boolean agregar(Proveedor proveedor);
        public boolean editar(Proveedor proveedor);
        public boolean eliminar(int id);
        public ArrayList<Proveedor> filtrar(String filtro);
    }
    
    public interface CRUD_Cliente {
        public String getViewComboBoxCliente(Empresa _empresa);
        public ArrayList<Cliente> filtrar(String filtro);
        public ArrayList<Cliente> listar(Empresa _empresa);
        public Cliente buscarPorId(int id);
        public Cliente buscarPorDNI(String dniCliente);
        public boolean agregar(Cliente cliente);
        public boolean editar(Cliente cliente);
        public boolean eliminar(int id);
    }
    
    public interface CRUD_detalleCompra {
        public ArrayList<DetalleCompra> listar(Compra _compra);
        public DetalleCompra buscarPorId(int id);
        public boolean agregar(DetalleCompra _detalleCompra);
        public boolean editar(DetalleCompra _detalleCompra);
        public boolean eliminar(int id);
    }
    
    public interface CRUD_Compra {
        public ArrayList<Compra> listar(Empresa _empresa);
        public Compra buscarPorCodigo(String _codigo);
        public boolean agregar(Compra _compra);
        public boolean editar(Compra _compra);
    }
    
    public interface CRUD_Venta {
        public ArrayList<Venta> listar(Empresa empresa);
        public Venta buscarPorCodigo(String _codigo);
        public boolean agregar(Venta _venta);
        public boolean editar(Venta venta);
    }
    
    public interface CRUD_detalleVenta {
        public ArrayList<DetalleVenta> listar(Venta _venta);
        public DetalleVenta buscarPorId(int id);
        public boolean agregar(DetalleVenta _detalleVenta);
        public boolean editar(DetalleVenta _detalleVenta);
        public boolean eliminar(int id);
    }
}
