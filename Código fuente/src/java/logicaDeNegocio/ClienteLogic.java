package logicaDeNegocio;

import java.util.ArrayList;
import modelo.Cliente;
import modelo.Empresa;
import modeloDAO.ClienteDAO;

public class ClienteLogic {
    ClienteDAO daoCliente = new ClienteDAO();
    
    public ArrayList<Cliente> listar(Empresa _empresa) {
        return daoCliente.listar(_empresa);
    }
    
    public ArrayList<String> listarNombres(Empresa _empresa) {
        return daoCliente.listarNombres(_empresa);
    }
    
    public Cliente buscarPorId(int _id) {
        return daoCliente.buscarPorId(_id);
    }
    
    public Cliente buscarPorDNI(String dniCliente) {
        return daoCliente.buscarPorDNI(dniCliente);
    }
    
    public Cliente buscarPorNombre(String nombreCliente) {
        return daoCliente.buscarPorNombre(nombreCliente);
    }
    
    public boolean agregar(Cliente _cliente) {
        return daoCliente.agregar(_cliente);
    }
    
    public boolean editar(Cliente _cliente){
        return daoCliente.editar(_cliente);
    }
    
    public boolean eliminar(int _id){
        return daoCliente.eliminar(_id);
    }
    
    public String getViewComboBoxCliente(Empresa _empresa){
        return daoCliente.getViewComboBoxCliente(_empresa);
    }
    
    public ArrayList<Cliente> filtrar(String filtro){
        return daoCliente.filtrar(filtro);
    }
}
