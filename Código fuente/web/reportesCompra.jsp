<%@page import="modelo.Perfil"%>
<!-- <%@page import="controlador.ControladorGeneral"%> -->
<!DOCTYPE jsp>
<!-- <%@page import="modelo.Empresa"%>
<%@page import="java.util.List"%>
<%@page session="true"%> -->
<jsp lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <title>BookWare</title>
        <link rel="icon" type="image/png" href="images/favicon.ico">
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
        <link rel="stylesheet" href="assets/fonts/fontawesome-all.min.css">
        <link rel="stylesheet" href="assets/css/index.css">
        <link href="assets/css/compra.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/tablas.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/ventas.css" rel="stylesheet" type="text/css"/>
        
    </head>

    <body id="page-top">
        <%
            HttpSession sesion = request.getSession();
            Empresa empresaLogeo = (Empresa) sesion.getAttribute("empresa");
            Perfil perfil = (Perfil) sesion.getAttribute("perfil");
            
            ControladorGeneral controladorGeneral = new ControladorGeneral();
            
            //ClienteDAO daoCliente = new ClienteDAO();
            if (empresaLogeo.getLogo() == null) {
                empresaLogeo.setLogo("");
            }
        %>
        <div id="wrapper">
            <nav class="navbar sidebar-dark align-items-start sidebar  accordion p-0 nav_vertical">
                <div class="container-fluid d-flex flex-column p-0">
                    <a class="navbar-brand d-flex justify-content-center align-items-center sidebar-brand m-0" href="index.jsp">
                        <div class="sidebar-brand-icon rotate-n-15">
                            <i class="fas fa-book-open"></i>
                        </div>
                        
                        <div class="sidebar-brand-text mx-3">
                            <span class="bookware">BOOKWARE</span>
                        </div>
                    </a>
                    
                    <hr class="sidebar-divider my-0">
                    
                    <div class="d-flex justify-content-center flex-column cont-logo">
                        <div class="d-flex align-items-center logo">
                            <img src="" width="150" height="150">
                        </div>
                        
                        <label for="" class="empresa"> <%= empresaLogeo.getRazonSocial()%> </label>
                        <label for="" class="ruc"><%= empresaLogeo.getRUC()%></label>
                    </div>

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
                        <li class="nav-item" role="presentation"><a class="nav-link" href="ControladorGeneral?accion=CerrarSesion" style="margin-bottom:12px;">
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
                            <ul class="nav navbar-nav flex-nowrap ml-auto">
                                
                                <li class="nav-item dropdown no-arrow mx-1" role="presentation">
                                    <div class="nav-item dropdown no-arrow">
                                        <div class="dropdown-menu dropdown-menu-right dropdown-list dropdown-menu-right animated--grow-in" role="menu">
                                        </div>
                                    </div>
                                </li>
                                
                                <li class="nav-item dropdown no-arrow mx-1" role="presentation">
                                    <div class="nav-item dropdown no-arrow">
                                        <a class="dropdown-toggle nav-link" data-toggle="dropdown" aria-expanded="false" href="#"></a>
                                    </div>
                                </li>
                                
                                <div class="d-none d-sm-block topbar-divider"></div>
                                
                                <li class="nav-item dropdown no-arrow" role="presentation">
                                    <div class="nav-item dropdown no-arrow"><a class="dropdown-toggle nav-link" data-toggle="dropdown" aria-expanded="false" href="#">
                                            <span class="d-none d-lg-inline mr-2 text-gray-600 small"><%= empresaLogeo.getRazonSocial()%></span>
                                            <img class="border rounded-circle img-profile" src="<%= empresaLogeo.getLogo()%>">
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
                                                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>&nbsp;Cerrar Sesi&oacute;n
                                            </a>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </nav>
                    
                    <!--contenido principal-->
                    <div class="container-fluid">
                        <!--contenido contenido-->
                        <h3 class="text-dark mb-4">Reporte de Compras</h3>
                        <div class="card shadow">
                            <div class="card-body">
                                <div class="row d-flex justify-content-between">
                                    <!-- <div class="col-md-6 text-nowrap">
                                        <div id="dataTable_length" class="dataTables_length" aria-controls="dataTable"><label>Show&nbsp;<select class="form-control form-control-sm custom-select custom-select-sm"><option value="10" selected="">10</option><option value="25">25</option><option value="50">50</option><option value="100">100</option></select>&nbsp;</label></div>
                                    </div> -->
                                    <div class="text-nowrap mb-3 d-flex justify-content-between">
                                        <div id="dataTable_length" class="dataTables_length mr-4" aria-controls="dataTable">
                                            <label for="start" class="label_fecha_inicio">Desde:
                                            </label>
                                            
                                            <input type="date" id="" name="trip-start" class="fecha_inicio" value="dd/mm/aaaa">
                                        </div>

                                        <div id="dataTable_length" class="dataTables_length mr-2" aria-controls="dataTable">
                                            <label for="start" class="label_fecha_inicio">Hasta:
                                            </label>
                                            
                                            <input type="date" id="" name="trip-start" class="fecha_inicio" value="dd/mm/aaaa">
                                        </div>

                                        <div id="dataTable_length" class="dataTables_length" aria-controls="dataTable">
                                            <button type="button" class="btn btn-outline-dark" title="Filtrar">
                                                <i class="fas fa-filter"></i>
                                            </button>
                                        </div>
                                    </div>
                                    
                                    <div class="text-nowrap d-flex justify-content-between">
                                        <div class="text-md-right dataTables_filter" id="dataTable_filter">
                                            <button type="button" class="btn  btn-success" title="Imprimir">
                                                <i class="fas fa-print"></i>
                                            </button>
                                        </div>
                                        
                                        <div class="text-md-right dataTables_filter ml-3 mr-3" id="dataTable_filter">
                                            <button type="button" class="btn  btn-danger" title="Exportar PDF" id="btnImprimirReporteCompra" name="btnImprimirReporteCompra">
                                                <i class="fas fa-file-pdf"></i>
                                            </button>
                                        </div>
                                    </div> 
                                </div>
                                
                                <div class="table-responsive table mt-2" id="dataTable" role="grid" aria-describedby="dataTable_info">
                                    <table class="table dataTable my-0" id="dataTable">
                                        <thead>
                                            <tr>
                                                <th>Codigo</th>
                                                <th>Fecha</th>
                                                <th>Proveedor</th>
                                                <th>Cantidad</th>
                                                <th>Libro</th>
                                                <th>Precio de compra</th>
                                                <th>Total</th>
                                            </tr>
                                        </thead>
                                        
                                        <tbody>
                                            <!-- <%= controladorGeneral.imprimirDetalleCompra(empresaLogeo) %> -->
                                        </tbody>
                                    </table>
                                </div>
                                
                                <div class="row">
                                    <div class="col-md-6 align-self-center">
                                        <p id="dataTable_info" class="dataTables_info" role="status" aria-live="polite">P&aacute;gina 1 de 27</p>
                                    </div>
                                    <div class="col-md-6">
                                        <nav class="d-lg-flex justify-content-lg-end dataTables_paginate paging_simple_numbers">
                                            <ul class="pagination">
                                                <li class="page-item disabled"><a class="page-link" href="#" aria-label="Previous"><span aria-hidden="true">◄</span></a></li>
                                                <li class="page-item active"><a class="page-link" href="#">1</a></li>
                                                <li class="page-item"><a class="page-link" href="#">2</a></li>
                                                <li class="page-item"><a class="page-link" href="#">3</a></li>
                                                <li class="page-item"><a class="page-link" href="#" aria-label="Next"><span aria-hidden="true">►</span></a></li>
                                            </ul>
                                        </nav>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
        <script src="assets/js/theme.js"></script>
        <script src="js/principal.js" type="text/javascript"></script>
        <script src="js/scripts.js" type="text/javascript"></script>
    </body>

</jsp>