
package controlador;

import conexion.ConnectionPool;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;


public class ControladorPDF extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JRException {
                response.setContentType("text/html;charset=UTF-8");
                String accion = request.getParameter("accion");
                
                switch (accion) {
            case "exportarPdfReporteVenta":
                try {
                    Connection conn = ConnectionPool.getInstance().getConnection();
                    
                    Map parameters = new HashMap ();
                    //parameters.put ("fechainicio", new Date() ); //A nuestro informe de prueba le vamos a enviar la fecha de hoy
                    
                    ServletOutputStream out;
                    /// Creamos un objecto jasper con el fichero previamente compilado
                    
                    //JasperReport reporte;
                    //reporte = (JasperReport)JRLoader.loadObject ("/home/hackro/NetBeansProjects/Reportes/src/java/Reporte/report1.jasper");
                
                    //JasperReport jasperReport = JasperCompileManager.compileReport(request.getSession().getServletContext().getRealPath("../../src/java/reportes/report1.jrxml"));
                    JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Users\\ACER\\Desktop\\BookwareOficial\\src\\java\\reportes\\report1.jrxml");
                    // Generamos el informe pasandole como parametros el objecto creado anteriormente jasperReport, parameters que es el hashmap
                    // creado anteriormente con todos los parametros que necesitemos enviarle al informe y conn una conexión activa con vuestro
                    // servidor de BD
                    
                    byte[] fichero = JasperRunManager.runReportToPdf (jasperReport, parameters, conn);
                    
                    // Y enviamos el pdf a la salida del navegador como podríamos hacer con cualquier otro pdf
                    response.setContentType("application/pdf");
                    response.setHeader("Content-disposition", "inline; filename=informeDemo.pdf");
                    response.setHeader("Cache-Control", "max-age=30");
                    response.setHeader("Pragma", "No-cache");
                    response.setDateHeader("Expires", 0);
                    response.setContentLength(fichero.length);
                    out = response.getOutputStream();

                    out.write(fichero, 0, fichero.length);
                    out.flush();
                    out.close();
                } catch (SQLException e) {
                    System.out.println("Error en controlador pdf venta: "+e.getMessage());
                }
                break;
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
        try {
            processRequest(request, response);
        } catch (JRException ex) {
            Logger.getLogger(ControladorPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (JRException ex) {
            Logger.getLogger(ControladorPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
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
