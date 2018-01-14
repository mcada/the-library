<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Loans">
    <jsp:attribute name="body">

      <table class="table">
            <thead>
            <tr>
                <th>Id</th>
                <th>Loan Created</th>
                <th>Loan Returned</th>
                <th>Member ID</th>
                <th>LoanItem IDs</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${loans}" var="loan">
                <tr>
                    <td><c:out value="${loan.id}"/></td>
                    <td><c:out value="${loan.loanCreated}"/></td>
                    <td><c:out value="${loan.loanReturned}"/></td>
                    <td><c:out value="${loan.member.id}"/></td>
                    <td>(
                        <c:forEach items="${loan.loanItems}" var="loanItem">
                            <c:out value="${loanItem.id}"/>,
                        </c:forEach>
                    )</td>
                    <td>
                    <td>
                        <my:a href="/loans/detail/${loan.id}" class="btn btn-primary">Show details</my:a>
                    </td>
                    <td>
                        <c:if test="${authenticatedUser.isAdmin()}">
                            <my:a href="/loans/update/${loan.id}" class="btn btn-primary">Update</my:a>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${authenticatedUser.isAdmin()}">
                            <form method="post" action="${pageContext.request.contextPath}/loans/delete/${loan.id}">
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