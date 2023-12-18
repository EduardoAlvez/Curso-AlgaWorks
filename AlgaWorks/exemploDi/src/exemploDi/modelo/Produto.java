package exemploDi.modelo;

import java.math.BigDecimal;

public class Produto {

	private String nome;
	private BigDecimal valorTotal;

	Produto(String nome, BigDecimal valorTotal) {
		super();
		this.nome = nome;
		this.valorTotal = valorTotal;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}
}
