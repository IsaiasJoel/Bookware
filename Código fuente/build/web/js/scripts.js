//////////////////////////////
//LOS CRUD 
//////////////////////////////

//////////AUTOR///////////////

        //editar
function agregaForm(campos){
    $('#idAutor').val(campos[0]);
    $('#uNombreAutor').val(campos[1]);
    $('#uApellidosAutor').val(campos[2]);
    $('#uNacionalidadAutor').val(campos[3]);
}

        //eliminar
function agregarID(_id){
    $('#dIdAutor').val(_id);
}

//////////CATEGORIA///////////////

        //editar
function cargarDatosEditarCategoria(campos){
    $('#uIdCategoria').val(campos[0]);
    $('#uNombreCategoria').val(campos[1]);
}

        //eliminar
function cargarIdEliminarCategoria(_id){
    $('#dIdCategoria').val(_id);
}

//////////SUBCATEGORIA///////////////

        //editar
function cargarDatosEditarSubcategoria(campos){
    $('#uIdSubcategoria').val(campos[0]);
    $('#uNombreSubcategoria').val(campos[1]);
    $('#uNombreCategoriaSubcategoria').val(campos[2]);
}

        //eliminar
        
function cargarIdEliminarSubcategoria(_id){
    $('#dIdSubcategoria').val(_id);
}


//////////EDITORIAL///////////////

        //editar
function cargarIdEditarEditorial(campos){
    $('#uIdEditorial').val(campos[0]);
    $('#uNombreEditorial').val(campos[1]);
}

        //eliminar
function cargarIdEliminarEditorial(_id){
    $('#dIdEditorial').val(_id);
}


//////////LIBRO///////////////

        //editar
function cargarDatosEditarLibro(campos){
    $('#uIdLibro').val(campos[0]);
    $('#uTituloLibro').val(campos[1]);
    $('#uPrecioLibro').val(campos[2]);
    $('#uAutorLibro').val(campos[3]);
    $('#uEditorialLibro').val(campos[4]);
    $('#uCategoriaLibro').val(campos[5]);
}

        //eliminar
function cargarIdEliminarLibro(_id){
    $('#dIdLibro').val(_id);
}

//////////PROVEEDOR///////////////
        //editar
function cargarDatosEditarProveedor(campos){
    $('#uIdProveedor').val(campos[0]);
    $('#uNombreProveedor').val(campos[1]);
}

        //eliminar
function cargarIdEliminarProveedor(_id) {
    $('#dIdProveedor').val(_id);
}

//////////CLIENTE///////////////
//editar
function cargarDatosEditarCliente(campos) {
    $('#uIdCliente').val(campos[0]);
    $('#uDniCliente').val(campos[1]);
    $('#uNombreCliente').val(campos[2]);
}

//eliminar
function cargarIdEliminarCliente(_id) {
    $('#dIdCliente').val(_id);
}

$(document).ready(function(){
    $("#usuarioEmpresa").focus();
});