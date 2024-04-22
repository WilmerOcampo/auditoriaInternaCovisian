function abrirModal(idEvaluacion, numeroOrden) {
    var modal = document.getElementById('verModal');

    // Realizar una solicitud AJAX al controlador para obtener los detalles de la evaluación
    fetch('/evaluacion/' + idEvaluacion + '/detalles?numeroOrden=' + numeroOrden)
        .then(response => response.json())
        .then(data => {
            // Actualizar el contenido del modal con los datos obtenidos
            document.getElementById('numeroOrdenModal').textContent = data.numeroOrden;
            document.getElementById('areaModal').textContent = data.area;
            document.getElementById('tipoModal').textContent = data.tipo;
            document.getElementById('subareaModal').textContent = data.subarea;

            // Mostrar el modal
            modal.classList.add('show');
            modal.style.display = 'block';
            modal.setAttribute('aria-modal', 'true');
            modal.setAttribute('aria-hidden', 'false');
        })
        .catch(error => {
            console.error('Error al obtener los detalles de la evaluación:', error);
        });
}

function cerrarModal() {
    var modal = document.getElementById('verModal');
    modal.classList.remove('show');
    modal.style.display = 'none';
    modal.setAttribute('aria-modal', 'false');
    modal.setAttribute('aria-hidden', 'true');
}



document.addEventListener("DOMContentLoaded", function() {
   var notas = document.getElementsByClassName('nota');
       var total = 0;
       for (var i = 0; i < notas.length; i++) {
           total += parseFloat(notas[i].textContent);
       }
       var promedio = total / notas.length;

       document.getElementById("promedio").textContent = promedio.toFixed(2);

       var mensaje = document.getElementById("mensaje");
       if (promedio >= 70) {

           mensaje.textContent = "Está dentro del objetivo.";
       } else {

           mensaje.textContent = "No está dentro del objetivo.";
       }
});