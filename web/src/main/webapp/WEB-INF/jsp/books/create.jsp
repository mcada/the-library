<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Add book">
<jsp:attribute name="body">

    <form:form method="post" action="${pageContext.request.contextPath}/books/create"
               modelAttribute="books" cssClass="form-vertical">

        <div class="form-group ${name_error?'has-error':''}">
            <form:label path="title" cssClass="col-sm-2 control-label">Title</form:label>
            <div class="col-sm-10">
                <form:input path="title" cssClass="form-control" required="required"/>
                <form:errors path="title" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${name_error?'has-error':''}">
            <form:label path="author" cssClass="col-sm-2 control-label">Author</form:label>
              <div class="col-sm-10">
                <form:input path="author" cssClass="form-control" required="required"/>
                <form:errors path="author" cssClass="help-block"/>
            </div>
        </div>
                    <div class="col-sm-10">

        <button class="btn btn-primary" type="submit">Create book</button>
        </div>
    </form:form>

</jsp:attribute>
</my:pagetemplate>