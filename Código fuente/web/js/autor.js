//$(document).ready(function () {
//
//    //agregar - autor
//    $('#form-agregar-autor').submit(function () {
//
//        var datos = {
//            accion: 'Registrar',
//            nombres: $('#nombres').val(),
//            apellidos: $('#apellidos').val(),
//            nacionalidad: $('#nacionalidad').val()
//        };
//
//        $.post("Controlador_autor", datos, procesarDatos);
//        return false;
//    });
//
//    function procesarDatos(respuestaDelControlador) {
//        if (respuestaDelControlador === "OK") {
//            //primero limpiar los campos de la ventana modal
//            $('#nombres').val('');
//            $('#apellidos').val('');
//            $('#nacionalidad').val('');
//
//            //cerrar la ventana modal
//            $('#btnCloseModalAgregarAutor').click();
//
//            alert("Autor agregado correctamente");
//            //agregar la fila
//
//
//        } else {
//            alert("Ocurrió un error al agregar");
//        }
//    }
//});
//
