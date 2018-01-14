<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="${book.title} details">
<jsp:attribute name="body">
    <div style="width:400px;">
        <div style="float: left; width: 130px">
            <my:a href="/books/list" class="btn btn-primary">Back to books</my:a>
        </div>
        <br>
        <br>
        <c:if test="${authenticatedUser.isAdmin()}">
            <div style="width: 225px">
                <form method="post" action="${pageContext.request.contextPath}/books/delete/${book.id}">
                    <button type="submit" class="btn btn-primary">Delete this book</button>
                </form>
            </div>
        </c:if>
    </div>
    <br>
    <table class="table" style="width:60%">
        <tbody>
            <tr>
                <td>Id</td>
                <td><c:out value="${book.id}"/></td>
            </tr>
            <tr>
                <td>Title</td>
                <td><c:out value="${book.author}"/></td>
            </tr>
            <tr>
                <td>Author</td>
                <td><c:out value="${book.title}"/></td>
            </tr>

        </tbody>
    </table>
<br/>

</jsp:attribute>
</my:pagetemplate>
