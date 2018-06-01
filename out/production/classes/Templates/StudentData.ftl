<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><h1>Estudiantes</h1></title>
</head>
<body>
<br>
<br>
<br>
<br>
<form action="/DeleteStudent/:matricula" method="get">
    matricula: <input name="matricula" type="number"/><br/>
    <button name="Enviar" type="submit">Eliminar Estudiante</button>
</form>
<br>
<br>
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
        <td><a href="/ModifyStudentForm/:Matricula"><button name="Editar" type="submit">Editar</button></a></td>
    </tr>
</#list>
</table>
</p>
<a href="/AddStudentForm/"><button name="Agregar" type="submit">Agregar Estudiante</button></a>




</body>
</html>