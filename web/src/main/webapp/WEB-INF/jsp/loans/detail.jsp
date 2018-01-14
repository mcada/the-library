<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="${loan.id} Loan details">
<jsp:attribute name="body">
    <div style="width:400px;">
        <div style="float: left; width: 130px">
            <my:a href="/loans/list">Back to loanItems</my:a>
        </div>
        <br>
        <br>
    </div>
    <br>
    <table class="table" style="width:60%">
        <tbody>
            <tr>
                <td>Id</td>
                <td><c:out value="${loan.id}"/></td>
            </tr>
            <tr>
                <td>Loan created</td>
                <td><c:out value="${loan.loanCreated}"/></td>
            </tr>
            <tr>
                <td>Loan Returned</td>
                <td><c:out value="${loan.loanReturned}"/></td>
            </tr>
            <tr>
                <td>Member id</td>
                <td><c:out value="${loan.member.id}"/></td>
            </tr>
            <tr>
                <td>LoanItem ids</td>
                <td>(
                    <c:forEach items="${loan.loanItems}" var="loanItem">
                        <c:out value="${loanItem.id}"/>,
                    </c:forEach>
                )</td>
            </tr>

        </tbody>
    </table>
<br/>


</jsp:attribute>
</my:pagetemplate>
