<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<div align="center">
	<table>
		<tr>
			<td colspan="2"><label for="lanches">Ola: </label> NOME</td>
		</tr>
		<tr>
			<td><label>O valor total do seu pedido é de: </label> VALOR</td>
		</tr>
		<tr>
			<td><label>Qual a forma de pagamento?</label> <br /> <input
				type="radio" class="radio-inline" name="formaPgto">Crédito <input
				type="radio" class="radio-inline" name="formaPgto">Débito <input
				type="radio" class="radio-inline" name="formaPgto">Dinheiro
			</td>

		</tr>
		<tr>
			<td><label>Precisa de troco?</label> <br /> <input type="radio"
				class="radio-inline" name="troco">Sim <input type="radio"
				class="radio-inline" name="troco">Não</td>
		</tr>
		<tr>
			<td><label>Seu pedido ja esta em andamento</label></td>
		</tr>
		<tr>
			<td><label>Use nossa calculadora caso queira dividir o
					valor</label></td>
		</tr>
		<tr>
			<td><label>Valor do pedido</label></td>
			<td><input type="text" id="valor" size="20" class="form-control"
				readonly="readonly"></td>
		</tr>
		<tr>
			<td><label>Quantidade de pessoa</label></td>
			<td><input type="number" id="qtd" size="20" class="form-control">
			</td>
		</tr>
		<tr>
			<td><label>Valor por pessoa</label></td>
			<td><input type="text" id="pessoa" size="20" maxlength="2"
				class="form-control" readonly="readonly"></td>
		</tr>

	</table>

</div>

<script type="text/javascript" async="async">
	
</script>
