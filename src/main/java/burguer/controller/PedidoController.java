package burguer.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import burguer.entity.Ingrediente;
import burguer.entity.Lanche;
import burguer.entity.Pedido;
import burguer.repository.IngredienteRepository;
import burguer.repository.PedidoRepository;

@Controller
@RequestMapping(value = "/pedido")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private IngredienteRepository ingredienteRepository;

	private Iterable<Ingrediente> buscarIngredientesPorTipo(String tipo) {
		Ingrediente ing = new Ingrediente().setTipoIngrediente(tipo);
		Example<Ingrediente> filtro = Example.of(ing);
		return this.ingredienteRepository.findAll(filtro);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(ModelMap model) {
		model.addAttribute("paes", this.buscarIngredientesPorTipo("pao"));
		model.addAttribute("queijos", this.buscarIngredientesPorTipo("queijo"));
		model.addAttribute("recheios", this.buscarIngredientesPorTipo("recheio"));
		model.addAttribute("saladas", this.buscarIngredientesPorTipo("salada"));
		model.addAttribute("molhos", this.buscarIngredientesPorTipo("molho"));
		model.addAttribute("temperos", this.buscarIngredientesPorTipo("tempero"));
		return "pedido.index";
	}

	@RequestMapping(value = "/adicionarLanche", method = RequestMethod.POST)
	public String adicionarLanche(@RequestParam Map<String, Object> parametros, HttpServletRequest request) {

		if (null == request.getSession().getAttribute("listaLanches")) {
			request.getSession().setAttribute("listaLanches", new ArrayList<Lanche>());
		}

		Lanche lanche = new Lanche();
		lanche.setIngredientes(new ArrayList<Ingrediente>());

		for (Map.Entry<String, Object> par : parametros.entrySet()) {
			if(!par.getValue().equals("")){
				Optional<Ingrediente> ingrediente = this.ingredienteRepository.findById(Long.parseLong((String) par.getValue()));
				lanche.getIngredientes().add(ingrediente.get());
			}
		}
		List<Lanche> listaLanches = (List<Lanche>) request.getSession().getAttribute("listaLanches");
		listaLanches.add(lanche);
		request.getSession().setAttribute("listaLanches", listaLanches);
		return "redirect:/pedido";
	}

	@RequestMapping(value = "/lanches", method = RequestMethod.GET)
	public String listarLanches(ModelMap model, HttpServletRequest request) {

		List<Lanche> lanches = (List<Lanche>) request.getSession().getAttribute("listaLanches");
		model.addAttribute("listaLanches", lanches);

		if (null != lanches) {
			BigDecimal total = BigDecimal.ZERO;
			for (Lanche lanche : lanches) {
				total = total.add(lanche.getValor());
			}
			model.addAttribute("valor", String.format("R$ %.2f", total.doubleValue()));
		}

		return "lista.lanches";
	}

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public String salvaPedido(@RequestParam Map<String, Object> parametros) {
		Pedido pedido = new Pedido();
		pedido.setNome((String) parametros.get("nome"));
		pedido.setEndereco((String) parametros.get("endereco"));
		// TODO Popular pedido com os lanches
		this.pedidoRepository.save(pedido);
		return "redirect:/pedido";
	}

	@PostConstruct
	public void inicializarBanco() {
		/* FIXME mocado */

		if (this.ingredienteRepository.count() < 1) {

			this.ingredienteRepository
					.save(new Ingrediente().setNome("Australiano").setPreco(BigDecimal.ZERO).setTipoIngrediente("pao"));
			this.ingredienteRepository.save(
					new Ingrediente().setNome("Brioche").setPreco(new BigDecimal(1.00)).setTipoIngrediente("pao"));
			this.ingredienteRepository.save(
					new Ingrediente().setNome("Italiano").setPreco(new BigDecimal(1.00)).setTipoIngrediente("pao"));

			this.ingredienteRepository.save(
					new Ingrediente().setNome("Cheddar").setPreco(new BigDecimal(1.0)).setTipoIngrediente("queijo"));
			this.ingredienteRepository.save(
					new Ingrediente().setNome("Provolone").setPreco(new BigDecimal(1.0)).setTipoIngrediente("queijo"));
			this.ingredienteRepository.save(
					new Ingrediente().setNome("Queijo Prato").setPreco(BigDecimal.ZERO).setTipoIngrediente("queijo"));

			this.ingredienteRepository.save(
					new Ingrediente().setNome("Costela").setPreco(new BigDecimal(5.0)).setTipoIngrediente("recheio"));
			this.ingredienteRepository.save(
					new Ingrediente().setNome("picanha").setPreco(new BigDecimal(6.0)).setTipoIngrediente("recheio"));

			this.ingredienteRepository
					.save(new Ingrediente().setNome("Alface").setPreco(BigDecimal.ZERO).setTipoIngrediente("salada"));
			this.ingredienteRepository.save(
					new Ingrediente().setNome("Rucula").setPreco(new BigDecimal(1.5)).setTipoIngrediente("salada"));
			this.ingredienteRepository.save(
					new Ingrediente().setNome("Acelga").setPreco(new BigDecimal(1.0)).setTipoIngrediente("salada"));

			this.ingredienteRepository.save(
					new Ingrediente().setNome("Italiano").setPreco(new BigDecimal(1.0)).setTipoIngrediente("molho"));
			this.ingredienteRepository.save(
					new Ingrediente().setNome("Apimentado").setPreco(new BigDecimal(1.0)).setTipoIngrediente("molho"));

			this.ingredienteRepository.save(
					new Ingrediente().setNome("Pimenta").setPreco(new BigDecimal(1.5)).setTipoIngrediente("tempero"));
			this.ingredienteRepository
					.save(new Ingrediente().setNome("Sal").setPreco(new BigDecimal(1.5)).setTipoIngrediente("tempero"));
			this.ingredienteRepository.save(
					new Ingrediente().setNome("Oregano").setPreco(new BigDecimal(1.5)).setTipoIngrediente("tempero"));
		}

	}

}
