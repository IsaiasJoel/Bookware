package logicaDeNegocio;

import java.util.ArrayList;
import modelo.Empresa;
import modeloDAO.EmpresaDAO;

public class EmpresaLogic {
    EmpresaDAO daoEmpresa = new EmpresaDAO();
    
    public Empresa buscarPorRUC(String _RUC) {
        return daoEmpresa.buscarPorRUC(_RUC);
    }
    
    public Empresa buscarPorID(int _id) {
        return daoEmpresa.buscarPorID(_id);
    }
    
    public boolean agregar(Empresa empresa){
        return daoEmpresa.agregar(empresa);
    }
    
    public boolean editar(Empresa empresa) {
        return daoEmpresa.editar(empresa);
    }
    
    public ArrayList<Empresa> listar(){
        return daoEmpresa.listar();
    }
    
}
