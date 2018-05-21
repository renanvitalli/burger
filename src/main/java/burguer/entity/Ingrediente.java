package burguer.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tb_ingrediente")
public class Ingrediente {

	@Id
	@SequenceGenerator(name = "tb_ingrediente_gen", sequenceName = "tb_ingrediente_id_ingrediente_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_ingrediente_gen")
	@Column(name = "id_ingrediente")
	private Long codigo;

	@Column(name = "nome", nullable = false, unique = false)
	private String nome;

	@Column(name = "preco", nullable = false)
	private BigDecimal preco;

	public Long getCodigo() {
		return this.codigo;
	}

	@Column(name = "tipo", nullable = false)
	private String tipoIngrediente;

	public Ingrediente setCodigo(Long codigo) {
		this.codigo = codigo;
		return this;
	}

	public String getNome() {
		return this.nome;
	}

	public Ingrediente setNome(String nome) {
		this.nome = nome;
		return this;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public Ingrediente setPreco(BigDecimal preco) {
		this.preco = preco;
		return this;
	}

	public String getTipoIngrediente() {
		return tipoIngrediente;
	}

	public Ingrediente setTipoIngrediente(String tipoIngrediente) {
		this.tipoIngrediente = tipoIngrediente;
		return this;
	}

	@Override
	public String toString() {
		return this.nome;
	}
	
}
