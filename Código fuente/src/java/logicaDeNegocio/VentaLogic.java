package logicaDeNegocio;

import java.util.ArrayList;
import modelo.Empresa;
import modelo.Venta;
import modeloDAO.VentaDAO;

public class VentaLogic {
    VentaDAO daoVenta = new VentaDAO();
    
    public ArrayList<Venta> listar(Empresa empresa) {
        return daoVenta.listar(empresa);
    }
    
    public Venta buscarPorCodigo(String _codigo){
        return daoVenta.buscarPorCodigo(_codigo);
    }
    
    public boolean agregar(Venta venta){
        return daoVenta.agregar(venta);
    }
    
    public boolean editar(Venta _venta){
        return daoVenta.editar(_venta);
    }
    
    public String GenerarID_BD(ArrayList<Venta> ventas, String _prefijo, int _digitos){
        return daoVenta.GenerarID_BD(ventas, _prefijo, _digitos);
    }
}
