<%-- 
    Document   : form
    Created on : 31/01/2014, 08:16:32
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
        <form action="salva">
            <table>
                <tr>
                    <td>Nome</td>
                    <td><input type="text" name="cargo.nome" /></td>
                </tr>
                <tr>
                    <td>Descrição</td>
                    <td><textarea name="cargo.descricao"></textarea></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Enviar" /></td>                    
                </tr>
            </table>
        </form>
    </body>
</html>
