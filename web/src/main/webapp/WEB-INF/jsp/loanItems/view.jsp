<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="# ${loanItem.id} Loan Item details">
<jsp:attribute name="body">
    <div style="width:400px;">
        <div style="float: left; width: 130px">
            <my:a href="/loanItems/list" class="btn btn-primary">Back to loanItems</my:a>
        </div>
        <br>
        <br>
        <c:if test="${authenticatedUser.isAdmin()}">
            <div style="width: 225px">
                <form method="post" action="${pageContext.request.contextPath}/loanItems/delete/${loanItem.id}">
                    <button type="submit" class="btn btn-primary">Delete this loanItem</button>
                </form>
            </div>
        </c:if>
    </div>
    <br>
    <table class="table" style="width:60%">
        <tbody>
            <tr>
                <td>Id</td>
                <td><c:out value="${loanItem.id}"/></td>
            </tr>
            <tr>
                <td>Book</td>
                <td><c:out value="${loanItem.book.title}"/></td>
            </tr>
            <tr>
                <td>Loan Id</td>
                <td><c:out value="${loanItem.loan.id}"/></td>
            </tr>

        </tbody>
    </table>
<br/>


</jsp:attribute>
</my:pagetemplate>
