<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div align="center" >
	<form name="login" action="<c:url value='/login' />" method="post">
		<table>
			<tr>
				<td>Usu&aacute;rio:</td>
				<td><input type="text" name="username" class="form-control" required="required"></td>
			</tr>
			<tr>
				<td>Senha:</td>
				<td><input type="password" name="password" class="form-control" required="required"></td>
			</tr>
			<tr>
				<td align="center" colspan="2"><input type="submit" name="submit" value="LOGAR" class="btn" /></td>
				<!--Implementar
				 <td><a href="/cadastrar" class="btn">NOVO</a></td> 
				 -->
			</tr>
		</table>
	
	</form>
</div>
