
    function abrirModal() {
        var modal = document.getElementById('verModal');
        modal.classList.add('show');
        modal.style.display = 'block';
        modal.setAttribute('aria-modal', 'true');
        modal.setAttribute('aria-hidden', 'false');
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

