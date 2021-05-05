package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logicaDeNegocio.ClienteLogic;
import logicaDeNegocio.CompraLogic;
import logicaDeNegocio.DetalleCompraLogic;
import logicaDeNegocio.DetalleVentaLogic;
import logicaDeNegocio.LibroLogic;
import logicaDeNegocio.ProveedorLogic;
import logicaDeNegocio.VentaLogic;
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
import modeloDAO.AutorDAO;
import modeloDAO.CategoriaDAO;
import modeloDAO.EditorialDAO;
import modeloDAO.EmpresaDAO;
import modeloDAO.SubcategoriaDAO;

public class ControladorGeneral extends HttpServlet {
    AutorDAO autorDAO = new AutorDAO();
    CategoriaDAO categoriaDAO = new CategoriaDAO();
    EditorialDAO editorialDAO = new EditorialDAO();
    SubcategoriaDAO subcategoriaDAO = new SubcategoriaDAO();
    LibroLogic logicLibro = new LibroLogic();
    ProveedorLogic logicProveedor = new ProveedorLogic();
    ClienteLogic logicCliente = new ClienteLogic();
    EmpresaDAO empresaDAO = new EmpresaDAO();
    CompraLogic logicCompra = new CompraLogic();
    DetalleCompraLogic logicDetalleCompra = new DetalleCompraLogic();
    DetalleVentaLogic logicDetalleVenta = new DetalleVentaLogic();
    VentaLogic logicVenta = new VentaLogic();
    
    ArrayList<DetalleCompra> lstProvisionalDetallesCompra;
    ArrayList<DetalleVenta> lstProvisionalDetallesVenta;

    Autor oAutor;
    Libro oLibro;
    Categoria oCategoria;
    Editorial oEditorial;
    Proveedor oProveedor;
    Cliente oCliente;
    Empresa oEmpresa;
    Compra oCompra;
    Venta oVenta;
    
    String rutaInventario = "inventario.jsp";
    String rutaProveedor = "proveedores.jsp";
    String rutaCliente = "clientes.jsp";
    String rutaIndex = "index.jsp";
    String rutaLogin = "login.jsp";
    String rutaCompra = "compras.jsp";
    String rutaVenta = "ventas.jsp";
    String ruta = ""; //queda pendiente poner una página de error
    String html = "";
    String tablaDetallesVenta = "";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        response.setContentType("application/json; charset=utf-8");
        String accion = request.getParameter("accion");
        try (PrintWriter out = response.getWriter()) {
            HttpSession sesionActual = request.getSession(); //cada vez que entra al processRequest captura la sesion actual
            lstProvisionalDetallesCompra = (ArrayList<DetalleCompra>) sesionActual.getAttribute("lstDetallesCompra");
            lstProvisionalDetallesVenta = (ArrayList<DetalleVenta>) sesionActual.getAttribute("lstDetallesVenta");
            
            switch (accion) {
                case "RegistrarAutor":
                    try {
                        String nombreAutor = request.getParameter("nombreAutor");
                        String apellidosAutor = request.getParameter("apellidosAutor");
                        String nacionalidadAutor = request.getParameter("nacionalidadAutor");
                        
                        if (autorDAO.agregar(new Autor(nombreAutor,apellidosAutor,nacionalidadAutor))) {
                            ruta = rutaInventario;
                        }
                    } catch (Exception e) {
                        System.out.println("error en controlador, registrar autor. "+e.getMessage());
                    }
                    break;
                    
                case "EditarAutor":
                    try {
                        int idAutor = Integer.parseInt(request.getParameter("idAutor"));
                        String uNombreAutor = request.getParameter("uNombreAutor");
                        String uApellidosAutor = request.getParameter("uApellidosAutor");
                        String uNacionalidadAutor = request.getParameter("uNacionalidadAutor");

                        if (autorDAO.editar(new Autor(idAutor, uNombreAutor, uApellidosAutor, uNacionalidadAutor))) {
                            ruta = rutaInventario;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("error en controlador editar autor "+e.getMessage());
                    }
                    break;
                    
                case "EliminarAutor":
                    try {
                        int idEliminarAutor = Integer.parseInt(request.getParameter("dIdAutor"));
                        
                        if (autorDAO.eliminar(idEliminarAutor)) {
                            ruta = rutaInventario;
                        }
                        
                    } catch (NumberFormatException e) {
                        System.out.println("error en controlador eliminar autor: "+e.getMessage());
                    }
                    break;
                    
                case "RegistrarCategoria":
                    try {
                        String nombreCategoria = request.getParameter("nombreCategoria");
                        
                        if (categoriaDAO.agregar(new Categoria(nombreCategoria))) {
                            ruta = rutaInventario;
                        }
                    } catch (Exception e) {
                        System.out.println("error en controlador agregar categoria: "+e.getMessage());
                    }
                    break;
                    
                case "EditarCategoria":
                    System.out.println("entró a controlador editar categoria");
                    try {
                        int uIdCategoria = Integer.parseInt(request.getParameter("uIdCategoria"));
                        String uNombreCategoria = request.getParameter("uNombreCategoria");
                        
                        if (categoriaDAO.editar(new Categoria(uIdCategoria, uNombreCategoria))) {
                            ruta = rutaInventario;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("error en controlador editar categoria: "+e.getMessage());
                    }
                    break;
                    
                case "EliminarCategoria":
                    int dIdCategoria = Integer.parseInt(request.getParameter("dIdCategoria"));
                    if (categoriaDAO.eliminar(dIdCategoria)) {
                        ruta = rutaInventario;
                    }
                    break;
                    
                case "RegistrarEditorial":
                    try {
                        String nombreEditorial = request.getParameter("nombreEditorial");
                        if (editorialDAO.agregar(new Editorial(nombreEditorial))) {
                            ruta = rutaInventario;
                        }
                    } catch (Exception e) {
                        System.out.println("error en controlador registrar editorial: "+e.getMessage());
                    }
                    break;
                    
                case "EditarEditorial":
                    try {
                        int uIdEditorial = Integer.parseInt(request.getParameter("uIdEditorial"));
                        String uNombreEditorial = request.getParameter("uNombreEditorial");
                        if (editorialDAO.editar(new Editorial(uIdEditorial, uNombreEditorial))) {
                            ruta = rutaInventario;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("error en controlador editar editorial: "+e.getMessage());
                    }
                    break;
                    
                case "EliminarEditorial":
                    try {
                        int dIdEditorial = Integer.parseInt(request.getParameter("dIdEditorial"));
                        if (editorialDAO.eliminar(dIdEditorial)) {
                            ruta = rutaInventario;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("error en controlador eliminar editorial: "+e.getMessage());
                    }
                    break;
                    
                case "RegistrarSubcategoria":
                    try {
                        String nombreSubcategoria = request.getParameter("nombreSubcategoria");
                        String nombreCategoria = request.getParameter("nombreCategoria");
                        Categoria categoria = categoriaDAO.buscarPorNombre(nombreCategoria);
                        if (categoria != null) {
                            if (subcategoriaDAO.agregar(new Subcategoria(nombreSubcategoria, categoria))) {
                                ruta = rutaInventario;
                            }
                        }
                    } catch (NullPointerException e) {
                        System.out.println("error en conrtolador registrar subcategoria: "+e.getMessage());
                    }
                    break;
                    
                case "EditarSubcategoria":
                    try {
                        int uIdSubcategoria = Integer.parseInt(request.getParameter("uIdSubcategoria"));
                        String uNombreSubcategoria = request.getParameter("uNombreSubcategoria");
                        String uNombreCategoriaSubcategoria = request.getParameter("uNombreCategoriaSubcategoria");
                        
                        Categoria categoria = categoriaDAO.buscarPorNombre(uNombreCategoriaSubcategoria);
                        
                        if (categoria != null) {
                            if (subcategoriaDAO.editar(new Subcategoria(uIdSubcategoria, uNombreSubcategoria, categoria))) {
                                ruta = rutaInventario;
                            }
                        }
                    } catch (NullPointerException e) {
                        System.out.println("error en controlador editar subcategoria: "+e.getMessage());
                    }
                    break;
                    
                case "EliminarSubcategoria":
                    try {
                        int dIdSubcategoria = Integer.parseInt(request.getParameter("dIdSubcategoria"));
                        if (subcategoriaDAO.eliminar(dIdSubcategoria)) {
                            ruta = rutaInventario;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("error en controlador eliminar subactegoria: "+e.getMessage());
                    }
                    break;
                    
                case "RegistrarLibro":
                    try {
                        String tituloLibro = request.getParameter("tituloLibro");
                        int stockLibro = Integer.parseInt(request.getParameter("stockLibro"));
                        double gananciaLibro = Double.parseDouble(request.getParameter("gananciaLibro"));
                        
                        String autorLibro = request.getParameter("autorLibro");
                        oAutor = autorDAO.buscarPorNombre(autorLibro);
                        
                        String nombreEditorial = request.getParameter("editorialLibro");
                        oEditorial = editorialDAO.buscarPorNombre(nombreEditorial);
                        
                        String categoriaLibro = request.getParameter("categoriaLibro");
                        oCategoria = categoriaDAO.buscarPorNombre(categoriaLibro);
                        
                        double precioSugerido = Double.parseDouble(request.getParameter("precioSugeridoLibro"));
                        
                        if (oAutor != null && oEditorial != null && oCategoria != null) {
                            oLibro = new Libro(tituloLibro, stockLibro, precioSugerido, gananciaLibro, oAutor, oEditorial, oCategoria);
                            if (logicLibro.agregar(oLibro)) {
                                ruta = rutaInventario;
                            }
                        }else{
                            System.out.println("algunos de los elementos en controlador libro es nulo");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("error en controlador agregar libro: "+e.getMessage());
                    }
                    break;
                    
                case "EditarLibro":
                    try {
                        int uIdLibro = Integer.parseInt(request.getParameter("uIdLibro"));
                        String uTituloLibro = request.getParameter("uTituloLibro");
                        
                        String uAutorLibro = request.getParameter("uAutorLibro");
                        this.oAutor = autorDAO.buscarPorNombre(uAutorLibro);
        
                        String uEditorialLibro = request.getParameter("uEditorialLibro");
                        this.oEditorial = editorialDAO.buscarPorNombre(uEditorialLibro);
                        
                        String uCategoriaLibro = request.getParameter("uCategoriaLibro");
                        this.oCategoria = categoriaDAO.buscarPorNombre(uCategoriaLibro);
                        
                        double uPrecioLibro = Double.parseDouble(request.getParameter("uPrecioLibro"));
                        double uGananciaLibro = Double.parseDouble(request.getParameter("uGananciaLibro"));
                        
                        if (this.oAutor != null && this.oEditorial != null && this.oCategoria != null) {
                            if (logicLibro.editar(new Libro(uIdLibro, uTituloLibro, uPrecioLibro, uGananciaLibro, this.oAutor, this.oEditorial, this.oCategoria))) {
                                ruta = rutaInventario;
                            }
                        }else{
                            System.out.println("Algunos de los campos en editar son nulos");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("error en controlador editar libro: "+e.getMessage());
                    }
                    break;
                    
                case "EliminarLibro":
                    try {
                        int dIdLibro = Integer.parseInt(request.getParameter("dIdLibro"));
                        
                        if (logicLibro.eliminar(dIdLibro)) {
                            ruta = rutaInventario;
                        }
                        
                    } catch (NumberFormatException e) {
                        System.out.println("error en controlador eliminar libro: "+e.getMessage());
                    }
                    break;
                    
                case "RegistrarProveedor":
                    try {
                        String nombreProveedor = request.getParameter("nombreProveedor");
                        if (logicProveedor.agregar(new Proveedor(nombreProveedor))) {
                            ruta = rutaProveedor;
                        }
                    } catch (NullPointerException e) {
                        System.out.println("error en controlador registrar proveedor: "+e.getMessage());
                    }
                    break;
                    
                case "EditarProveedor":
                    try {
                        int uIdProveedor = Integer.parseInt(request.getParameter("uIdProveedor"));
                        String uNombreProveedor = request.getParameter("uNombreProveedor");
                        
                        if (logicProveedor.editar(new Proveedor(uIdProveedor, uNombreProveedor))) {
                            ruta = rutaProveedor;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("error controlador editar proveedor: "+e.getMessage());
                    }
                    break;
                    
                case "EliminarProveedor":
                    try {
                        int dIdProveedor = Integer.parseInt(request.getParameter("dIdProveedor"));
                        if (logicProveedor.eliminar(dIdProveedor)) {
                            ruta = rutaProveedor;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("error en controlador eliminar proveedor: "+e.getMessage());
                    }
                    break;
                    
                case "RegistrarCliente":
                    try {
                        String nombreCliente = request.getParameter("nombreCliente");
                        String dniCliente = request.getParameter("dniCliente");
                        if (dniCliente.length() == 8) {
                            if (logicCliente.agregar(new Cliente(dniCliente, nombreCliente))) {
                                ruta = rutaCliente;
                            }
                        }else{
                            System.out.println("faltan o sobran digitos del dni");
                        }
                    } catch (NullPointerException e) {
                        System.out.println("error en controlador registrar cliente: " + e.getMessage());
                    }
                    break;
                    
                case "EditarCliente":
                    try {
                        int uIdCliente = Integer.parseInt(request.getParameter("uIdCliente"));
                        String uDniCliente = request.getParameter("uDniCliente");
                        String uNombreCliente = request.getParameter("uNombreCliente");

                        if (logicCliente.editar(new Cliente(uIdCliente,uDniCliente, uNombreCliente))) {
                            ruta = rutaCliente;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("error controlador editar cliente: " + e.getMessage());
                    }
                    break;
                    
                case "EliminarCliente":
                    try {
                        int dIdCliente = Integer.parseInt(request.getParameter("dIdCliente"));
                        if (logicCliente.eliminar(dIdCliente)) {
                            ruta = rutaCliente;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("error en controlador eliminar cliente: " + e.getMessage());
                    }
                    break;
                    
                case "Logear":
                    System.out.println("Entró a controlador logear");
                    try {
                        String usuarioEmpresa = request.getParameter("usuarioEmpresa");
                        String passwordEmpresa = request.getParameter("passwordEmpresa");

                        oEmpresa = empresaDAO.verificarLogeo(usuarioEmpresa, passwordEmpresa);

                        if (oEmpresa != null) {
                            sesionActual.setAttribute("empresa", oEmpresa);
                            System.out.println("Creó la sesión");
                            ruta = rutaIndex;
                        }
                    } catch (NullPointerException e) {
                        System.out.println("error en controlador logeo: "+e.getMessage());
                    }
                    break;
                    
                case "CerrarSesion":
                    //logica para cerrar la sesion
                    request.getSession().invalidate();
                    ruta = rutaLogin;
                    break;
                    
//                    agregar un libro nuevo mediante COMPRA
                case "AgregarLibroNuevoCompra":
                    try {
                        String tituloLibroCompra = request.getParameter("libroCompra");
                        int cantidadLibroCompra = Integer.parseInt(request.getParameter("cantidadLibroCompra"));
                        double precioCompraLibro = Double.parseDouble(request.getParameter("precioCompraLibro"));
                        
                        double gananciaLibroCompra = Double.parseDouble(request.getParameter("gananciaLibroCompra"));
                        Autor autorLibroCompra = autorDAO.buscarPorNombre(request.getParameter("autorLibroCompra"));
                        Editorial editorialLibro = editorialDAO.buscarPorNombre(request.getParameter("editorialLibroCompra"));
                        Categoria categoriaLibro = categoriaDAO.buscarPorNombre(request.getParameter("categoriaLibroCompra"));
                        double gananciaLibro = Double.parseDouble(request.getParameter("gananciaLibro"));
                        double precioSugeridoLibro = precioCompraLibro + gananciaLibroCompra;
                        
                        //agregar el libro al detalle de compra
                        oLibro = new Libro(tituloLibroCompra, cantidadLibroCompra, precioSugeridoLibro, gananciaLibro, autorLibroCompra, editorialLibro, categoriaLibro);
                        double subtotal = precioCompraLibro * cantidadLibroCompra;
                        DetalleCompra detalleCompra;
                        detalleCompra = new DetalleCompra(subtotal,precioCompraLibro,cantidadLibroCompra,oLibro);

                        lstProvisionalDetallesCompra.add(detalleCompra);

                        html="";
                        for (DetalleCompra oDetalleCompra : lstProvisionalDetallesCompra) {
                            html += ""
                                    + "<tr>\n"
                                    + "   <td>"+ oDetalleCompra.getoLibro().getTitulo()+"</td>\n"
                                    + "   <td>"+oDetalleCompra.getCantidad()+"</td>\n"
                                    +"    <td>"+oDetalleCompra.getPrecioCompra()+"</td>\n"
                                    + "   <td>"+oDetalleCompra.getSubtotal()+"</td>\n"
                                    + "   <td>\n"
                                    + "      <svg width=\"1.2em\" height=\"1.2em\" viewBox=\"0 0 16 16\" class=\"bi bi-pencil-square\" fill=\"currentColor\" xmlns=\"http://www.w3.org/2000/svg\">\n"
                                    + "         <path d=\"M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456l-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z\"/>\n"
                                    + "         <path fill-rule=\"evenodd\" d=\"M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z\"/>\n"
                                    + "      </svg>\n"
                                    + "   </td>\n"
                                    + "</tr>";
                        }
                        sesionActual.setAttribute("tablaDetallesCompra", html);

                        ruta = rutaCompra;
                    } catch (NullPointerException e) {
                        System.out.println("error en controlador agregar libro nuevo por compra: "+e.getMessage());
                    }
                    break;
                    
//                    aumentar el stock de un libro existente
                case "AgregarLibroExisteCompra":
                    try {
                        System.out.println("Entró a controlador libro existe compra");
                        String libroExisteCompra = request.getParameter("libroExisteCompra");
                        int cantidadLibroExisteCompra = Integer.parseInt(request.getParameter("cantidadLibroExisteCompra"));
                        double precioLibroExisteCompra = Double.parseDouble(request.getParameter("precioLibroExisteCompra"));
                        double subtotalDetalleLibroExiste = cantidadLibroExisteCompra * precioLibroExisteCompra;
                        
                        Libro oLibroExisteCompra = logicLibro.buscarPorNombre(libroExisteCompra);
                        if (oLibroExisteCompra != null) {
                            oLibroExisteCompra.setStock(oLibroExisteCompra.getStock() + cantidadLibroExisteCompra); //acá le estoy aumentando el stock al libro
                            
                            DetalleCompra detalleCompraLibroExiste = new DetalleCompra(subtotalDetalleLibroExiste, precioLibroExisteCompra, cantidadLibroExisteCompra, oLibroExisteCompra);
                            
                            lstProvisionalDetallesCompra.add(detalleCompraLibroExiste);
                            
                            html = "";
                            for (DetalleCompra oDetalleCompra : lstProvisionalDetallesCompra) {
                                html += ""
                                        + "<tr>\n"
                                        + "   <td>" + oDetalleCompra.getoLibro().getTitulo() + "</td>\n"
                                        + "   <td>" + oDetalleCompra.getCantidad() + "</td>\n"
                                        + "    <td>" + oDetalleCompra.getPrecioCompra() + "</td>\n"
                                        + "   <td>" + oDetalleCompra.getSubtotal() + "</td>\n"
                                        + "   <td>" + oDetalleCompra.getoLibro().getGanancia() + "</td>\n"
                                        + "   <td>\n"
                                        + "      <button type=\"button\" class=\"btn btn-warning\">\n"
                                        + "         <svg width=\"1.2em\" height=\"1.2em\" viewBox=\"0 0 16 16\" class=\"bi bi-pencil-square\" fill=\"currentColor\" xmlns=\"http://www.w3.org/2000/svg\">\n"
                                        + "             <path d=\"M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456l-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z\"/>\n"
                                        + "             <path fill-rule=\"evenodd\" d=\"M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z\"/>\n"
                                        + "         </svg>\n"
                                        + "      </button>\n"
                                        + "   </td>\n"
                                        +"\n"
                                        + "   <td>\n"
                                        + "     <button type = \"button\" class=\"btn btn-danger\">\n"
                                        +"         <svg width = \"1.2em\" height = \"1.2em\" viewBox = \"0 0 16 16\" class=\"bi bi-trash\" fill = \"currentColor\" xmlns = \"http://www.w3.org/2000/svg\">\n" 
                                        +"            <path d = \"M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z\"/>\n" 
                                        +"            <path fill - rule = \"evenodd\" d = \"M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z\"/>\n"
                                        +"         </svg>\n" 
                                        +"     </button>\n"
                                        + "   </td>\n"
                                        + "</tr>";
                            }
                            sesionActual.setAttribute("tablaDetallesCompra", html);
                            ruta = rutaCompra;
//                            System.out.println("el libro nuevo se modificó correctamente");
                        }else{
                            System.out.println("el libro devuelto es nulo");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("error en controlador agregar libro existe compra: "+e.getMessage());
                    }
                    break;
                    
                case "RealizarCompra":
                    Proveedor proveedor = logicProveedor.buscarPorNombre(request.getParameter("proveedorCompra"));
                    
                    oEmpresa = (Empresa) sesionActual.getAttribute("empresa");
                    String codigoCompra = logicCompra.GenerarID_BD("COMP", 4);
                    double totalCompra = 0;
                    
                    for (DetalleCompra detalleCompra : lstProvisionalDetallesCompra) {
                        totalCompra += detalleCompra.getSubtotal();
                    }
                    oCompra = new Compra(totalCompra, new Date(), oEmpresa, proveedor, codigoCompra);

                    if (logicCompra.agregar(oCompra)) {
                        System.out.println("Agregó la compra a la BBDD");
                        
                        //acá obtenemos la compra desde la BBDD
                        oCompra = logicCompra.buscarPorCodigo(oCompra.getCodigoCompra());
                        
                        for (DetalleCompra detalleCompra : lstProvisionalDetallesCompra) {
                            detalleCompra.setoCompra(oCompra);
                            
                            //editar o agrega los libros que contienen los detalles
                            if (detalleCompra.getoLibro().getId_libro() == 0) { //si el id de un libro es 0 significa que no existe en la BBDD
                                logicLibro.agregar(detalleCompra.getoLibro());
                            } else {
                                logicLibro.editar(detalleCompra.getoLibro());
                            }
                            logicDetalleCompra.agregar(detalleCompra); //registrar los detalles de cpmpra en la bbdd
                        }
                        sesionActual.setAttribute("tablaDetallesCompra","");
                        sesionActual.setAttribute("mensajeConfirmación", "ok");
                        System.out.println("Todo debe de haber salido okis");
                        ruta = rutaCompra;
                    }
                    break;
                    
                case "AgregarDetalleVenta":
                    try {
                        Libro libro = logicLibro.buscarPorNombre(request.getParameter("libroVenta"));
                        int cantidadLibroVenta = Integer.parseInt(request.getParameter("cantidadLibroVenta"));
                        double precioVenta = Double.parseDouble(request.getParameter("precioVenta"));
                        double subtotal = cantidadLibroVenta * precioVenta;
                        libro.setStock(libro.getStock() - cantidadLibroVenta);
                        
                        lstProvisionalDetallesVenta.add(new DetalleVenta(subtotal,precioVenta,cantidadLibroVenta,libro));
                        
                        tablaDetallesVenta = "";
                        for (DetalleVenta oDetalleVenta : lstProvisionalDetallesVenta) {
                            tablaDetallesVenta += ""
                                    + "<tr id=\"tabla_vertical\">\n"
                                    + "    <td>"+oDetalleVenta.getLibro().getTitulo()+"</td>\n"
                                    + "    <td>"+oDetalleVenta.getCantidad()+"</td>\n"
                                    + "    <td>s/"+oDetalleVenta.getPrecioVenta()+"</td>\n"
                                    + "    <td>s/"+oDetalleVenta.getSubtotal()+"</td>\n"
                                    + "    \n"
                                    + "    <td class=\"editar\">\n"
                                    + "        <button type=\"button\" class=\"botones-acciones\">\n"
                                    + "            <i class=\"fas fa-edit\"></i>\n"
                                    + "        </button>\n"
                                    + "    </td>\n"
                                    + "    \n"
                                    + "    <td class=\"eliminar\">\n"
                                    + "        <button type=\"button\" class=\"botones-acciones\">\n"
                                    + "            <i class=\"fas fa-trash\"></i>\n"
                                    + "        </button>\n"
                                    + "    </td>\n"
                                    + "</tr>";
                        }
                        sesionActual.setAttribute("tablaDetallesVenta", tablaDetallesVenta);
                        ruta = rutaVenta;
                    } catch (NumberFormatException e) {
                        System.out.println("error en controlador agregar detalle venta: "+e.getMessage());
                    }
                    break;
                    
                case "RealizarVenta":
                    try {
                        oCliente = logicCliente.buscarPorNombre(request.getParameter("clienteVenta"));
                        oEmpresa = (Empresa) sesionActual.getAttribute("empresa");
                        String tipoDocumento = request.getParameter("tipoDocumentoVenta");
                        double totalVenta = 0;
                        String codigoVenta = logicVenta.GenerarID_BD(logicVenta.listar(oEmpresa), "VENT", 4);
                        
                        for (DetalleVenta oDetalleVenta : lstProvisionalDetallesVenta) {
                            totalVenta += oDetalleVenta.getSubtotal();
                        }
                        if (logicVenta.agregar(new Venta(totalVenta, new Date(), tipoDocumento, oEmpresa, oCliente, codigoVenta))) {
                            oVenta = logicVenta.buscarPorCodigo(codigoVenta);
                            
                            for (DetalleVenta oDetalleVenta : lstProvisionalDetallesVenta) {
                                oDetalleVenta.setVenta(oVenta); //asigno los detalles de venta a la venta actual
                                
                                logicLibro.editar(oDetalleVenta.getLibro()); //edito los libros del detalle de venta
                                
                                logicDetalleVenta.agregar(oDetalleVenta); //agrego los detalles de venta a la bbd
                            }
                            System.out.println("Se agregaron correctamente los detalles de venta");
                        }else{
                            System.out.println("no se pudo agregar la venta a la bbdd");
                        }
                        
                        sesionActual.setAttribute("tablaDetallesVenta","");
                        sesionActual.setAttribute("mensajeConfirmaciónVenta", "ok");
                        ruta = rutaVenta;
                    } catch (Exception e) {
                        System.out.println("error en controlador agregar venra: "+e.getMessage());
                    }
                    break;
            }
            request.getRequestDispatcher(ruta).forward(request, response);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    public String imprimirDetalleVenta(Empresa _empresa){
        String tablaVentas = "";
        try {
//            oEmpresa = (Empresa) sesionActual.getAttribute("empresa");
            ArrayList<Venta> lstVentas = logicVenta.listar(_empresa);
            String detallesVenta[];
            for (Venta oventa : lstVentas) {
                detallesVenta = logicDetalleVenta.imprimirDetalleVenta_Libro_Cantidad_Precio(oventa);
                tablaVentas += ""
                        + "<tr>\n"
                        + "<td>" + oventa.getCodigoVenta() + "</td>\n"
                        + "<td>" + oventa.getFechaVenta() + "</td>\n"
                        + "<td>" + oventa.getTipoDocumento() + "</td>\n"
                        + "<td>" + oventa.getCliente().getDni() + " - " + oventa.getCliente().getNombres() + "</td>\n"
                        + "<td>" + detallesVenta[0] + "</td>\n"
                        + "<td>" + detallesVenta[1] + "</td>\n"
                        + "<td>" + detallesVenta[2] + "</td>\n"
                        + "<td>" + oventa.getTotalVenta() + "</td>\n"
                        + "</tr>\n";
            }
            
        } catch (Exception e) {
            System.out.println("error en controlador Listar Ventas: " + e.getMessage());
        }
        return tablaVentas;
    }
    
    public String imprimirDetalleCompra(Empresa _empresa) {
        String tablaCompras = "";
        try {
            ArrayList<Compra> lstCompra = logicCompra.listar(_empresa);
            String detallesCompra[];
            for (Compra ocompra : lstCompra) {
                detallesCompra = logicDetalleCompra.imprimirDetalleCompra_Libro_Cantidad_Precio(ocompra);
                tablaCompras += ""
                        + "<tr>\n"
                        + "<td>" + ocompra.getCodigoCompra()+ "</td>\n"
                        + "<td>" + ocompra.getFechaCompra()+ "</td>\n"
                        + "<td>" + ocompra.getoProveedor().getNombres()+"</td>\n"
                        + "<td>" + detallesCompra[0] + "</td>\n"
                        + "<td>" + detallesCompra[1] + "</td>\n"
                        + "<td>" + detallesCompra[2] + "</td>\n"
                        + "<td>" + ocompra.getTotalCompra()+ "</td>\n"
                        + "</tr>\n";
            }

        } catch (Exception e) {
            System.out.println("error en controlador Listar Compras: " + e.getMessage());
        }
        return tablaCompras;
    }
}
