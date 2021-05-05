package logicaDeNegocio;

import java.util.ArrayList;
import modelo.DetalleVenta;
import modelo.Venta;
import modeloDAO.DetalleVentaDAO;

public class DetalleVentaLogic {
    DetalleVentaDAO daoDetalleVenta = new DetalleVentaDAO();
    
    public ArrayList<DetalleVenta> listar(Venta _venta){
        return daoDetalleVenta.listar(_venta);
    }
    
    public String[] imprimirDetalleVenta_Libro_Cantidad_Precio(Venta _venta) {
        ArrayList<DetalleVenta> lstDetalles = daoDetalleVenta.listar(_venta);
        String[] filas = new String[3];
        filas[0] = "";filas[1] = "";filas[2] = "";
        for (DetalleVenta oDetalle : lstDetalles) {
            filas[0] += oDetalle.getCantidad() + "<br><br><br>";
            filas[1] += oDetalle.getLibro().getTitulo() + "<br><br>";
            filas[2] += oDetalle.getPrecioVenta() + "<br><br><br>";
        }
        return filas;
    }
    
    public DetalleVenta buscarPorId(int _id){
        return daoDetalleVenta.buscarPorId(_id);
    }
    
    public boolean agregar(DetalleVenta _detalleVenta){
        return daoDetalleVenta.agregar(_detalleVenta);
    }
    
    public boolean editar(DetalleVenta _detalleVenta){
        return daoDetalleVenta.editar(_detalleVenta);
    }
    
    public boolean eliminar(int id){
        return daoDetalleVenta.eliminar(id);
    }
}
