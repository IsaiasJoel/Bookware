<%@page import="modelo.Perfil"%>
<!DOCTYPE jsp>
<!-- <%@page import="java.util.ArrayList"%>
<%@page import="modeloDAO.EditorialDAO"%>
<%@page import="modeloDAO.SubcategoriaDAO"%>
<%@page import="modeloDAO.CategoriaDAO"%>
<%@page import="modeloDAO.AutorDAO"%>
<%@page import="modelo.Empresa"%>
<%@page import="java.util.List"%>
<%@page import="modelo.DetalleCompra"%>
<%@page import="modeloDAO.DetalleCompraDAO"%>
<%@page import="modelo.Compra"%>
<%@page import="modeloDAO.LibroDAO"%>
<%@page import="modeloDAO.ProveedorDAO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page session="true"%> -->
<jsp lang="es">
    <head>
        <title>BookWare</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        
        <link rel="icon" type="image/png" href="images/favicon.ico">
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
        <link rel="stylesheet" href="assets/fonts/fontawesome-all.min.css">
        <link rel="stylesheet" href="assets/css/index.css">
        <link rel="stylesheet" href="assets/css/compra.css">
        <link rel="stylesheet" href="assets/css/tablas.css">
        <link href="assets/css/jquery-ui.css" rel="stylesheet" type="text/css"/>
    </head>

    <body id="page-top">
        <!-- <%
            HttpSession sesionActual = request.getSession();
            Empresa empresaLogeo = (Empresa) sesionActual.getAttribute("empresa");
            Perfil perfil = (Perfil) sesionActual.getAttribute("perfil");
            ArrayList<DetalleCompra> lstDetallesCompra = (ArrayList<DetalleCompra>) sesionActual.getAttribute("lstDetallesCompra");
            String mensajeConfirmacion = (String) sesionActual.getAttribute("mensajeConfirmaci&oacute;n");
            
            if (lstDetallesCompra == null) {
                lstDetallesCompra = new ArrayList<>();
                sesionActual.setAttribute("lstDetallesCompra", lstDetallesCompra);
            }

            String tablaDetallesCompra = (String) sesionActual.getAttribute("tablaDetallesCompra");

            if (tablaDetallesCompra == null) {
                tablaDetallesCompra = "";
            }
            
            if (mensajeConfirmacion == null) {
                mensajeConfirmacion = "";
            }
            if (empresaLogeo.getLogo() == null) {empresaLogeo.setLogo("");}
        %> -->
        
        <div id="wrapper">
            <nav class="navbar sidebar-dark align-items-start sidebar  accordion p-0 nav_vertical">
                <div class="container-fluid d-flex flex-column p-0">
                    <a class="navbar-brand d-flex justify-content-center align-items-center sidebar-brand m-0" href="#">
                        <div class="sidebar-brand-icon rotate-n-15"><i class="fas fa-book-open"></i></div>
                        <div class="sidebar-brand-text mx-3"><span class="bookware">BOOKWARE</span></div>
                    </a>
                    <hr class="sidebar-divider my-0">
                    <div class="d-flex justify-content-center flex-column cont-logo">
                        <div class="d-flex align-items-center logo">
                            <img src="" width="150" height="150">
                        </div>
                        
                        <label for="" class="empresa"> <%= empresaLogeo.getRazonSocial()%> </label>
                        <label for="" class="ruc"><%= empresaLogeo.getRUC()%></label>
                    </div>
                    <hr class="sidebar-divider my-0">

                    <ul class="nav navbar-nav text-light" id="accordionSidebar" style="margin-top: 30px;">
                        <!--INICIO-->
                        <li class="nav-item" role="presentation" id="btn_inicio">
                            <a class="nav-link active" href="index.jsp" style="margin-bottom: 12px;">
                                <i class="fas fa-home"></i>
                                <span>Inicio</span>
                            </a>
                        </li>

                        <!--CONSULTAS-->
                        <li class="nav-item" role="presentation">
                            <a class="nav-link dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="margin-bottom: 12px;">
                                <i class="fas fa-book"></i>
                                <span>Consultas</span>
                            </a>

                            <div class="dropdown-menu dropdown-menu-right xs-2 sm-2" aria-labelledby="dropdownMenuLink">
                                <!--clientes-->
                                <a class="dropdown-item" href="clientes.jsp" id="btn_Cliente">
                                    <i class="far fa-user"></i> Clientes
                                </a>
                                <!--inventario-->
                                <a class="dropdown-item" href="inventario.jsp" id="btn_inventario">
                                    <i class="fas fa-book-reader"></i> Inventario
                                </a>
                                <!--proveedores-->
                                <a class="dropdown-item" href="proveedores.jsp">
                                    <i class="fas fa-luggage-cart"></i> Proveedores
                                </a>
                            </div>
                        </li>

                        <!--COMPRAS -->
                        <li class="nav-item" role="presentation">
                            <a class="nav-link" href="compras.jsp" style="margin-bottom: 12px;" id="btn_compra">
                                <i class="fas fa-truck"></i>
                                <span>Compras</span>
                            </a>
                        </li>

                        <!--VENTAS-->
                        <li class="nav-item" role="presentation">
                            <a class="nav-link" href="ventas.jsp" style="margin-bottom: 12px;" id="btn_ventas">
                                <i class="fas fa-shopping-cart"></i>
                                <span>Ventas</span>
                            </a>
                        </li>

                        <!--REPORTES-->
                        <li class="nav-item" role="presentation">
                            <a class="nav-link dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="margin-bottom: 12px;">
                                <i class="fas fa-clipboard-list"></i>
                                <span>Reportes</span>
                            </a>

                            <div class="dropdown-menu dropdown-menu-right xs-2 sm-2" aria-labelledby="dropdownMenuLink">
                                <!--Reporte compras-->
                                <a class="dropdown-item" href="reportesCompra.jsp">
                                    Reporte compras
                                </a>
                                <!--Resportes ventas-->
                                <a class="dropdown-item" href="reportesVenta.jsp">
                                    Reporte ventas
                                </a>
                            </div>
                        </li>

                        <!--CERRAR SESION-->
                        <li class="nav-item" role="presentation">
                            <a class="nav-link"href="ControladorGeneral?accion=CerrarSesion" style="margin-bottom:12px;">
                                <i class="fas fa-reply"></i>
                                <span>Cerrar Sesi&oacute;n</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </nav>
            
            <div class="d-flex flex-column" id="content-wrapper">
                <div id="content">
                    <nav class="navbar navbar-light navbar-expand bg-white shadow mb-4 topbar static-top">
                        <div class="container-fluid"><button class="btn btn-link d-md-none rounded-circle mr-3" id="sidebarToggleTop" type="button"><i class="fas fa-bars"></i></button>
                            <form class="form-inline d-none d-sm-inline-block mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                                <div class="input-group"></div>
                            </form>
                            
                            <ul class="nav navbar-nav flex-nowrap ml-auto">
                                <li class="nav-item dropdown d-sm-none no-arrow">
                                    <a class="dropdown-toggle nav-link" data-toggle="dropdown" aria-expanded="false" href="#">
                                        <i class="fas fa-search"></i>
                                    </a>
                                        
                                    <div class="dropdown-menu dropdown-menu-right p-3 animated--grow-in" role="menu" aria-labelledby="searchDropdown">
                                        <form class="form-inline mr-auto navbar-search w-100">
                                            <div class="input-group">
                                                <input class="bg-light form-control border-0 small" type="text" placeholder="Search for ...">
                                                <div class="input-group-append">
                                                    <button class="btn btn-primary py-0" type="button">
                                                        <i class="fas fa-search"></i>
                                                    </button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </li>
                                
                                <li class="nav-item dropdown no-arrow mx-1" role="presentation">
                                    <div class="nav-item dropdown no-arrow"><a class="dropdown-toggle nav-link" data-toggle="dropdown" aria-expanded="false" href="#"></a>
                                        <div class="dropdown-menu dropdown-menu-right dropdown-list dropdown-menu-right animated--grow-in" role="menu">
                                            <h6 class="dropdown-header">alerts center</h6>
                                            <a class="d-flex align-items-center dropdown-item" href="#">
                                                <div class="mr-3">
                                                    <div class="bg-primary icon-circle"><i class="fas fa-file-alt text-white"></i></div>
                                                </div>
                                                <div><span class="small text-gray-500">December 12, 2019</span>
                                                    <p>A new monthly report is ready to download!</p>
                                                </div>
                                            </a>
                                            <a class="d-flex align-items-center dropdown-item" href="#">
                                                <div class="mr-3">
                                                    <div class="bg-success icon-circle"><i class="fas fa-donate text-white"></i></div>
                                                </div>
                                                <div><span class="small text-gray-500">December 7, 2019</span>
                                                    <p>$290.29 has been deposited into your account!</p>
                                                </div>
                                            </a>
                                            <a class="d-flex align-items-center dropdown-item" href="#">
                                                <div class="mr-3">
                                                    <div class="bg-warning icon-circle"><i class="fas fa-exclamation-triangle text-white"></i></div>
                                                </div>
                                                <div><span class="small text-gray-500">December 2, 2019</span>
                                                    <p>Spending Alert: We've noticed unusually high spending for your account.</p>
                                                </div>
                                            </a><a class="text-center dropdown-item small text-gray-500" href="#">Show All Alerts</a></div>
                                    </div>
                                </li>
                                
                                <li class="nav-item dropdown no-arrow mx-1" role="presentation">
                                    <div class="nav-item dropdown no-arrow"><a class="dropdown-toggle nav-link" data-toggle="dropdown" aria-expanded="false" href="#"></a>
                                        <div class="dropdown-menu dropdown-menu-right dropdown-list dropdown-menu-right animated--grow-in" role="menu">
                                            <h6 class="dropdown-header">alerts center</h6>
                                            <a class="d-flex align-items-center dropdown-item" href="#">
                                                <div class="dropdown-list-image mr-3">
                                                    <span class="d-none d-lg-inline mr-2 text-gray-600 small"><%= empresaLogeo.getRazonSocial()%></span>
                                                    <img class="border rounded-circle img-profile" src="<%= empresaLogeo.getLogo()%>">
                                                </div>
                                            </a>
                                            <a class="d-flex align-items-center dropdown-item" href="#">
                                                <div class="dropdown-list-image mr-3"><img class="rounded-circle" src="assets/img/avatars/avatar2.jpeg">
                                                    <div class="status-indicator"></div>
                                                </div>
                                                <div class="font-weight-bold">
                                                    <div class="text-truncate"><span>I have the photos that you ordered last month!</span></div>
                                                    <p class="small text-gray-500 mb-0">Jae Chun - 1d</p>
                                                </div>
                                            </a>
                                            <a class="d-flex align-items-center dropdown-item" href="#">
                                                <div class="dropdown-list-image mr-3"><img class="rounded-circle" src="assets/img/avatars/avatar3.jpeg">
                                                    <div class="bg-warning status-indicator"></div>
                                                </div>
                                                <div class="font-weight-bold">
                                                    <div class="text-truncate"><span>Last month's report looks great, I am very happy with the progress so far, keep up the good work!</span></div>
                                                    <p class="small text-gray-500 mb-0">Morgan Alvarez - 2d</p>
                                                </div>
                                            </a>
                                            <a class="d-flex align-items-center dropdown-item" href="#">
                                                <div class="dropdown-list-image mr-3"><img class="rounded-circle" src="assets/img/avatars/avatar5.jpeg">
                                                    <div class="bg-success status-indicator"></div>
                                                </div>
                                                <div class="font-weight-bold">
                                                    <div class="text-truncate"><span>Am I a good boy? The reason I ask is because someone told me that people say this to all dogs, even if they aren't good...</span></div>
                                                    <p class="small text-gray-500 mb-0">Chicken the Dog 2w</p>
                                                </div>
                                            </a><a class="text-center dropdown-item small text-gray-500" href="#">Show All Alerts</a></div>
                                    </div>
                                    <div class="shadow dropdown-list dropdown-menu dropdown-menu-right" aria-labelledby="alertsDropdown"></div>
                                </li>
                                
                                <div class="d-none d-sm-block topbar-divider"></div>
                                
                                <li class="nav-item dropdown no-arrow" role="presentation">
                                    <div class="nav-item dropdown no-arrow">
                                        <a class="dropdown-toggle nav-link" data-toggle="dropdown" aria-expanded="false" href="#">
                                            <span class="d-none d-lg-inline mr-2 text-gray-600 small"><%= empresaLogeo.getRazonSocial()%></span>
                                            <img class="border rounded-circle img-profile" src="<%= empresaLogeo.getLogo()%>"></a>
                                        </a>
                                        
                                        <div class="dropdown-menu shadow dropdown-menu-right animated--grow-in" role="menu">
                                            <!--validacion para que se muestre la pagina de administrador-->
                                            <%
                                                if (perfil.getNombre().equalsIgnoreCase("ADMINISTRADOR SISTEMA")) {
                                            %>
                                            <a class="dropdown-item" role="presentation" href="administracion.jsp">
                                                &nbsp;Configuraci&oacute;n
                                            </a>
                                            <%}%>
                                            <a class="dropdown-item" role="presentation" href="ControladorGeneral?accion=CerrarSesion">
                                                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>&nbsp;Logout
                                            </a>
                                        </div>
                                        
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </nav>
                    
                <div class="container-fluid">
                    <!-- <%
                        if(mensajeConfirmacion.equalsIgnoreCase("ok")){
                            mensajeConfirmacion = "";
                    %>
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                        <strong>Hecho!</strong> La compra se ha realizado con &eacute;xito.
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    
                    <%}%> -->
                    
                    <div class="d-sm-flex justify-content-between align-items-center mb-4">
                        <h3 class="text-dark mb-0">Nueva Compra</h3>
                    </div>

                    <div class="formulario">
                        <!--Input de proveedor-->
                        <label for="nombre">Proveedor:</label>
                        <input class="proveedor" type="text" name="proveedorCompra" id="proveedorCompra" form="formRealizarCompra"><br><br>
                        
                        <!--Input de libro-->
                        <label for="nombre">Libro:</label>
                        <input class="libro" type="text" name="libroExisteCompra" id="libroExisteCompra" form="formAddCarritoCompra">
                        
                        <!--Boton ventana emergente-->
                        <div class="contenedor-btn-agregar">
                            <a href="#ventana1" class="btn btn-agregar my-3 ml-3 btn-md btn-outline-info" data-toggle="modal">
                            <span data-feather="file"></span>Nuevo Libro  <i class="fas fa-plus-circle align-self-center"></i>
                            </a>
                        </div>

                        <br><br>
                         <!--Ventana emergente-->
                        <div class="modal fade" id="ventana1">
                                <div class="modal-dialog modal-dialog-centered">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h4 class="modal-title">Registro de libros</h4>
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        </div>

                                        <form action="">
                                            <div class="modal-body">
                                                <div class="form-group mb-2">
                                                    <label for="titulo_libro">Titulo de libro:</label><br>
                                                    <input type="text" class="form-control titulo" placeholder="" id="titulo_libro"> 
                                                </div><br>

                                                <div class="form-group mb-2">
                                                   <label for="autor">Autor:</label> <br>
                                                   <input class="form-control autor" type="text" placeholder="" id="autor">
                                               </div><br>

                                                <div class="form-group mb-2">
                                                    <label for="editorial">Editorial:</label><br>
                                                    <input class="form-control editorial" type="text" placeholder="" id="editorial">
                                                </div>

                                            <!--Div para agrupar los select-->
                                            <div class="d-flex justify-content-between mt-4">
                                                <div class="form-group categ">
                                                    <label>Categor&iacute;a:</label><br>
                                                    <select name="categoría"  id="categoría" class="form-control categoria">
                                                        <option value="matematica">Matem&aacute;tica</option>
                                                        <option value="comunicacion">Comunicaci&oacute;n</option>
                                                        <option value="literatura">Literatura</option>
                                                    </select>
                                                </div>    
                                                <!-- <button class="btn categ-sub">
                                                    <img src="icons/mas.png" width="28" alt="">
                                                </button> <br><br> -->
                                                <div class="form-group mb-2 subcat">
                                                    <label>Subcategor&iacute;a:</label><br>
                                                    <select name="subcategoría"  class="form-control subcategoria" id="subcategoría">
                                                        <option value="infantil">Infantil</option>
                                                        <option value="primaria">Primaria</option>
                                                        <option value="secundaria">Secundaria</option>
                                                    </select>
                                                </div>
                                            </div>

                                           <!--  <button class="btn categ-sub">
                                                <img src="icons/mas.png" width="28" alt="">
                                            </button><br><br> -->
                                            <div class="d-flex justify-content-between mt-4 mb-4">
                                                <div class="form-group">
                                                    <label class="precio_label" for="precio">Precio:</label><br>
                                                    <input class="form-control precio" type="text" placeholder="" id="precio">
                                                </div>
                                                <div class="form-group">
                                                    <label class="precio_label" for="precio">Ganancia:</label><br>
                                                    <input class="form-control precio" type="text" placeholder="" id="precio">
                                                </div>
                                            </div>
                                                <div class="d-flex justify-content-end mt-4 cont-botones">
                                                    <div class="form-group mr-4">
                                                        <button type="button" class="form-control btn btn-outline-secondary" data-dismiss="modal">Cancelar</button>
                                                    </div>
                                                    <div class="form-group">
                                                        <button type="button" class="form-control btn btn-outline-warning">Registrar</button>
                                                    </div>
                                                </div>    
                                            </div>
                                        </form>

                                    </div>
                                </div>
                            </div>

                        <div class="media">
                            <label for="nombre" class="label">Cantidad:</label>
                            <input class="cantidad" type="number" name="cantidadLibroExisteCompra" id="cantidadLibroExisteCompra" min="1" form="formAddCarritoCompra">
                        </div>

                        <div class="media">
                            <label for="nombre" class="label2">Precio:</label>
                            <input class="cantidad" type="text" name="precioLibroExisteCompra" id="precioLibroExisteCompra" placeholder="S/" form="formAddCarritoCompra">
                        </div>

                        <div class="media">
                            <label for="nombre" class="label2">Ganancia:</label>
                            <input class="cantidad" type="text" name="gananciaLibroExisteCompra" id="gananciaLibroExisteCompra" placeholder="S/" form="formAddCarritoCompra">
                        </div>
                   </div>

                    <br>

                    <form action="ControladorGeneral" method="GET" accept-charset=utf-8 name="formAddCarritoCompra" id="formAddCarritoCompra">
                        <div class="anadir">
                            <button type="submit" class="btn btn-success btn-block d-flex flex-nowrap justify-content-around" name="accion" value="AgregarLibroExisteCompra">
                                A&ntilde;adir a la compra
                                <i class="fas fa-plus-circle align-self-center"></i>
                            </button>
                        </div>
                    </form>


                    <div class="table-responsive">
                        <table class="table table-striped table-sm">
                            <thead>
                                <tr>
                                    <th>Libro</th>
                                    <th>Cantidad</th>
                                    <th>Precio de compra</th>
                                    <th>Subtotal</th>
                                    <th>Ganancia</th>
                                    <th>Editar</th>
                                    <th>Eliminar</th>
                                </tr>
                            </thead>
                            
                            <tbody>
                                <!-- <%= tablaDetallesCompra %> -->
                            </tbody>
                        </table>
                    </div>

                    <form class="compra d-flex" action="ControladorGeneral" method="GET" name="formRealizarCompra" id="formRealizarCompra">
                        <button type="submit" class="btn btn-dark btn-block d-flex flex-nowrap justify-content-around" name="accion" value="RealizarCompra">Realizar compra
                            <i class="fas fa-truck-loading align-self-center"></i>
                        </button>
                    </form>
                </div>
            </div>
            </div>
        </div>
                            
        <!--<script src="assets/js/jquery.min.js"></script>-->
        <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <!--<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>-->
        <script src="assets/js/theme.js"></script>
        <script src="js/principal.js" type="text/javascript"></script>
        <script src="js/scripts.js" type="text/javascript"></script>
        <script src="assets/js/jquery-ui.js" type="text/javascript"></script>
        <script src="js/compra.js" type="text/javascript"></script>
    </body>
</jsp>