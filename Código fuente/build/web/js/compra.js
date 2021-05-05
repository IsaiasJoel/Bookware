//////////////////////////////
// input-search-proveedor
//////////////////////////////
$(document).ready(function(){
    $("#proveedorCompra").focus();
//    var datosPrueba = [ "Jörn Zaefferer", "de Scott González", "John Resig"];
//autocompletar proveedores
    
//    var dataProveedor = {};
//    $.ajax({
//        url: "ControladorAjax",
//        data: {accion: "InputSearchProveedor"},
//        type: 'post',
//        success: function(responseProveedor){
//            dataProveedor = JSON.parse(responseProveedor);
//            console.log(dataProveedor);
//        },
//        error : function(xhr, status) {
//            alert('Disculpe, existió un problema');
//            console.log(status);
//            console.log(xhr);
//        }
//    });

//    $("#proveedorCompra").autocomplete({
//        source: dataProveedor
//    });
    
    $("#proveedorCompra").autocomplete({
        source : "ControladorAjax?accion=InputSearchProveedor"
    });
    
//    autocomplete libro
    $("#libroExisteCompra").autocomplete({
        source: "ControladorAjax?accion=InputSearchLibro"
    });
    
    $("#libroVenta").autocomplete({
        source: "ControladorAjax?accion=InputSearchLibro"
    });
//    
//    venta
//    
    $("#clienteVenta").focus();

//autocompletar cliente
    $("#clienteVenta").autocomplete({
        source: "ControladorAjax?accion=InputSearchCliente"
    });
    
    
    //iniciar los inputs de cantidad en compra y venta
    $("#cantidadLibroExisteCompra").val("1");
    $("#cantidadLibroVenta").val("1");
});


