<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate>
<jsp:attribute name="body">
	<div class="jumbotron">
		<h1>Welcome to the library!!!!</h1>
		<p class="lead">Here you can loan books.</p>
		<p><a class="btn btn-lg btn-success" href="${pageContext.request.contextPath}/books/list"
			  role="button">Go reading!</a></p>
	</div>
</jsp:attribute>
</my:pagetemplate>