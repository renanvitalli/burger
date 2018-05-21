<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<div align="center">
	<h2>Ol&aacute;, <c:if test="${not empty pageContext.request.userPrincipal}">${pageContext.request.userPrincipal.name},</c:if> qual ser&aacute; o seu pedido?</h2>
	<br/>
	
	<div id="msg"></div>
	
	<form name="pedido" action="<c:url value='/pedido/salvar'/>" method="post" onsubmit="return false">
		
		<table>
			<tr>
				<td><label>Nome: </label></td>
				<td>
					<input type="text" id="nome" maxlength="75" size="30" required="required" class="form-control"> 
				</td>
				<td rowspan="3" align="center">
					<img src="..\img\logo.png" maxlength="100" height="100" width="100" class="img-rounded" >
				</td>
			</tr>
			<tr>
				<td>
					<label for="endereco">Endere&ccedil;o: </label>
				</td>
				<td>
					<input type="text" id="endereco" maxlength="100" size="30" required="required" class="form-control"> 
				</td>
			</tr>
		</table>
		
	</form>
	
	<form name="lanche" action="<c:url value='/pedido/adicionarLanche' />" method="post" onsubmit="return false">
		<table>
			<tr>
				<td>
					<label for="lanches">Lista De Lanches: </label>
				</td>
				<td>
					<select onchange="atualizarLanchePrePronto();" id="comboLanchePronto" class="selectpicker" > 
						<option value= "">Selecione...</option>
						<option value="lanche1">Lanche 1</option>
						<option value="lanche2">Lanche 2</option>	
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<label>Tipo de P&atilde;o:</label>
				</td>
				<td>
					<select name="pao" required="required" class="selectpicker" >
							<option value= "">Selecione...</option>
						<c:forEach items="${paes}" var="pao">
							<option value="${pao.codigo}">${pao.nome}</option>
						</c:forEach>
					</select>
				</td>
				<td rowspan="3">
					<div id="listaLanches"></div>
				</td>
			</tr>
			<tr>
				<td>
					<label>Tipo de Queijo:</label>
				</td>
				<td>
					<select name="queijo" class="selectpicker" > 
						<option value= "">Selecione...</option>
						<c:forEach items="${queijos}" var="queijo">
							<option value="${queijo.codigo}">${queijo.nome}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td><label>Recheio:</label></td>
				<td>
					<select name="recheio" class="selectpicker" >
						<option value= "">Selecione...</option>
						<c:forEach items="${recheios}" var="recheio">
							<option value="${recheio.codigo}">${recheio.nome}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td><label>Salada:</label></td>
					<td>
					<c:forEach items="${saladas}" var="salada">
							<input type="radio" class="radio-inline" name="salada" required="required" value="${salada.codigo}">${salada.nome}
					</c:forEach>
					</td>
			</tr>
			<tr>
				<td><label>Molhos:</label></td>
				<td>
				<c:forEach items="${molhos}" var="molho" varStatus="loop">
					<input type="checkbox" class="checkbox-inline" name="molho-${loop.index}" value="${molho.codigo}">${molho.nome}&nbsp;	
				</c:forEach>
				</td>	
			</tr>
			<tr>
				<td><label>Temperos:</label></td>
				<td>
				<c:forEach items="${temperos}" var="tempero" varStatus="loop">
					<input type="checkbox" class="checkbox-inline" name="tempero-${loop.index}" value="${tempero.codigo}">${tempero.nome}
				</c:forEach>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="Salvar" onclick="return adicionarLanche();" class="btn"></td>
			</tr>
		</table>
	</form>
	
	<input type="submit" class="btn" value="Finalizar" onclick="return registrarPedido();" >  
	
</div>

<script type="text/javascript" async="async">

	function adicionarLanche() {
		var frm = document.getElementsByName('lanche')[0];
		frm.submit();
		atualizarListaLanches();
		/* frm.reset(); */

		var element = document.getElementById('msg');
		element.innerHTML = '<div class="alert alert-success">Pedido inserido com sucesso!</div>';
		return false;
	}

	function registrarPedido() {
		/*
		IMPLEMENTAR
		var frm = document.getElementsByName('pedido')[0];
		frm.submit();
		frm.reset(); 
		document.getElementsByName('lanche')[0].reset();
		*/
		return false;
	}

	function atualizarListaLanches() {
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				document.getElementById('listaLanches').innerHTML = this.responseText;
			}
		};
		xhttp.open("GET", "/pedido/lanches", true);
		xhttp.send();

	}

	atualizarListaLanches();

	function atualizarLanchePrePronto() {
		var lanche = document.getElementById('comboLanchePronto').value;
		if (lanche == 'lanche1') {
			document.getElementsByName('pao')[0].value = 1;
			document.getElementsByName('queijo')[0].value = 4;
			document.getElementsByName('recheio')[0].value = 7;
			document.getElementsByName('salada')[0].checked = true;
			document.getElementsByName('salada')[1].checked = false;
			document.getElementsByName('molho-0')[0].checked = true;
			document.getElementsByName('molho-1')[0].checked = false;
			document.getElementsByName('tempero-0')[0].checked = true;
			document.getElementsByName('tempero-1')[0].checked = false;
			document.getElementsByName('tempero-2')[0].checked = false;
		} else if (lanche == 'lanche2') {
			document.getElementsByName('pao')[0].value = 3;
			document.getElementsByName('queijo')[0].value = 6;
			document.getElementsByName('recheio')[0].value = 8;
			document.getElementsByName('salada')[0].checked = false;
			document.getElementsByName('salada')[1].checked = true;
			document.getElementsByName('molho-0')[0].checked = false;
			document.getElementsByName('molho-1')[0].checked = true;
			document.getElementsByName('tempero-0')[0].checked = false;
			document.getElementsByName('tempero-1')[0].checked = true;
			document.getElementsByName('tempero-2')[0].checked = true;
		}else {
			document.getElementsByName('pao')[0].value = "";
			document.getElementsByName('queijo')[0].value = "";
			document.getElementsByName('recheio')[0].value = "";
			document.getElementsByName('salada')[0].checked = false;
			document.getElementsByName('salada')[1].checked = false;
			document.getElementsByName('molho-0')[0].checked = false;
			document.getElementsByName('molho-1')[0].checked = false;
			document.getElementsByName('tempero-0')[0].checked = false;
			document.getElementsByName('tempero-1')[0].checked = false;
			document.getElementsByName('tempero-2')[0].checked = false;
		}
		
	}
</script> 