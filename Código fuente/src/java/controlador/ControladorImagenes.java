package controlador;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logicaDeNegocio.EmpresaLogic;
import modelo.Empresa;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class ControladorImagenes extends HttpServlet {
    EmpresaLogic logicEmpresa = new EmpresaLogic();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Empresa empresaRoot = (Empresa) request.getSession().getAttribute("empresa");
        
        FileItemFactory file_factory = new DiskFileItemFactory();
        ServletFileUpload sfu = new ServletFileUpload(file_factory);
        
        ArrayList<String> campos = new ArrayList<>();
        ArrayList<String> imgs = new ArrayList<>();
        
        try{
            List items = sfu.parseRequest(request);
            for (int i = 0; i < items.size(); i++) {
                FileItem item = (FileItem) items.get(i);
                if(!item.isFormField()){
                    File archivo = new File("C:\\Users\\ACER\\Desktop\\BookwareOficial\\web\\images\\logosEmpresas\\"+item.getName());
                    item.write(archivo);
                    imgs.add("images/logosEmpresas/"+item.getName());
                }else{
                    campos.add(item.getString());
                }
            }
            
        }catch(Exception ex){
            
        }
        
        if(logicEmpresa.agregar(new Empresa(campos.get(0), campos.get(1), campos.get(2), imgs.get(0)))){
            response.getWriter().println("Producto Creado Exitosamente");
        }else{
            response.getWriter().println("ERROR al crear producto");
        }
        request.getRequestDispatcher("administracion.jsp").forward(request, response);
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

    public String imprimirTablaEmpresas(){
        String html = "";
        ArrayList<Empresa> lst = logicEmpresa.listar();
        
        for (Empresa oempresa : lst) {
            html += ""
                    + "<tr>"
                    + "     <td>"+oempresa.getRUC()+"</td>"
                    + "     <td>"+oempresa.getUsuario()+"</td>"
                    + "     <td><img src=\""+oempresa.getLogo()+"\" width=\"80\"></td>"
                    + "     <td class=\"text-dark\">"
                    + "         <button type=\"button\" class=\"btn\" data-toggle=\"modal\" data-target=\"\" onclick=\"\">\n"
                    + "             <svg width=\"1.2em\" height=\"1.2em\" viewBox=\"0 0 16 16\" class=\"bi bi-pencil-square\" fill=\"currentColor\" xmlns=\"http://www.w3.org/2000/svg\">\n"
                    + "                 <path d=\"M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456l-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z\"/>\n"
                    + "                 <path fill-rule=\"evenodd\" d=\"M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z\"/>\n"
                    + "             </svg>\n"
                    + "         </button>"
                    + "     </td>"
                    
                    + "     <td class=\"text-dark\">"
                    + "         <button type=\"button\" class=\"btn\" data-toggle=\"modal\" data-target=\"\">\n"
                    + "             <svg width=\"1.2em\" height=\"1.2em\" viewBox=\"0 0 16 16\" class=\"bi bi-trash\" fill=\"currentColor\" xmlns=\"http://www.w3.org/2000/svg\">\n"
                    + "                 <path d=\"M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z\"/>\n"
                    + "                 <path fill-rule=\"evenodd\" d=\"M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z\"/>\n"
                    + "             </svg> \n"
                    + "         </button>"
                    + "     </td>"
                    + "</tr>";
        }
        return html;
    }
}
