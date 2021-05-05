//function agregarDatosAutor(){
//    
////    también se puede mandar como una cadena
////    var datos = "nombre=" + nombre + "&apellidos="+apellidos+"&nacionalidad="+nacionalidad;
//    
//    var datos = {
//        accion: 'Registrar',
//        nombres: $('#nombres').val(),
//        apellidos: $('#apellidos').val(),
//        nacionalidad: $('#nacionalidad').val()
//    };
//    
//    $.ajax({
//        type:'post',
//        url: "Controlador_autor",
//        data: datos,
//        success:function(request){
//            if (request === "OK") {
////                $('#nombres').val('');
////                $('#apellidos').val('');
////                $('#nacionalidad').val('');
//                
//                $('#btnCloseModalAgregarAutor').click();
//                $('body').removeClass('modal-open');
//                $('.modal-backdrop').remove();
//                $('#Contenido').load('inventario.jsp#nav-autores-tab');
//                alertify.success('Agregado con éxito');
//                return false;
//            }else{
//                alertify.error('Falló');
//            }
//        }
//    });
//    return false;
//}

//function agregaForm(campos){
//    $('#id_autor').val(campos[0]);
//    $('#Unombre').val(campos[1]);
//    $('#Uapellidos').val(campos[2]);
//    $('#Unacionalidad').val(campos[3]);
//}

//function actualizaDatos(){
//    
//    var datos = {
//        accion: 'editar',
//        id_autor: $('#id_autor').val(),
//        nombres: $('#Unombre').val(),
//        apellidos: $('#Uapellidos').val(),
//        nacionalidad: $('#Unacionalidad').val()
//    };
//
//    $.ajax({
//        type: 'post',
//        url: "Controlador_autor",
//        data: datos,
//        success: function (request) {
//            if (request === "OK") {
//                $('#btnCloseModalModificarAutor').click();
//                $('body').removeClass('modal-open');
//                $('.modal-backdrop').remove();
//                $('#Contenido').load('inventario.jsp');
////                 irTab();
//                alertify.success('Actualizado con éxito');
//                return false;
//            } else {
//                alertify.error('Falló');
//            }
//        }
//    });
//    return false;
//}
//
//function preguntarSiNo(_id){
//    alertify.confirm('Confirma eliminar', '¿Seguro que desea eliminar?', function () {
//        eliminarDatos(_id);
//        alertify.success('Eliminado');
//    }
//    , function () {
//        alertify.error('Cancelado');
//    });
//}
//
//function eliminarDatos(_id){
//    var data ={
//        accion: 'eliminar',
//        id: _id
//    };
//    
//    $.ajax({
//        type: 'post',
//        url: 'Controlador_autor',
//        data: data,
//        success: function(request){
//            if (request === "OK") {
//                $('#Contenido').load('inventario.jsp');
//                alertify.success("Eliminado con éxito");
//            }else{
//                alertify.error("Falló el AJAX :c ");
//            }
//        }
//    });
//}


