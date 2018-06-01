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
    <tr><th>Matricula</th><th>Nombre</th><th>Apellido</th><th>Telefono</th></tr>
<#-- Iterando elementos.-->
<#list Students as estudiante>
    <tr><td>${estudiante.matricula?string["0"]}</td>
        <td>${estudiante.nombre}</td>
        <td>${estudiante.apellido}</td>
        <td>${estudiante.telefono}</td>
        <td><a href="/ModifyStudentForm/${estudiante.matricula?string["0"]}"><button name="Editar" type="submit">Editar</button></a></td>
        <td><a href="/DeleteStudent/${estudiante.matricula?string["0"]}"><button name="Eliminar" type="submit">Eliminar</button> </a> </td>
    </tr>
</#list>
</table>
</p>
<a href="/AddStudentForm/"><button name="Agregar" type="submit">Agregar Estudiante</button></a>




</body>
</html>