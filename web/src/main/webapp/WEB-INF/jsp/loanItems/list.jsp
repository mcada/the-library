<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Loan Items">
    <jsp:attribute name="body">

      <table class="table">
            <thead>

            <td>
                  <my:a href="/loanItems/create" class="btn btn-primary">Create new loanItem</my:a>
            </td>
            <tr>
                <th>Id</th>
                <th>Book</th>
                <th>Loan Id</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${loanItems}" var="loanItem">
                <tr>
                    <td><c:out value="${loanItem.id}"/></td>
                    <td><c:out value="${loanItem.book.title}"/></td>
                    <td><c:out value="${loanItem.loan.id}"/></td>
                    <td>
                        <my:a href="/loanItems/view/${loanItem.id}" class="btn btn-primary">Show details</my:a>
                    </td>
                    <td>
                        <c:if test="${authenticatedUser.isAdmin()}">
                            <my:a href="/loanItems/update/${loanItem.id}" class="btn btn-primary">Update</my:a>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${authenticatedUser.isAdmin()}">
                            <form method="post" action="${pageContext.request.contextPath}/loanItem/delete/${loanItem.id}">
                                <button type="submit" class="btn btn-primary">Delete</button>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>


</jsp:attribute>
</my:pagetemplate>
