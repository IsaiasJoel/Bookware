package logicaDeNegocio;

import java.util.ArrayList;
import modelo.Compra;
import modelo.DetalleCompra;
import modeloDAO.DetalleCompraDAO;

public class DetalleCompraLogic {
    DetalleCompraDAO daoDetalleCompra = new DetalleCompraDAO();
    
    public ArrayList<DetalleCompra> listar(Compra _compra) {
        return daoDetalleCompra.listar(_compra);
    }
    
    public String[] imprimirDetalleCompra_Libro_Cantidad_Precio(Compra _compra) {
        ArrayList<DetalleCompra> lstDetalles = daoDetalleCompra.listar(_compra);
        String[] filas = new String[3];
        filas[0] = "";
        filas[1] = "";
        filas[2] = "";
        for (DetalleCompra oDetalle : lstDetalles) {
            filas[0] += oDetalle.getCantidad() + "<br><br>";
            filas[1] += oDetalle.getoLibro().getTitulo() + "<br><br>";
            filas[2] += oDetalle.getPrecioCompra() + "<br><br>";
        }
        return filas;
    }
    
    public DetalleCompra buscarPorId(int id){
        return daoDetalleCompra.buscarPorId(id);
    }
    
    public boolean agregar(DetalleCompra _detalleCompra){
        return daoDetalleCompra.agregar(_detalleCompra);
    }

    public boolean editar(DetalleCompra _detalleCompra){
        return daoDetalleCompra.editar(_detalleCompra);
    }
    
    public boolean eliminar(int id){
      return daoDetalleCompra.eliminar(id);
    }
}
