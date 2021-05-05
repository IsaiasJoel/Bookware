package controlador;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import logicaDeNegocio.ClienteLogic;
import logicaDeNegocio.LibroLogic;
import logicaDeNegocio.ProveedorLogic;
import modelo.Cliente;
import modelo.Empresa;
import modelo.Libro;
import modelo.Proveedor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ControladorAjax extends HttpServlet {
    ArrayList<String> lstFiltrosProveedor = new ArrayList<>();
    ArrayList<String> lstFiltrosLibro = new ArrayList<>();
    ArrayList<String> lstFiltrosCliente = new ArrayList<>();
    
    ProveedorLogic logicProveedor = new ProveedorLogic();
    LibroLogic logicLibro = new LibroLogic();
    ClienteLogic logicCliente = new ClienteLogic();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("application/json;charset=utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        Gson gson = new Gson();
        
        HttpSession sesionActual = request.getSession();
        Empresa empresa = (Empresa) sesionActual.getAttribute("empresa");
        
        lstFiltrosProveedor = logicProveedor.listarNombres(empresa);
        lstFiltrosCliente = logicCliente.listarNombres(empresa);
        lstFiltrosLibro = logicLibro.listarTitulo(empresa);
        
        try (PrintWriter out = response.getWriter()) {
            switch (accion) {
                
                case "InputSearchProveedor":
                    try {
                        out.print(new Gson().toJson(lstFiltrosProveedor));
                    } catch (Exception e) {
                        System.out.println("error: " + e.getMessage());
                    }
                    break;
                    
                case "InputSearchLibro":
                    try {
                        out.print(gson.toJson(lstFiltrosLibro));
                    } catch (Exception e) {
                    }
                    break;
                    
                case "InputSearchCliente":
                    try {
                        out.print(gson.toJson(lstFiltrosCliente));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
