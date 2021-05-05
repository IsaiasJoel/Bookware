package logicaDeNegocio;

import java.util.ArrayList;
import modelo.Empresa;
import modelo.Proveedor;
import modeloDAO.ProveedorDAO;

public class ProveedorLogic {
    ProveedorDAO daoProveedor = new ProveedorDAO();
    
    public ArrayList<Proveedor> filtrar(String filtro){
        return daoProveedor.filtrar(filtro);
    }
    
    public ArrayList<Proveedor> listar(Empresa _empresa) {
        return daoProveedor.listar(_empresa);
    }
    
    public ArrayList<String> listarNombres(Empresa _empresa) {
        return daoProveedor.listarNombres(_empresa);
    }
    
    public Proveedor buscarPorId(int id) {
        return daoProveedor.buscarPorId(id);
    }
    
    public Proveedor buscarPorNombre(String nombreProveedor) {
        return daoProveedor.buscarPorNombre(nombreProveedor);
    }
    
    public boolean agregar(Proveedor _proveedor) {
        return daoProveedor.agregar(_proveedor);
    }
    
     public boolean editar(Proveedor _proveedor) {
         return daoProveedor.editar(_proveedor);
     }
     
     public boolean eliminar(int id) {
         return daoProveedor.eliminar(id);
     }
     
     public String getViewComboBoxProveedor(Empresa _empresa) {
         return daoProveedor.getViewComboBoxProveedor(_empresa);
     }
}
