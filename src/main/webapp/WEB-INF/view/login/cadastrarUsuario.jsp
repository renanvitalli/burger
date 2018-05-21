<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div align="center">
	<form name="cadastrarUsuario" action="<c:url value='/cadastro' />" method="post">

		<table cellspacing="10">
			<tr>
				<td>
					<label for="nome">Nome: </label>
				</td>			
				<td align="left">
					<input type="text" name="nome">
				</td>
			</tr>
			<tr>
				<td>
					<label for="email">Email: </label>
				</td>
				<td align="left">
					<input type="text" name="email">
				</td>
			</tr>
			<tr>
				<td><label>Data de Nascimento: </label></td>
				<td align="left">
					<input type="text" name="dia" size="2" maxlength="2" value="dd">
					<input type="text" name="mes" size="2" maxlength="2" value="mm">
					<input type="text" name="ano" size="4" maxlength="4" value="aaaa">
				</td>
			</tr>
			<tr>
				<td><label for="senha">Senha: </label></td>
				<td align="left">
					<input type="text" name="senha" size="13"maxlength="13">
				</td>
			</tr>
			<tr>
				<td><label for="confirmacaoSenha">Repetir Senha: </label></td>
				<td align="left">
					<input type="text" name="confirmacaoSenha" size="13"maxlength="13">
				</td>
			</tr>
			<tr>
				<td><input type="submit" name="submit" value="SALVAR"/></td>
				<td><a href="/login" class="btn">VOLTAR</a></td>
			</tr>
		</table>
	</form>
</div>