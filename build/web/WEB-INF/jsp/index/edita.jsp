<%-- 
    Document   : edita
    Created on : 31/01/2014, 08:30:37
    Author     : Marcos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="altera">
            <input type="hidden" name="cargo.id" value="${cargo.id}" />
            <table>
                <tr>
                    <td>Nome</td>
                    <td><input type="text" name="cargo.nome" value="${cargo.nome}" /></td>
                </tr>
                <tr>
                    <td>Descrição</td>
                    <td><textarea name="cargo.descricao">${cargo.descricao}</textarea></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Enviar" /></td>                    
                </tr>
            </table>
        </form>
    </body>
</html>
