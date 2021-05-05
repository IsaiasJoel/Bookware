package logicaDeNegocio;

import java.util.ArrayList;
import modelo.Empresa;
import modelo.Libro;
import modeloDAO.LibroDAO;

public class LibroLogic {
    LibroDAO daoLibro = new LibroDAO();
    
    public ArrayList<Libro> filtrar(String filtro){
        return daoLibro.filtrar(filtro);
    }
    
    public ArrayList<Libro> listar(Empresa _empresa) {
        return daoLibro.listar(_empresa);
    }
    
    public ArrayList<String> listarTitulo(Empresa _empresa) {
        return daoLibro.listarTitulo(_empresa);
    }
    
    public Libro buscarPorId(int id){
        return daoLibro.buscarPorId(id);
    }
    
    public Libro buscarPorNombre(String tituloLibro){
        return daoLibro.buscarPorNombre(tituloLibro);
    }
    
    public boolean agregar(Libro libro){
        return daoLibro.agregar(libro);
    }
    
    public boolean editar(Libro libro){
        return daoLibro.editar(libro);
    }
    
    public boolean eliminar(int id){
        return daoLibro.eliminar(id);
    }
    
    public String getViewRowLibro(Empresa _empresa){
        return daoLibro.getViewRowLibro(_empresa);
    }
    
    public String getViewComboBoxLibro(Empresa _empresa){
        return daoLibro.getViewComboBoxLibro(_empresa);
    }
    
    public String imprimirSubcategorias(int _idCategoria){
        return daoLibro.imprimirSubcategorias(_idCategoria);
    }
}
