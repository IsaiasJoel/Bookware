package logicaDeNegocio;

import java.util.ArrayList;
import modelo.Compra;
import modelo.Empresa;
import modeloDAO.CompraDAO;

public class CompraLogic {
    CompraDAO daoCompra = new CompraDAO();
    
    public String GenerarID_BD(String _prefijo, int _digitos){
        return daoCompra.GenerarID_BD(_prefijo, _digitos);
    }
    
    public boolean agregar(Compra _compra) {
        return daoCompra.agregar(_compra);
    }
    
    public Compra buscarPorCodigo(String _codigo){
        return daoCompra.buscarPorCodigo(_codigo);
    }
    
    public ArrayList<Compra> listar(Empresa _empresa){
        return daoCompra.listar(_empresa);
    }
            
    public ArrayList<Compra> listar(){
        return daoCompra.listar();
    }
                    
    public boolean editar(Compra _compra){
        return daoCompra.editar(_compra);
    }
}
