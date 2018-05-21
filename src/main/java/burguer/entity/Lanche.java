package burguer.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_lanche")
public class Lanche {

	@Id
	@SequenceGenerator(name = "tb_lanche_gen", sequenceName = "tb_lanche_id_lanche_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_lanche_gen")
	@Column(name = "id_lanche")
	private Long codigo;

	@NotBlank(message = "Nome é obrigatório")
	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "pronto", nullable = false)
	private boolean isPronto;

	@OneToMany(targetEntity = Ingrediente.class)
	private List<Ingrediente> ingredientes;

	public BigDecimal getValor() {

		if (ingredientes == null || ingredientes.isEmpty()) {
			return BigDecimal.ZERO;
		}

		BigDecimal total = BigDecimal.ZERO;
		for (Ingrediente ingrediente : getIngredientes()) {
			total = total.add(ingrediente.getPreco());
		}
		return total;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isPronto() {
		return isPronto;
	}

	public void setPronto(boolean isPronto) {
		this.isPronto = isPronto;
	}

	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < ingredientes.size(); i++) {
			sb.append(ingredientes.get(i));
			sb.append(i == ingredientes.size() - 1 ? "" : " - ");
		}

		return sb.toString();
	}

}
