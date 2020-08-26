<%-- 
    Document   : lista
    Created on : 30/01/2014, 23:19:31
    Author     : Marcos
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p>
            <a href="relatorio" target="_blank">Imprimir Relatório</a>
        </p>
        <p>
            <a href="form">Adicionar Cargo</a>
        </p>
        <table>
            <thead>
                <tr>
                    <td>Id</td>
                    <td>Nome</td>
                    <td>Editar</td>
                    <td>Excluir</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${cargoList}" var="cargo">
                    <tr>
                        <td>${cargo.id}</td>
                        <td>${cargo.nome}</td>
                        <td><a href="edita?id=${cargo.id}">Editar</a></td>
                        <td><a href="remove?id=${cargo.id}">Excluir</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
    </body>
</html>
