<!DOCTYPE html>
<%@page import="logicaDeNegocio.LibroLogic"%>
<%@page import="modelo.Empresa"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Editorial"%>
<%@page import="modeloDAO.EditorialDAO"%>
<%@page import="modeloDAO.EditorialDAO"%>
<%@page import="modelo.Autor"%>
<%@page import="modeloDAO.AutorDAO"%>
<%@page import="modeloDAO.AutorDAO"%>
<%@page import="modelo.Libro"%>
<%@page import="modeloDAO.LibroDAO"%>
<%@page import="modelo.Subcategoria"%>
<%@page import="modeloDAO.SubcategoriaDAO"%>
<%@page import="modelo.Categoria"%>
<%@page import="java.util.List"%>
<%@page import="modeloDAO.CategoriaDAO"%>
<%@page session="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="es">
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
            CategoriaDAO daoCategoria = new CategoriaDAO();
            SubcategoriaDAO daoSubcategoria = new SubcategoriaDAO();
            LibroLogic logicLibro = new LibroLogic();
            AutorDAO daoAutor = new AutorDAO();
            EditorialDAO daoEditorial = new EditorialDAO();
            
            HttpSession sesion = request.getSession();
            Empresa empresaLogeo = (Empresa) sesion.getAttribute("empresa");
            if (empresaLogeo.getLogo() == null) {empresaLogeo.setLogo("");}
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
                            <img src="<%= empresaLogeo.getLogo() %>" width="150" height="150">
                        </div>
                        
                        <label for="" class="empresa"><%= empresaLogeo.getUsuario()%></label>
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
                                <span>Cerrar Sesi??n</span>
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
                                    <div class="nav-item dropdown no-arrow">
                                        <a class="dropdown-toggle nav-link" data-toggle="dropdown" aria-expanded="false" href="#">
                                            <span class="d-none d-lg-inline mr-2 text-gray-600 small"><%= empresaLogeo.getUsuario()%></span>
                                            <img class="border rounded-circle img-profile" src="<%= empresaLogeo.getLogo()%>">
                                        </a>
                                        <div class="dropdown-menu shadow dropdown-menu-right animated--grow-in" role="menu">                                       
                                            <%
                                                if (empresaLogeo.getUsuario().equalsIgnoreCase("root")) {
                                            %>
                                            <a class="dropdown-item" role="presentation" href="administracion.jsp">
                                                &nbsp;Configuraci??n
                                            </a>

                                            <%}%>
                                            
                                            <a class="dropdown-item" role="presentation" href="ControladorGeneral?accion=CerrarSesion">
                                                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>&nbsp;Cerrar Sesi??n
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
                        <div class="container-fluid">
                            <!--head-->
                            <div class="row"> 
                                <div class="col-12">
                                    <br>
                                    <!--pesta??as-->
                                    <h2>Inventario</h2>
                                </div>
                            </div>

                            <!--tabla y pesta??as-->
                            <div class="row">
                                <div class="col-12">
                                    <nav>
                                        <div class="nav nav-tabs" id="nav-tab" role="tablist">
                                            <a class="nav-link active" id="nav-libros-tab" data-toggle="tab" href="#nav-libros" role="tab" aria-controls="nav-libros" aria-selected="true">Libros</a>
                                            <a class="nav-link" id="nav-autores-tab" data-toggle="tab" href="#nav-autores" role="tab" aria-controls="nav-autores" aria-selected="false">Autores</a>
                                            <a class="nav-link" id="nav-categorias-tab" data-toggle="tab" href="#nav-categorias" role="tab" aria-controls="nav-categorias" aria-selected="false">Categor??as</a>
                                            <a class="nav-link" id="nav-subcategorias-tab" data-toggle="tab" href="#nav-subcategorias" role="tab" aria-controls="nav-subcategorias" aria-selected="false">Subcategor??as</a>
                                            <a class="nav-link" id="nav-editoriales-tab" data-toggle="tab" href="#nav-editoriales" role="tab" aria-controls="nav-editoriales" aria-selected="false">Editoriales</a>
                                        </div>
                                    </nav>

                                    <div class="tab-content" id="nav-tabContent">
                                        <!--libros-->
                                        <div class="tab-pane fade show active" id="nav-libros" role="tabpanel" aria-labelledby="nav-libros-tab">
                                            <br>
                                            <h3 class="title-inventario text-center">Libros</h3>
                                            <hr>

                                            <!--boton agregar-->
                                            <button type="button" data-toggle="modal" data-target="#modalAgregarLibro" class="btn btn-success">
                                                <span data-feather="file"></span>
                                                Agregar
                                            </button>

                                            <!--modal agregar-->
                                            <div class="modal fade" id="modalAgregarLibro">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">

                                                        <div class="modal-header">
                                                            <h5 class="modal-title title-inventario">Registro de libros</h5>
                                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                        </div>

                                                        <div class="modal-body">
                                                            <form action="ControladorGeneral" method="GET">
                                                                <!--t??tulo-->
                                                                <div class="form-group">
                                                                    <label class="title-inventario" for="tituloLibro">T??tulo:</label>
                                                                    <input type="text" class="form-control" id="tituloLibro" name="tituloLibro">
                                                                </div>

                                                                <!--stock-->
                                                                <div class="form-group">
                                                                    <label class="title-inventario" for="stockLibro">Stock: </label>
                                                                    <input type="number" min="0" class="form-control" id="stockLibro" name="stockLibro">
                                                                </div>

                                                                <!--autor-->
                                                                <div class="form-group">
                                                                    <label class="title-inventario" for="autorLibro">Autor:</label>
                                                                    <div class="d-flex">
                                                                        <select class="form-control" name="autorLibro"  id="autorLibro">
                                                                            <%= daoAutor.getViewComboBoxAutor()%>
                                                                        </select>

                                                                        <button class="btn">
                                                                            <svg width="1.2em" height="1.2em" viewBox="0 0 16 16" class="bi bi-plus-circle" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                                            <path fill-rule="evenodd" d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                                                            <path fill-rule="evenodd" d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
                                                                            </svg>
                                                                        </button>
                                                                    </div> 
                                                                </div>

                                                                <!--editorial-->
                                                                <div class="form-group">
                                                                    <label class="title-inventario" for="editorialLibro">Editorial:</label>
                                                                    <div class="d-flex">
                                                                        <select class="form-control" name="editorialLibro"  id="editorialLibro">
                                                                            <%= daoEditorial.getViewComboBoxEditorial()%>
                                                                        </select>

                                                                        <button class="btn">
                                                                            <svg width="1.2em" height="1.2em" viewBox="0 0 16 16" class="bi bi-plus-circle" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                                            <path fill-rule="evenodd" d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                                                            <path fill-rule="evenodd" d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
                                                                            </svg>
                                                                        </button>
                                                                    </div> 
                                                                </div>

                                                                <!--cmb categoria-->
                                                                <div class="form-group">
                                                                    <label for="categoriaLibro" class="title-inventario">Categor??a:</label><br>
                                                                    <div class="d-flex">
                                                                        <select class="form-control" name="categoriaLibro"  id="categoriaLibro">
                                                                            <%= daoCategoria.getViewComboBoxCategoria()%>
                                                                        </select>

                                                                        <button class="btn">
                                                                            <svg width="1.2em" height="1.2em" viewBox="0 0 16 16" class="bi bi-plus-circle" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                                            <path fill-rule="evenodd" d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                                                            <path fill-rule="evenodd" d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
                                                                            </svg>
                                                                        </button>
                                                                    </div> 
                                                                </div>

                                                                <!--precio-->
                                                                <div class="form-group">
                                                                    <label class="title-inventario" for="precioSugeridoLibro">Precio:</label>
                                                                    <input class="form-control" type="text" id="precioSugeridoLibro" name="precioSugeridoLibro">
                                                                </div>
                                                                
                                                                <!--precio-->
                                                                <div class="form-group">
                                                                    <label class="title-inventario" for="gananciaLibro">Ganancia: </label>
                                                                    <input class="form-control" type="text" id="gananciaLibro" name="gananciaLibro">
                                                                </div>

                                                                <div class="form-group">
                                                                    <button type="button" class="btn btn-light" data-dismiss="modal">Cancelar</button>
                                                                    <button type="submit" class="btn btn-outline-success" name="accion" value="RegistrarLibro">Registrar</button>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <table class="table table-striped">
                                                <thead>
                                                    <tr>
                                                        <th>Libro</th>
                                                        <th>Autor</th>
                                                        <th>Editorial</th>
                                                        <th>Categor??a</th>
                                                        <th>Subcategor??a</th>
                                                        <th>Stock</th>
                                                        <th>Precio</th>
                                                        <th>Ganancia</th>
                                                        <th>Editar</th>
                                                        <th>Eliminar</th>
                                                    </tr>
                                                </thead>

                                                <tbody>
                                                    <%
                                                        List<Libro> lstlibros = logicLibro.listar(empresaLogeo);
                                                        for (Libro oLibro : lstlibros) {
                                                    %>

                                                    <tr>
                                                        <td><%= oLibro.getTitulo()%></td>
                                                        <td><%=oLibro.getAutor().toString()%></td>
                                                        <td><%=oLibro.getEditorial().getNombre()%></td>
                                                        <td><%=oLibro.getCategoria().getNombreCategoria()%></td>
                                                        <td><%= logicLibro.imprimirSubcategorias(oLibro.getCategoria().getId_categoria()) %></td>
                                                        <td><%=oLibro.getStock()%></td>
                                                        <td>S/ <%=oLibro.getPrecioSugerido()%></td>
                                                        <td>S/ <%=oLibro.getGanancia() %></td>

                                                        <td>
                                                            <!--btn editar-->
                                                            <!--NOTA: no modifico el stock pq eso le corresponde a compra-venta-->
                                                            <button class="btn" type="button" data-target="#modalEditarLibro" data-toggle="modal" onclick="cargarDatosEditarLibro(['<%= oLibro.getId_libro()%>','<%= oLibro.getTitulo()%>','<%= oLibro.getPrecioSugerido()%>','<%= oLibro.getAutor().toString()%>','<%= oLibro.getEditorial().getNombre()%>','<%= oLibro.getCategoria().getNombreCategoria()%>'])">
                                                                <svg width="1.2em" height="1.2em" viewBox="0 0 16 16" class="bi bi-pencil-square" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                                <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456l-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                                                                <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                                                                </svg>
                                                            </button>

                                                            <!--Modal editar-->
                                                            <div class="modal fade" id="modalEditarLibro" tabindex="-1" role="dialog" aria-labelledby="modalEditarLibro" aria-hidden="true">
                                                                <div class="modal-dialog">
                                                                    <div class="modal-content">
                                                                        <div class="modal-header">
                                                                            <h5 class="heading">Editar Libro</h5>
                                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                                <span aria-hidden="true" class="white-text">??</span>
                                                                            </button>
                                                                        </div>

                                                                        <div class="modal-body">
                                                                            <form action="ControladorGeneral" method="GET">
                                                                                <!--t??tulo-->
                                                                                <div class="form-group">
                                                                                    <!--input que tiene el ID-->
                                                                                    <input type="number" name="uIdLibro" id="uIdLibro" hidden="">

                                                                                    <label for="uTituloLibro">Titulo de libro:</label>
                                                                                    <input type="text" class="form-control" name="uTituloLibro" id="uTituloLibro">
                                                                                </div>

                                                                                <!--autor-->
                                                                                <div class="form-group">
                                                                                    <label for="uAutorLibro">Autor:</label>
                                                                                    <select name="uAutorLibro"  id="uAutorLibro" class="form-control">
                                                                                        <%=daoAutor.getViewComboBoxAutor()%>
                                                                                    </select>
                                                                                </div>

                                                                                <!--editorial-->
                                                                                <div class="form-group">
                                                                                    <label for="uEditorialLibro">Editorial:</label>
                                                                                    <select name="uEditorialLibro"  id="uEditorialLibro" class="form-control">
                                                                                        <%=daoEditorial.getViewComboBoxEditorial()%>
                                                                                    </select>
                                                                                </div>

                                                                                <!--cmb categoria-->
                                                                                <div class="form-group">
                                                                                    <label for="uCategoriaLibro">Categor??a:</label>
                                                                                    <select name="uCategoriaLibro"  id="uCategoriaLibro" class="form-control">
                                                                                        <%=daoCategoria.getViewComboBoxCategoria()%>
                                                                                    </select>
                                                                                </div>

                                                                                <!--NOTA: Por ahora libro NO TIENE una lista de subcategorias-->

                                                                                <!--precio-->
                                                                                <div class="form-group">
                                                                                    <label for="uPrecioLibro">Precio:</label>
                                                                                    <input type="text" id="uPrecioLibro" name="uPrecioLibro" class="form-control">
                                                                                </div>

                                                                                <div class="form-group">
                                                                                    <button type="button" class="btn btn-light" data-dismiss="modal">Cancelar</button>
                                                                                    <button type="submit" class="btn btn-outline-success" name="accion" value="EditarLibro">Editar Libro</button>
                                                                                </div>
                                                                            </form>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </td>

                                                        <td>
                                                            <!--btn eliminar-->
                                                            <button class="btn" type="button" data-toggle="modal" data-target="#modalEliminarLibro" onclick="cargarIdEliminarLibro(<%= oLibro.getId_libro()%>)">
                                                                <svg width="1.2em" height="1.2em" viewBox="0 0 16 16" class="bi bi-trash" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                                <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                                                                <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11zd"/>
                                                                </svg>
                                                            </button>

                                                            <!--modal-eliminar-->
                                                            <div class="modal fade" id="modalEliminarLibro" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                                <div class="modal-dialog">
                                                                    <div class="modal-content">
                                                                        <div class="modal-header">
                                                                            <h5 class="heading title-inventario">??Est??s seguro?</h5>
                                                                        </div>

                                                                        <div class="modal-body">
                                                                            <form action="ControladorGeneral" method="GET">
                                                                                <div class="form-group">
                                                                                    <input type="number" id="dIdLibro" name="dIdLibro" hidden="">

                                                                                    <button type="button" class="btn btn-light" data-dismiss="modal">Cancelar</button>
                                                                                    <button type="submit" class="btn  btn-outline-danger" name="accion" value="EliminarLibro">S??, eliminar</button>
                                                                                </div>
                                                                            </form>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div> 
                                                        </td>
                                                    </tr>
                                                    <%}%>
                                                </tbody>
                                            </table>
                                        </div>

                                        <!--autores-->
                                        <div class="tab-pane fade" name="nav-autores" id="nav-autores" role="tabpanel" aria-labelledby="nav-autores-tab">
                                            <br>
                                            <h3 class="title-inventario text-center">Autores</h3>
                                            <hr>

                                            <!-- Button trigger modal -->
                                            <button type="button" class="btn btn-success" data-toggle="modal" data-target="#modalAgregarAutor">
                                                Agregar
                                            </button>

                                            <!-- Modal -->
                                            <div class="modal fade" id="modalAgregarAutor" tabindex="-1" aria-labelledby="modalAgregarAutor" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title title-inventario">Registro de autores</h5>
                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close" id="btnCloseModalAgregarAutor">
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <form action="ControladorGeneral" method="GET">
                                                                <div class="form-group">
                                                                    <label for="nombres" class="title-inventario">Nombres:</label>
                                                                    <input type="text" class="form-control" name="nombreAutor" id="nombres" aria-describedby="emailHelp">
                                                                </div>

                                                                <div class="form-group">
                                                                    <label for="apellidos" class="title-inventario">Apellidos:</label>
                                                                    <input type="text" class="form-control" name="apellidosAutor" id="apellidos">
                                                                </div>

                                                                <div class="form-group">
                                                                    <label for="nacionalidad" class="title-inventario">Nacionalidad:</label>
                                                                    <input type="text" class="form-control" name="nacionalidadAutor" id="nacionalidad">
                                                                </div>

                                                                <button type="submit" class="btn btn-outline-success" name="accion" id="btnRegistrarAutor" value="RegistrarAutor">Registrar</button>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <!--tabla-->
                                            <div class="table-responsive">
                                                <table class="table table-striped">
                                                    <thead>
                                                        <tr>
                                                            <th>Autor</th>
                                                            <th>Nacionalidad</th>
                                                            <th>Editar</th>
                                                            <th>Eliminar</th>
                                                        </tr>
                                                    </thead>

                                                    <tbody>
                                                        <%
                                                            List<Autor> lstAutores = daoAutor.listar();
                                                            //for (Autor oAutor : lstAutores) {
                                                            for (Autor oAutor : lstAutores) {
                                                        %>

                                                        <%--<%= daoAutor.getViewAutor()%>--%>
                                                        <tr id="tabla_vertical">
                                                            <td><%= oAutor.getNombres() + " " + oAutor.getApellidos()%></td>
                                                            <td><%= oAutor.getNacionalidad()%></td>

                                                            <!--btn-editar-->
                                                            <td>
                                                                <button type="button" class="btn" data-target="#modalEditarAutor" data-toggle="modal" id="btnEditarAutor" onClick="agregaForm(['<%= oAutor.getId_autor()%>','<%= oAutor.getNombres()%>','<%= oAutor.getApellidos()%>','<%= oAutor.getNacionalidad()%>'])">
                                                                    <svg width="1.2em" height="1.2em" viewBox="0 0 16 16" class="bi bi-pencil-square" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                                    <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456l-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                                                                    <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                                                                    </svg>
                                                                </button>

                                                                <!--Modal editar-->
                                                                <div class="modal fade right" id="modalEditarAutor" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" data-backdrop="false">
                                                                    <div class="modal-dialog modal-full-height modal-right modal-notify modal-info" role="document">
                                                                        <div class="modal-content">
                                                                            <!--Header-->
                                                                            <div class="modal-header">
                                                                                <p class="heading lead">Editar autor</p>

                                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close" id="btnCloseModalModificarAutor">
                                                                                    <span aria-hidden="true" class="white-text">??</span>
                                                                                </button>
                                                                            </div>

                                                                            <!--Body-->
                                                                            <div class="modal-body">
                                                                                <form action="ControladorGeneral" method="get">
                                                                                    <!--este input contendr?? el id del autor a eliminar-->
                                                                                    <input id="idAutor" name="idAutor" hidden="" type="number">

                                                                                    <div class="form-group">
                                                                                        <span>Nuevo nombre: </span>
                                                                                        <input id="uNombreAutor" class="form-control" type="text" name="uNombreAutor">
                                                                                    </div>

                                                                                    <div class="form-group">
                                                                                        <span>Apellidos: </span>
                                                                                        <input id="uApellidosAutor" class="form-control" type="text" name="uApellidosAutor">
                                                                                    </div>

                                                                                    <div class="form-goup">
                                                                                        <span>Nacionalidad: </span>
                                                                                        <input id ="uNacionalidadAutor" class="form-control" type="text" name="uNacionalidadAutor">
                                                                                    </div>

                                                                                    <br>
                                                                                    <button type="button" class="btn btn-light" data-dismiss="modal">Cancelar</button>
                                                                                    <button type="submit" class="btn btn-outline-success waves-effect" name="accion" value="EditarAutor" id="btnActualizarAutor">
                                                                                        Actualizar
                                                                                    </button>
                                                                                </form>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </td>

                                                            <!--btn eliminar-->
                                                            <td>
                                                                <button class="btn" type="button" data-target="#modalEliminarAutor" data-toggle="modal" id="btnEliminarAutor" onClick="agregarID(<%= oAutor.getId_autor()%>)">
                                                                    <svg width="1.2em" height="1.2em" viewBox="0 0 16 16" class="bi bi-trash" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                                    <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                                                                    <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                                                                    </svg>
                                                                </button>

                                                                <!--<a href="ControladorGeneral?accion=EliminarAutor&idEliminarAutor=ac?? va el ID">S??, eliminar</a>-->

                                                                <!--modal-eliminar-->
                                                                <div class="modal fade" id="modalEliminarAutor" tabindex="-1" aria-labelledby="modalEliminarAutor" aria-hidden="true">
                                                                    <div class="modal-dialog">
                                                                        <div class="modal-content">
                                                                            <div class="modal-header">
                                                                                <h5 class="text-center">??Est??s seguro que deseas eliminar?</h5>
                                                                            </div>

                                                                            <div class="modal-body">
                                                                                <form action="ControladorGeneral" method="GET">
                                                                                    <div class="form-group">
                                                                                        <input type="number" name="dIdAutor" hidden="" id="dIdAutor">
                                                                                    </div>

                                                                                    <button type="button" class="btn btn-light">Cancelar</button>
                                                                                    <button type="submit" class="btn btn-outline-danger" name="accion" value="EliminarAutor">Eliminar</button>
                                                                                </form>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <%}%>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>

                                        <!--categorias-->
                                        <div class="tab-pane fade" id="nav-categorias" role="tabpanel" aria-labelledby="nav-categorias-tab">
                                            <br>
                                            <h3 class="title-inventario text-center">Categor??as</h3>
                                            <hr>

                                            <button type="button" class="btn btn-success" data-toggle="modal" data-target="#modalAgregarCategoria">
                                                Agregar
                                            </button>

                                            <!--modal-agregar-categoria-->
                                            <div class="modal fade" id="modalAgregarCategoria" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                <div class="modal-dialog modal-dialog-centered">
                                                    <div class="modal-content">

                                                        <div class="modal-header">
                                                            <h4 class="modal-title title-inventario text-center">Registro de categor??a</h4>
                                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                        </div>

                                                        <div class="modal-body">
                                                            <form action="ControladorGeneral" method="GET">
                                                                <div class="form-group">
                                                                    <label for="nombreCategoria" class="title-inventario">Categor??a:</label><br>
                                                                    <input type="text" placeholder="" id="nombreCategoria" name="nombreCategoria">
                                                                </div>

                                                                <div class="form-group">
                                                                    <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">Cancelar</button>
                                                                    <button type="submit" class="btn btn-outline-success" name="accion" value="RegistrarCategoria" id="btnRegistrarCategoria">Registrar</button>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <!--tabla categorias-->
                                            <div class="table-responsive">
                                                <table class="table table-striped">
                                                    <thead>
                                                        <tr>
                                                            <th scope="col">Categor??a</th>
                                                            <th scope="col">Editar</th>
                                                            <th scope="col">Eliminar</th>
                                                        </tr>
                                                    </thead>

                                                    <tbody>
                                                        <%
                                                            List<Categoria> lstCategoria = daoCategoria.listar();
                                                            for (Categoria oCategoria : lstCategoria) {
                                                        %>
                                                        <tr>
                                                            <td><%= oCategoria.getNombreCategoria()%></td>

                                                            <!--btn_editar-->
                                                            <td>
                                                                <button type="button" class="btn" data-target="#modalEditarCategoria" data-toggle="modal" onclick="cargarDatosEditarCategoria(['<%= oCategoria.getId_categoria()%>','<%= oCategoria.getNombreCategoria()%>'])">
                                                                    <svg width="1.2em" height="1.2em" viewBox="0 0 16 16" class="bi bi-pencil-square" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                                    <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456l-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                                                                    <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                                                                    </svg>
                                                                </button>

                                                                <!--Modal editar-->
                                                                <div class="modal fade right" id="modalEditarCategoria" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" data-backdrop="false">
                                                                    <div class="modal-dialog modal-full-height modal-right modal-notify modal-info" role="document">
                                                                        <div class="modal-content">
                                                                            <div class="modal-header">
                                                                                <p class="heading lead">Editar categor??a</p>
                                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                                    <span aria-hidden="true" class="white-text">??</span>
                                                                                </button>
                                                                            </div>

                                                                            <div class="modal-body">
                                                                                <form action="ControladorGeneral" method="get">
                                                                                    <div class="form-group">
                                                                                        <span class="title-inventario">Nuevo nombre de categoria</span>
                                                                                        <input id="uIdCategoria" name="uIdCategoria" hidden="">
                                                                                        <input id="uNombreCategoria" name="uNombreCategoria" class="form-control" type="text" value="">
                                                                                    </div>

                                                                                    <div class="form-group">
                                                                                        <button type="button" class="btn btn-outline-danger" data-dismiss="modal">Cancelar</button>
                                                                                        <button type="submit" class="btn btn-outline-success" name="accion" value="EditarCategoria"> Actualizar</button>
                                                                                    </div>
                                                                                </form>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </td>

                                                            <!--btn_eliminar-->
                                                            <td>
                                                                <button class="btn" type="button" data-toggle="modal" data-target="#modalEliminarCategoria" onclick="cargarIdEliminarCategoria(<%= oCategoria.getId_categoria()%>)">
                                                                    <svg width="1.2em" height="1.2em" viewBox="0 0 16 16" class="bi bi-trash" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                                    <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                                                                    <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                                                                    </svg>
                                                                </button>

                                                                <!--modal-eliminar-->
                                                                <div class="modal fade" id="modalEliminarCategoria" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                                    <div class="modal-dialog modal-sm modal-notify modal-danger" role="document">
                                                                        <div class="modal-content text-center">
                                                                            <form action="ControladorGeneral" method="GET">
                                                                                <div class="modal-header d-flex justify-content-center">
                                                                                    <p class="heading">Est??s seguro?</p>
                                                                                </div>

                                                                                <div class="modal-body">
                                                                                    <input type="number" name="dIdCategoria" id="dIdCategoria" hidden="">

                                                                                    <button type="button" class="btn btn-light" data-dismiss="modal">No, cancelar</button>
                                                                                    <button type="submit" class="btn btn-outline-danger" name="accion" value="EliminarCategoria">S??, eliminar</button>
                                                                                </div>
                                                                            </form>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <%}%>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>

                                        <!--editoriales-->
                                        <div class="tab-pane fade" id="nav-editoriales" role="tabpanel" aria-labelledby="nav-editoriales-tab">
                                            <br>
                                            <h3 class="title-inventario text-center">Editoriales</h3>
                                            <hr>

                                            <button type="button" class="btn btn-success" data-target="#modalAgregarEditorial" data-toggle="modal">
                                                Agregar
                                            </button>

                                            <!--modal-agregar-editorial-->
                                            <div class="modal fade" id="modalAgregarEditorial">
                                                <div class="modal-dialog modal-dialog-centered">
                                                    <div class="modal-content">

                                                        <div class="modal-header">
                                                            <h4 class="modal-title title-inventario">Registro de editorial</h4>
                                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                        </div>

                                                        <div class="modal-body">
                                                            <form action="ControladorGeneral" method="GET">
                                                                <div class="form-group">
                                                                    <label class="title-inventario">Editorial:</label>
                                                                    <input type="text" class="form-control" id="nombreEditorial" name="nombreEditorial">
                                                                </div>

                                                                <div class="form-group">
                                                                    <button type="button" class="btn btn-light" data-dismiss="modal">Cancelar</button>
                                                                    <button type="submit" class="btn btn-outline-success" name="accion" value="RegistrarEditorial" id="btnRegistrarEditorial">Registrar</button>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <!--tabla categorias-->
                                            <table class="table table-striped">
                                                <thead>
                                                    <tr>
                                                        <th>Editorial</th>
                                                        <th>Editar</th>
                                                        <th>Eliminar</th>
                                                    </tr>
                                                </thead>

                                                <tbody>
                                                    <%
                                                        List<Editorial> lstEditorial = daoEditorial.listar();
                                                        for (Editorial oEditorial : lstEditorial) {
                                                    %>
                                                    <tr>
                                                        <td><%= oEditorial.getNombre()%></td>

                                                        <td>
                                                            <!--btn_editar-->
                                                            <button type="button" class="btn" data-target="#modalEditarEditorial" data-toggle="modal" onclick="cargarIdEditarEditorial(['<%= oEditorial.getId_editorial()%>','<%= oEditorial.getNombre()%>'])">                                                      
                                                                <svg width="1.2em" height="1.2em" viewBox="0 0 16 16" class="bi bi-pencil-square" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                                <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456l-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                                                                <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                                                                </svg>
                                                            </button>

                                                            <!--Modal editar-->
                                                            <div class="modal fade" id="modalEditarEditorial" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                                <div class="modal-dialog modal-full-height modal-right modal-notify modal-info" role="document">
                                                                    <div class="modal-content">

                                                                        <div class="modal-header">
                                                                            <h5 class="text-center">Editar editorial</h5>
                                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                                <span aria-hidden="true" class="white-text">??</span>
                                                                            </button>
                                                                        </div>

                                                                        <div class="modal-body">
                                                                            <form action="ControladorGeneral" method="GET">
                                                                                <div class="form-group">
                                                                                    <input type="number" name="uIdEditorial" id="uIdEditorial" hidden="">

                                                                                    <span>Nombre de editorial</span>
                                                                                    <input id="uNombreEditorial" class="form-control" type="text" name="uNombreEditorial">
                                                                                </div>

                                                                                <div class="form-group">
                                                                                    <button type="button" class="btn btn-light" data-dismiss="modal">Cancelar</button>
                                                                                    <button type="submit" class="btn btn-outline-success" name="accion" value="EditarEditorial">Actualizar</button>
                                                                                </div>
                                                                            </form>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </td>

                                                        <td>
                                                            <!--btn_eliminar-->
                                                            <button class="btn" type="button" data-toggle="modal" data-target="#modalEliminarEditorial" onclick="cargarIdEliminarEditorial(<%= oEditorial.getId_editorial()%>)">
                                                                <svg width="1.2em" height="1.2em" viewBox="0 0 16 16" class="bi bi-trash" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                                <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                                                                <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                                                                </svg>
                                                            </button>

                                                            <!--modal-eliminar-->
                                                            <div class="modal fade" id="modalEliminarEditorial" tabindex="-1" role="dialog" aria-labelledby="modalEliminarEditorial" aria-hidden="true">
                                                                <div class="modal-dialog">
                                                                    <div class="modal-content">
                                                                        <div class="modal-header d-flex justify-content-center">
                                                                            <p class="heading">Est??s seguro?</p>
                                                                        </div>

                                                                        <div class="modal-body">
                                                                            <form action="ControladorGeneral" method="GET">
                                                                                <div class="form-group">
                                                                                    <input type="number" name="dIdEditorial" id="dIdEditorial" hidden="">

                                                                                    <button type="button" class="btn  btn-light" data-dismiss="modal">No, cancelar</button>
                                                                                    <button type="submit" class="btn btn-outline-danger" name="accion" value="EliminarEditorial">S??, eliminar</button>
                                                                                </div>
                                                                            </form>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <%}%>
                                                </tbody>
                                            </table>
                                        </div>

                                        <!--subcategorias-->
                                        <div class="tab-pane fade" id="nav-subcategorias" role="tabpanel" aria-labelledby="nav-subcategorias-tab">
                                            <br>
                                            <h3 class="title-inventario text-center">Subcategor??as</h3>
                                            <hr>

                                            <button type="button" data-target="#modalAgregarSubcategoria" class="btn btn-success" data-toggle="modal">
                                                <span data-feather="file"></span>
                                                Agregar
                                            </button>

                                            <!--modal-agregar-subcategoria-->
                                            <div class="modal fade" id="modalAgregarSubcategoria">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">

                                                        <div class="modal-header">
                                                            <h5 class="modal-title title-inventario">Registro de subcategor??a</h5>
                                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                        </div>

                                                        <div class="modal-body">
                                                            <form action="ControladorGeneral" method="GET">
                                                                <div class="form-group">
                                                                    <label class="title-inventario" for="nombreSubcategoria">Subcategor??a:</label><br>
                                                                    <input type="text" class="form-control" id="nombreSubcategoria" name="nombreSubcategoria">
                                                                </div>

                                                                <div class="form-group">
                                                                    <label class="title-inventario" for="nombreCategoria">Categor??a: </label>
                                                                    <select class="form-control" name="nombreCategoria" id="nombreCategoria">
                                                                        <%= daoCategoria.getViewComboBoxCategoria()%>
                                                                    </select>
                                                                </div>

                                                                <div class="form-group">
                                                                    <button type="button" class="btn btn-light" data-dismiss="modal">Cancelar</button>
                                                                    <button type="submit" class="btn btn-outline-success" name="accion" value="RegistrarSubcategoria" id="btnRegistrarSubcategoria">Registrar</button>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="table-responsive">
                                                <table class="table table-striped">
                                                    <thead>
                                                        <tr>
                                                            <th>Subcategor??a</th>
                                                            <th>Categor??a</th>
                                                            <th>Editar</th>
                                                            <th>Eliminar</th>
                                                        </tr>
                                                    </thead>

                                                    <tbody>
                                                        <%--<%= daoSubcategoria.getViewRowSubcategoria()%>--%>
                                                        <%
                                                            List<Subcategoria> lstSubcategoria = daoSubcategoria.listar();
                                                            for (Subcategoria oSubcategoria : lstSubcategoria) {
                                                        %>
                                                        <tr>
                                                            <td><%= oSubcategoria.getNombre()%></td>
                                                            <td><%= oSubcategoria.getCategoria().getNombreCategoria()%></td>

                                                            <!--btn_editar-->
                                                            <td>
                                                                <button type="button" class="btn" data-target="#modalEditarSubcategoria" data-toggle="modal" onclick="cargarDatosEditarSubcategoria(['<%= oSubcategoria.getId_subcategoria()%>','<%= oSubcategoria.getNombre()%>','<%= oSubcategoria.getCategoria().getNombreCategoria()%>'])">
                                                                    <svg width="1.2em" height="1.2em" viewBox="0 0 16 16" class="bi bi-pencil-square" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                                    <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456l-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                                                                    <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                                                                    </svg>
                                                                </button>

                                                                <!--Modal editar-->
                                                                <div class="modal fade" id="modalEditarSubcategoria" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                                    <div class="modal-dialog">
                                                                        <div class="modal-content">
                                                                            <div class="modal-header">
                                                                                <h5 class="heading">Editar subcategor??a</h5>
                                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                                    <span aria-hidden="true" class="white-text">??</span>
                                                                                </button>
                                                                            </div>

                                                                            <div class="modal-body">
                                                                                <form action="ControladorGeneral" method="GET">
                                                                                    <div class="form-group">
                                                                                        <input type="number" hidden="" name="uIdSubcategoria" id="uIdSubcategoria">
                                                                                        <label for="uNombreSubcategoria">Subcategor??a</label>
                                                                                        <input id="uNombreSubcategoria" class="form-control" type="text" name="uNombreSubcategoria">
                                                                                    </div>

                                                                                    <div class="form-group">
                                                                                        <label for="uNombreCategoria">Categor??a: </label>
                                                                                        <select name="uNombreCategoriaSubcategoria" id="uNombreCategoriaSubcategoria" class="form-control">
                                                                                            <%= daoCategoria.getViewComboBoxCategoria()%>
                                                                                        </select>
                                                                                    </div>

                                                                                    <div class="form-group">
                                                                                        <button type="button" class="btn btn-light" data-dismiss="modal">Cancelar</button>
                                                                                        <button type="submit" class="btn btn-outline-success" name="accion" value="EditarSubcategoria">Actualizar</button>
                                                                                    </div>
                                                                                </form>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </td>

                                                            <!--btn_eliminar-->
                                                            <td>
                                                                <button class="btn" type="button" data-toggle="modal" data-target="#modalEliminarSubcategoria" onclick="cargarIdEliminarSubcategoria(<%= oSubcategoria.getId_subcategoria()%>)">
                                                                    <svg width="1.2em" height="1.2em" viewBox="0 0 16 16" class="bi bi-trash" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                                    <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                                                                    <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                                                                    </svg>
                                                                </button>

                                                                <!--modal-eliminar-->
                                                                <div class="modal fade" id="modalEliminarSubcategoria" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                                    <div class="modal-dialog">
                                                                        <div class="modal-content text-center">
                                                                            <div class="modal-header">
                                                                                <h5 class="heading">??Est??s seguro?</h5>
                                                                            </div>

                                                                            <div class="modal-body">
                                                                                <form action="ControladorGeneral" method="GET">
                                                                                    <div class="form-group">
                                                                                        <input type="number" id="dIdSubcategoria" name="dIdSubcategoria" hidden="">
                                                                                        <button type="button" class="btn  btn-light" data-dismiss="modal">Cancelar</button>
                                                                                        <button type="submit" class="btn btn-outline-danger" name="accion" value="EliminarSubcategoria">S??, eliminar</button>
                                                                                    </div>
                                                                                </form>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <%}%>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--<script src="assets/js/jquery.min.js"></script>-->
        <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
        <script src="assets/js/theme.js"></script>
        <script src="js/principal.js" type="text/javascript"></script>
        <script src="js/scripts.js" type="text/javascript"></script>
    </body>

</html>