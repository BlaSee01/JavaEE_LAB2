<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Formularze</title>
</head>
<body>
  
    <form action="./DodawanieServlet" method="POST">
        <input type='text' name='name' placeholder='Name'>
        <input type='text' name='gendre' placeholder='Gendre'>
        <input type='text' name='type' placeholder='Type'>
        <input type='number' name='price_pln' placeholder='Price'>
        <input type='text' name='manufacturer' placeholder='Manufacturer'>
        <input type='checkbox' name='is_polish_manufacturer' value='true'>Is Polish Manufacturer<br>
        <input type='hidden' name='action' value='add'>
        <input type='submit' value='Add'>
    </form>

    <form action='./UsuwanieServlet' method='POST'>
        <input type='number' name='id_del' placeholder='ID to delete'>
        <input type='hidden' name='action' value='delete'>
        <input type='submit' value='Delete'>
    </form>

    <form action='./AktualizacjaServlet' method='POST'>
        <input type='number' name='id_upd' placeholder='ID to update'>
        <input type='text' name='name_upd' placeholder='New Name'>
        <input type='text' name='gendre_upd' placeholder='New Gendre'>
        <input type='text' name='type_upd' placeholder='New Type'>
        <input type='number' name='price_pln_upd' placeholder='New Price'>
        <input type='text' name='manufacturer_upd' placeholder='New Manufacturer'>
        <input type='checkbox' name='is_polish_manufacturer_upd' value='true'>Is Polish Manufacturer<br>
        <input type='hidden' name='action' value='update'>
        <input type='submit' value='Update'>
    </form>
   <a href="index.html">Powrót do strony głównej</a>
    
</body>
</html>
