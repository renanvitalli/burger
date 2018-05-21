<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<ol style="width: 100%;">
	<c:forEach items="${listaLanches}" var="lanche">
		<li>${lanche}</li>
	</c:forEach>
</ol>

<div align="right">
	<input type="text" class="form-control" readonly="readonly" value="${valor}" placeholder="R$ 0,00"  style="width: 100px; text-align: center">
</div>

