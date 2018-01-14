<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="New loan item :) :) :)">
<jsp:attribute name="body">

    You will not create new loan Item because it does not make any sense. Just go back...
    <br/>
    <br/>

   <td>
           <my:a href="/loanItems/list" class="btn btn-primary">GO BACK</my:a>
   </td>

</jsp:attribute>
</my:pagetemplate>