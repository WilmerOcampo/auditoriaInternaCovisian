/*$(document).ready(function () {

});*/

$('#capacitacion-link').on('click', function () {
    forCapacitaciones(function (data) {
        if (data.length > 0) {
            $('#modalDataParams').modal('show');
        }
    });
});

function forCapacitaciones(callback) {
    $.ajax({
        type: "GET",
        url: "/lider/evaluacion/list-capacitacion",
        dataType: "json",
        success: function (data) {
            console.log(data);
            $("#tableFeedback > tbody").empty();
            if (data.length === 0) {
                Swal.fire({
                    icon: 'info',
                    title: 'Información',
                    text: 'No existen empleados que requieran capacitación'
                });
            } else {
                $.each(data, function (index, value) {
                    var row = $(`<tr>` +
                        `<td>${value[0]}</td>` + // idEvaluacion
                        `<td>${value[1]}</td>` + // nombreEmpleado
                        `<td>${value[2]}</td>` + // nombreCliente
                        `<td>${value[3]}</td>` + // observacionesEvaluacion
                        `<td>${value[4]}</td>` + // nota
                        `<td><button type="button" class="btn btn-primary btn-new-capacitacion" data-id="${value[0]}">Generar Capacitación` +
                        `</button></td>` +
                        `</tr>`);
                    $("#tableFeedback > tbody").append(row);

                    row.find('.btn-new-capacitacion').on('click', function () {
                        var idEvaluacion = $(this).data('id');
                        $.ajax({
                            type: "GET",
                            url: "/lider/evaluacion/" + idEvaluacion,
                            dataType: "json",
                            success: function (evaluacion) {
                                $('#nombreEmpleadot').val(evaluacion.nombreempleado);//
                                $('#areat').val(evaluacion.area);
                                findAllSala();
                                $('#modalidadt').val('Presencial');
                                $('#fechat').val(fechaHora());
                                $('#dniLidert').val(evaluacion.dnilider);//
                                $('#dniEmpleadot').val(evaluacion.dniempleado);//
                                $('#modalNewTraining').modal('show');
                            },
                            error: function (jqXHR, textStatus, errorThrown) {
                                console.log(textStatus, errorThrown);
                            }
                        });
                    });
                });
            }
            callback(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    });
}
function findAllSala() {
    $.ajax({
        type: "GET",
        url: "/lider/sala/list",
        dataType: "json",
        success: function (result) {
            $("#cboSala").append(`<option>Seleccione</option>`);
            $.each(result, function (index, value) {
                $("#cboSala").append(`<option value="${value.idSala}">${value.nroSala}</option>`)
            });

            /*if (idSala > 0) {
                $("#cboSala").val(idSala);
            }*/
        }
    })
}
$('#formTraining').on('submit', function (e) {
    e.preventDefault();

    var data = {
        tema: $('#temat').val(),
        detalle: $('#detallet').val(),
        dniLider: $('#dniLidert').val(),
        idSala: $('#cboSala').val(),
        dniEmpleado: $('#dniEmpleadot').val(),
        modalidad: $('#modalidadt').val(),
        fecha: $('#fechat').val()
    };

    Swal.fire({
        title: '¿Estás seguro?',
        text: "Estás a punto de enviar la capacitación",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Confirmar'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                type: "POST",
                url: "/lider/capac-asis/save",
                data: JSON.stringify(data),
                contentType: "application/json",
                success: function (response) {
                    console.log(response);
                    Swal.fire(
                        '¡Registrado!',
                        'La capacitación ha sido registrado con éxito.',
                        'success'
                    );
                    $('#modalNewTraining').modal('hide');
                    $('#modalDataParams').modal('hide');
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(textStatus, errorThrown);
                }
            });
        }
    });
});



$('#formFeedback').on('submit', function (e) {
    e.preventDefault();

    var data = {
        motivo: $('#motivo').val(),
        dniEmpleado: $('#dniEmpleado').val(),
        dniLider: $('#dniLider').val(),
        asunto: $('#asunto').val(),
        cuerpo: $('#cuerpo').val(),
        fecha: $('#fecha').val()
    };

    Swal.fire({
        title: '¿Estás seguro?',
        text: "Estás a punto de enviar el feedback",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Confirmar'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                type: "POST",
                url: "/lider/fb-memo/save",
                data: JSON.stringify(data),
                contentType: "application/json",
                success: function (response) {
                    console.log(response);
                    Swal.fire(
                        '¡Registrado!',
                        'El feedback ha sido registrado con éxito.',
                        'success'
                    );
                    $('#modalNewFeedback').modal('hide');
                    $('#modalDataParams').modal('hide');
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(textStatus, errorThrown);
                }
            });
        }
    });
});


$(document).ready(function () {
    $('#tablaEvaluaciones').DataTable();
});

$('#feedback-link').on('click', function () {
    forFeedabacks(function (data) {
        if (data.length > 0) {
            $('#modalDataParams').modal('show');
        }
    });
});

function forFeedabacks(callback) {
    $.ajax({
        type: "GET",
        url: "/lider/evaluacion/list",
        dataType: "json",
        success: function (data) {
            console.log(data);
            $("#tableFeedback > tbody").empty();
            if (data.length === 0) {
                Swal.fire({ // Reemplaza 'alert' con 'Swal.fire'
                    icon: 'info',
                    title: 'Información',
                    text: 'No existen empleados que requieran feedback'
                });
            } else {
                $.each(data, function (index, value) {
                    var row = $(`<tr>` +
                        `<td>${value[0]}</td>` + // idEvaluacion
                        `<td>${value[1]}</td>` + // nombreEmpleado
                        `<td>${value[2]}</td>` + // nombreCliente
                        `<td>${value[3]}</td>` + // observacionesEvaluacion
                        `<td>${value[4]}</td>` + // nota
                        `<td><button type="button" class="btn btn-primary btn-new-feedback" data-id="${value[0]}"> Generar Feedback` +
                        `</button></td>` +
                        `</tr>`);
                    $("#tableFeedback > tbody").append(row);

                    row.find('.btn-new-feedback').on('click', function () {
                        var idEvaluacion = $(this).data('id');
                        $.ajax({
                            type: "GET",
                            url: "/lider/evaluacion/" + idEvaluacion,
                            dataType: "json",
                            success: function (evaluacion) {
                                $('#dniEmpleado').val(evaluacion.dniempleado);
                                $('#nombreEmpleado').val(evaluacion.nombreempleado);
                                $('#cuerpo').val(evaluacion.cuerpo);
                                $('#fecha').val(fechaHora());
                                $('#dniLider').val(evaluacion.dnilider);
                                $('#modalNewFeedback').modal('show');
                            },
                            error: function (jqXHR, textStatus, errorThrown) {
                                console.log(textStatus, errorThrown);
                            }
                        });
                    });
                });
            }
            callback(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    });
}

function fechaHora() {
    var now = new Date();
    var yyyy = now.getFullYear();
    var mm = String(now.getMonth() + 1).padStart(2, '0');
    var dd = String(now.getDate()).padStart(2, '0');
    var hh = String(now.getHours()).padStart(2, '0');
    var min = String(now.getMinutes()).padStart(2, '0');
    return nowStr = yyyy + '-' + mm + '-' + dd + 'T' + hh + ':' + min;
}
