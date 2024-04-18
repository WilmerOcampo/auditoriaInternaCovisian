$(document).ready(function () {
    $(".enlace-actualizar").click(function (event) {
        event.preventDefault();
        var url = $(this).attr('href');
        var idEvaluacion = $(this).closest('tr').find('.id-evaluacion').text().trim();
        $.ajax({
            url: url,
            type: 'POST',
            success: function(){
                $.ajax({
                    url: '/lider/obtenerEvaluacion?id=' + idEvaluacion,
                    type: 'GET',
                    dataType: 'json',
                    success: function (datos) {
                        llenarDatos(datos);
                        $('#miModal').modal('show');
                    },
                    error: function () {
                    console.log('Error al obtener los datos de la evaluación');
                    }
                });
            }
        });
    });
});

function llenarDatos(datos) {
     $('#nota').text('Nota: ' + datos.nota);
     $('#observacion').text('Observacion del Auditor: ' + datos.observacionesEvaluacion);
     $('#nombre_empleado').text('Nombre Empleado: ' + datos.nombreEmpleado);
     $('#apellido_empleado').text('Apellido Empleado: ' + datos.apellidoEmpleado);
     $('#dni_empleado').text('DNI Empleado: ' + datos.dniEmpleado);
     $('#nombre_completo').text(datos.nombreCompleto);
     $('#llamada_tipo').text('Tipifacion 1: ' + datos.tipoLlamada);
}



