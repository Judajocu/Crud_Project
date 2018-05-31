<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><h1>Estudiantes</h1></title>
</head>
<body>

<p>
<h3>La cantidad de estudiantes: ${Students?size}</h3>
<table>
    <tr><th>Matricula</th><th>Nombre</th><th>Carrera</th></tr>
<#-- Iterando elementos.-->
<#list Students as estudiante>
    <tr><td>${estudiante.matricula?string["0"]}</td>
        <td>${estudiante.nombre}</td>
        <td>${estudiante.apellido}</td>
        <td>${estudiante.telefono}</td></tr>
</#list>
</table>
</p>

</body>
</html>