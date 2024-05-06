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
                        $('#modalEvaluacion').modal('show');
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
     $('#nota').val(datos.nota);
     $('#observacion').val(datos.observacionesEvaluacion);

     $('#nombre_empleado').val(datos.nombreEmpleado);
     $('#apellido_empleado').val(datos.apellidoEmpleado);
     $('#dni_empleado').val(datos.dniEmpleado);
     $('#nombre_completo').text(datos.nombreCompleto);

     $('#nombre_auditor').val(datos.nombreAuditor);
     $('#apellido_auditor').val(datos.apellidoAuditor);
     $('#dni_auditor').val(datos.dniAuditor);

     $('#llamada_tipo').text('Tipifacion 1: ' + datos.tipoLlamada);
     $('#nombre_auditor').val(datos.nombreAuditor);
}



